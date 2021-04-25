package com.jxx.mapper;

import com.jxx.excel.LogDataDo;

public interface LogDataDtoMapper {

    int deleteByPrimaryKey(Integer inLogId);

    int insert(LogDataDo record);

    int insertSelective(LogDataDo record);

    LogDataDo selectByPrimaryKey(Integer inLogId);

    int updateByPrimaryKeySelective(LogDataDo record);

    int updateByPrimaryKey(LogDataDo record);

    int findMaxId();
}