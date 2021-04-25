package com.jxx.common.model.vo;

import lombok.Data;

/**
 * @author Strange
 * @ClassName WarehouseGoodsOperateLogVo.java
 * @Description TODO
 * @createTime 2021年04月12日 16:58:00
 */
@Data
public class WarehouseGoodsOperateLogVo {

    private Integer warehouseGoodsOperateLogId;

    private Integer goodsId;

    private Integer operateType;

    private Integer relatedId;

    private Integer logType;

    private Integer limit;

    private Integer barcodeId;

    private String orderNo;

    private String sku;
}
