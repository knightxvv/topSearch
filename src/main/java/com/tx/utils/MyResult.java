package com.tx.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service
public class MyResult {
    private static Logger logger = LoggerFactory.getLogger(MyResult.class);
    
    public static String getParseResult(int success,String website,String time){
        Map<String,String> map=new HashMap<>();
        if(success>0) {
            map.put("code","1");
            map.put("website",website);
            map.put("time",time);
            map.put("count",String.valueOf(success));
            logger.info(String.format("%s %-15s 成功抓取%d条",time,website,success));
        }else {
            map.put("code","0");
            map.put("website",website);
            map.put("time",time);
            map.put("count",String.valueOf(success));
            logger.error(String.format("%s %-15s 抓取失败",time,website,success));
        }
        return map.toString();
    }
}
