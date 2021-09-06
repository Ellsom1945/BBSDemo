package com.ellsom.bbs.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ellsom.bbs.Mapper.TagMapper;
import com.ellsom.bbs.Service.ITagService;
import com.ellsom.bbs.pojo.po.Tag;
import com.ellsom.bbs.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITagServiceImpl implements ITagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> selectTags(int pageNum, int pageSize, Tag tag) {
        LambdaQueryWrapper<Tag> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotNull(tag.getTagId()) && StringUtils.isNotEmpty(tag.getTagId().toString()), Tag::getTagId, tag.getTagId())
                .like(StringUtils.isNotNull(tag.getTagName()) && StringUtils.isNotEmpty(tag.getTagName()), Tag::getTagName, tag.getTagName());
        PageHelper.startPage(pageNum, pageSize);
        List<Tag> dqTags = tagMapper.selectList(queryWrapper);
        queryWrapper.clear();
        return dqTags;

    }

    @Override
    public Tag selectTagById(Long TagId) {
       Tag dqTag = tagMapper.selectById(TagId);
        return dqTag;
    }

    @Override
    public Tag selectTagByTagName(String TagName) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getTagName, TagName);
        Tag dqTag = tagMapper.selectOne(queryWrapper);
        queryWrapper.clear();

        return dqTag;
    }
}
