package com.tx.website.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tx.hotsearch.dao.hotSearch;

@Service
public class douyin {
    public static String website="douyin";
    
    public List<hotSearch> parse(String time,String html) { 
        List<hotSearch> hotSearchList=new LinkedList<>();
        
        JSONObject jsons=JSONObject.parseObject(html);
        JSONArray elements=jsons.getJSONObject("data").getJSONArray("word_list");
        
        for (int i=0;i<elements.size();i++) {
            String title=elements.getJSONObject(i).getString("word");
            String content="";
            int score=Integer.valueOf(elements.getJSONObject(i).getString("hot_value"));

            hotSearch hs=new hotSearch(time,title,content,score,i+1,0,website);
            hotSearchList.add(hs);
        }
        return hotSearchList;
    }
}
