package com.ellsom.bbs.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ellsom.bbs.pojo.domaino.CommentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CommentDOMapper extends BaseMapper<CommentDO> {
    public List<CommentDO> selectAllCommentDO(@Param("commentDO") CommentDO commentDO);
}
