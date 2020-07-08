package com.tx.user.dao;

public class User {
    public String userid;
    public String username;
    public String password;
    public String email;
    public String active;
    public String activecode;
    
    public User() {

    }
    public User(String userid,String username,String password,String email) {
        this.userid=userid;
        this.username=username;
        this.password=password;
        this.email=email;
    }
    
    public User(String userid,String username,String password,String email,String active,String activecode) {
        this.userid=userid;
        this.username=username;
        this.password=password;
        this.email=email;
        this.active=active;
        this.activecode=activecode;
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
