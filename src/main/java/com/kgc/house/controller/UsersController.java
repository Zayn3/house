package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.Users;
import com.kgc.house.entity.UsersCondition;
import com.kgc.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class UsersController {
  @Autowired
  private UsersService usersService;
  @RequestMapping("selectAllUsers")
  @ResponseBody
  public Map<String,Object> selectAllUsers(UsersCondition condition){
    PageInfo<Users> pageInfo = usersService.selectByExample(condition);
    Map<String,Object> map=new HashMap<>();
    map.put("rows",pageInfo.getList());
    map.put("total",pageInfo.getTotal());
    return map;
  }
  @RequestMapping("addUser")
  @ResponseBody
  public String addUser(Users record){
    int i = usersService.insertSelective(record);
    return "{\"result\":"+i+"}";
  }
  @RequestMapping("upUser")
  @ResponseBody
  public String upUser(Users record){
    int i = usersService.updateByPrimaryKeySelective(record);
    return "{\"result\":"+i+"}";
  }
  @RequestMapping("delOneUser")
  @ResponseBody
  public String delOneUser(Integer id){
    int i = usersService.deleteByPrimaryKey(id);
    return "{\"result\":"+i+"}";
  }
  /*批量删除*/
  @RequestMapping("deleteMoreUsers")
  @ResponseBody
  public String deleteMoreUsers(String ids){
    String[] idsStr = ids.split(",");
    Integer[] idsInt=new Integer[idsStr.length];
    for (int i=0;i<idsStr.length;i++){
      idsInt[i]=Integer.parseInt(idsStr[i]);
    }
    int i = usersService.deleteMoreUsers(idsInt);
    return "{\"result\":"+i+"}";
  }
}
