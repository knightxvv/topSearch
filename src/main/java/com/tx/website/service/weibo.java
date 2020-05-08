package com.tx.website.service;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;

import com.tx.hotsearch.dao.hotSearch;

@Service
public class weibo {
    static String website="weibo";
		
    public List<hotSearch> parse(String time,String html) {  
        List<hotSearch> hotSearchList=new LinkedList<>();

		Document document = Jsoup.parse(html);
        Elements elements = document.select(".td-02");
        
        if(elements.size()==51) {
        	elements.remove(0);
        }
//        if(elements.size()!=50) {
////            String cause="元素获取错误：elements.size()!=50";
////            logger.info(String.format("%s %s 抓取失败:%s",time,website,cause));
//            return hotSearchList;
//        }
        for (int i=0;i<elements.size();i++) {
			String title=elements.get(i).select("a").html();
			String content="";
			int score=Integer.valueOf(elements.get(i).select("span").text());
			
			hotSearch hs=new hotSearch(time,title,content,score,i+1,0,website);
            hotSearchList.add(hs);
        }
        return hotSearchList;
	}
}
