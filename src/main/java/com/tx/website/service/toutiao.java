package com.tx.website.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tx.hotsearch.dao.hotSearch;


@Service
public class toutiao {
//  https://ib.snssdk.com/rogue/aladdin_landingpage/template/aladdin_landingpage/hot_words.html"
    
    public static String website="toutiao";
    
    public List<hotSearch> parse(String time,String html) { 
        List<hotSearch> hotSearchList=new LinkedList<>();

        JSONObject jsons=JSONObject.parseObject(html);
        JSONArray elements=jsons.getJSONArray("data").getJSONObject(0).getJSONArray("words");
        
        for (int i=0;i<elements.size();i++) {
            String title=elements.getJSONObject(i).getString("word");
            String content="";
            int score=Integer.valueOf(elements.getJSONObject(i).getJSONObject("params").getString("fake_click_cnt"));
            
            hotSearch hs=new hotSearch(time,title,content,score,i+1,0,website);
            hotSearchList.add(hs);
        }
        return hotSearchList;
    }
}
