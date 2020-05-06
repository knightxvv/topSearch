package com.tx.website.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tx.utils.MyHttpClient;
import com.tx.utils.MyJdbcUtil;
import com.tx.utils.MyResult;

@Service
@EnableScheduling
public class toutiao {
    @Autowired
    private MyJdbcUtil MyJdbcUtil;
    @Autowired
    private MyHttpClient MyHttpClient;
    @Autowired
    private MyResult MyResult;
    
    public static String url="http://is.snssdk.com/api/suggest_words/?business_id=10017";
//  https://ib.snssdk.com/rogue/aladdin_landingpage/template/aladdin_landingpage/hot_words.html"
    
    public static String website="toutiao";
    
//    private Logger logger = LoggerFactory.getLogger(tieba.class);
    
    @Scheduled(cron = "0 0/10 * * * ?")
    public String start() {
        int success=0;
        String html=MyHttpClient.doGet(url,"utf-8");
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time=df.format(new Date());// new Date()为获取当前系统时间
        JSONObject jsons=JSONObject.parseObject(html);
        JSONArray elements=jsons.getJSONArray("data").getJSONObject(0).getJSONArray("words");
        
//        if(elements.size()!=50) {
//            String cause="元素获取错误：elements.size()!=50";
//            logger.info(String.format("%s %s 抓取失败:%s",time,website,cause));
//            return MyResult.getParseResult(success, website, time);
//        }
        for (int i=0;i<elements.size();i++) {
            String title=elements.getJSONObject(i).getString("word");
            String content="";
            int score=Integer.valueOf(elements.getJSONObject(i).getJSONObject("params").getString("fake_click_cnt"));
//            System.out.println("标题:" + title);
//          System.out.println("内容:" + content);
//          System.out.println("热度:" + score);
            String sql=String.format("insert into %s(ranking,time,title,content,score) values('%d','%s','%s','%s','%d')",website,(i+1),time,title,content,score);
            if(!MyJdbcUtil.insert(sql)) {
//              System.out.println("sql执行失败："+sql);
            }else {
//              System.out.println("sql执行成功："+sql);
                success++;
            }
        }
        String sql=String.format("insert into log(time,website,count) values('%s','%s','%d')",time,website,success);
        MyJdbcUtil.insert(sql);
        
        return MyResult.getParseResult(success, website, time);
    }
}
