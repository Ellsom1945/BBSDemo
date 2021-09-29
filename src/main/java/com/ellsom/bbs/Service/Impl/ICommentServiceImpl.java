package com.ellsom.bbs.Service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ellsom.bbs.Mapper.ArticleMapper;
import com.ellsom.bbs.Mapper.CommentMapper;
import com.ellsom.bbs.Mapper.CommentDOMapper;
import com.ellsom.bbs.Mapper.UserMapper;
import com.ellsom.bbs.Service.ICommentService;
import com.ellsom.bbs.pojo.domaino.CommentDO;
import com.ellsom.bbs.pojo.po.Article;
import com.ellsom.bbs.pojo.po.Comment;
import com.ellsom.bbs.pojo.po.User;
import com.ellsom.bbs.util.DateUtils;
import com.ellsom.bbs.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
@Service
public class ICommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentDOMapper commentDOMapper;

    QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
    QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

    @Override
    public List<Comment> selectComment(int pageNum, int pageSize, Comment comment) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        queryWrapper.lambda().eq(StringUtils.isNotNull(comment.getCommentId()), Comment::getCommentId, comment.getCommentId())
                //文章id
                .eq(StringUtils.isNotNull(comment.getArticleId()), Comment::getArticleId, comment.getArticleId())
                //发表评论用户id
                .eq(StringUtils.isNotNull(comment.getCommentUserId()), Comment::getCommentUserId, comment.getCommentUserId())
                //评论类型
                .eq(StringUtils.isNotNull(comment.getCommentType()), Comment::getCommentType, comment.getCommentType())
                //发给用户的id
                .eq(StringUtils.isNotNull(comment.getToUserId()), Comment::getToUserId, comment.getToUserId())
                //父评论id
                .eq(StringUtils.isNotNull(comment.getReplyId()), Comment::getReplyId, comment.getReplyId())
                //根评论id
                .eq(StringUtils.isNotNull(comment.getRootId()), Comment::getRootId, comment.getRootId())
                .eq(Comment::getStatus, "0");
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        queryWrapper.clear();
        return comments;
    }

    @Override
    public Comment selectCommentById(Long CommentId) {
        queryWrapper.eq("comment_id", CommentId).eq("status", "0");
        Comment comment = commentMapper.selectOne(queryWrapper);
        queryWrapper.clear();
        return comment;
    }

    @Override
    public List<Comment> selectCommentByUserId(int pageNum, int pageSize, Long userId) {
        userQueryWrapper.lambda().eq(User::getUserId, userId).eq(User::getStatus, "0");
        User user = userMapper.selectOne(userQueryWrapper);
        userQueryWrapper.clear();
        queryWrapper.eq("comment_userid", userId).eq("status", "0");
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        queryWrapper.clear();
        return comments;
    }

    @Override
    public List<Comment> selectCommentByArticleId(int pageNum, int pageSize, Long articleId) {
        articleQueryWrapper.lambda().eq(Article::getArticleId, articleId).eq(Article::getStatus, "0");
        Article article = articleMapper.selectOne(articleQueryWrapper);
        articleQueryWrapper.clear();
        queryWrapper.lambda().eq(Comment::getArticleId, articleId).eq(Comment::getRootId, "0").eq(Comment::getStatus, "0");
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        queryWrapper.clear();
        return comments;
    }

    @Override
    public List<Comment> selectCommentsByRootId(int pageNum, int pageSize, Long rootId) {
        queryWrapper.lambda().eq(Comment::getCommentId, rootId).eq(Comment::getStatus, "0");
        Comment rootComment = commentMapper.selectOne(queryWrapper);
        queryWrapper.clear();
        queryWrapper.lambda().eq(Comment::getRootId, rootId).eq(Comment::getStatus, "0");
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        queryWrapper.clear();
        return comments;
    }

    @Override
    public List<Comment> selectCommentToUserByToUserId(int pageNum, int pageSize, Long toUserId) {
        QueryWrapper<Comment> myQueryWrapper = new QueryWrapper<>();
        myQueryWrapper.eq("to_user_id", String.valueOf(toUserId));
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = commentMapper.selectList(myQueryWrapper);
        myQueryWrapper.clear();
        return comments;
    }

    @Override
    public List<Comment> selectCommentToMe(int pageNum, int pageSize) {
        userQueryWrapper.lambda().eq(User::getUserId, StpUtil.getLoginIdAsLong()).eq(User::getStatus, "0");
        User user = userMapper.selectOne(userQueryWrapper);
        userQueryWrapper.clear();
        PageHelper.startPage(pageNum, pageSize);
        queryWrapper.lambda().eq(Comment::getToUserId, StpUtil.getLoginIdAsLong());
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        queryWrapper.clear();
        return comments;
    }

    @Override
    public int insertComment(Comment comment) {
        Article article = articleMapper.selectById(comment.getArticleId());
        User user = userMapper.selectById(StpUtil.getLoginIdAsLong());
        comment.setCommentUsername(user.getUserName());
        comment.setCommentUserNickName(user.getNickName());
        comment.setCommentUserAvatar(user.getAvatar());
        comment.setCreateTime(DateUtils.getNowDate());
        if (comment.getReplyId() != 0) {
            //回复
            comment.setCommentType("2");
            //根据回复来查原来的评论或回复
            QueryWrapper<Comment> myQueryWrapper = new QueryWrapper<>();
            myQueryWrapper.eq("comment_id", comment.getReplyId());
            Comment oldComments = commentMapper.selectOne(myQueryWrapper);
            comment.setToUserId(oldComments.getCommentUserId());
            User toUser = userMapper.selectById(comment.getToUserId());
            comment.setToUsername(toUser.getUserName());
            comment.setToNickname(toUser.getNickName());
            comment.setToUserAvatar(toUser.getAvatar());
            if (oldComments.getRootId() == 0) {
                comment.setRootId(oldComments.getCommentId());
            } else {
                comment.setRootId(oldComments.getRootId());
            }
            myQueryWrapper.clear();
        } else {
            //评论
            comment.setCommentType("1");
            comment.setToUserId(article.getAuthorId());
            User toUser = userMapper.selectById(article.getAuthorId());
            comment.setToUsername(toUser.getUserName());
            comment.setToNickname(toUser.getNickName());
            comment.setToUserAvatar(toUser.getAvatar());
            comment.setRootId(0L);
        }
        int insert = commentMapper.insert(comment);
        return insert;
    }

    @Override
    public int deleteCommentById(Long commentId) {
        Comment dqComment = commentMapper.selectById(commentId);
        Long dqCommentUserId = dqComment.getCommentUserId();
        int i = commentMapper.deleteById(commentId);
        return i;
    }

    @Override
    public List<CommentDO> selectCommentDOs(int pageNum, int pageSize, CommentDO commentDO) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentDO> commentDOList = commentDOMapper.selectAllCommentDO(commentDO);
        return commentDOList;
    }
}
