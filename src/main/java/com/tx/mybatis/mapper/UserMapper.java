package com.tx.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tx.user.dao.User;

@Mapper
public interface UserMapper {
    
    boolean registry(User user);
    
    int userExist(User user);
    
    boolean activeAccount(String activecode);
    
    int userPasswordVarify(String userid,String password);
    
    boolean setUserOnline(String userid,String logintime,String token);
    
    String getUseridByToken(String token);
    
    User getUserByUserid(String userid);
}
