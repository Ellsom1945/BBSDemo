package com.ellsom.bbs.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ellsom.bbs.Mapper.TypeDOMapper;
import com.ellsom.bbs.Mapper.TypeMapper;
import com.ellsom.bbs.Service.ITypeService;
import com.ellsom.bbs.pojo.domaino.TypeDO;
import com.ellsom.bbs.pojo.po.Type;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
@Service
public class ITypeServiceImpl implements ITypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private TypeDOMapper typeDOMapper;
    QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
    @Override
    public Type selectTypeById(Long typeId) {
        Type type = typeMapper.selectById(typeId);
        return type;
    }

    @Override
    public Type selectTypeByName(String typeName) {
        QueryWrapper<Type> myQueryWrapper = new QueryWrapper<>();
        myQueryWrapper.eq("type_name",typeName);
        Type dqType = typeMapper.selectOne(myQueryWrapper);
        myQueryWrapper.clear();
        return dqType;
    }

    @Override
    public List<Type> selectAllTypes(int pageNum, int pageSize, Type type) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        List<Type> types = typeMapper.selectList(null);
        return types;
    }

    @Override
    public List<TypeDO> selectAllTypeDOs(int pageNum, int pageSize, TypeDO typeDO) {
        PageHelper.startPage(pageNum, pageSize);
        List<TypeDO> typeDOs = typeDOMapper.selectAllTypeDO(typeDO);
        return typeDOs;
    }
}
