package com.tx.user.service;

import java.util.HashMap;
import java.util.Map;

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
}
