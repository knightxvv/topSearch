package com.tx.hotsearch.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tx.mybatis.mapper.*;
import com.tx.hotsearch.dao.*;

@Service
public class hotSearchService{
    @Autowired
    hotSearchMapper hotSearchMapper;
    
    public hotSearch selectHotSearch() {
        // TODO Auto-generated method stub
        hotSearch hs=hotSearchMapper.selectHotSearch();
        return hs;
    }
    
    public boolean insertHotSearch(hotSearch hotSearch) {
        boolean flag=hotSearchMapper.insertHotSearch(hotSearch);
        return flag;
    }
    
    public boolean insertHotSearchOfWebsite(List<hotSearch> hotSearchList) {
        boolean flag=hotSearchMapper.insertHotSearchOfWebsite(hotSearchList);
        return flag;
    }
    
    public List<hotSearch> selectLatestHotSearchByWebsite(String website){
        List<hotSearch> list=hotSearchMapper.selectLatestHotSearchByWebsite(website);
        return list;
    }
    
    public List<hotSearch> selectLatestHotSearchByWebsiteTopN(String website,int count){
        List<hotSearch> list=hotSearchMapper.selectLatestHotSearchByWebsiteTopN(website,count);
        return list;
    }
    
    public List<HashMap<String, Object>> searchTitleByKeyword(String keyword){
        List<HashMap<String, Object>> list = hotSearchMapper.searchTitleByKeyword(keyword);
        return list;
    }

  //以特定标题来获取所有热搜，按时间升序
    public List<hotSearch> selectHotSearchByTitle(String title,String website){
        List<hotSearch> list=hotSearchMapper.selectHotSearchByTitle(title,website);
        return list;
    }
}
