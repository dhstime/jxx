package com.jxx.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <b>Description:</b><br>
 * 类解释
 *
 * @author Thor
 * @Note <b>ProjectName:</b> demo <br>
 * <b>PackageName:</b> com.example.demo.entity <br>
 * <b>ClassName:</b> OutStockDataDo <br>
 * <b>Date:</b> 2021/4/22 18:54 <br>
 */
public class OutStockDataDo {
    private Integer inLogId;

    private Integer logType;

    private String yearMonthDay;

    private String yearMonth;

    private Integer warehouseLogId;

    @Excel(name = "单号")
    private String orderNo;

    @Excel(name = "单据类型")
    private String orderType;

    @Excel(name = "关联单号")
    private String assOrderNo;

    @Excel(name = "备注")
    private String remark;

    @Excel(name = "订单状态")
    private String orderStatus;

    @Excel(name = "是否直发")
    private String deliveryDirect;

    @Excel(name = "创建时间",importFormat = "yyyy-MM-dd")
    private Date addTime;

    @Excel(name = "生效时间",importFormat = "yyyy-MM-dd")
    private Date validTime;

    @Excel(name = "客户名称")
    private String traderName;

    @Excel(name = "客户ID")
    private Integer traderId;

    @Excel(name = "客户等级")
    private String traderLevel;

    @Excel(name = "客户类别")
    private String customerType;

    @Excel(name = "客户性质")
    private String customerNature;

    @Excel(name = "归属部门")
    private String orgName;

    @Excel(name = "发票类型")
    private String invoiceType;

    @Excel(name = "客户归属省")
    private String regionName;

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

    @Excel(name = "贝登条码")
    private String barcode;

    @Excel(name = "厂商条码")
    private String barcodeFactory;

    @Excel(name = "数量",type = 10)
    private Integer num;

    @Excel(name = "单价",type = 10)
    private BigDecimal newCostPrice;

    @Excel(name = "出库金额",type = 10)
    private BigDecimal totalAmount;

    @Excel(name = "批次")
    private String batchNumber;

    @Excel(name = "出库时间",importFormat = "yyyy-MM-dd")
    private Date logAddTime;

    public Integer getInLogId() {
        return inLogId;
    }

    public void setInLogId(Integer inLogId) {
        this.inLogId = inLogId;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
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

    public Integer getWarehouseLogId() {
        return warehouseLogId;
    }

    public void setWarehouseLogId(Integer warehouseLogId) {
        this.warehouseLogId = warehouseLogId;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getAssOrderNo() {
        return assOrderNo;
    }

    public void setAssOrderNo(String assOrderNo) {
        this.assOrderNo = assOrderNo == null ? null : assOrderNo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDeliveryDirect() {
        return deliveryDirect;
    }

    public void setDeliveryDirect(String deliveryDirect) {
        this.deliveryDirect = deliveryDirect == null ? null : deliveryDirect.trim();
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

    public String getTraderLevel() {
        return traderLevel;
    }

    public void setTraderLevel(String traderLevel) {
        this.traderLevel = traderLevel == null ? null : traderLevel.trim();
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    public String getCustomerNature() {
        return customerNature;
    }

    public void setCustomerNature(String customerNature) {
        this.customerNature = customerNature == null ? null : customerNature.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
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

    public String getThreeFl() {
        return threeFl;
    }

    public void setThreeFl(String threeFl) {
        this.threeFl = threeFl == null ? null : threeFl.trim();
    }

    public String getTwoFl() {
        return twoFl;
    }

    public void setTwoFl(String twoFl) {
        this.twoFl = twoFl == null ? null : twoFl.trim();
    }

    public String getOneFl() {
        return oneFl;
    }

    public void setOneFl(String oneFl) {
        this.oneFl = oneFl == null ? null : oneFl.trim();
    }

    public String getOldThreeFl() {
        return oldThreeFl;
    }

    public void setOldThreeFl(String oldThreeFl) {
        this.oldThreeFl = oldThreeFl == null ? null : oldThreeFl.trim();
    }

    public String getOldTwoFl() {
        return oldTwoFl;
    }

    public void setOldTwoFl(String oldTwoFl) {
        this.oldTwoFl = oldTwoFl == null ? null : oldTwoFl.trim();
    }

    public String getOldOneFl() {
        return oldOneFl;
    }

    public void setOldOneFl(String oldOneFl) {
        this.oldOneFl = oldOneFl == null ? null : oldOneFl.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getBarcodeFactory() {
        return barcodeFactory;
    }

    public void setBarcodeFactory(String barcodeFactory) {
        this.barcodeFactory = barcodeFactory == null ? null : barcodeFactory.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getNewCostPrice() {
        return newCostPrice;
    }

    public void setNewCostPrice(BigDecimal newCostPrice) {
        this.newCostPrice = newCostPrice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber == null ? null : batchNumber.trim();
    }

    public Date getLogAddTime() {
        return logAddTime;
    }

    public void setLogAddTime(Date logAddTime) {
        this.logAddTime = logAddTime;
    }
}
