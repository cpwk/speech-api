package com.mdtech.zyedu.api.file.controller;

import com.mdtech.zyedu.api.file.service.ImgBase64Utils;
import com.mdtech.zyedu.common.authority.AdminType;
import com.mdtech.zyedu.common.authority.RequiredPermission;
import com.mdtech.zyedu.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/common/file")
public class CommonFileController extends BaseController {

    @RequestMapping(value = "/img_to_base64")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView img_to_base64(String url) throws Exception {
        return feedback(ImgBase64Utils.base64FromURL(url));
    }

}
