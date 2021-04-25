package com.jxx.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

public class StockDataDo {

    private Integer inStockId;

    private String yearMonthDay;

    private String yearMonth;

    @Excel(name = "入库单号")
    private String orderNo;

    @Excel(name = "单据类型")
    private String orderType;

    @Excel(name = "供应商名称")
    private String traderName;

    @Excel(name = "供应商ID")
    private Integer traderId;

    @Excel(name = "关联单号")
    private String assOrderNo;

    @Excel(name = "创建日期",importFormat = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    @Excel(name = "生效日期",importFormat = "yyyy-MM-dd HH:mm:ss")
    private Date validTime;

    @Excel(name = "创建人员")
    private String creator;

    @Excel(name = "SKU")
    private String sku;

    @Excel(name = "商品名称")
    private String skuName;

    @Excel(name = "品牌")
    private String brandName;

    @Excel(name = "规格/型号")
    private String model;

    @Excel(name = "计量单位")
    private String unitName;

    @Excel(name = "库存数量")
    private Integer num;

    @Excel(name = "成本价")
    private BigDecimal costPrice;

    @Excel(name = "库存金额")
    private BigDecimal totalAmount;

    @Excel(name = "成本价新")
    private BigDecimal newCostPrice;

    @Excel(name = "库存金额新")
    private BigDecimal newTotalAmount;

    @Excel(name = "效期")
    private Date expireDate;

    @Excel(name = "一级分类")
    private String oneFl;

    @Excel(name = "二级分类")
    private String twoFl;

    @Excel(name = "三级分类")
    private String threeFl;

    @Excel(name = "老一级分类")
    private String oldOneFl;

    @Excel(name = "老二级分类")
    private String oldTwoFl;

    @Excel(name = "老三级分类")
    private String oldThreeFl;

    public Integer getInStockId() {
        return inStockId;
    }

    public void setInStockId(Integer inStockId) {
        this.inStockId = inStockId;
    }

    public String getYearMonthDay() {
        return yearMonthDay;
    }

    public void setYearMonthDay(String yearMonthDay) {
        this.yearMonthDay = yearMonthDay == null ? null : yearMonthDay.trim();
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth == null ? null : yearMonth.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName == null ? null : traderName.trim();
    }

    public Integer getTraderId() {
        return traderId;
    }

    public void setTraderId(Integer traderId) {
        this.traderId = traderId;
    }

    public String getAssOrderNo() {
        return assOrderNo;
    }

    public void setAssOrderNo(String assOrderNo) {
        this.assOrderNo = assOrderNo == null ? null : assOrderNo.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getNewCostPrice() {
        return newCostPrice;
    }

    public void setNewCostPrice(BigDecimal newCostPrice) {
        this.newCostPrice = newCostPrice;
    }

    public BigDecimal getNewTotalAmount() {
        return newTotalAmount;
    }

    public void setNewTotalAmount(BigDecimal newTotalAmount) {
        this.newTotalAmount = newTotalAmount;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getOneFl() {
        return oneFl;
    }

    public void setOneFl(String oneFl) {
        this.oneFl = oneFl == null ? null : oneFl.trim();
    }

    public String getTwoFl() {
        return twoFl;
    }

    public void setTwoFl(String twoFl) {
        this.twoFl = twoFl == null ? null : twoFl.trim();
    }

    public String getThreeFl() {
        return threeFl;
    }

    public void setThreeFl(String threeFl) {
        this.threeFl = threeFl == null ? null : threeFl.trim();
    }

    public String getOldOneFl() {
        return oldOneFl;
    }

    public void setOldOneFl(String oldOneFl) {
        this.oldOneFl = oldOneFl == null ? null : oldOneFl.trim();
    }

    public String getOldTwoFl() {
        return oldTwoFl;
    }

    public void setOldTwoFl(String oldTwoFl) {
        this.oldTwoFl = oldTwoFl == null ? null : oldTwoFl.trim();
    }

    public String getOldThreeFl() {
        return oldThreeFl;
    }

    public void setOldThreeFl(String oldThreeFl) {
        this.oldThreeFl = oldThreeFl == null ? null : oldThreeFl.trim();
    }
}