package com.mdtech.zyedu.api.article.service;

import com.mdtech.zyedu.api.article.model.Article;
import com.mdtech.zyedu.api.article.qo.ArticleQo;
import com.mdtech.zyedu.api.article.repository.IArticleRepository;
import com.mdtech.zyedu.common.entity.Constants;
import com.mdtech.zyedu.common.exception.DetailedException;
import com.mdtech.zyedu.common.exception.ServiceException;
import com.mdtech.zyedu.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements IArticleService {

    @Autowired
    private IArticleRepository articleRepository;

    @Override
    public List<Article> getTopArticles(int limit) {
        ArticleQo qo = new ArticleQo();
        qo.setPageSize(limit);
        return articleRepository.findAll(qo).getContent();
    }


    @Override
    public Article article(int id) {
        return articleRepository.getOne(id);
    }

    @Override
    public Page<Article> articles(ArticleQo qo, boolean adm) {
        if (adm) {
            qo.setStatus(0);
        }
        return articleRepository.findAll(qo);
    }

    @Override
    public void save(Article article) throws ServiceException {

        if (StringUtils.isEmpty(article.getTitle())) {
            throw new DetailedException("请填写标题");
        }
        if (StringUtils.isEmpty(article.getImg())) {
            throw new DetailedException("请上传封面图");
        }
        if (StringUtils.isEmpty(article.getIntro())) {
            throw new DetailedException("请填写简介");
        }
        if (StringUtils.isEmpty(article.getContent())) {
            throw new DetailedException("请填写文章内容");
        }

        if (StringUtils.isEmpty(article.getName())) {
            throw new DetailedException("请填写文章作者");
        }

        if (article.getId() == null) {
            article.setCreatedAt(System.currentTimeMillis());
        }
        if (article.getStatus() == null) {
            article.setStatus(Constants.STATUS_OK);
        }
        articleRepository.save(article);
    }

    @Override
    public void remove(int id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void updateStatus(Byte status, Integer id) {
        Article article = articleRepository.getOne(id);
        article.setStatus(status);
        articleRepository.save(article);
    }
}
