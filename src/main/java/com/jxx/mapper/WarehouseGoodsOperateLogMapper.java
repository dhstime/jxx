package com.jxx.mapper;

import com.jxx.common.model.WarehouseGoodsOperateLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Strange
 * @ClassName WarehouseGoodsOperateLogMapper.java
 * @Description TODO
 * @createTime 2021年03月24日 17:18:00
 */
public interface WarehouseGoodsOperateLogMapper {


    WarehouseGoodsOperateLog selectById(Integer warehouseGoodsOperateLogId);

    List<WarehouseGoodsOperateLog> getBuyorder(@Param("buyorderNo") String buyorderNo, @Param("sku") String sku);
}
