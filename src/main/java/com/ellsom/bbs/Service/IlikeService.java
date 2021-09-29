package com.ellsom.bbs.Service;

import com.ellsom.bbs.pojo.po.User;

import java.util.List;

public interface IlikeService  {
    public int giveALike(Long articleId);


    public int unlike(Long articleId);


    public int checkStatusForArticle(Long articleId);


    public int getLikeNumberInArticle(Long articleId);


    public List<User> likedDqUserInfoInArticle(int pageNum, int pageSize, Long articleId);

}
