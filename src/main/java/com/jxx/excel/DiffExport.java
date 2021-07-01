package com.jxx.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class DiffExport {

    private Integer buyorderGoodsId;

    private Integer saleorderGoodsId;

    @ExcelProperty("销售SKU")
    private String outSku;

    @ExcelProperty("产品名称")
    private String skuName;

    @ExcelProperty("品牌")
    private String brand;

    @ExcelProperty("规格型号")
    private String gg;

    @ExcelProperty("计价单位")
    private String dw;

    @ExcelProperty("销售单号")
    private String orderNo;

    @ExcelProperty("客户名称")
    private String outTraderName;

    @ExcelProperty("归属销售")
    private String user;

    @ExcelProperty("归属部门")
    private String depart;

    @ExcelProperty("采购单号")
    private String buyorderNo;

    @ExcelProperty("供应商名称")
    private String inTraderName;

    @ExcelProperty("归属采购")
    private String belongUser;

    @ExcelProperty("出库时间")
    private String outTime;

    @ExcelProperty("销售数量")
    private BigDecimal outNum;

    @ExcelProperty("采购数量")
    private BigDecimal inNum;

    @ExcelProperty("销售单状态")
    private String outOrderStatus;

    @ExcelProperty("采购单状态")
    private String inOrderStatus;






}

