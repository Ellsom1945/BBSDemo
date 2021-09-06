package com.ellsom.bbs.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ellsom.bbs.pojo.domaino.ArticleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleDOMapper extends BaseMapper<ArticleDO> {
    @Select(value = "select * ," +
            "(select count(*) from " +
            "comment where article_id = " +
            "article.article_id) as " +
            "comment_num from article inner " +
            "join user du on article.author_id" +
            " = du.user_id inner join type dt on " +
            "article.type_id = dt.type_id where " +
            "article.status=0 and dt.status=0 and " +
            "du.status=0;")
    public List<ArticleDO> selectAllArticleDO(@Param("articleVO") ArticleDO articleDO);

}
