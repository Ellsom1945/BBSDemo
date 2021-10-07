package com.ellsom.bbs.Service;

import com.ellsom.bbs.Pojo.Dmo.ArticleDO;
import com.ellsom.bbs.Pojo.Po.Article;

import java.util.List;

public interface IArticleService {
    public List<Article> selectArticle(int pageNum, int pageSize, Article Article);


    public Article selectArticleById(Long ArticleId);


    public Article selectArticleByTitle(String title);

    public List<Article> selectArticlesByTypeId(int pageNum, int pageSize, Long typeId);

    public List<Article> selectArticlesByAuthorId(int pageNum, int pageSize, Long authorId);


    public List<Article> selectArticlesByAuthorUsername(int pageNum, int pageSize, String authorUsername);

    public int insertArticle(Article Article);

    public int updateArticle(Article Article);

    public int deleteArticleById(Long articleId);

    public List<ArticleDO> selectDqArticleDOs(int pageNum, int pageSize, ArticleDO articleDO);

    public ArticleDO selectDqArticlePostProcesserByDqArticleId();

}
