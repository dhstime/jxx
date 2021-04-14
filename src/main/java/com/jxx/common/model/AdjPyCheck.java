package com.jxx.common.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author Strange
 * @ClassName AdjPyCheck.java
 * @Description TODO
 * @createTime 2021年04月08日 14:19:00
 */
@Data
public class AdjPyCheck {

    @Excel(name = "盘亏单单号")
    private String adjNo;
    @Excel(name = "盘亏单sku")
    private String adjSku;
    @Excel(name = "盘亏单sku名称")
    private String adjSkuName;
    @Excel(name = "盘盈单单号")
    private String pyNo;
    @Excel(name = "盘盈单sku")
    private String pySku;
    @Excel(name = "盘盈单sku名称")
    private String pySkuName;
    @Excel(name = "结果")
    private String result;
}
