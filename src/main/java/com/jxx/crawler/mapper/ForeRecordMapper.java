package com.jxx.crawler.mapper;


import com.jxx.crawler.model.ForeRecord;

public interface ForeRecordMapper {

    int insert(ForeRecord record);

    int updateByPrimaryKeySelective(ForeRecord record);

    int updateByPrimaryKey(ForeRecord record);

    ForeRecord getForeRecordByNumber(ForeRecord foreRecord);
}