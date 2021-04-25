package com.jxx.mapper;

import com.jxx.excel.StockDataDo;

public interface StockDataDtoMapper {
    int deleteByPrimaryKey(Integer inStockId);

    int insert(StockDataDo record);

    int insertSelective(StockDataDo record);

    StockDataDo selectByPrimaryKey(Integer inStockId);

    int updateByPrimaryKeySelective(StockDataDo record);

    int updateByPrimaryKey(StockDataDo record);
}