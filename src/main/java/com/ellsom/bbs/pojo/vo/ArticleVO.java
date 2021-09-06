package com.ellsom.bbs.pojo.vo;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVO {

    /**
     * 文章标题
     */
    @NotNull
    private String articleTitle;

    /**
     * 文章内容
     */
    @NotNull
    private String articleContent;

    /**
     * 文章图片
     */
    @NotNull
    private String articleImage;

    /**
     * 类型ID
     */
    @NotNull
    private Long typeId;
}