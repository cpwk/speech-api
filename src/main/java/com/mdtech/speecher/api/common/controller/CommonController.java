package com.mdtech.speecher.api.common.controller;

import com.mdtech.speecher.api.common.entity.SimpleArticle;
import com.mdtech.speecher.api.common.service.ICommonService;
import com.mdtech.speecher.api.common.service.WXArticleService;
import com.mdtech.speecher.common.authority.AdminType;
import com.mdtech.speecher.common.authority.RequiredPermission;
import com.mdtech.speecher.common.controller.BaseController;
import com.mdtech.speecher.common.exception.DetailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {

    @Autowired
    private ICommonService commonService;

    @RequestMapping(value = "/fetch_article")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView fetch_article(String url) throws Exception {
        SimpleArticle article;
        try {
            article = WXArticleService.fetch(url);
        } catch (IOException e) {
            throw new DetailedException("抓取失败");
        }
        if (article == null || article.getContent() == null) {
            throw new DetailedException("仅支持微信文章");
        }
        return feedback(article);
    }

}
