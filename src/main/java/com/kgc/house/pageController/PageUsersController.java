package com.kgc.house.pageController;

import com.kgc.house.entity.Users;
import com.kgc.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/")
public class PageUsersController {
@Autowired
  private UsersService usersService;
//检测用户名是否可用
@RequestMapping("checkName")
@ResponseBody
  public String checkName(String username){
  if (username.trim().equals("")){
    return "{\"result\":1}";
  }else {
  int i = usersService.checkName(username);
  return "{\"result\":"+i+"}";}
  }
//注册用户(加密)
@RequestMapping("regUser")
public String regUser(Users user){
  int i = usersService.regUser(user);
  if (i>0)
    return "login";
  else
    return "error";
  }
//登陆
@RequestMapping("loginUser")
public String loginUser(String name, String password, HttpSession httpSession,Model model){
  Users user = usersService.loginUser(name, password);
   if (user==null){
     model.addAttribute("info","用户名密码错误");
     return "login";
   }else{
     httpSession.setAttribute("user",user);
     httpSession.setMaxInactiveInterval(600);
     return "redirect:selectPageHouseByUsersId";
   }
  }
}
