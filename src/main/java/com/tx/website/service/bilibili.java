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
public class bilibili {
    @Autowired
    private MyJdbcUtil MyJdbcUtil;
    @Autowired
    private MyHttpClient MyHttpClient;
    @Autowired
    private MyResult MyResult;
    
	public static String url="http://www.bilibili.com/ranking/all/0/0/1";
	public static String website="bilibili";
//	private Logger logger = LoggerFactory.getLogger(bilibili.class);
	
	@Scheduled(cron = "0 0/10 * * * ?")
	public String start() {
	    int success=0;
	    
		String html=MyHttpClient.doGet(url);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time=df.format(new Date());// new Date()为获取当前系统时间
        
		Document document = Jsoup.parse(html);
        Elements elements = document.select(".rank-item");

//        System.out.println(elements.size());
        if(elements.size()!=100) {
        	String cause="元素获取错误：elements.size()!=100";
//        	logger.info(String.format("%s %s 抓取失败:%s",time,website,cause));
        	return MyResult.getParseResult(success, website, time);
        }
        
        for (int i=0;i<elements.size();i++) {
			String title=elements.get(i).select(".info a").get(0).html();
			String content="";
			float score=Float.valueOf(elements.get(i).select(".data-box").get(0).text().replace("万",""));
			int playcount=Integer.valueOf(elements.get(i).select(".pts div").text());
//            System.out.println("标题:" + title);
//			System.out.println("内容:" + content);
//			System.out.println("热度:" + score);
			String sql=String.format("insert into %s(ranking,time,title,content,score,playcount) values('%d','%s','%s','%s','%f','%d')",website,(i+1),time,title,content,score,playcount);
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
