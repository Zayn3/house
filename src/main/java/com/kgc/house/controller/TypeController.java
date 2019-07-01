package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.Type;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class TypeController {
  @Autowired
  private TypeService typeService;
  @RequestMapping("selectTypePage")
  @ResponseBody
  public Map<String,Object> selectTypePage(Integer page,Integer rows){
    PageInfo<Type> pageInfo = typeService.selectTypePage(page, rows);
    Map<String,Object> map=new HashMap<>();
    map.put("rows",pageInfo.getList());
    map.put("total",pageInfo.getTotal());
    return map;
  }
  @RequestMapping("addType")
  @ResponseBody
  public String addType(Type record){
    int i = typeService.insertSelective(record);
    return "{\"result\":"+i+"}";
  }
  @RequestMapping("upType")
  @ResponseBody
  public String upType(Type record){
    int i = typeService.updateByPrimaryKeySelective(record);
    return "{\"result\":"+i+"}";
  }
  @RequestMapping("delOneType")
  @ResponseBody
  public String delOneType(Integer id){
    int i = typeService.deleteByPrimaryKey(id);
    return "{\"result\":"+i+"}";
  }
  /*批量删除*/
  @RequestMapping("deleteMoreType")
  @ResponseBody
  public String deleteMoreType(String ids){
    String[] idsStr = ids.split(",");
    Integer[] idsInt=new Integer[idsStr.length];
    for (int i=0;i<idsStr.length;i++){
      idsInt[i]=Integer.parseInt(idsStr[i]);
    }
    int i = typeService.deleteMoreType(idsInt);
    return "{\"result\":"+i+"}";
  }
}
