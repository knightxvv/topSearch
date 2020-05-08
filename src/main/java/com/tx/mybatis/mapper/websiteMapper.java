package com.tx.mybatis.mapper;
import java.util.List;

import com.tx.website.dao.*;

public interface websiteMapper {
    List<website> getWebsiteList();
    
    String getUrlByWebsite(String website);
    
    website getWebsite(String website);
}
