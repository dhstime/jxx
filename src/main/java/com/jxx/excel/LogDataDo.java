package com.jxx.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LogDataDo {
    private Integer inLogId;

    private Integer logType;

    private String yearMonthDay;

    private String yearMonth;

    private Integer warehouseLogId;

    @Excel(name = "单号")
    private String orderNo;

    @Excel(name = "单据类型")
    private String orderType;

    @Excel(name = "订单状态")
    private String orderStatus;

    @Excel(name = "关联单号")
    private String assOrderNo;

    @Excel(name = "备注")
    private String remark;

    @Excel(name = "是否直发")
    private String deliveryDirect;

    @Excel(name = "创建时间",importFormat = "yyyy-MM-dd")
    private Date addTime;

    @Excel(name = "生效时间",importFormat = "yyyy-MM-dd")
    private Date validTime;

    @Excel(name = "供应商名称")
    private String traderName;

    @Excel(name = "供应商ID")
    private Integer traderId;

    private String traderLevel;

    @Excel(name = "客户类别")
    private String customerType;

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

    @Excel(name = "入库金额",type = 10)
    private BigDecimal totalAmount;

    @Excel(name = "批次")
    private String batchNumber;

    @Excel(name = "入库时间",importFormat = "yyyy-MM-dd")
    private Date logAddTime;

    private BigDecimal realPrice;

    private BigDecimal realTotalAmount;


}