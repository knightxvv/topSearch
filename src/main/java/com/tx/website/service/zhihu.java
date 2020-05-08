package com.tx.website.service;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.tx.hotsearch.dao.*;

@Service
public class zhihu {
    
	public static String website="zhihu";
		
	@SuppressWarnings("static-access")
	public List<hotSearch> parse(String time,String html) {      
        List<hotSearch> hotSearchList=new LinkedList<>();

		Document document = Jsoup.parse(html);
        Elements elements = document.select(".HotList-item");
        for (int i=0;i<elements.size();i++) {
			String title=elements.get(i).select(".HotList-itemTitle").text();
			String content=elements.get(i).select(".HotList-itemExcerpt").text();
			int score=0;
			try{//防止热度位置为盐选故事的情况
			    score=Integer.valueOf(elements.get(i).select(".HotList-itemMetrics").text().split("万")[0].trim());
			}catch(Exception e) {
			    
			}
			hotSearch hs=new hotSearch(time,title,content,score,i+1,0,website);
			hotSearchList.add(hs);
        }
        return hotSearchList;
	}
}
