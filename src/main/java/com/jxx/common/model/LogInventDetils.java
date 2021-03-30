package com.jxx.common.model;

import com.jxx.common.model.po.InventoryAdjustmentDetailPo;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author Strange
 * @ClassName LogInventDetils.java
 * @Description TODO
 * @createTime 2021年03月25日 15:35:00
 */
@Data
public class LogInventDetils {

    private String value;

    private String orderNo;

    private Integer diffNum;

    private List<WarehouseGoodsOperateLog> logList;

    private Set<InventoryAdjustmentDetailPo> detailPoList;

    private InventoryAdjustmentDetailPo inventoryAdjustmentDetailPo;
}
