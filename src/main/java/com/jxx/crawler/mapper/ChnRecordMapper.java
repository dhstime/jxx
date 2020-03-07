package com.jxx.crawler.mapper;


import com.jxx.crawler.model.ChnRecord;

public interface ChnRecordMapper {
    int deleteByPrimaryKey(Integer chnRecordId);

    int insert(ChnRecord record);

    int insertSelective(ChnRecord record);

    ChnRecord selectByPrimaryKey(Integer chnRecordId);

    int updateByPrimaryKeySelective(ChnRecord record);

    int updateByPrimaryKey(ChnRecord record);

    ChnRecord getChnRecordByNumber(ChnRecord chnRecord);
}