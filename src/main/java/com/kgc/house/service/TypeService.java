package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.entity.TypeExample;

import java.util.List;

public interface TypeService {
  int deleteMoreType(Integer[] ids);
  int deleteByPrimaryKey(Integer id);

  int insert(Type record);

  int insertSelective(Type record);
  List<Type> selectAllType();
  PageInfo<Type> selectTypePage(Integer page, Integer rows);

  Type selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(Type record);

  int updateByPrimaryKey(Type record);
}
