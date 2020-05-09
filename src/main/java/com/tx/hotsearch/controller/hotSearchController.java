package com.tx.hotsearch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @RequestMapping(value="/{website}/latest/{count}",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public List<hotSearch> selectLatestHotSearchByWebsiteTopN(@PathVariable("website") String website,@PathVariable("count") int count) {
        return hotSearchService.selectLatestHotSearchByWebsiteTopN(website,count);
    }
    
    @RequestMapping(value="/search/{keyword}",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public List<HashMap<String, Object>> selectLatestHotSearchByWebsiteTopN(@PathVariable("keyword") String keyword) {
        return hotSearchService.searchTitleByKeyword(keyword);
    }
    
    @RequestMapping(value="/search/title",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public List<hotSearch> selectHotSearchByTitle(@RequestParam("title") String title,@RequestParam("website") String website) {
        return hotSearchService.selectHotSearchByTitle(title,website);
    }
}