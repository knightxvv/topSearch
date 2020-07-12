package com.tx.user.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.user.dao.User;
import com.tx.user.service.UserService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    public UserService userService;
    
    //注册账户
    @RequestMapping(value="/registry",method=RequestMethod.POST,produces="application/json;charset=utf-8")
    public Map<String, String> registry(
            @RequestParam("userid") String userid,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email) {
        String activecode=UUID.randomUUID().toString().replaceAll("-", "");
        Date day=new Date();    
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        User user=new User(userid,username,password,email,"0",activecode,df.format(day));
        return userService.registry(user);
    }
    
    //激活账户
    @RequestMapping(value="/activeAccount",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public void activeAccount(
            @RequestParam("id") String activecode,HttpServletResponse response) {
        userService.activeAccount(activecode);
        
        try {
            response.sendRedirect("/topSearch/");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //登录账户
    @RequestMapping(value="/login",method=RequestMethod.POST,produces="application/json;charset=utf-8")
    public HashMap<String, String> login(
            @RequestParam("userid") String userid,
            @RequestParam("password") String password,
            HttpServletResponse response,HttpServletRequest request) {
        
        return userService.login(userid,password,request,response);
    }
    
    //获取已登录账户
    @RequestMapping(value="/getCurrentUser",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public User getCurrentUser(
            HttpServletResponse response,HttpServletRequest request) {
        User user=userService.getCurrentUserFromRedis(request);
        if(user==null) {
            user=userService.getCurrentUser(request);
            //redis中没有而mysql中有，则写入redis
            if(user!=null) {
                userService.setUserOnlineInRedis(user,request);
            }
        }
        return user;
        
    }
}
