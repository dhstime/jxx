package com.jxx.crawler.mapper;


import com.jxx.crawler.model.ChnRecord;

public interface ChnRecordMapper {

    int insert(ChnRecord record);



    int updateByPrimaryKeySelective(ChnRecord record);

    int updateByPrimaryKey(ChnRecord record);

    ChnRecord getChnRecordByNumber(ChnRecord chnRecord);
}