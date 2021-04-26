package com.jxx.mapper;

import com.jxx.excel.StockDataDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockDataDtoMapper {
    int deleteByPrimaryKey(Integer inStockId);

    int insert(StockDataDo record);

    int insertSelective(StockDataDo record);

    StockDataDo selectByPrimaryKey(Integer inStockId);

    int updateByPrimaryKeySelective(StockDataDo record);

    int updateByPrimaryKey(StockDataDo record);

    List<StockDataDo> selectStockData(@Param("dateStr")String dateStr);
}