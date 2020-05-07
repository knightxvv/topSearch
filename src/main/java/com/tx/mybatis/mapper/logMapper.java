package com.tx.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tx.log.dao.log;

@Mapper
public interface logMapper {
    boolean addLog(log log);
}
