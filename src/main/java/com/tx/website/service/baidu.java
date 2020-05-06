package com.tx.website.service;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tx.utils.*;


@Service
@EnableScheduling
public class baidu {
    
    @Autowired
    private MyJdbcUtil MyJdbcUtil;
    @Autowired
    private MyHttpClient MyHttpClient;
    @Autowired
    private MyResult MyResult;
    
	public static String url="http://top.baidu.com/buzz?b=1&fr=topindex";
	public static String website="baidu";
//	private Logger logger = LoggerFactory.getLogger(baidu.class);
	
	@Scheduled(cron = "0 0/10 * * * ?")
	public String start() {
		String html=MyHttpClient.doGet(url,"gbk");

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time=df.format(new Date());// new Date()为获取当前系统时间
        
		Document document = Jsoup.parse(html);
        Elements keywords = document.select(".list-title");
        Elements scores = document.select("td.last");
//        System.out.println(keywords.size());
//        System.out.println(scores.size());
        int success=0;
        if(keywords.size()!=keywords.size()) {
        	String cause="元素获取错误：keywords.size()!=keywords.size()";
//        	logger.info(String.format("%s %s 抓取失败:%s",time,website,cause));
        	return MyResult.getParseResult(success, website, time);
        }
        for (int i=0;i<keywords.size();i++) {
			String title=keywords.get(i).html();
			String content="";
			int score=Integer.valueOf(scores.get(i).text());
//            System.out.println("标题:" + title);
//			System.out.println("内容:" + content);
//			System.out.println("热度:" + score);
			String sql=String.format("insert into %s(ranking,time,title,content,score) values('%d','%s','%s','%s','%d')",website,(i+1),time,title,content,score);
			if(!MyJdbcUtil.insert(sql)) {
//				System.out.println("sql执行失败："+sql);
			}else {
//				System.out.println("sql执行成功："+sql);
				success++;
			}
        }
        String sql=String.format("insert into log(time,website,count) values('%s','%s','%d')",time,website,success);
        MyJdbcUtil.insert(sql);
//        logger.info(String.format("%s %s 成功抓取%d条",time,website,success));
        return MyResult.getParseResult(success, website, time);
	}
}
