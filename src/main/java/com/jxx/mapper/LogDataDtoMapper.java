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
    List<LogDataDo> getOutWeightedPriceLogInfo(LogDataDo month);

    //销售售后入库类型
    List<LogDataDo> getSaleAfterInLogList(LogDataDo month);

    List<LogDataDo> getSaleOutLogList( @Param("orderNo") String orderNo, @Param("sku") String sku,@Param("yearMonth") String yearMonth);

    List<InStockDataDo> getInLogListBySku(List<String> sku);

    List<OutStockDataDo> getOutLogListBySku(List<String> sku);

    int updateByOrderNo(LogDataDo logDataDo);

    int updateRealPriceById(LogDataDo update);

    List<LogDataDo> getTotalInfo(LogDataDo search);

    LogDataDo getInfoByWarehouseId(Integer warehouseId);

    List<LogDataDo> getSaleOutPriceLogList(LogDataDo search);

    List<LogDataDo> getWeihthedInTotalInfo(LogDataDo search);

    List<LogDataDo> getAdjLogInfo(LogDataDo search);

    List<LogDataDo> getWeightPriceSource(String yearMonth);

    List<LogDataDo> getAfterSaleInlogList(String yearMonth);
}