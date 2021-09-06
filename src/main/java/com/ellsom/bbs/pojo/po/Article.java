package com.ellsom.bbs.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("article")
public class Article {
    /** 文章ID **/
    @TableId(value = "article_id",type= IdType.AUTO)
    private Long articleId;

    /** 文章标题 **/
    @TableField(value = "article_title")
    private String articleTitle;

    /** 文章内容 **/
    @TableField(value = "article_content")
    private String articleContent;

    /** 文章首图（后续会优化更新）**/
    @TableField(value = "article_image")
    private String articleImage;

    /** 文章状态(0为正常，1为被封禁) **/
    @TableField(value = "status")
    private String status;

    /** 文章类别ID **/
    @TableField(value = "type_id")
    private Long typeId;

    /** 作者ID **/
    @TableField(value = "author_id")
    private Long authorId;

    /** 作者的昵称 **/
    @TableField(value = "author_nickname")
    private String authorNickname;

    /** 作者的用户名 **/
    @TableField(value = "author_username")
    private String authorUsername;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
