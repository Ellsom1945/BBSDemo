package com.ellsom.bbs.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ellsom.bbs.Pojo.Po.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper  extends BaseMapper<Article> {
}
