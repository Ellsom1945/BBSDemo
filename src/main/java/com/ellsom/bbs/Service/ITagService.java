package com.ellsom.bbs.Service;

import com.ellsom.bbs.pojo.po.Tag;

import java.util.List;

public interface ITagService {
    public List<Tag> selectTags(int pageNum, int pageSize, Tag Tag);

    public Tag selectTagById(Long TagId);

    public Tag selectTagByTagName(String TagName);
}
