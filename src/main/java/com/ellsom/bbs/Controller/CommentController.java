package com.ellsom.bbs.Controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.ellsom.bbs.Service.IArticleService;
import com.ellsom.bbs.Service.ICommentService;
import com.ellsom.bbs.Service.IUserService;
import com.ellsom.bbs.pojo.domaino.CommentDO;
import com.ellsom.bbs.pojo.po.Comment;
import com.ellsom.bbs.pojo.vo.CommentVO;
import com.ellsom.bbs.util.AjaxResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/forumApi/dqcomment")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;
    @Autowired
    private IArticleService iArticleService;
    @Autowired
    private IUserService iUserService;
    private PageInfo<Comment> pageInfo;

    @GetMapping("/list")

    public AjaxResult listComment(@RequestParam(defaultValue = "1", name = "pageNum") int pageNum,
                                  @RequestParam(defaultValue = "10", name = "pageSize") int pageSize,
                                  Comment comment) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        List<Comment> dqComments = iCommentService.selectComment(pageNum, pageSize, comment);
        return AjaxResult.success("查询成功", new PageInfo<>(dqComments));
    }

    @GetMapping("/{dqCommentId}")
    public AjaxResult getInfo(@PathVariable Long dqCommentId) {
        //后面可以根据缓存中先查询
        Comment comment = iCommentService.selectCommentById(dqCommentId);
        return AjaxResult.success("查询成功", comment);
    }

    @GetMapping("/dqarticle/{articleId}")
    public AjaxResult getInfoByArticleId(@RequestParam(defaultValue = "1") int pageNum,
                                         @RequestParam(defaultValue = "10") int pageSize,
                                         @PathVariable Long articleId) {
        List<Comment> Comments = iCommentService.selectCommentByArticleId(pageNum, pageSize, articleId);
        pageInfo = new PageInfo<>(Comments);
        return AjaxResult.success("查询成功", pageInfo);
    }

    @GetMapping("/dqroot/{rootId}")
    public AjaxResult getDqCommentsByRootId(@RequestParam(defaultValue = "1") int pageNum,
                                            @RequestParam(defaultValue = "100") int pageSize,
                                            @PathVariable Long rootId) {
        List<Comment> comments = iCommentService.selectCommentsByRootId(pageNum, pageSize, rootId);
        pageInfo = new PageInfo<>(comments);
        return AjaxResult.success("查询成功", pageInfo);
    }

    @GetMapping("/dquser/{userId}")
    public AjaxResult getInfoByUserId(@RequestParam(defaultValue = "1") int pageNum,
                                      @RequestParam(defaultValue = "10") int pageSize,
                                      @PathVariable Long userId) {
        List<Comment> comments = iCommentService.selectCommentByUserId(pageNum, pageSize, userId);
        pageInfo = new PageInfo<>(comments);
        return AjaxResult.success("查询成功", pageInfo);
    }

    @SaCheckLogin
    @GetMapping("/tome")
    public AjaxResult getCommentToMe(@RequestParam(defaultValue = "1") int pageNum,
                                     @RequestParam(defaultValue = "10") int pageSize) {
        List<Comment> dqComments = iCommentService.selectCommentToMe(pageNum, pageSize);
        pageInfo = new PageInfo<>(dqComments);
        return AjaxResult.success("查询成功", pageInfo);
    }

    @SaCheckLogin
    @PostMapping("/add")
    public AjaxResult addType(@RequestBody CommentVO commentVO) {
        Comment comment = new Comment();
        comment.setContent(commentVO.getContent());
        comment.setCommentUserId(StpUtil.getLoginIdAsLong());
        comment.setArticleId(commentVO.getArticleId());
        comment.setReplyId(commentVO.getReplyId());
        int i = iCommentService.insertComment(comment);
        return AjaxResult.success("发表评论成功", i);
    }

    @SaCheckLogin
    @GetMapping("/remove/{dqCommentId}")
    public AjaxResult remove(@PathVariable Long dqCommentId) {
        int i = iCommentService.deleteCommentById(dqCommentId);
        return AjaxResult.success("删除评论成功", i);
    }
    @GetMapping("/superlist")
    public AjaxResult selectDqCommentPostProcessers(@RequestParam(defaultValue = "1", name = "pageNum") int pageNum,
                                                    @RequestParam(defaultValue = "10", name = "pageSize") int pageSize,
                                                    CommentDO commentDO) {
        List<CommentDO> commentDOList = iCommentService.selectCommentDOs(pageNum, pageSize, commentDO);
        return AjaxResult.success("查询成功", new PageInfo<>(commentDOList));
    }
}
