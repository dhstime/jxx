package com.jxx.common.model.vo;


import com.jxx.common.model.po.InventoryAdjustmentDetailPo;

import java.io.Serializable;

/**
 * 库存转移单详情视图对象
 *
 * @author: hugo
 * @date 2020/7/29 11:33:28
 */
public class InventoryAdjustmentDetailVo extends InventoryAdjustmentDetailPo implements Serializable {

    /**
     * 注册证号
     */
    private String registrationNumber;

    /**
     * 逻辑仓名称
     */
    private String logicalWarehouseStr;

    @Override
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getLogicalWarehouseStr() {
        return logicalWarehouseStr;
    }

    public void setLogicalWarehouseStr(String logicalWarehouseStr) {
        this.logicalWarehouseStr = logicalWarehouseStr;
    }
}
