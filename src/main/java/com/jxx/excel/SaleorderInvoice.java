package com.jxx.excel;

/**
 * @author Strange
 * @ClassName SaleorderInvoice.java
 * @Description TODO
 * @createTime 2021年06月02日 11:31:00
 */
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleorderInvoice {

    @ExcelProperty("销售单号")
    private String saleorderNo;

    @ExcelProperty("客户名称")
    private String saleTraderName;

    @ExcelProperty("归属销售")
    private String saleUserName;

    @ExcelProperty("订单生效时间")
    private String saleValidTime;

    @ExcelProperty("归属部门")
    private String saleDept;

    @ExcelProperty("sku数量")
    private Integer saleNum;


    @ExcelProperty("sku售后数量")
    private Integer saleAfNum;

    @ExcelProperty("sku实际采购数量")
    private Integer buyNumAct;

    @ExcelProperty("sku开票数量")
    private Integer saleInvoNum;

    @ExcelProperty("SKU")
    private String sku;

    @ExcelProperty("产品名称")
    private String skuname;

    @ExcelProperty("规格型号")
    private String model;

    @ExcelProperty("品牌")
    private String brandname;

    @ExcelProperty("计量单位")
    private String unitname;

    @ExcelProperty("销售单价")
    private BigDecimal salePrice;

    @ExcelProperty("采购单号")
    private String buyorderNo;

    @ExcelProperty("供应商名称")
    private String buyTraderName;

    @ExcelProperty("归属采购")
    private String buyUserName;
    @ExcelIgnore
    @ExcelProperty("归属采购部门")
    private String buyDept;

    @ExcelProperty("采购单生效时间")
    private String buyValidTime;

    @ExcelProperty("采购单价")
    private BigDecimal buyPrice;

    @ExcelProperty("采购数量")
    private Integer buyNum;
    @ExcelProperty("采购售后数量")
    private Integer buyAfNum;
    @ExcelProperty("到货数量")
    private Integer arriveNum;

    @ExcelProperty("录票数量")
    private Integer buyInvoNum;
    @ExcelIgnore
    private Integer saleorderGoodsId;

}

