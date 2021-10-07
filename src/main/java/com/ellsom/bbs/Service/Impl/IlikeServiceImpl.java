package com.ellsom.bbs.Service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ellsom.bbs.Mapper.LikeMapper;
import com.ellsom.bbs.Mapper.UserMapper;
import com.ellsom.bbs.Service.IlikeService;
import com.ellsom.bbs.Pojo.Po.Like;
import com.ellsom.bbs.Pojo.Po.User;
import com.ellsom.bbs.Util.DateUtils;
import com.ellsom.bbs.Util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IlikeServiceImpl implements IlikeService {
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private UserMapper userMapper;
    QueryWrapper<Like> likeQueryWrapper = new QueryWrapper<>();

    @Override
    public int giveALike(Long articleId) {
        Long userId = StpUtil.getLoginIdAsLong();
        likeQueryWrapper.lambda().eq(Like::getLikeArticleId, articleId).eq(Like::getGiveLikeUserId, userId);
        Like check = likeMapper.selectOne(likeQueryWrapper);
        likeQueryWrapper.clear();
        if (StringUtils.isNull(check)) {
            Like like = new Like();
            like.setGiveLikeUserId(userId);
            like.setLikeArticleId(articleId);
            like.setStatus("1");
            like.setCreateTime(DateUtils.getNowDate());
            like.setUpdateTime(DateUtils.getNowDate());
            likeMapper.insert(like);
            return 1;
        }
        return 0;
    }

    @Override
    public int unlike(Long articleId) {
        Long userId = StpUtil.getLoginIdAsLong();
        likeQueryWrapper.lambda().eq(Like::getLikeArticleId, articleId).eq(Like::getGiveLikeUserId, userId);
        Like like = likeMapper.selectOne(likeQueryWrapper);
        likeQueryWrapper.clear();
        if (StringUtils.isNull(like)) {
            return -1;
        }
        likeMapper.deleteById(like.getLikeId());
        return 1;

    }

    @Override
    public int checkStatusForArticle(Long articleId) {
        Long userId = StpUtil.getLoginIdAsLong();
        likeQueryWrapper.lambda().eq(Like::getLikeArticleId, articleId).eq(Like::getGiveLikeUserId, userId);
        Like like = likeMapper.selectOne(likeQueryWrapper);
        likeQueryWrapper.clear();
        if (StringUtils.isNull(like)) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getLikeNumberInArticle(Long articleId) {
        Integer count = likeMapper.selectCount(likeQueryWrapper.lambda().eq(Like::getLikeArticleId, articleId));
        likeQueryWrapper.clear();
        return count;
    }

    @Override
    public List<User> likedDqUserInfoInArticle(int pageNum, int pageSize, Long articleId) {
        List<Like> distinct_like_id = likeMapper.selectList(likeQueryWrapper.select("distinct like_id").lambda().eq(Like::getLikeArticleId,articleId));
        if (distinct_like_id.isEmpty()){
            return null;
        }
        List<Long> list = distinct_like_id.stream().map(Like::getLikeId).collect(Collectors.toList());
        System.out.println(distinct_like_id);
        if (list.isEmpty()){
            return null;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectBatchIds(list);
        return users;
    }
}
