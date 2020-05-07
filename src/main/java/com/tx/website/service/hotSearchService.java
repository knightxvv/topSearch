package com.tx.website.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tx.mybatis.mapper.*;
import com.tx.website.dao.hotSearch;

@Service
public class hotSearchService{
    @Autowired
    hotSearchMapper hotSearchMapper;
    
    public hotSearch getHotSearch() {
        // TODO Auto-generated method stub
        hotSearch hs=hotSearchMapper.getHotSearch();
        return hs;
    }
    
    public boolean addHotSearch(hotSearch hotSearch) {
        // TODO Auto-generated method stub
        return hotSearchMapper.addHotSearch(hotSearch);
    }

}
