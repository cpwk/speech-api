package com.mdtech.zyedu.api.form.controller;

import com.mdtech.zyedu.api.admin.authority.AdminPermission;
import com.mdtech.zyedu.api.form.model.Form;
import com.mdtech.zyedu.api.form.qo.FormQo;
import com.mdtech.zyedu.api.form.service.FormService;
import com.mdtech.zyedu.api.heavywork.service.IHeavyWorkService;
import com.mdtech.zyedu.common.authority.AdminType;
import com.mdtech.zyedu.common.authority.RequiredPermission;
import com.mdtech.zyedu.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * @author: MengKai
 * @create: 2019-08-2019-08-31-09:34
 * * * * * * * * * * * * * * * * * * * * * * * *
 **/
@Controller
@RequestMapping(value = "/admin/form")
public class FormController extends BaseController {
    @Autowired
    private FormService formService;
    @Autowired
    private IHeavyWorkService heavyWorkService;

    @RequestMapping(value = "/findAllForm")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.FORM_QUERY)
    public ModelAndView findAllForm(String formQo) throws Exception {
        return feedback(formService.findAllForm(parseModel(formQo, new FormQo())));
    }

    @RequestMapping(value = "/export_form")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.FORM_QUERY)
    public ModelAndView export_qaPapers(String formQo) throws Exception {
        return feedback(formService.export_form(parseModel(formQo, new FormQo())));
    }

    @RequestMapping(value = "/export_form_progress")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.FORM_QUERY)
    public ModelAndView export_qaPapers_progress(Integer id, String secret) throws Exception {
        return feedback(heavyWorkService.getById(id, secret));
    }
}
