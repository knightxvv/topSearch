package com.tx.website.dao;

public class hotSearch {
    int id;
    String time;
    String title;
    String content;
    int score;
    int ranking;
    String website;
    
    public hotSearch(String time,String title,String content,int score,int ranking,String website) {
        this.time=time;
        this.title=title;
        this.content=content;
        this.score=score;
        this.ranking=ranking;
        this.website=website;
    }
    public String toString() {
        return id+" "+time+" "+title+" "+content+" "+score+" "+ranking;
    }
}
