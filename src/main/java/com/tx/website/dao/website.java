package com.tx.website.dao;

import com.alibaba.fastjson.JSONObject;

public class website {
    public int id;
    public String website;
    public String url;
    
    public website(String website,String url) {
        this.website=website;
        this.url=url;
    }
    
    public String toString() {
        JSONObject json=new JSONObject();
        json.put("id", id);
        json.put("website", website);
        json.put("url", url);
        return json.toString();
    }
}
