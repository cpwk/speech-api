package com.mdtech.zyedu.api.video.controller;

import com.mdtech.zyedu.api.admin.authority.AdminPermission;
import com.mdtech.zyedu.api.video.model.Video;
import com.mdtech.zyedu.api.video.qo.VideoQo;
import com.mdtech.zyedu.api.video.service.VideoService;
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
@RequestMapping(value = "/admin/video")
public class VideoController extends BaseController {
    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/findAllVideo")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIDEO_EDIT)
    public ModelAndView findAllVideo(String videoQo) throws Exception {
        return feedback(videoService.findAllVideo(parseModel(videoQo, new VideoQo()), true));
    }

    @RequestMapping(value = "/removeVideo")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIDEO_EDIT)
    public ModelAndView removeVideo(Integer id) throws Exception {
        videoService.removeVideo(id);
        return feedback(null);
    }

    @RequestMapping(value = "/findVideo")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIDEO_EDIT)
    public ModelAndView findVideo(Integer id) throws Exception {
        return feedback(videoService.findVideo(id));
    }

    @RequestMapping(value = "/saveVideo")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIDEO_EDIT)
    public ModelAndView saveVideo(String video) throws Exception {
        videoService.saveVideo(parseModel(video, new Video()));
        return feedback(null);
    }

    @RequestMapping(value = "/updateStatus")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.CAMPUS_EDIT)
    public ModelAndView updateStatus(Byte status, Integer id) throws Exception {
        videoService.updateStatus(status,id);
        return feedback(null);
    }
}
