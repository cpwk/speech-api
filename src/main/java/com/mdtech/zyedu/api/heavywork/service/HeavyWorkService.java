package com.mdtech.zyedu.api.heavywork.service;

import com.mdtech.zyedu.api.heavywork.entity.HeavyWorkConstants;
import com.mdtech.zyedu.api.heavywork.model.HeavyWork;
import com.mdtech.zyedu.api.heavywork.repository.HeavyWorkRepository;
import com.mdtech.zyedu.common.exception.DetailedException;
import com.mdtech.zyedu.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HeavyWorkService implements IHeavyWorkService {

    @Autowired
    private HeavyWorkRepository heavyWorkRepository;

    @Override
    public HeavyWork create(String owner) throws Exception {
        HeavyWork job = new HeavyWork();
        job.setSecret(StringUtils.getRandNum(64));
        job.setOwner(owner);
        job.setProgress(0);
        job.setCreatedAt(System.currentTimeMillis());
        job.setUpdatedAt(job.getCreatedAt());
        job.setStatus(HeavyWorkConstants.STATUS_INIT);
        heavyWorkRepository.save(job);
        return job;
    }

    @Override
    public void updateProgress(Integer id, int progress) {
        if (progress > 100) {
            progress = 100;
        }
        HeavyWork work = heavyWorkRepository.getOne(id);
        work.setUpdatedAt(System.currentTimeMillis());
        work.setProgress(progress);

        heavyWorkRepository.save(work);

    }

    @Override
    public void updateFailed(Integer id, String errors) {
        HeavyWork work = heavyWorkRepository.getOne(id);
        work.setUpdatedAt(System.currentTimeMillis());
        work.setStatus(HeavyWorkConstants.STATUS_FAILED);
        work.setErrors(errors);

        heavyWorkRepository.save(work);

    }

    @Override
    public void updateSuccess(Integer id, String output) {

        HeavyWork work = heavyWorkRepository.getOne(id);
        work.setUpdatedAt(System.currentTimeMillis());
        work.setStatus(HeavyWorkConstants.STATUS_SUCCESS);
        work.setProgress(100);
        work.setOutput(output);

        heavyWorkRepository.save(work);

    }


    @Override
    public HeavyWork getById(Integer id, String secret) throws Exception {

        HeavyWork work = heavyWorkRepository.getOne(id);
        if (work == null || !work.getSecret().equals(secret)) {
            throw new DetailedException("没有找到任务");
        }
        return work;
    }

}
