package com.ellsom.bbs.Service.Impl;

import com.ellsom.bbs.Mapper.UserMapper;
import com.ellsom.bbs.Service.IlikeService;
import com.ellsom.bbs.pojo.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IlikeServiceImpl implements IlikeService {
   @Autowired
    private UserMapper userMapper;
    @Override
    public int giveALike(Long articleId) {
        return 0;
    }

    @Override
    public int unlike(Long articleId) {
        return 0;
    }

    @Override
    public int checkStatusForArticle(Long articleId) {
        return 0;
    }

    @Override
    public int getLikeNumberInArticle(Long articleId) {
        return 0;
    }

    @Override
    public List<User> likedDqUserInfoInArticle(int pageNum, int pageSize, Long articleId) {
        return null;
    }
}
