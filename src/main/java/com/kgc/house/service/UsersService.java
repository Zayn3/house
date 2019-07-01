package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Users;
import com.kgc.house.entity.UsersCondition;
import com.kgc.house.entity.UsersExample;

import java.util.List;

public interface UsersService {
  int deleteMoreUsers(Integer[] ids);
  int deleteByPrimaryKey(Integer id);

  int insert(Users record);
  int regUser(Users user);
  Users loginUser(String name,String password);
  int insertSelective(Users record);

  PageInfo<Users> selectByExample(UsersCondition condition);

  Users selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(Users record);

  int updateByPrimaryKey(Users record);

  int checkName(String username);
}
