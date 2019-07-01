package com.kgc.house.pageController;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.*;
import com.kgc.house.service.DistrictService;
import com.kgc.house.service.HouseService;
import com.kgc.house.service.StreetService;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/page/")
public class PageHouseController {
  @Autowired
  private TypeService typeService;
  @Autowired
  private DistrictService districtService;
  @Autowired
  private StreetService streetService;
  @Autowired
  private HouseService houseService;
//查询类型区域,下拉列表
  @RequestMapping("goFabu")
  public String goFabu(Model model,HttpSession session){
    List<Type> types = typeService.selectAllType();
    List<District> districts = districtService.selectAllDistrict();
    model.addAttribute("types",types);
    model.addAttribute("districts",districts);
    return "fabu";
  }
//街道下拉列表
  @RequestMapping("selectStreetBydid")
  @ResponseBody
  public List<Street> selectStreetBydid(Integer did){
    List<Street> streets = streetService.selectStreetByDid(did);
    return streets;
  }
//添加发布的房屋
@RequestMapping("addHouse")
public String addHouse(House house, @RequestParam(value = "picFile",required = false) CommonsMultipartFile picFile, HttpSession session){
  //文件上传
  //1、取原名
  String filename = picFile.getOriginalFilename();
  //2、取后缀
  String lastname = filename.substring(filename.lastIndexOf("."));
  //3、新文件名
  String savename = System.currentTimeMillis() + lastname;
  //4、保存文件
  File pfile=new File("H:\\images\\"+savename);
   try{ picFile.transferTo(pfile);
  //房屋编号、UsersID、ispass、isdel、path
  house.setId(System.currentTimeMillis()+"");
  house.setUserId(((Users)session.getAttribute("user")).getId());
  house.setIspass(0);
  house.setIsdel(0);
  house.setPath(savename);
  if (houseService.addHouse(house)>0){
    //session.setAttribute("fabuInfo","发布成功");
    return "redirect:selectPageHouseByUsersId";
  }else {
    //失败删除文件
    pfile.delete();
    //session.setAttribute("fabuInfo","发布失败");
    return "redirect:selectPageHouseByUsersId";
  }
 }catch (Exception e){
   pfile.delete();
   //session.setAttribute("fabuInfo","发布失败");
   return "redirect:selectPageHouseByUsersId";
 }
}
//管理页面分页查询用户发布房屋
  @RequestMapping("selectPageHouseByUsersId")
  public String selectPageHouseByUsersId(Integer page,HttpSession session,Model model){
    page=page==null?1:page;
    PageInfo<House> pageInfo = houseService.selectPageHouseByUsersId(page, ((Users) session.getAttribute("user")).getId());
    model.addAttribute("pageInfo",pageInfo);
    return "guanli";
  }

//查询单条房屋+所在区域
@RequestMapping("selectOneHouseAndDistrict")
  public String selectOneHouseAndDistrict(String id,Model model){
  House house = houseService.selectOneHouseAndDistrict(id);
  List<Type> types = typeService.selectAllType();
  List<District> districts = districtService.selectAllDistrict();
  model.addAttribute("house",house);
  model.addAttribute("types",types);
  model.addAttribute("districts",districts);
  return "upHouse";
  }
//修改发布房屋
@RequestMapping("upHouse")
  public String upHouse(House house,String oldFile,@RequestParam(value = "picFile",required = false) CommonsMultipartFile picFile) throws IOException {
   File file=null;
    if (picFile.getOriginalFilename().equals("")){
     //没有上传图片
     //System.out.println(house.getPath());null,path属性不需要管
   }else {
     //上传图片
     //覆盖原图片
      file=new File("H:\\images\\"+oldFile);
      picFile.transferTo(file);
   }
  int i = houseService.upHouse(house);
    //上传失败删除图片
   if (i<=0 && file!=null){
     file.delete();
   }
  return "redirect:selectPageHouseByUsersId";
  }
//逻辑删除房屋
 @RequestMapping("delHouse")
 public String delHouse(String id){
    houseService.upIsdel(id);
    return "redirect:selectPageHouseByUsersId";
 }
//用户展示页面+查询所有区域,户型
 @RequestMapping("goList")
 public String goList(HouseCondition houseCondition,Model model){
   houseCondition.setPage(houseCondition.getPage()==null?1:houseCondition.getPage());
   PageInfo<House> pageInfo = houseService.selectHouseToUsers(houseCondition);
   model.addAttribute("pageInfo",pageInfo);

   List<Type> types = typeService.selectAllType();
   List<District> districts = districtService.selectAllDistrict();
   model.addAttribute("types",types);
   model.addAttribute("districts",districts);
   //回显
   model.addAttribute("houseCondition",houseCondition);
   return "list";
 }
//单条房屋
  @RequestMapping("selectOneHouse")
  public String selectOneHouse(String id,Model model){
    House house = houseService.selectOneHouse(id);
    model.addAttribute("house",house);
    return "details";
  }
}
