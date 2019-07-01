package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.Street;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class StreetController {
  @Autowired
  private StreetService streetService;
  /*查询全部*/
  @RequestMapping("selectStreetPage")
  @ResponseBody
  public Map<String,Object> selectStreetPage(Integer page, Integer rows,Integer did){
    PageInfo<Street> pageInfo = streetService.selectStreetPage(page,rows,did);
    Map<String,Object> map=new HashMap<>();
    map.put("total",pageInfo.getTotal());
    map.put("rows",pageInfo.getList());
    return map;
  }
  @RequestMapping("addStreet")
  @ResponseBody
  public String addStreet(Street record){
    int i = streetService.insertSelective(record);
    return "{\"result\":"+i+"}";
  }
  @RequestMapping("upStreet")
  @ResponseBody
  public String upStreet(Street record){
    int i = streetService.updateByPrimaryKeySelective(record);
    return "{\"result\":"+i+"}";
  }
  @RequestMapping("delOneStreet")
  @ResponseBody
  public String delOneStreet(Integer id){
    int i = streetService.deleteByPrimaryKey(id);
    return "{\"result\":"+i+"}";
  }
  /*批量删除*/
  @RequestMapping("deleteMoreStreet")
  @ResponseBody
  public String deleteMoreStreet(String ids){
    String[] idsStr = ids.split(",");
    Integer[] idsInt=new Integer[idsStr.length];
    for (int i=0;i<idsStr.length;i++){
      idsInt[i]=Integer.parseInt(idsStr[i]);
    }
    int i = streetService.deleteMoreStreet(idsInt);
    return "{\"result\":"+i+"}";
  }
}
