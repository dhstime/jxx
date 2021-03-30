package com.jxx.common.model.po;

import java.io.Serializable;

/**
 * 库存调整单
 *
 * @author Hugo
 * @date 2020/7/9 11:03:04
 */
public class InventoryAdjustmentPo implements Serializable {
    /**
     * 库存调整单ID
     */
    private Integer inventoryAdjustmentId;

    /**
     * 库存调整单单号
     */
    private String inventoryAdjustmentNo;

    /**
     * 库存调整单类型
     */
    private Integer type;

    /**
     * 调整单审核状态
     */
    private Integer status;

    /**
     * 仓库ID
     */
    private Integer warehouseId;

    /**
     * 货主
     */
    private String customer;

    /**
     * 调整原因
     */
    private String reason;

    /**
     * 库存调整单创建时间
     */
    private Long creatTime;

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
    private Integer updateTime;

    /**
     * 最后一次编辑人
     */
    private Integer updater;

    public Integer getInventoryAdjustmentId() {
        return inventoryAdjustmentId;
    }

    public void setInventoryAdjustmentId(Integer inventoryAdjustmentId) {
        this.inventoryAdjustmentId = inventoryAdjustmentId;
    }

    public String getInventoryAdjustmentNo() {
        return inventoryAdjustmentNo;
    }

    public void setInventoryAdjustmentNo(String inventoryAdjustmentNo) {
        this.inventoryAdjustmentNo = inventoryAdjustmentNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdater() {
        return updater;
    }

    public void setUpdater(Integer updater) {
        this.updater = updater;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }
}
