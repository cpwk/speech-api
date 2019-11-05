package com.mdtech.zyedu.api.form.service;

import com.mdtech.zyedu.api.file.entity.UploadOptions;
import com.mdtech.zyedu.api.file.service.FileService;
import com.mdtech.zyedu.api.form.model.Form;
import com.mdtech.zyedu.api.form.qo.FormQo;
import com.mdtech.zyedu.api.form.repository.FormRepository;
import com.mdtech.zyedu.api.heavywork.model.HeavyWork;
import com.mdtech.zyedu.api.heavywork.service.IHeavyWorkService;
import com.mdtech.zyedu.common.service.TaskService;
import com.mdtech.zyedu.common.task.ApiTask;
import com.mdtech.zyedu.common.util.L;
import com.sunnysuperman.commons.util.FileUtil;
import net.bytebuddy.asm.Advice;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * @author: MengKai
 * @create: 2019-08-2019-08-31-09:35
 * * * * * * * * * * * * * * * * * * * * * * * *
 **/
@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private IHeavyWorkService heavyWorkService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FileService fileService;

    @Override
    public Page<Form> findAllForm(FormQo qo) {
        return formRepository.findAll(qo);
    }


    @Override
    public void saveForm(Form form) {
        form.setCreatedAt(System.currentTimeMillis());
        formRepository.save(form);
    }

    @Override
    public HeavyWork export_form(FormQo qo) throws Exception {
        HeavyWork task = heavyWorkService.create(String.valueOf(System.currentTimeMillis()));

        qo.setPageSize(500);

        taskService.addTask(new ExportQAPapersTask(task, qo));
        return task;
    }

    private class ExportQAPapersTask extends ApiTask {
        HeavyWork job;
        FormQo qo;
        int progress = 0;

        public ExportQAPapersTask(HeavyWork job, FormQo qo) {
            super();
            this.job = job;
            this.qo = qo;
        }

        private Workbook writeToWorkbook() throws Exception {

            SXSSFWorkbook wb = new SXSSFWorkbook(100);
            SXSSFSheet sheet = wb.createSheet("用户表单");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            int rowIndex = 0;
            {
                int cellIndex = -1;
                Row titleRow = sheet.createRow(rowIndex);

                titleRow.createCell(++cellIndex).setCellValue("姓名");
                titleRow.createCell(++cellIndex).setCellValue("手机号");
                titleRow.createCell(++cellIndex).setCellValue("课程");
                titleRow.createCell(++cellIndex).setCellValue("提交时间");

            }
            Page<Form> page;
            int sum = 0;

            while (true) {
                // 查询
                page = formRepository.findAll(qo);
                if (page.getContent().size() == 0) {
                    break;
                }
                // 写入Excel
                for (Form form : page.getContent()) {
                    Row row = sheet.createRow(++rowIndex);
                    int cellIndex = -1;

                    row.createCell(++cellIndex).setCellValue(form.getName());
                    row.createCell(++cellIndex).setCellValue(form.getMobile());
                    row.createCell(++cellIndex).setCellValue(form.getCourse());

                    String date = null;
                    try {
                        date = sdf.format(new Date(form.getCreatedAt()));

                    } catch (Exception e) {
                    }
                    row.createCell(++cellIndex).setCellValue(date);

                }
                // 进度加一点
                int newProgress = progress + 5;
                if (newProgress > 90) {
                    newProgress = 90;
                }
                doUpdateProgress(newProgress);
                Pageable pageable = page.getPageable();

                // 没有更多了
                if (page.getContent().size() < qo.getPageSize()) {
                    break;
                }
                // 标记分页，由于分页算法默认-1，此处需+2
                qo.setPageNumber(qo.getPageNumber() + 2);
                // 最多10w条
                sum += page.getSize();
                if (sum >= 100000) {
                    break;
                }
            }
            return wb;
        }

        private void doUpdateProgress(int progress) {
            try {
                // 进度不能倒退
                if (progress <= this.progress) {
                    return;
                }
                this.progress = progress;
                heavyWorkService.updateProgress(job.getId(), progress);
            } catch (Exception ex) {
                L.error(ex);
            }
        }

        @Override
        protected void doApiWork() throws Exception {
            // 开始
            doUpdateProgress(1);
            // 生成表格
            Workbook wb = null;
            File file = null;
            try {
                wb = writeToWorkbook();
                file = fileService.createTmpFile("form-export", "xlsx");
                // 写入文件
                {
                    FileOutputStream out = new FileOutputStream(file);
                    wb.write(out);
                    out.flush();
                    out.close();
                }
                // 上传前先报告一下状态
                doUpdateProgress(95);

                // 上传
                UploadOptions options = new UploadOptions();
                options.setContentType("application/x-xls");
                String exportUrl = fileService.upload(file, options);

                // 报告成功
                heavyWorkService.updateSuccess(job.getId(), exportUrl);
            } catch (Exception ex) {
                L.error(ex);
                heavyWorkService.updateFailed(job.getId(), null);
            } finally {
                FileUtil.close(wb);
                if (file != null) {
                    FileUtil.delete(file);
                }
            }
        }

    }
}
