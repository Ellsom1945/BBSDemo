package com.ellsom.bbs.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "like_table")
public class Like implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "like_id", type = IdType.AUTO)
    private Long likeId;
    /**
     * 点赞的用户ID
     **/
    @TableField(value = "give_like_user_id")
    private Long giveLikeUserId;
    /**
     * 被点赞的文章ID
     **/
    @TableField(value = "like_article_id")
    private Long likeArticleId;
    /**
     * 点赞状态（0为取消 1为点赞）
     **/
    @TableField(value = "status")
    private String status;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
