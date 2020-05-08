package com.tx.website.service;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tx.hotsearch.dao.hotSearch;

@Service
public class tieba {
    public static String website="tieba";
        
    public List<hotSearch> parse(String time,String html) {  
        List<hotSearch> hotSearchList=new LinkedList<>();

        JSONObject jsons=JSONObject.parseObject(html);
        JSONArray elements=jsons.getJSONObject("data").getJSONObject("bang_topic").getJSONArray("topic_list");

        for (int i=0;i<elements.size();i++) {
            String title=elements.getJSONObject(i).getString("topic_name");
            String content=elements.getJSONObject(i).getString("abstract");
            int score=Integer.valueOf(elements.getJSONObject(i).getString("discuss_num"));

            hotSearch hs=new hotSearch(time,title,content,score,i+1,0,website);
            hotSearchList.add(hs);
        }
        return hotSearchList;
    }
}
