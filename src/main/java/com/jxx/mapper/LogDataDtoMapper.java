package com.jxx.mapper;

import com.jxx.excel.InStockDataDo;
import com.jxx.excel.LogDataDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDataDtoMapper {

    int deleteByPrimaryKey(Integer inLogId);

    int insert(LogDataDo record);

    int insertSelective(LogDataDo record);

    LogDataDo selectByPrimaryKey(Integer inLogId);

    int updateByPrimaryKeySelective(LogDataDo record);

    int updateByPrimaryKey(LogDataDo record);

    List<InStockDataDo> selectLogData( @Param("dateStr") String dateStr);
}