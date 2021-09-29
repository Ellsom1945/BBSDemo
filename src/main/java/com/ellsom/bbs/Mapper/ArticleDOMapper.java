package com.ellsom.bbs.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ellsom.bbs.pojo.domaino.ArticleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleDOMapper extends BaseMapper<ArticleDO> {
    public List<ArticleDO> selectAllArticleDO(@Param("articleDO") ArticleDO articleDO);

}
