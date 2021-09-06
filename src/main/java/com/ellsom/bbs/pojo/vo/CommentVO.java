package com.ellsom.bbs.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {
    /** 评论的内容 **/
    @NonNull
    private String content;

    /** 文章的ID **/
    @NonNull
    private Long articleId;

    /** 回复的评论的ID **/
    @NonNull
    private Long replyId;


}
