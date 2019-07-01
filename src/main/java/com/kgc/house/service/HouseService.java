package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseCondition;

import java.util.List;

public interface HouseService {
  int addHouse(House house);
  PageInfo<House> selectPageHouseByUsersId(Integer page,Integer uid);
  House selectOneHouseAndDistrict(String id);
  int upHouse(House house);
  int upIsdel(String id);
  PageInfo<House> selectAllIspassHouse(Integer page,Integer rows,Integer ispass);
  int optHouseIspass(String id,Integer ispass);
  int optMoreHouseIspass(String[] ids,Integer ispass);
  PageInfo<House> selectHouseToUsers(HouseCondition houseCondition);
  House selectOneHouse(String id);
}
