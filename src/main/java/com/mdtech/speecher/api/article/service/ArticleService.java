package com.mdtech.speecher.api.article.service;

import com.mdtech.speecher.api.article.model.Article;
import com.mdtech.speecher.api.article.qo.ArticleQo;
import com.mdtech.speecher.api.article.repository.IArticleRepository;
import com.mdtech.speecher.common.entity.Constants;
import com.mdtech.speecher.common.exception.DetailedException;
import com.mdtech.speecher.common.exception.ServiceException;
import com.mdtech.speecher.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Article> three_article(Integer id) {
        Map<String, Article> result = new HashMap<>();
        Article article = articleRepository.getOne(id);
        result.put("article", article);
        Byte type = article.getType();
        Article pre = articleRepository.findFirstByTypeAndIdBeforeOrderByIdDesc(type, id);
        Article next = articleRepository.findFirstByTypeAndIdAfterOrderByIdAsc(type, id);
        result.put("pre", pre);
        result.put("next", next);
        return result;
    }
}
