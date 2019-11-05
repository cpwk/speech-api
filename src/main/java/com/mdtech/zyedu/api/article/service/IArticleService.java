package com.mdtech.zyedu.api.article.service;

import com.mdtech.zyedu.api.article.model.Article;
import com.mdtech.zyedu.api.article.qo.ArticleQo;
import com.mdtech.zyedu.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IArticleService {


    List<Article> getTopArticles(int limit);

    Article article(int id);

    Page<Article> articles(ArticleQo qo, boolean adm);

    void save(Article article) throws ServiceException;

    void remove(int id);

    void updateStatus(Byte status, Integer id);

}
