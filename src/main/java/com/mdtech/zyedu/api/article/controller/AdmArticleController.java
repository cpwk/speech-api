package com.mdtech.zyedu.api.article.controller;

import com.mdtech.zyedu.api.admin.authority.AdminPermission;
import com.mdtech.zyedu.api.article.model.Article;
import com.mdtech.zyedu.api.article.qo.ArticleQo;
import com.mdtech.zyedu.api.article.service.IArticleService;
import com.mdtech.zyedu.common.authority.AdminType;
import com.mdtech.zyedu.common.authority.RequiredPermission;
import com.mdtech.zyedu.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adm/article")
public class AdmArticleController extends BaseController {

    @Autowired
    private IArticleService articleService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ARTICLE_EDIT)
    public ModelAndView save(String article) throws Exception {
        articleService.save(parseModel(article, new Article()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ARTICLE_EDIT)
    public ModelAndView remove(Integer id) throws Exception {
        articleService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/article")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ARTICLE_EDIT)
    public ModelAndView article(Integer id) throws Exception {
        return feedback(articleService.article(id));
    }

    @RequestMapping(value = "/articles")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ARTICLE_EDIT)
    public ModelAndView articles(String articleQo) throws Exception {
        return feedback(articleService.articles(parseModel(articleQo, new ArticleQo()), true));
    }

    @RequestMapping(value = "/updateStatus")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.CAMPUS_EDIT)
    public ModelAndView updateStatus(Byte status, Integer id) throws Exception {
        articleService.updateStatus(status, id);
        return feedback(null);
    }

    @RequestMapping(value = "/three_article")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView three_article(Integer id) throws Exception {
        return feedback(articleService.three_article(id));
    }

}