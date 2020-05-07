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
import com.tx.website.dao.hotSearch;
import com.tx.log.dao.log;
import com.tx.log.service.*;

@Service
@EnableScheduling
public class zhihu {
    @Autowired
    private MyJdbcUtil MyJdbcUtil;
    @Autowired
    private MyHttpClient MyHttpClient;
    @Autowired
    private MyResult MyResult;
    @Autowired
    private hotSearchService hotSearchService;
    @Autowired
    private logService logService;
    
	public static String url="http://www.zhihu.com/billboard";
	public static String website="zhihu";
	
//	private Logger logger = LoggerFactory.getLogger(zhihu.class);
	
	@Scheduled(cron = "0 0/10 * * * ?")
	public String start() {
	    int success=0;
		String html=MyHttpClient.doGet(url);
	    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time=df.format(new Date());// new Date()为获取当前系统时间
        
		Document document = Jsoup.parse(html);
        Elements elements = document.select(".HotList-item");
        if(elements.size()!=50) {
            String cause="元素获取错误：elements.size()!=50";
//            logger.info(String.format("%s %s 抓取失败:%s",time,website,cause));
            return MyResult.getParseResult(success, website, time);
        }
        for (int i=0;i<elements.size();i++) {
			String title=elements.get(i).select(".HotList-itemTitle").text();
			String content=elements.get(i).select(".HotList-itemExcerpt").text();
			int score=0;
			try{//防止热度位置为盐选故事的情况
			    score=Integer.valueOf(elements.get(i).select(".HotList-itemMetrics").text().split("万")[0].trim());
			}catch(Exception e) {
			    
			}
//            System.out.println("标题:" + title);
//			System.out.println("内容:" + content);
//			System.out.println("热度:" + score);
			String sql=String.format("insert into %s(ranking,time,title,content,score) values('%d','%s','%s','%s','%d')",website,(i+1),time,title,content,score);
			hotSearch hs=new hotSearch(time,title,content,score,i+1,website);
			System.out.println(hs);
			if(hotSearchService.addHotSearch(hs)) {
			    success++;
			}
//			if(!MyJdbcUtil.insert(sql)) {
////				System.out.println("sql执行失败："+sql);
//			}else {
////				System.out.println("sql执行成功："+sql);
//				
//			}
        }
//        String sql=String.format("insert into log(time,website,count) values('%s','%s','%d')",time,website,success);
//        MyJdbcUtil.insert(sql);
        log log=new log(time,website,success);
        logService.addLog(log);
//        logger.info(String.format("%s %s 成功抓取%d条",time,website,success));
        return MyResult.getParseResult(success, website, time);
	}
}
