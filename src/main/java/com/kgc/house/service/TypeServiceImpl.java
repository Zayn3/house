package com.kgc.house.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.entity.TypeExample;
import com.kgc.house.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
  @Autowired
  private TypeMapper typeMapper;

  @Override
  public int deleteByPrimaryKey(Integer id) {
    return typeMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int insert(Type record) {
    return 0;
  }

  @Override
  public int insertSelective(Type record) {
    return typeMapper.insertSelective(record);
  }

  @Override
  public PageInfo<Type> selectTypePage(Integer page, Integer rows) {
    PageHelper.startPage(page,rows);
    TypeExample typeExample=new TypeExample();
    //TypeExample.Criteria criteria = typeExample.createCriteria();
    List<Type> types = typeMapper.selectByExample(typeExample);
    PageInfo<Type> pageInfo=new PageInfo<>(types);
    return pageInfo;
  }

  @Override
  public Type selectByPrimaryKey(Integer id) {
    return null;
  }

  @Override
  public int updateByPrimaryKeySelective(Type record) {
    return typeMapper.updateByPrimaryKeySelective(record);
  }

  @Override
  public int updateByPrimaryKey(Type record) {
    return 0;
  }

  @Override
  public int deleteMoreType(Integer[] ids) {
  return typeMapper.deleteMoreType(ids);
  }

  @Override
  public List<Type> selectAllType() {
    TypeExample typeExample2=new TypeExample();
    List<Type> types = typeMapper.selectByExample(typeExample2);
    return types;
  }
}
