package com.jxx.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

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
@Data
public class OutStockDataDo {
    @ExcelIgnore
    private Integer inLogId;
    @ExcelIgnore
    private Integer logType;
    @ExcelIgnore
    private String yearMonthDay;
    @ExcelIgnore
    private String yearMonth;

    @Excel(name = "id")
    @ExcelProperty("id")
    private Integer warehouseLogId;

    @Excel(name = "单号")
    @ExcelProperty("单号")
    private String orderNo;

    @Excel(name = "单据类型")
    @ExcelProperty("单据类型")
    private String orderType;

    @Excel(name = "关联单号")
    @ExcelProperty("关联单号")
    private String assOrderNo;

    @Excel(name = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Excel(name = "订单状态")
    @ExcelProperty("订单状态")
    private String orderStatus;

    @Excel(name = "是否直发")
    @ExcelProperty("是否直发")
    private String deliveryDirect;

    @Excel(name = "创建时间",importFormat = "yyyy-MM-dd")
    @ExcelProperty("创建时间")
    @DateTimeFormat("yyyy-MM-dd")
    private Date addTime;

    @Excel(name = "生效时间",importFormat = "yyyy-MM-dd")
    @ExcelProperty("生效时间")
    @DateTimeFormat("yyyy-MM-dd")
    private Date validTime;

    @Excel(name = "客户名称")
    @ExcelProperty("客户名称")
    private String traderName;

    @Excel(name = "客户ID")
    @ExcelProperty("客户ID")
    private Integer traderId;

    @Excel(name = "客户等级")
    @ExcelProperty("客户等级")
    private String traderLevel;

    @Excel(name = "客户类别")
    @ExcelProperty("客户类别")
    private String customerType;

    @Excel(name = "客户性质")
    @ExcelProperty("客户性质")
    private String customerNature;

    @Excel(name = "归属部门")
    @ExcelProperty("归属部门")
    private String orgName;

    @Excel(name = "发票类型")
    @ExcelProperty("发票类型")
    private String invoiceType;

    @Excel(name = "客户归属省")
    @ExcelProperty("客户归属省")
    private String regionName;

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

    @Excel(name = "一级分类")
    @ExcelProperty("一级分类")
    private String oneFl;

    @Excel(name = "二级分类")
    @ExcelProperty("二级分类")
    private String twoFl;

    @Excel(name = "三级分类")
    @ExcelProperty("三级分类")
    private String threeFl;

    @Excel(name = "老一级分类")
    @ExcelProperty("老一级分类")
    private String oldOneFl;

    @Excel(name = "老二级分类")
    @ExcelProperty("老二级分类")
    private String oldTwoFl;

    @Excel(name = "老三级分类")
    @ExcelProperty("老三级分类")
    private String oldThreeFl;

    @Excel(name = "贝登条码")
    @ExcelProperty("贝登条码")
    private String barcode;

    @Excel(name = "厂商条码")
    @ExcelProperty("厂商条码")
    private String barcodeFactory;

    @Excel(name = "数量",type = 10)
    @ExcelProperty("数量")
    private Integer num;

    @Excel(name = "单价",type = 10)
    @ExcelProperty("单价")
    private BigDecimal newCostPrice;

    @Excel(name = "出库金额",type = 10)
    @ExcelProperty("出库金额")
    private BigDecimal totalAmount;

    @Excel(name = "批次")
    @ExcelProperty("批次")
    private String batchNumber;

    @Excel(name = "出库时间",importFormat = "yyyy-MM-dd")
    @ExcelProperty("出库时间")
    @DateTimeFormat("yyyy-MM-dd")
    private Date logAddTime;

}
