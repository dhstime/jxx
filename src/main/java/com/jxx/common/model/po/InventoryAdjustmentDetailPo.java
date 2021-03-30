package com.jxx.common.model.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 库存调整单详情
 *
 * @author: Hugo
 * @date: 2020/7/29 11:16:23
 */

@Data
public class InventoryAdjustmentDetailPo implements Serializable {
    /**
     * 库存调整单详情ID
     */
    private Integer inventoryAdjustmentDetailId;

    /**
     * 库存调整单ID
     */
    private Integer inventoryAdjustmentId;

    /**
     * 调整单行号
     */
    private String adjustmentNo;

    private String inventoryAdjustmentNo;

    /**
     * 订货号
     */
    private String skuNo;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 调整数量
     */
    private BigDecimal num;

    /**
     * 单价(元)
     */
    private BigDecimal price;

    /**
     * 总计（元）
     */
    private BigDecimal totalPrice;

    /**
     * 入库单单号
     */
    private String orderNo;

    /**
     * 逻辑仓ID
     */
    private Integer logicalWarehouseId;

    /**
     * 生产时间
     */
    private Long productionTime;

    /**
     * 有效期至
     */
    private Long validUntilTime;

    /**
     * 入库时间
     */
    private Long warehousingTime;

    /**
     * 厂家批号
     */
    private String manufacturerBatchNo;

    /**
     * 灭菌编号
     */
    private String sterilizationBatchNo;

    /**
     * 注册证ID
     */
    private Integer registrationNumberId;

    /**
     * 注册证号
     */
    private String registrationNumber;

    private String vedengBatchNumer;

    /**
     * 添加时间
     */
    private Long addTime;

    /**
     * 添加人
     */
    private Integer creator;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 最后一次编辑人
     */
    private Integer updater;

}
