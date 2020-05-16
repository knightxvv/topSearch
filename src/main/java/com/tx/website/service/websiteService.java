package com.tx.website.service;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.tx.utils.MyHttpClient;
import com.tx.utils.MyResult;
import com.tx.website.dao.*;
import com.tx.hotsearch.dao.hotSearch;
import com.tx.hotsearch.service.*;
import com.tx.log.dao.log;
import com.tx.log.service.logService;
import com.tx.mybatis.mapper.*;

import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;

@Service
@EnableScheduling
public class websiteService {
    @Autowired
    private MyHttpClient MyHttpClient;
    @Autowired
    private MyResult MyResult;
    @Autowired
    private hotSearchService hotSearchService;
    @Autowired
    private logService logService;
    @Autowired
    websiteMapper websiteMapper;
    @Autowired
    private ApplicationContext applicationContext;
    
    private Logger logger = LoggerFactory.getLogger(websiteService.class);
    
    
    @SuppressWarnings({ "static-access", "unchecked" })
    public List<String> parseWebsite(String website){
        website web=websiteMapper.getWebsite(website);
        int success=0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time=df.format(new Date());// new Date()为获取当前系统时间
        List<String> results=new LinkedList<>();
        String html=MyHttpClient.doGet(web.url,web.encoding);
        if(html==null || html.equals("")) {
            logger.error(String.format("%s %s 抓取失败:页面为空",time,website));
            results.add(MyResult.getParseResult(success, website, time));
            return results;
        }
        //反射获取各网站的hotsearch列表
        Object obj=applicationContext.getBean(website);
        Method method=ReflectionUtils.findMethod(obj.getClass(),"parse",String.class,String.class);
        List<hotSearch> hotSearchList= (List<hotSearch>) ReflectionUtils.invokeMethod(method,obj,time,html);
        if(hotSearchList.size()==0) {
            results.add(MyResult.getParseResult(success, website, time));
            return results;
        }
        //写入数据库
        try {
            if(hotSearchService.insertHotSearchOfWebsite(hotSearchList)) {
                success=hotSearchList.size();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        results.add(MyResult.getParseResult(success, website, time));
        log log=new log(time,website,success);
        logService.addLog(log);
        return results;
    }
    
    
    //定时爬取各网站
    @Scheduled(cron = "0 0/10 * * * ?")
    @SuppressWarnings({ "static-access", "unchecked" })
    public List<String> parseAllWebsite() {
        int success=0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time=df.format(new Date());// new Date()为获取当前系统时间
        List<String> results=new LinkedList<>();
        List<website> websiteList=getWebsiteList();
        for(website web:websiteList) {
            String website=web.website;
            //b站每天更新一次，因此设定每3小时爬一次
            if(website.equals("bilibili")) {
                String hour=time.split(" ")[1].split(":")[0];
                String min=time.split(" ")[1].split(":")[1];
                if(!(Integer.valueOf(hour)%3==0 && Integer.valueOf(min)==0)) {
                    continue;
                }
            }
            
            String html=MyHttpClient.doGet(web.url,web.encoding);
            if(html==null || html.equals("")) {
                logger.error(String.format("%s %s 抓取失败:页面为空",time,website));
                results.add(MyResult.getParseResult(success, website, time));
                continue;
            }
            //反射获取各网站的hotsearch列表
            Object obj=applicationContext.getBean(website);
            Method method=ReflectionUtils.findMethod(obj.getClass(),"parse",String.class,String.class);
            List<hotSearch> hotSearchList= (List<hotSearch>) ReflectionUtils.invokeMethod(method,obj,time,html);
            //写入数据库
            if(hotSearchList.size()==0) {
                results.add(MyResult.getParseResult(success, website, time));
                continue;
            }
            try {
                if(hotSearchService.insertHotSearchOfWebsite(hotSearchList)) {
                    success=hotSearchList.size();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
            results.add(MyResult.getParseResult(success, website, time));
            log log=new log(time,website,success);
            logService.addLog(log);
        }
        return results;
    }
    
    public List<website> getWebsiteList(){
        List<website> list=new LinkedList<>();
        list=websiteMapper.getWebsiteList();
        return list;
    }
    
}
