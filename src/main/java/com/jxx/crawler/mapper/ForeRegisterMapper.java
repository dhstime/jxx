package com.jxx.crawler.mapper;


import com.jxx.crawler.model.ForeRegister;

public interface ForeRegisterMapper {
    int deleteByPrimaryKey(Integer foreRegisterId);

    int insert(ForeRegister record);

    int insertSelective(ForeRegister record);

    ForeRegister selectByPrimaryKey(Integer foreRegisterId);

    int updateByPrimaryKeySelective(ForeRegister record);

    int updateByPrimaryKey(ForeRegister record);

    ForeRegister getForeRegisterByNumber(ForeRegister foreRegister);
}