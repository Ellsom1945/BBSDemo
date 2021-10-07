package com.ellsom.bbs.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ellsom.bbs.Pojo.Dmo.TypeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TypeDOMapper extends BaseMapper<TypeDO> {
    List<TypeDO> selectAllTypeDO(@Param("typeDO") TypeDO typeDO);
}
