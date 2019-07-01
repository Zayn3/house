package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.DistrictExample;
import com.kgc.house.mapper.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DistrictService {
  //PageInfo<District> selectAllDistrict(Integer page,Integer rows);
  int deleteOneDistrictAndStreet(Integer id);
  int deleteMoreDistrict(Integer[] ids);
  //------------------------------------
  int deleteByPrimaryKey(Integer id);

  int insert(District record);

  int insertSelective(District record);
  List<District> selectAllDistrict();
  PageInfo<District> selectByExample(Integer page,Integer rows);

  District selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(District record);

  int updateByPrimaryKey(District record);
}
