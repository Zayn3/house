package com.kgc.house.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.DistrictExample;
import com.kgc.house.mapper.DistrictMapper;
import com.kgc.house.mapper.StreetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
  @Autowired
  private DistrictMapper districtMapper;
  @Autowired
  private StreetMapper streetMapper;
  @Override
  @Transactional
  public int deleteOneDistrictAndStreet(Integer id) {
    try {
      districtMapper.deleteByPrimaryKey(id);
      streetMapper.deleteByDistrict(id);
      return 1;
    }catch (Exception e){
      return 0;
    }
  }

  @Override
  @Transactional
  public int deleteMoreDistrict(Integer[] ids) {
    try {
      int i = districtMapper.deleteMoreDistrict(ids);
      streetMapper.deleteMoreStreetByDistrict(ids);
      return i;
    }catch (Exception e){
      return 0;
    }
  }

  @Override
  public int deleteByPrimaryKey(Integer id) {
    return districtMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int insert(District record) {
    return 0;
  }

  @Override
  public int insertSelective(District record) {
    return districtMapper.insertSelective(record);
  }

  @Override
  public PageInfo<District> selectByExample(Integer page, Integer rows) {
    PageHelper.startPage(page,rows);
    DistrictExample example=new DistrictExample();
    /*DistrictExample.Criteria criteria = example.createCriteria();
    criteria.andNameLike("%东%");*/
    List<District> districts = districtMapper.selectByExample(example);
    PageInfo<District> pageInfo=new PageInfo<>(districts);
    return pageInfo;
  }

  @Override
  public District selectByPrimaryKey(Integer id) {
    return null;
  }

  @Override
  public int updateByPrimaryKeySelective(District record) {
    return districtMapper.updateByPrimaryKeySelective(record);
  }

  @Override
  public int updateByPrimaryKey(District record) {
    return 0;
  }

  @Override
  public List<District> selectAllDistrict() {
    DistrictExample districtExample=new DistrictExample();
    List<District> districts = districtMapper.selectByExample(districtExample);
    return districts;
  }
  /*@Override
  public PageInfo<District> selectAllDistrict(Integer page,Integer rows) {
    PageHelper.startPage(page,rows);
    List<District> districts = districtMapper.selectAllDistrict();
    PageInfo<District> pageInfo=new PageInfo<>(districts);
    return pageInfo;
  }*/
}
