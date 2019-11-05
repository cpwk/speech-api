package com.mdtech.zyedu.api.trainer.controller;

import com.mdtech.zyedu.api.admin.authority.AdminPermission;
import com.mdtech.zyedu.api.trainer.model.Trainer;
import com.mdtech.zyedu.api.trainer.qo.TrainerQo;
import com.mdtech.zyedu.api.trainer.service.ITrainerService;
import com.mdtech.zyedu.common.authority.AdminType;
import com.mdtech.zyedu.common.authority.RequiredPermission;
import com.mdtech.zyedu.common.controller.BaseController;
import com.mdtech.zyedu.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adm/trainer")
public class AdmTrainerController extends BaseController {

    @Autowired
    private ITrainerService trainerService;

    @RequestMapping(value = "/save_trainer")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.TRAINER_EDIT)
    public ModelAndView save_trainer(String trainer) throws Exception {
        trainerService.save_trainer(parseModel(trainer, new Trainer()));
        return feedback(null);
    }

    @RequestMapping(value = "/trainers")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.TRAINER_EDIT)
    public ModelAndView trainers(String trainerQo) throws ServiceException {
        return feedback(trainerService.trainers(parseModel(trainerQo, new TrainerQo()), true));
    }

    @RequestMapping(value = "/trainer")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.TRAINER_EDIT)
    public ModelAndView trainer(Integer id) throws ServiceException {
        return feedback(trainerService.trainer(id, false));
    }

    @RequestMapping(value = "/remove_trainer")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.TRAINER_EDIT)
    public ModelAndView remove_trainer(Integer id) throws ServiceException {
        trainerService.remove_trainer(id, false);
        return feedback(null);
    }

    @RequestMapping(value = "/updateStatus")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.CAMPUS_EDIT)
    public ModelAndView updateStatus(Byte status, Integer id) throws Exception {
        trainerService.updateStatus(status, id);
        return feedback(null);
    }

}