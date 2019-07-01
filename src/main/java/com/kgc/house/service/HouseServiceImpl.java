package com.kgc.house.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.*;
import com.kgc.house.mapper.DistrictMapper;
import com.kgc.house.mapper.HouseMapper;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
  @Autowired
  private HouseMapper houseMapper;
  @Autowired
  private StreetMapper streetMapper;
  @Autowired
  private DistrictMapper districtMapper;
  @Override
  public int addHouse(House house) {
    return  houseMapper.insertSelective(house);
  }
//管理房屋展示所有房屋信息
  @Override
  public PageInfo<House> selectPageHouseByUsersId(Integer page, Integer uid) {
    PageHelper.startPage(page,5);
    List<House> houses = houseMapper.selectPageHouseByUsersId(uid);
    PageInfo<House> pageInfo=new PageInfo<>(houses);
    return pageInfo;
  }
//发布页面区域回显房屋+区域
  @Override
  public House selectOneHouseAndDistrict(String id) {
    House house=houseMapper.selectByPrimaryKey(id);
    Street street = streetMapper.selectByPrimaryKey(house.getStreetId());
    District district = districtMapper.selectByPrimaryKey(street.getDistrictId());
    house.setDname(district.getName());
    return house;
  }
  @Override
  public int upHouse(House house) {
    return  houseMapper.updateByPrimaryKeySelective(house);
  }

//逻辑删除房屋
  @Override
  public int upIsdel(String id) {
    House house=new House();
    house.setId(id);
    house.setIsdel(1);
    return houseMapper.updateByPrimaryKeySelective(house);
  }

//后台查询未审核的房屋
  @Override
  public PageInfo<House> selectAllIspassHouse(Integer page, Integer rows,Integer ispass) {
    PageHelper.startPage(page,rows);
    List<House> houses = houseMapper.selectAllHouseByIspass(ispass);
    PageInfo<House> pageInfo=new PageInfo<>(houses);
    return pageInfo;
  }
//审核操作
  @Override
  public int optHouseIspass(String id,Integer ispass) {
    House house=new House();
    house.setId(id);
    house.setIspass(ispass);
    return houseMapper.updateByPrimaryKeySelective(house);
  }
//批量审核
  @Override
  public int optMoreHouseIspass(String[] ids, Integer ispass) {
    return houseMapper.optMoreHouseIspass(ids,ispass);
  }
//展示所有房屋给前端用户
  @Override
  public PageInfo<House> selectHouseToUsers(HouseCondition houseCondition) {
    PageHelper.startPage(houseCondition.getPage(),houseCondition.getRows());
    if (houseCondition.getArea()!=null && !houseCondition.getArea().equals("")){
      String[] split = houseCondition.getArea().split("-");
      houseCondition.setStartArea(split[0]);
      houseCondition.setEndArea(split[1]);
    }
    List<House> houses = houseMapper.selectHouseToUsers(houseCondition);
    PageInfo<House> pageInfo=new PageInfo<>(houses);
    return pageInfo;
  }

  @Override
  public House selectOneHouse(String id) {
    return houseMapper.selectOneHouse(id);
  }
}
