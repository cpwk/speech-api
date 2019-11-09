package com.mdtech.zyedu.api.common.controller;

import com.mdtech.zyedu.api.common.entity.SimpleArticle;
import com.mdtech.zyedu.api.common.service.ICommonService;
import com.mdtech.zyedu.api.common.service.WXArticleService;
import com.mdtech.zyedu.common.authority.AdminType;
import com.mdtech.zyedu.common.authority.RequiredPermission;
import com.mdtech.zyedu.common.controller.BaseController;
import com.mdtech.zyedu.common.entity.ValCode;
import com.mdtech.zyedu.common.exception.DetailedException;
import com.mdtech.zyedu.common.util.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
