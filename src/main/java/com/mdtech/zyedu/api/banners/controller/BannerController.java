package com.mdtech.zyedu.api.banners.controller;

import com.mdtech.zyedu.api.admin.authority.AdminPermission;
import com.mdtech.zyedu.api.banners.model.Banner;
import com.mdtech.zyedu.api.banners.qo.BannerQo;
import com.mdtech.zyedu.api.banners.service.BannerService;
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
@RequestMapping(value = "/admin/banner")
public class BannerController extends BaseController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "/findBanners")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView findAll(String bannerQo) throws Exception {
        return feedback(bannerService.findAll(parseModel(bannerQo, new BannerQo()), true));
    }

    @RequestMapping(value = "/removeBanner")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView removeBanner(Integer id) throws Exception {
        bannerService.removeBanner(id);
        return feedback(null);
    }

    @RequestMapping(value = "/findBanner")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView findBanner(Integer id) throws Exception {
        return feedback(bannerService.findBanner(id));
    }

    @RequestMapping(value = "/saveBanner")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView saveBanner(String banner) throws Exception {
        bannerService.saveBanner(parseModel(banner, new Banner()));
        return feedback(null);
    }

    @RequestMapping(value = "/updateStatus")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.CAMPUS_EDIT)
    public ModelAndView updateStatus(Byte status, Integer id) throws Exception {
        bannerService.updateStatus(status, id);
        return feedback(null);
    }
}
