package com.tx.user.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tx.mybatis.mapper.UserMapper;
import com.tx.user.dao.User;

import com.tx.utils.*;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    MyMailUtil MyMailUtil;
    @Resource
    MyRedisUtil MyRedisUtil;
    
    //注册账户，但未激活
    public Map<String, String> registry(User user) {
        HashMap<String, String> map=new HashMap<String,String>();
        try {
            int userCount=userMapper.userExist(user);
            if(userCount>0) {
                map.put("code","0");
                map.put("desc","用户已存在");
                return map;
            }else {
                userMapper.registry(user);
                MyMailUtil.sendActiveMail(user);
                map.put("code","1");
                map.put("desc","用户注册成功，前往邮箱进行激活");
                return map;
            }
        }catch(Exception e) {
            e.printStackTrace();
            map.put("code","0");
            map.put("desc","用户注册失败："+e.getMessage());
            return map;
        }
    }
    //激活账户
    public boolean activeAccount(String activecode) {
        return userMapper.activeAccount(activecode);
    }
    
    //登录账户
    public HashMap<String, String> login(String userid,String password,HttpServletRequest request,HttpServletResponse response) {
//        HttpSession session = request.getSession();
        HashMap<String,String> map=new HashMap<>();
        if(userMapper.userPasswordVarify(userid, password)>0) {
            try {
            Date day=new Date();    
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
            String logintime=df.format(day);
            String token=UUID.randomUUID().toString().replaceAll("-", "");
            //mysql
            setUserOnline(userid,logintime,token);
            //redis
            setUserOnlineInRedis(userid,token);
            Cookie cookie = new Cookie("token",token);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.addHeader("token",token);
            map.put("code","1");
            map.put("msg","登录成功");
            }catch(Exception e) {
                e.printStackTrace();
            }
        }else {
            map.put("code","0");
            map.put("msg","用户名或密码错误");
        }
        return map;
                
    }
    
    //mysql中获取当前用户
    public User getCurrentUser(HttpServletRequest request) {
        String cookie=request.getHeader("Cookie");
        User user=null;
        if(cookie!=null && !cookie.equals("") && cookie.indexOf("token")>=0) {
            String token=request.getHeader("Cookie").split("=")[1];
            String userid=userMapper.getUseridByToken(token);
            if(!"".equals(userid)) {
                user=userMapper.getUserByUserid(userid);
                user.password="";
            }
        }
        return user;
    }
    
    //mysql中存放token和userid
    public void setUserOnline(String userid,String logintime,String token) {
        userMapper.setUserOnline(userid,logintime,token);
    }
    
    //redis中存放token
    public void setUserOnlineInRedis(String userid,String token) {
        HashMap<String,Object> map=new HashMap<>();
        map.put(token,userid);
        MyRedisUtil.hmset("user_online",map);
    }
    
    //redis中存放token
    public void setUserOnlineInRedis(User user,HttpServletRequest request) {
        String userid=user.userid;
        String token="";
        String cookie=request.getHeader("Cookie");
        if(cookie!=null && !cookie.equals("") && cookie.indexOf("token")>=0) {
            token=request.getHeader("Cookie").split("=")[1];
        }
        HashMap<String,Object> map=new HashMap<>();
        map.put(token,userid);
        MyRedisUtil.hmset("user_online",map);
    }
    
    //redis中获取当前用户
    public User getCurrentUserFromRedis(HttpServletRequest request) {
        String cookie=request.getHeader("Cookie");
        User user=null;
        if(cookie!=null && !cookie.equals("") && cookie.indexOf("token")>=0) {
            String token=request.getHeader("Cookie").split("=")[1];
            String userid=(String)MyRedisUtil.hmget("user_online").get(token);
            if(userid!=null && !"".equals(userid)) {
                user=userMapper.getUserByUserid(userid);
                user.password="";
            }
        }
        return user;
    }
}
