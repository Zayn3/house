package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller  //@RestController controller+responseBody
@RequestMapping("/admin/")
public class DistrictController {
  @Autowired
  private DistrictService districtService;
  /*查询全部*/
  @RequestMapping("selectAllDistrict")
  @ResponseBody
  public Map<String,Object> selectAllDistrict(Integer page,Integer rows){
    PageInfo<District> pageInfo = districtService.selectByExample(page, rows);
    Map<String,Object> map=new HashMap<>();
    map.put("total",pageInfo.getTotal());
    map.put("rows",pageInfo.getList());
    return map;
  }
  @RequestMapping("addDistrict")
  @ResponseBody
  public String addDistrict(District record){
    int i = districtService.insertSelective(record);
    return "{\"result\":"+i+"}";
  }
  @RequestMapping("upDistrict")
  @ResponseBody
  public String upDistrict(District record){
    int i = districtService.updateByPrimaryKeySelective(record);
    return "{\"result\":"+i+"}";
  }
  @RequestMapping("delOneDistrict")
  @ResponseBody
  public String delOneDistrict(Integer id){
    int i = districtService.deleteOneDistrictAndStreet(id);
    return "{\"result\":"+i+"}";
  }
  /*批量删除*/
  @RequestMapping("deleteMoreDistrict")
  @ResponseBody
  public String deleteMoreDistrict(String ids){
    String[] idsStr = ids.split(",");
    Integer[] idsInt=new Integer[idsStr.length];
    for (int i=0;i<idsStr.length;i++){
      idsInt[i]=Integer.parseInt(idsStr[i]);
    }
    int i = districtService.deleteMoreDistrict(idsInt);
    return "{\"result\":"+i+"}";
  }
}
