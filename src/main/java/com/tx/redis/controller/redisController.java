package com.tx.redis.controller;


import com.alibaba.fastjson.JSONObject;
import com.tx.user.dao.User;
import com.tx.utils.MyRedisUtil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/redis")
@RestController
public class redisController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private MyRedisUtil redisUtil;

    @RequestMapping("set")
    public boolean redisset(){
        User user =new User("1","2","3","4");
        JSONObject json=new JSONObject();
        //return redisUtil.set(key,userEntity,ExpireTime);

        return redisUtil.set("key1",user);
    }

    @RequestMapping("get")
    public Object redisget(){
        return redisUtil.get("key1");
    }

    @RequestMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }
}