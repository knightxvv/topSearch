package com.tx.mybatis.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tx.website.dao.hotSearch;

@Mapper
public interface hotSearchMapper {
    hotSearch getHotSearch();
    
    boolean addHotSearch(hotSearch hotSearch);
    
    List<hotSearch> getHotSearchByKey();
    
    List<hotSearch> getHotSearchByRank();
    
    List<hotSearch> getHotSearchByTime();
    
}
