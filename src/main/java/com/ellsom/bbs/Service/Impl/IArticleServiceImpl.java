package com.ellsom.bbs.Service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ellsom.bbs.Mapper.*;
import com.ellsom.bbs.Service.IArticleService;
import com.ellsom.bbs.Service.IUserService;
import com.ellsom.bbs.Pojo.Dmo.ArticleDO;
import com.ellsom.bbs.Pojo.Po.Article;
import com.ellsom.bbs.Pojo.Po.Type;
import com.ellsom.bbs.Pojo.Po.User;
import com.ellsom.bbs.Util.DateUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleMapper articlemapper;
    @Autowired
    private ArticleDOMapper articleDOmapper;
    @Autowired
    private UserMapper usermapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;
    @Autowired
    private IUserService iUserService;

    @Override
    public List<Article> selectArticle(int pageNum, int pageSize, Article Article) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articles = articlemapper.selectList(null);
        if (articles.isEmpty()) {

        }
        return articles;
    }

    @Override
    public Article selectArticleById(Long ArticleId) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", ArticleId).eq("status", "0");
        return articlemapper.selectOne(wrapper);

    }

    @Override
    public Article selectArticleByTitle(String title) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.like("article_title", title).eq("status", "0");
        return articlemapper.selectOne(wrapper);
    }

    @Override
    public List<Article> selectArticlesByTypeId(int pageNum, int pageSize, Long typeId) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Article::getTypeId, typeId)
                .eq(Article::getStatus, "0")
                .orderByDesc(Article::getArticleId);
//        MyQueryWrapper<DqArticle> myQueryWrapper = new MyQueryWrapper<>();
//        myQueryWrapper.statuseq("type_id", String.valueOf(typeId));
        Type type = typeMapper.selectById(typeId);
        PageHelper.startPage(pageNum, pageSize);
        List<Article> dqArticles = articlemapper.selectList(queryWrapper);
        queryWrapper.clear();
        //清除queryWrapper的数据，防止给其他的带来影响
        return dqArticles;
    }

    @Override
    public List<Article> selectArticlesByAuthorId(int pageNum, int pageSize, Long authorId) {
        /** 对authorid校验,将Long转换为String **/
        QueryWrapper<Article> myQueryWrapper = new QueryWrapper<>();
        myQueryWrapper.eq("author_id", String.valueOf(authorId));
        User dqUser = iUserService.selectUserById(authorId);
        PageHelper.startPage(pageNum, pageSize);
        List<Article> dqArticles = articlemapper.selectList(myQueryWrapper);
        //清除queryWrapper的数据，防止给其他的带来影响
        myQueryWrapper.clear();
        return dqArticles;
    }

    @Override
    public List<Article> selectArticlesByAuthorUsername(int pageNum, int pageSize, String authorUsername) {
        User dqUser = iUserService.selectUserByUserName(authorUsername);
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<Article> myQueryWrapper = new QueryWrapper<>();
        myQueryWrapper.eq("author_username", authorUsername);
        List<Article> dqArticles = articlemapper.selectList(myQueryWrapper);
        //清除queryWrapper的数据，防止给其他的带来影响
        myQueryWrapper.clear();
        return dqArticles;
    }

    @Override
    public int insertArticle(Article Article) {
        Long autorId = Article.getAuthorId();
        User user = usermapper.selectById(autorId);
//        DqUser dqUser = idqUserService.selectDqUserById(autorId);
        Article.setAuthorUsername(user.getUserName());
        Article.setAuthorNickname(user.getNickName());

        int insert = articlemapper.insert(Article);
        return insert;
    }

    @Override
    public int updateArticle(Article newArticle) {
        Long articleId = newArticle.getArticleId();
        Article article = articlemapper.selectById(articleId);
        article.setArticleTitle(newArticle.getArticleTitle());
        article.setArticleContent(newArticle.getArticleContent());
        article.setArticleImage(newArticle.getArticleImage());
        article.setTypeId(newArticle.getTypeId());
        article.setUpdateTime(DateUtils.getNowDate());
        int i = articlemapper.updateById(article);
        return i;
    }

    @Override
    public int deleteArticleById(Long articleId) {
        Article article = articlemapper.selectById(articleId);
        if (article == null) {
//            throw new CustomException("未查询到此文章", HttpStatus.NOT_FOUND);
        }
        Long autorId = article.getAuthorId();
        if (StpUtil.getLoginIdAsLong() != autorId) {
//            throw new CustomException("不能删除其他人的文章", HttpStatus.ERROR);
        }
        int i = articlemapper.deleteById(articleId);
        if (i > 0) {
            return i;
        } else {
            return 0;
//            throw new CustomException("删除失败", HttpStatus.ERROR);
        }
    }

    @Override
    public List<ArticleDO> selectDqArticleDOs(int pageNum, int pageSize, ArticleDO articleDO) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDO> articles = articleDOmapper.selectAllArticleDO(articleDO);
        return articles;
    }

    @Override
    public ArticleDO selectDqArticlePostProcesserByDqArticleId() {
        return null;
    }
}
