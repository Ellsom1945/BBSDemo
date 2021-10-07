package com.ellsom.bbs.Service;

import com.ellsom.bbs.Pojo.Dmo.TypeDO;
import com.ellsom.bbs.Pojo.Po.Type;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ITypeService {
    public Type selectTypeById(Long typeId);

    public Type selectTypeByName(String typeName);

    public List<Type> selectAllTypes(int pageNum, int pageSize, Type type) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    public List<TypeDO> selectAllTypeDOs(int pageNum, int pageSize, TypeDO typeDO);

}
