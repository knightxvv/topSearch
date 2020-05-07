package com.tx.log.dao;

public class log {
    int id;
    String time;
    String website;
    int count;
    
    public log(String time,String website,int count) {
        this.time=time;
        this.website=website;
        this.count=count;
    }
}
