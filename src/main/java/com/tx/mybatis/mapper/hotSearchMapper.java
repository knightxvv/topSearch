package com.tx.mybatis.mapper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    //以特定标题和网站（不同网站可能有相同的热搜）来获取所有热搜，按时间升序
    List<hotSearch> selectHotSearchByTitle(String title,String website);
    
}
