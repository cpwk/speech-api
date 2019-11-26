package com.mdtech.speecher.api.ws.controller;

import com.mdtech.speecher.api.article.qo.ArticleQo;
import com.mdtech.speecher.api.article.service.IArticleService;
import com.mdtech.speecher.api.banners.qo.BannerQo;
import com.mdtech.speecher.api.banners.service.BannerService;
import com.mdtech.speecher.api.campus.qo.CampusQo;
import com.mdtech.speecher.api.campus.service.CampusService;
import com.mdtech.speecher.api.form.model.Form;
import com.mdtech.speecher.api.form.service.FormService;
import com.mdtech.speecher.api.trainer.qo.TrainerQo;
import com.mdtech.speecher.api.trainer.service.ITrainerService;
import com.mdtech.speecher.api.video.qo.VideoQo;
import com.mdtech.speecher.api.video.service.VideoService;
import com.mdtech.speecher.common.authority.AdminType;
import com.mdtech.speecher.common.authority.RequiredPermission;
import com.mdtech.speecher.common.controller.BaseController;
import com.mdtech.speecher.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ws/home")
public class HomeController extends BaseController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private CampusService campusService;

    @Autowired
    private ITrainerService trainerService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private FormService formService;

    @RequestMapping(value = "/articles")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView articles(String articleQo) throws Exception {
        return feedback(articleService.articles(parseModel(articleQo, new ArticleQo()), false));
    }


    @RequestMapping(value = "/findBanners")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView findAll(String bannerQo) throws Exception {
        return feedback(bannerService.findAll(parseModel(bannerQo, new BannerQo()), false));
    }


    @RequestMapping(value = "/findAllCampus")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView findAllCampus(String campusQo) throws Exception {
        return feedback(campusService.findAllCampus(parseModel(campusQo, new CampusQo()), false));
    }

    @RequestMapping(value = "/trainers")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView trainers(String trainerQo) throws ServiceException {
        return feedback(trainerService.trainers(parseModel(trainerQo, new TrainerQo()), false));
    }

    @RequestMapping(value = "/findAllVideo")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView findAllVideo(String videoQo) throws Exception {
        return feedback(videoService.findAllVideo(parseModel(videoQo, new VideoQo()), false));
    }

    @RequestMapping(value = "/saveForm")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView saveForm(String form) throws Exception {
        formService.saveForm(parseModel(form, new Form()));
        return feedback(null);
    }

    @RequestMapping(value = "/article")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView article(Integer id) throws Exception {
        return feedback(articleService.article(id));
    }


}
