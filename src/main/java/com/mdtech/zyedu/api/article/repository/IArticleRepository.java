package com.mdtech.zyedu.api.article.repository;

import com.mdtech.zyedu.api.article.model.Article;
import com.mdtech.zyedu.common.reposiotry.BaseRepository;

import java.util.List;

public interface IArticleRepository extends BaseRepository<Article, Integer> {
    List<Article> findAllByTypeOrderByIdDesc(Byte type);

    Article findFirstByTypeAndIdBeforeOrderByIdDesc(Byte type, Integer id);

    Article findFirstByTypeAndIdAfterOrderByIdAsc(Byte type, Integer id);

}