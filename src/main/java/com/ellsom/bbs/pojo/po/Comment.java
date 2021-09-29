package com.ellsom.bbs.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "comment")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 评论的ID **/
    @TableId(value = "comment_id",type= IdType.AUTO)
    private Long commentId;

    /** 文章的ID **/
    @TableField(value = "article_id")
    private Long articleId;

    /** 评论的内容 **/
    @TableField(value = "content")
    private String content;

    /** 发表评论用户的Id **/
    @TableField(value = "comment_userid")
    private Long commentUserId;

    /** 评论状态(0为正常，1为被封禁) **/
    @TableField(value = "status")
    private String status;

    /** 发表评论用户的昵称 **/
    @TableField(value = "comment_usernickname")
    private String commentUserNickName;

    /** 发表评论的用户的用户名 **/
    @TableField(value = "comment_username")
    private String commentUsername;

    /** 发表评论的用户的头像 **/
    @TableField(value = "comment_user_avatar")
    private String commentUserAvatar;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 评论的类型（1-评论 2-回复） **/
    @TableField(value = "comment_type")
    private String commentType;

    /** 被回复的人的ID **/
    @TableField(value = "to_user_id")
    private Long toUserId;

    /** 被回复的人的用户名 **/
    @TableField(value = "to_username")
    private String toUsername;

    /**  被回复的人的昵称 **/
    @TableField(value = "to_nickname")
    private String toNickname;

    /** 被回复的人的头像 **/
    @TableField(value = "to_user_avatar")
    private String toUserAvatar;

    /** 回复的评论的ID(0则为评论而不是回复) **/
    @TableField(value = "reply_id")
    private Long replyId;

    /**  根评论ID **/
    @TableField(value = "root_id")
    private Long rootId;
}
