package com.kgc.house.mapper;

import com.kgc.house.entity.Type;
import com.kgc.house.entity.TypeExample;
import java.util.List;
import java.util.Map;

public interface TypeMapper {
    int deleteMoreType(Integer[] ids);
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(TypeExample example);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);


}