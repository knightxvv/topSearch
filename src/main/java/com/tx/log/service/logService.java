package com.tx.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tx.log.dao.log;
import com.tx.mybatis.mapper.*;
@Service
public class logService {
    @Autowired
    logMapper logMapper;
    
    public boolean addLog(log log) {
        return logMapper.addLog(log);
    }
}
