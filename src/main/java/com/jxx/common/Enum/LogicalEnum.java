package com.jxx.common.Enum;

/**
 * @author Strange
 * @ClassName LogicalEnum.java
 * @Description TODO 逻辑仓枚举
 * @createTime 2020年06月28日 11:37:00
 */
public enum LogicalEnum {
    HG(1701, "合格库", "HG"), HDC(1702, "活动库", "HDC"), CJXQ(1703, "超近效期", "CJXQ"),
    B(1704, "等级B", "B"), C(1705, "等级C", "C"), D(1706, "等级D", "D"),
    THREE(1707, "三方库", "3PL"), JXQ(1708, "近效期", "JXQ"), DJC(1709, "待检测库", "DJC"),
    BHG(1710, "不合格库", "BHG"), DCL(1711, "待处理库", "DCL");

    LogicalEnum(Integer logicalWarehouseId, String logicalWarehouseName, String logicalWarehouseCode) {
        this.logicalWarehouseId = logicalWarehouseId;
        this.logicalWarehouseName = logicalWarehouseName;
        this.logicalWarehouseCode = logicalWarehouseCode;
    }

    private Integer logicalWarehouseId;

    private String logicalWarehouseName;

    private String logicalWarehouseCode;

    public static String getLogicalWarehouseName(Integer warehouseId) {
        String logicalWarehouseName = "";

        for (LogicalEnum enumItem : LogicalEnum.values()) {
            if (enumItem.getLogicalWarehouseId().equals(warehouseId)) {
                logicalWarehouseName = enumItem.getLogicalWarehouseName();
                break;
            }
        }

        return logicalWarehouseName;
    }

    public static String getLogicalWarehouseCode(Integer warehouseId) {
        String logicalWarehouseCode = "";

        for (LogicalEnum enumItem : LogicalEnum.values()) {
            if (enumItem.getLogicalWarehouseId().equals(warehouseId)) {
                logicalWarehouseCode = enumItem.getLogicalWarehouseCode();
                break;
            }
        }

        return logicalWarehouseCode;
    }

    public static Integer getLogicalWarehouseIdByCode(String logicalWarehouseCode) {
        Integer logicalWarehouseId = 0;

        for (LogicalEnum enumItem : LogicalEnum.values()) {
            if (enumItem.getLogicalWarehouseCode().equals(logicalWarehouseCode)) {
                logicalWarehouseId = enumItem.getLogicalWarehouseId();
                break;
            }
        }

        return logicalWarehouseId;
    }

    public Integer getLogicalWarehouseId() {
        return logicalWarehouseId;
    }

    public String getLogicalWarehouseName() {
        return logicalWarehouseName;
    }

    public String getLogicalWarehouseCode() {
        return logicalWarehouseCode;
    }
}

