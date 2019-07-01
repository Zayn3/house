package com.kgc.house.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Users;
import com.kgc.house.entity.UsersCondition;
import com.kgc.house.entity.UsersExample;
import com.kgc.house.mapper.UsersMapper;
import com.kgc.house.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
  @Autowired
  private UsersMapper usersMapper;
//批量删除
  @Override
  public int deleteMoreUsers(Integer[] ids) {
    return usersMapper.deleteMoreUsers(ids);
  }

  @Override
  public int deleteByPrimaryKey(Integer id) {
    return usersMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int insert(Users record) {
    return 0;
  }

  @Override
  public int insertSelective(Users record) {
    return usersMapper.insertSelective(record);
  }

  @Override
  public PageInfo<Users> selectByExample(UsersCondition condition) {
    PageHelper.startPage(condition.getPage(),condition.getRows());
    UsersExample usersExample=new UsersExample();
    UsersExample.Criteria criteria = usersExample.createCriteria();
    if (condition.getTelephone()!=null){
      criteria.andTelephoneLike("%"+condition.getTelephone()+"%");
    }
    if (condition.getUsername()!=null){
      criteria.andNameLike("%"+condition.getUsername()+"%");
    }
    List<Users> users = usersMapper.selectByExample(usersExample);
    PageInfo<Users> pageInfo=new PageInfo<>(users);
    return pageInfo;
  }

  @Override
  public Users selectByPrimaryKey(Integer id) {
    return null;
  }

  @Override
  public int updateByPrimaryKeySelective(Users record) {
    return usersMapper.updateByPrimaryKeySelective(record);
  }

  @Override
  public int updateByPrimaryKey(Users record) {
    return 0;
  }

  @Override
  public int checkName(String username) {
    UsersExample usersExample=new UsersExample();
    UsersExample.Criteria criteria = usersExample.createCriteria();
    criteria.andNameEqualTo(username);
    List<Users> users = usersMapper.selectByExample(usersExample);
    return users.size()==0?0:1;
  }
  //注册用户
  @Override
  public int regUser(Users user) {
    user.setIsadmin(0);
    user.setPassword(MD5Utils.md5Encrypt(user.getPassword()));
    return usersMapper.insertSelective(user);
  }
  //登陆用户

  @Override
  public Users loginUser(String name, String password) {
    UsersExample usersExample=new UsersExample();
    UsersExample.Criteria criteria = usersExample.createCriteria();
    criteria.andNameEqualTo(name);
    criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
    List<Users> users = usersMapper.selectByExample(usersExample);
    if (users.size()>0){
      return users.get(0);
    }
    return null;
  }
}
