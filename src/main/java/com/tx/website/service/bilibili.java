package com.tx.website.service;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;

import com.tx.hotsearch.dao.hotSearch;


@Service
public class bilibili {
	public static String website="bilibili";
	
	public List<hotSearch> parse(String time,String html) {  
	    List<hotSearch> hotSearchList=new LinkedList<>();

		Document document = Jsoup.parse(html);
        Elements elements = document.select(".rank-item");
        int size=50;
        for (int i=0;i<size;i++) {
			String title=elements.get(i).select(".info a").get(0).html();
			String content="";
			int playcount=Integer.valueOf((int) (Float.valueOf(elements.get(i).select(".data-box").get(0).text().replace("万",""))*10000));
			int score=Integer.valueOf(elements.get(i).select(".pts div").text());

			hotSearch hs=new hotSearch(time,title,content,score,i+1,playcount,website);
            hotSearchList.add(hs);
        }
        return hotSearchList;
	}
}
