package com.tx.website.dao;

import com.alibaba.fastjson.JSONObject;

public class website {
    public int id;
    public String website;
    public String url;
    public String encoding;
    public String name;
    
    public website() {

    }
    
    public website(String website,String url,String encoding,String name) {
        this.website=website;
        this.url=url;
        this.encoding=encoding;
        this.name=name;
    }
    
    public String toString() {
        JSONObject json=new JSONObject();
        json.put("id", id);
        json.put("website", website);
        json.put("url", url);
        json.put("encoding", encoding);
        return json.toString();
    }
}
