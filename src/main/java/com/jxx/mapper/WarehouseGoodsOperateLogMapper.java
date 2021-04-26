package com.jxx.mapper;

import com.jxx.common.model.Order;
import com.jxx.common.model.OrderGoods;
import com.jxx.common.model.WarehouseGoodsOperateLog;
import com.jxx.common.model.WarehouseGoodsOperateLogDto;
import com.jxx.common.model.po.InventoryAdjustmentDetailPo;
import com.jxx.common.model.po.WmsInputOrderGoods;
import com.jxx.common.model.vo.WarehouseGoodsOperateLogVo;
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

    List<InventoryAdjustmentDetailPo> getAdjDetilsByAdjNo(String adjNo);

    List<WmsInputOrderGoods> getInorderDetilsByInNo(String inNo);

    WarehouseGoodsOperateLogDto selectDtoById(Integer warehouseGoodsOperateLogId);

    int insertSelective(WarehouseGoodsOperateLogDto warehouseGoodsOperateLog);

    WmsInputOrderGoods getInorderDetilsByInNoSku(@Param("orderNo") String inNo, @Param("sku") String inSku);

    InventoryAdjustmentDetailPo getAdjDetilByDetilId(@Param("adjDetilId") Integer adjDetilId);

    List<WarehouseGoodsOperateLogDto> getWarehouseIn(WarehouseGoodsOperateLogVo warehouseGoodsOperateLogVo);

    List<InventoryAdjustmentDetailPo> getAdjDetilByorderNoSku(@Param("orderNo") String inNo, @Param("sku") String inSku);

    List<WarehouseGoodsOperateLogDto> getWarehouseLogByBarcodeId(WarehouseGoodsOperateLogVo warehouseGoodsOperateLogVo);

    List<WarehouseGoodsOperateLogDto> getZeroPriceLog();

    List<Order> getAvgPrice(Integer goodsId);

    List<WarehouseGoodsOperateLogDto> getNoNumberLogByOrderNo(WarehouseGoodsOperateLogVo search);

    List<WarehouseGoodsOperateLogDto> getLogOtherBytagSource(@Param("idList") List<Integer> idList);

    OrderGoods getBFDetailByOrderIdSku(@Param("orderId") Integer orderId, @Param("sku") String sku);
}
