package com.ellsom.bbs.Controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.ellsom.bbs.Service.IArticleService;
import com.ellsom.bbs.Service.IUserService;
import com.ellsom.bbs.Pojo.Dmo.ArticleDO;
import com.ellsom.bbs.Pojo.Po.Article;
import com.ellsom.bbs.Util.AjaxResult;
import com.ellsom.bbs.Util.DateUtils;
import com.ellsom.bbs.Util.HttpStatus;
import com.ellsom.bbs.Util.StringUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forumApi/dqarticle/")
public class ArticleController {
    @Autowired
    private IArticleService iArticleService;
    @Autowired
    private IUserService iUserService;

    @GetMapping("/list")
    public AjaxResult listArticle(@RequestParam(defaultValue = "1", name = "pageNum") int pageNum,
                                  @RequestParam(defaultValue = "10", name = "pageSize") int pageSize,
                                  Article article) {
        List<Article> articles = iArticleService.selectArticle(pageNum, pageSize, article);
        return AjaxResult.success("查询成功", new PageInfo<>(articles));
    }

    @GetMapping("/{articleId}")
    public AjaxResult getDqArticleById(@PathVariable Long articleId) {
        Article article = iArticleService.selectArticleById(articleId);
        return AjaxResult.success("查询成功", article);
    }

    @GetMapping("/author/{authorId}")
    public AjaxResult getByAuthorId(@RequestParam(defaultValue = "1") int pageNum,
                                    @RequestParam(defaultValue = "10") int pageSize,
                                    @PathVariable Long authorId) {
        List<Article> articles = iArticleService.selectArticlesByAuthorId(pageNum, pageSize, authorId);
        if (StringUtils.isNull(articles) || articles.size() == 0) {
            return AjaxResult.error(HttpStatus.NOT_FOUND, "此用户没有文章");
        }
        return AjaxResult.success("查询成功", new PageInfo<>(articles));
    }

    @GetMapping("/superlist")
    public AjaxResult selectDqArticlePostProcesser(@RequestParam(defaultValue = "1", name = "pageNum") int pageNum,
                                                   @RequestParam(defaultValue = "10", name = "pageSize") int pageSize,
                                                   ArticleDO articleDO) {
        List<ArticleDO> articleDOList = iArticleService.selectDqArticleDOs(pageNum, pageSize, articleDO);
        return AjaxResult.success("查询成功", new PageInfo<>(articleDOList));
    }

    @SaCheckLogin
    @SaCheckPermission(value = "user-add")
    @PostMapping("/add")
    public AjaxResult addArticle(@RequestBody ArticleDO articleVO) {
        Article article = new Article();
        article.setArticleTitle(articleVO.getArticleTitle());
        article.setArticleContent(articleVO.getArticleContent());
        article.setArticleImage(articleVO.getArticleImage());
        article.setTypeId(articleVO.getTypeId());
        article.setAuthorId(StpUtil.getLoginIdAsLong());
        article.setStatus("0");
        article.setCreateTime(DateUtils.getNowDate());
        article.setUpdateTime(DateUtils.getNowDate());
        int i = iArticleService.insertArticle(article);
        return AjaxResult.success("添加成功", i);
    }

    @SaCheckLogin
    @SaCheckPermission(value = "user-update")
    @PostMapping("/update")
    public AjaxResult updateArticle(@RequestBody Article dqArticle) {
        int i = iArticleService.updateArticle(dqArticle);
        return AjaxResult.success("更新成功", i);
    }
    @SaCheckLogin
    @SaCheckPermission(value = "user-delete")
    @GetMapping("/delete/{articleId}")
    public AjaxResult deleteByArticleId(@PathVariable Long articleId) {
//        文章作者ID
        int i = iArticleService.deleteArticleById(articleId);
        return AjaxResult.success("删除成功", i);
    }
    @GetMapping("/type/{typeId}")
    public AjaxResult getByTypeId(@RequestParam(defaultValue = "1") int pageNum,
                                  @RequestParam(defaultValue = "10") int pageSize,
                                  @PathVariable Long typeId) {
        List<Article> dqArticles = iArticleService.selectArticlesByTypeId(pageNum, pageSize, typeId);
        return AjaxResult.success("查询成功", new PageInfo<>(dqArticles));
    }

}
