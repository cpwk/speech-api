package com.mdtech.speecher.api.article.service;

import com.mdtech.speecher.api.article.model.Article;
import com.mdtech.speecher.api.article.qo.ArticleQo;
import com.mdtech.speecher.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IArticleService {


    List<Article> getTopArticles(int limit);

    Article article(int id);

    Page<Article> articles(ArticleQo qo, boolean adm);

    void save(Article article) throws ServiceException;

    void remove(int id);

    void updateStatus(Byte status, Integer id);

    Map<String, Article> three_article(Integer id);

}
