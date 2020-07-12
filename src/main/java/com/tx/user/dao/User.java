package com.tx.user.dao;

import java.io.Serializable;

public class User implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 7250099772363889688L;
    public String userid;
    public String username;
    public String password;
    public String email;
    public String active;
    public String activecode;
    public String createtime;
    
    public User() {

    }
    public User(String userid,String username) {
        this.userid=userid;
        this.username=username;
    }
    
    public User(String userid,String username,String password,String email) {
        this.userid=userid;
        this.username=username;
        this.password=password;
        this.email=email;
    }
    
    public User(String userid,String username,String password,String email,String active,String activecode,String createtime) {
        this.userid=userid;
        this.username=username;
        this.password=password;
        this.email=email;
        this.active=active;
        this.activecode=activecode;
        this.createtime=createtime;
    }
    
    public String getuserid() {
        return this.userid;
    }
    public String getusername() {
        return this.username;
    }
    public String getpassword() {
        return this.password;
    }
    public String getemail() {
        return this.email;
    }
}
