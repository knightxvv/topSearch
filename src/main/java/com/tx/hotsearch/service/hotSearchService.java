package com.tx.hotsearch.service;
import java.util.List;

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
        // TODO Auto-generated method stub
        return hotSearchMapper.insertHotSearch(hotSearch);
    }
    
    public boolean insertHotSearchOfWebsite(List<hotSearch> hotSearchList) {
        // TODO Auto-generated method stub
        return hotSearchMapper.insertHotSearchOfWebsite(hotSearchList);
    }

}
