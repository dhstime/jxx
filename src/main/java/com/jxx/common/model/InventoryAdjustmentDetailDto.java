package com.jxx.common.model;

import java.math.BigDecimal;

/**
 * 库存调整单详情DTO
 */
public class InventoryAdjustmentDetailDto {

    /**
     * 库存调整单行号
     */
    private String ADJLineNo;

    /**
     * 商品内码
     */
    private String SKU;

    /**
     * 调整变动数量 正数代码盘盈  负数代表盘亏
     */
    private BigDecimal ToQty;

    /**
     * 生产日期
     */
    private String LotAtt01;

    /**
     * 有效期至
     */
    private String LotAtt02;
    /**
     * 入库日期
     */
    private String LotAtt03;

    /**
     * 厂家批号
     */
    private String LotAtt04;

    /**
     * 灭菌批号
     */
    private String LotAtt05;

    /**
     * 注册证号
     */
    private String LotAtt06;

    /**
     * 预留字段
     */
    private String LotAtt07;

    /**
     * 质量状态
     */
    private String LotAtt08;

    /**
     * 销售订单号
     */
    private String LotAtt09;

    /**
     * 入库单号
     */
    private String LotAtt10;

    /**
     * 贝登批次码
     */
    private String LotAtt11;

    public String getADJLineNo() {
        return ADJLineNo;
    }

    public void setADJLineNo(String ADJLineNo) {
        this.ADJLineNo = ADJLineNo;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public BigDecimal getToQty() {
        return ToQty;
    }

    public void setToQty(BigDecimal toQty) {
        ToQty = toQty;
    }

    public String getLotAtt01() {
        return LotAtt01;
    }

    public void setLotAtt01(String lotAtt01) {
        LotAtt01 = lotAtt01;
    }

    public String getLotAtt02() {
        return LotAtt02;
    }

    public void setLotAtt02(String lotAtt02) {
        LotAtt02 = lotAtt02;
    }

    public String getLotAtt03() {
        return LotAtt03;
    }

    public void setLotAtt03(String lotAtt03) {
        LotAtt03 = lotAtt03;
    }

    public String getLotAtt04() {
        return LotAtt04;
    }

    public void setLotAtt04(String lotAtt04) {
        LotAtt04 = lotAtt04;
    }

    public String getLotAtt05() {
        return LotAtt05;
    }

    public void setLotAtt05(String lotAtt05) {
        LotAtt05 = lotAtt05;
    }

    public String getLotAtt06() {
        return LotAtt06;
    }

    public void setLotAtt06(String lotAtt06) {
        LotAtt06 = lotAtt06;
    }

    public String getLotAtt07() {
        return LotAtt07;
    }

    public void setLotAtt07(String lotAtt07) {
        LotAtt07 = lotAtt07;
    }

    public String getLotAtt08() {
        return LotAtt08;
    }

    public void setLotAtt08(String lotAtt08) {
        LotAtt08 = lotAtt08;
    }

    public String getLotAtt09() {
        return LotAtt09;
    }

    public void setLotAtt09(String lotAtt09) {
        LotAtt09 = lotAtt09;
    }

    public String getLotAtt10() {
        return LotAtt10;
    }

    public void setLotAtt10(String lotAtt10) {
        LotAtt10 = lotAtt10;
    }

    public String getLotAtt11() {
        return LotAtt11;
    }

    public void setLotAtt11(String lotAtt11) {
        LotAtt11 = lotAtt11;
    }
}
