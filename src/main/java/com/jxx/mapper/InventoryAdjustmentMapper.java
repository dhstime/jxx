package com.jxx.mapper;


import com.jxx.common.model.po.InventoryAdjustmentDetailPo;

import java.util.List;

/**
 * @author Strange
 * @ClassName InventoryAdjustmentMapper.java
 * @Description TODO
 * @createTime 2021年03月24日 18:45:00
 */
public interface InventoryAdjustmentMapper {

    List<InventoryAdjustmentDetailPo> getDetailsByInfo(InventoryAdjustmentDetailPo search);
}
