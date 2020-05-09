package com.tx.hotsearch.dao;

public class hotSearch {
    public int id;
    public String time;
    public String title;
    public String content;
    public int score;
    public int ranking;
    public int playcount;
    public String website;
    
    public hotSearch() {

    }
    
    public hotSearch(String time,String title,String content,int score,int ranking,int playcount,String website) {
        this.time=time;
        this.title=title;
        this.content=content;
        this.score=score;
        this.ranking=ranking;
        this.playcount=playcount;
        this.website=website;
    }
    public String toString() {
        return time+" "+title+" "+content+" "+score+" "+ranking;
    }
}
