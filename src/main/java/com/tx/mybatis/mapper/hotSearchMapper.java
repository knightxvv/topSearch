package com.tx.mybatis.mapper;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tx.hotsearch.dao.*;

@Mapper
public interface hotSearchMapper {
    //
    hotSearch selectHotSearch();
    
    //导入某一条hotsearch
    boolean insertHotSearch(hotSearch hotSearch);
    
    //批量insert某个website在某个时间生成的hotsearch,约50-100条
    boolean insertHotSearchOfWebsite(List<hotSearch> hotSearchList);
    
    //获取某网站最新时间的全部热搜
    List<hotSearch> selectLatestHotSearchByWebsite(String website);
    
    //获取某网站最新时间的前N条热搜
    List<hotSearch> selectLatestHotSearchByWebsiteTopN(String website,int count);
    
    //以keyword搜索来获取标题和网站
    List<HashMap<String,Object>> searchTitleByKeyword(String keyword);
    
    //以特定标题和网站（不同网站可能有相同的热搜）来获取热搜
    List<hotSearch> selectHotSearchByTitle(String title,String website);
    
    //获取某一天的时间轴，格式如下12:00:00
    List<String> selectTimeListByDate(String date,String website);
    
    //以特定日期（年月日）和网站（不同网站可能有相同的热搜）和排名来获取热搜
    List<hotSearch> selectHotSearchByDate(String date,String website,int topN);
    
    //按标题获取热搜的详细信息，最高最低排名、热度、时间等
    HashMap<String,Object> getDetailOfHotSearch(String title,String website);
}
