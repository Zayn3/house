package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.entity.StreetExample;

import java.util.List;

public interface StreetService {
  int deleteMoreStreet(Integer[] ids);
  int deleteByDistrict(Integer districtId);
  int deleteMoreStreetByDistrict(Integer[] ids);
  //----------------------------------
  int deleteByPrimaryKey(Integer id);

  int insert(Street record);

  int insertSelective(Street record);
  List<Street> selectStreetByDid(Integer did);
  PageInfo<Street> selectStreetPage(Integer page,Integer rows,Integer did);

  Street selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(Street record);

  int updateByPrimaryKey(Street record);
}
