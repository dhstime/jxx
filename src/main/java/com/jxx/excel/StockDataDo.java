package com.jxx.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class StockDataDo {
    @ExcelIgnore
    private Integer inStockId;
    @ExcelIgnore
    private String yearMonthDay;
    @ExcelIgnore
    private String yearMonth;

    @Excel(name = "入库单号")
    @ExcelProperty("入库单号")
    private String orderNo;

    @Excel(name = "单据类型")
    @ExcelProperty("单据类型")
    private String orderType;

    @Excel(name = "供应商名称")
    @ExcelProperty("供应商名称")
    private String traderName;

    @Excel(name = "供应商ID")
    @ExcelProperty("供应商ID")
    private Integer traderId;

    @Excel(name = "关联单号")
    @ExcelProperty("关联单号")
    private String assOrderNo;

    @Excel(name = "创建日期",importFormat = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("创建日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date addTime;

    @Excel(name = "生效日期",importFormat = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("生效日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date validTime;

    @Excel(name = "创建人员")
    @ExcelProperty("创建人员")
    private String creator;

    @Excel(name = "SKU")
    @ExcelProperty("SKU")
    private String sku;

    @Excel(name = "商品名称")
    @ExcelProperty("商品名称")
    private String skuName;

    @Excel(name = "品牌")
    @ExcelProperty("品牌")
    private String brandName;

    @Excel(name = "规格/型号")
    @ExcelProperty("规格/型号")
    private String model;

    @Excel(name = "计量单位")
    @ExcelProperty("计量单位")
    private String unitName;

    @Excel(name = "库存数量")
    @ExcelProperty("库存数量")
    private Integer num;

    @Excel(name = "成本价")
    @ExcelProperty("成本价")
    private BigDecimal costPrice;

    @Excel(name = "库存金额")
    @ExcelProperty("库存金额")
    private BigDecimal totalAmount;

    @Excel(name = "成本价新")
    @ExcelProperty("成本价新")
    private BigDecimal newCostPrice;

    @Excel(name = "库存金额新")
    @ExcelProperty("库存金额新")
    private BigDecimal newTotalAmount;

    @Excel(name = "效期")
    @ExcelProperty("效期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date expireDate;

    @Excel(name = "一级分类")
    @ExcelProperty("一级分类")
    private String oneFl;

    @Excel(name = "二级分类")
    @ExcelProperty("二级分类")
    private String twoFl;

    @Excel(name = "三级分类")
    @ExcelProperty("三级分类")
    private String threeFl;


}