package com.tx.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tx.website.service.*;
import com.tx.utils.*;

@RestController
@RequestMapping("/parse")
public class websiteController {
    @Autowired
    MyMailUtil MyMailUtil;
    
    @Autowired
    private baidu baidu;
    @Autowired
    private zhihu zhihu;
    @Autowired
    private bilibili bilibili;
    @Autowired
    private weibo weibo;
    @Autowired
    private tieba tieba;
    @Autowired
    private douyin douyin;
    @Autowired
    private toutiao toutiao;
    
    @RequestMapping(value="/baidu",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String get1() {
//        MyMailUtil.sendSimpleMail("442329302@qq.com");
        return baidu.start();
    }
    @RequestMapping(value="/zhihu",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String get2() {
        return zhihu.start();
    }
    @RequestMapping(value="/bilibili",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String get3() {
        return bilibili.start();
    }
    @RequestMapping(value="/weibo",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String get4() {
        return weibo.start();
    }
    @RequestMapping(value="/tieba",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String get5() {
        return tieba.start();
    }
    @RequestMapping(value="/douyin",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String get6() {
        return douyin.start();
    }
    @RequestMapping(value="/toutiao",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public String get7() {
        return toutiao.start();
    }
}
