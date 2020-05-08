package com.tx.mybatis.mapper;


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
    
    List<hotSearch> selectHotSearchByKey();
    
    List<hotSearch> selectHotSearchByRank();
    
    List<hotSearch> selectHotSearchByTime();
    
}
