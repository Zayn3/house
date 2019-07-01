package com.kgc.house.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.entity.StreetExample;
import com.kgc.house.mapper.StreetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
  @Autowired
  private StreetMapper streetMapper;

  @Override
  public int deleteByDistrict(Integer districtId) {
    return 0;
  }

  @Override
  public int deleteMoreStreetByDistrict(Integer[] ids) {
    return 0;
  }

  @Override
  public int deleteByPrimaryKey(Integer id) {
    return streetMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int insert(Street record) {
    return 0;
  }

  @Override
  public int insertSelective(Street record) {
    return streetMapper.insertSelective(record);
  }

  @Override
  public PageInfo<Street> selectStreetPage(Integer page, Integer rows, Integer did) {
    PageHelper.startPage(page,rows);
    StreetExample streetExample=new StreetExample();
    StreetExample.Criteria criteria = streetExample.createCriteria();
    if (did !=null){
      criteria.andDistrictIdEqualTo(did);
    }
    List<Street> streets = streetMapper.selectByExample(streetExample);
    return new PageInfo<Street>(streets);
  }

  @Override
  public Street selectByPrimaryKey(Integer id) {
    return streetMapper.selectByPrimaryKey(id);
  }

  @Override
  public int updateByPrimaryKeySelective(Street record) {
    return streetMapper.updateByPrimaryKeySelective(record);
  }

  @Override
  public int updateByPrimaryKey(Street record) {
    return 0;
  }

  @Override
  public int deleteMoreStreet(Integer[] ids) {
    return streetMapper.deleteMoreStreet(ids);
  }

  @Override
  public List<Street> selectStreetByDid(Integer did) {
    StreetExample streetExample=new StreetExample();
    StreetExample.Criteria criteria = streetExample.createCriteria();
    criteria.andDistrictIdEqualTo(did);
    List<Street> streets = streetMapper.selectByExample(streetExample);
    return streets;
  }
}
