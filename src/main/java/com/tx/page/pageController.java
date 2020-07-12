package com.tx.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class pageController {
    
    @RequestMapping("")
    public String init() {
        return "html/index.html";
    }
    
    @RequestMapping("registry")
    public String registry() {
        return "html/registry.html";
    }
    
    @RequestMapping("login")
    public String login() {
        return "html/login.html";
    }
}
