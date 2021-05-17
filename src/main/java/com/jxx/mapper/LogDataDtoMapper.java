package com.jxx.mapper;

import com.jxx.excel.InStockDataDo;
import com.jxx.excel.LogDataDo;
import com.jxx.excel.OutStockDataDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDataDtoMapper {

    int deleteByPrimaryKey(Integer inLogId);

    int insert(LogDataDo record);

    int insertSelective(LogDataDo record);

    LogDataDo selectByPrimaryKey(Integer inLogId);

    int updateByPrimaryKeySelective(LogDataDo record);

    int updateByPrimaryKey(LogDataDo record);

    List<InStockDataDo> selectInLogData( @Param("dateStr") String dateStr);

    List<OutStockDataDo> selectOutLogData(@Param("dateStr")String dateStr);

    List<LogDataDo> getLogDataByOrderNoSku(@Param("orderNo") String orderNo, @Param("sku") String sku,@Param("logType") Integer logType);

    List<InStockDataDo> selectInSometing();

    List<OutStockDataDo> selectOutSometing();
    //获取直接赋予加权价
    List<LogDataDo> getOutWeightedPriceLogInfo();

    //销售售后入库类型
    List<LogDataDo> getSaleAfterInLogList();

    List<LogDataDo> getSaleOutLOgList(@Param("orderNo") String orderNo, @Param("sku") String sku);

    List<InStockDataDo> getInLogListBySku(String sku);

    List<OutStockDataDo> getOutLogListBySku(String sku);
}