package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class HouseController {
  @Autowired
  private HouseService houseService;

//后台查询房屋是否审核
  @RequestMapping("selectAllIspassHouse")
  @ResponseBody
  public Map<String,Object> selectAllIspassHouse(Integer page, Integer rows, Integer ispass){
    PageInfo<House> pageInfo = houseService.selectAllIspassHouse(page, rows, ispass);
    Map<String,Object> map=new HashMap<>();
    map.put("total",pageInfo.getTotal());
    map.put("rows",pageInfo.getList());
    return map;
  }
//房屋审核操作
  @RequestMapping("optHouseIspass")
  @ResponseBody
  public String optHouseIspass(String id,Integer ispass){
    int i = houseService.optHouseIspass(id,ispass);
    return "{\"result\":"+i+"}";
  }
//房屋批量审核操作
@RequestMapping("optMoreHouseIspass")
@ResponseBody
public String optMoreHouseIspass(String[] ids,Integer ispass){
  int i = houseService.optMoreHouseIspass(ids,ispass);
  return "{\"result\":"+i+"}";
}
}
