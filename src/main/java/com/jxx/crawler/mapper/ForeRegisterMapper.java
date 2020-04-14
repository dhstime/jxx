package com.jxx.crawler.mapper;


import com.jxx.crawler.model.ForeRegister;

public interface ForeRegisterMapper {

    int insert(ForeRegister record);

    int updateByPrimaryKeySelective(ForeRegister record);

    int updateByPrimaryKey(ForeRegister record);

    ForeRegister getForeRegisterByNumber(ForeRegister foreRegister);
}