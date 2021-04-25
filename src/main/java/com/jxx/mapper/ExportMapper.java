package com.jxx.mapper;

import com.jxx.excel.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Strange
 * @ClassName ExportMapper.java
 * @Description TODO
 * @createTime 2021年03月10日 22:09:00
 */
public interface ExportMapper {

    List<OutExport> selectOutAll(@Param("startTime") Long startTime, @Param("endTime") Long endTime);

    List<InExport> selectInAll(@Param("startTime")Long startTime,@Param("endTime") Long endTime);

    List<StockExport> selectStockSnapshot(@Param("startTime")Long startTime,@Param("endTime") Long endTime);

    List<OutExport> selectDirectOutAll(@Param("startTime") Long startTime, @Param("endTime") Long endTime);

    List<InExport> selectDirectInAll(@Param("startTime")Long startTime,@Param("endTime") Long endTime);

    List<WarehouseInExport> getWarehouseInLogByTime(@Param("startTime") Long startTime, @Param("endTime") Long endTime);

    List<WarehouseOutExport> getWarehouseOutLogByTime(@Param("startTime") Long startTime, @Param("endTime") Long endTime);
}
