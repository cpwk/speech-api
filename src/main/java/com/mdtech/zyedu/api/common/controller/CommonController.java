package com.mdtech.zyedu.api.common.controller;

import com.mdtech.zyedu.api.common.entity.SimpleArticle;
import com.mdtech.zyedu.api.common.service.ICommonService;
import com.mdtech.zyedu.api.common.service.WXArticleService;
import com.mdtech.zyedu.common.authority.AdminType;
import com.mdtech.zyedu.common.authority.RequiredPermission;
import com.mdtech.zyedu.common.controller.BaseController;
import com.mdtech.zyedu.common.entity.KeyValue;
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
import java.util.List;

@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {

    @Autowired
    private ICommonService commonService;


    @RequestMapping(value = "/gen_valCode_signin")
    @RequiredPermission(adminType = AdminType.NONE)
    public void genValCode(HttpServletRequest request, HttpServletResponse response, Long key) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            String randomString = randomValidateCode.getRandcode(request, response, key);//输出验证码图片方法
            commonService.saveValCode(key, new ValCode(randomString));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



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
