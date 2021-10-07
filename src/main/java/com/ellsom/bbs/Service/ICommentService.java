package com.ellsom.bbs.Service;

import com.ellsom.bbs.Pojo.Dmo.CommentDO;
import com.ellsom.bbs.Pojo.Po.Comment;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ICommentService {
    public List<Comment> selectComment(int pageNum, int pageSize, Comment comment) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    public Comment selectCommentById(Long CommentId);

    public List<Comment> selectCommentByUserId(int pageNum, int pageSize,Long userId);


    public List<Comment> selectCommentByArticleId(int pageNum, int pageSize,Long articleId);

    public List<Comment> selectCommentsByRootId(int pageNum, int pageSize,Long rootId);


    public List<Comment> selectCommentToUserByToUserId(int pageNum, int pageSize,Long toUserId);


    public List<Comment> selectCommentToMe(int pageNum, int pageSize);

    public int insertComment(Comment comment);

    public int deleteCommentById(Long commentId);

    public List<CommentDO> selectCommentDOs(int pageNum, int pageSize, CommentDO commentDO);

}
