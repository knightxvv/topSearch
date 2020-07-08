package com.tx.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tx.website.service.*;
import com.tx.website.dao.*;
import com.tx.utils.*;

@RestController
@RequestMapping(value="/website")
public class websiteController {
    @Autowired
    MyMailUtil MyMailUtil;
    
    
    @Autowired
    public websiteService websiteService;
    
    @RequestMapping(value="/parse/{website}",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public List<String> parseWebsite(@PathVariable("website") String website) {
        return websiteService.parseWebsite(website);
    }
    
    @RequestMapping(value="/parse/allWebsite",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public List<String> parseAllWebsite() {
        return websiteService.parseAllWebsite();
    }
    
    @RequestMapping(value="/get/allWebsite",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public List<website> getWebsiteList() {
        return websiteService.getWebsiteList();
    }
    
    
}
