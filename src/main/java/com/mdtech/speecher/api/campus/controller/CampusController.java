package com.mdtech.speecher.api.campus.controller;

import com.mdtech.speecher.api.admin.authority.AdminPermission;
import com.mdtech.speecher.api.campus.model.Campus;
import com.mdtech.speecher.api.campus.qo.CampusQo;
import com.mdtech.speecher.api.campus.service.CampusService;
import com.mdtech.speecher.common.authority.AdminType;
import com.mdtech.speecher.common.authority.RequiredPermission;
import com.mdtech.speecher.common.controller.BaseController;
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
@RequestMapping(value = "/admin/campus")
public class CampusController extends BaseController {
    @Autowired
    private CampusService campusService;

    @RequestMapping(value = "/findAllCampus")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.CAMPUS_EDIT)
    public ModelAndView findAllCampus(String campusQo) throws Exception {
        return feedback(campusService.findAllCampus(parseModel(campusQo, new CampusQo()), true));
    }

    @RequestMapping(value = "/removeCampus")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.CAMPUS_EDIT)
    public ModelAndView removeCampus(Integer id) throws Exception {
        campusService.removeCampus(id);
        return feedback(null);
    }

    @RequestMapping(value = "/findCampus")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.CAMPUS_EDIT)
    public ModelAndView findCampus(Integer id) throws Exception {
        return feedback(campusService.findCampus(id));
    }

    @RequestMapping(value = "/saveCampus")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.CAMPUS_EDIT)
    public ModelAndView saveCampus(String campus) throws Exception {
        campusService.saveCampus(parseModel(campus, new Campus()));
        return feedback(null);
    }

    @RequestMapping(value = "/updateStatus")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.CAMPUS_EDIT)
    public ModelAndView updateStatus(Byte status, Integer id) throws Exception {
        campusService.updateStatus(status, id);
        return feedback(null);
    }
}
