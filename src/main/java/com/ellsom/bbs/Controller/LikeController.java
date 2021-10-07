package com.ellsom.bbs.Controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.ellsom.bbs.Service.IlikeService;
import com.ellsom.bbs.Pojo.Po.User;
import com.ellsom.bbs.Util.AjaxResult;
import com.ellsom.bbs.Util.HttpStatus;
import com.ellsom.bbs.Util.StringUtils;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forumApi/dqlike")
public class LikeController {
    @Autowired
    private IlikeService ilikeService;

    @SaCheckLogin
    @GetMapping("/like/{dqArticleId}")
    public AjaxResult like(@PathVariable @NonNull Long dqArticleId) {
        int i = ilikeService.giveALike(dqArticleId);
        if (i <= 0)
            return AjaxResult.error(HttpStatus.ERROR, "不能重复点赞");
        System.out.println(AjaxResult.success("点赞成功", i));
        return AjaxResult.success("点赞成功", i);
    }

    @SaCheckLogin
    @GetMapping("/unlike/{dqArticleId}")
    public AjaxResult unlike(@PathVariable @NonNull Long dqArticleId) {
        int i = ilikeService.unlike(dqArticleId);
        if (i <= 0)
            return AjaxResult.error(HttpStatus.ERROR, "该用户还未给该文章点赞");
        return AjaxResult.success("取消点赞成功", i);
    }

    @SaCheckLogin
    @GetMapping("/status/{dqArticleId}")
    public AjaxResult checkStatusForDqArticle(@PathVariable @NonNull Long dqArticleId) {
        int i = ilikeService.checkStatusForArticle(dqArticleId);
        return AjaxResult.success("查询成功", i);
    }

    @GetMapping("/getlikes/{dqArticleId}")
    public AjaxResult getLikeNumberInDqArticle(@PathVariable @NonNull Long dqArticleId) {
        int i = ilikeService.getLikeNumberInArticle(dqArticleId);
        return AjaxResult.success("查询成功", i);
    }
    @GetMapping("/getlikeusers/{dqArticleId}")
    public AjaxResult likedDqUserInfoInDqArticle(@RequestParam(defaultValue = "1", name = "pageNum") int pageNum,
                                                 @RequestParam(defaultValue = "10", name = "pageSize") int pageSize,
                                                 @PathVariable @NonNull Long dqArticleId) {
        List<User> dqUsers = ilikeService.likedDqUserInfoInArticle(pageNum, pageSize, dqArticleId);
        if (StringUtils.isNull(dqUsers)){
            return AjaxResult.error("此文章没有点赞", HttpStatus.NOT_FOUND);
        }
        return AjaxResult.success("查询成功", new PageInfo<>(dqUsers));
    }
}
