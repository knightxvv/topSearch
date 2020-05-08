package com.tx.website.service;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;

import com.tx.hotsearch.dao.*;

@Service
public class baidu {

	public static String website="baidu";
	
	public List<hotSearch> parse(String time,String html) {  
	    List<hotSearch> hotSearchList=new LinkedList<>();
	    
		Document document = Jsoup.parse(html);
        Elements keywords = document.select(".list-title");
        Elements scores = document.select("td.last");
        
        if(keywords.size()!=keywords.size()) {
        	return hotSearchList;
        }
        for (int i=0;i<keywords.size();i++) {
			String title=keywords.get(i).html();
			String content="";
			int score=Integer.valueOf(scores.get(i).text());

            hotSearch hs=new hotSearch(time,title,content,score,i+1,0,website);
            hotSearchList.add(hs);
        }
        return hotSearchList;
	}
}
