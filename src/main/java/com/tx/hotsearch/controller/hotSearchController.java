package com.tx.hotsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tx.hotsearch.dao.hotSearch;
import com.tx.hotsearch.service.*;

@RestController
@RequestMapping(value="/hotSearch")
public class hotSearchController {
    @Autowired
    public hotSearchService hotSearchService;
    
    @RequestMapping(value="/{website}/latest",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public List<hotSearch> selectLatestHotSearchByWebsite(@PathVariable("website") String website) {
        return hotSearchService.selectLatestHotSearchByWebsite(website);
    }
}
