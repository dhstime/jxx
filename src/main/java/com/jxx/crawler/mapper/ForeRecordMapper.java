package com.jxx.crawler.mapper;


import com.jxx.crawler.model.ForeRecord;

public interface ForeRecordMapper {
    int deleteByPrimaryKey(Integer foreRecordId);

    int insert(ForeRecord record);

    int insertSelective(ForeRecord record);

    ForeRecord selectByPrimaryKey(Integer foreRecordId);

    int updateByPrimaryKeySelective(ForeRecord record);

    int updateByPrimaryKey(ForeRecord record);

    ForeRecord getForeRecordByNumber(ForeRecord foreRecord);
}