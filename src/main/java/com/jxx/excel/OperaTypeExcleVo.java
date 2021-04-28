package com.jxx.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Strange
 * @ClassName OperaTypeExecleVo.java
 * @Description TODO 类型转化处理model
 * @createTime 2021年04月28日 09:27:00
 */
@Data
public class OperaTypeExcleVo {

    @Excel(name = "ADJ单号")
    private String adjNo;
    @Excel(name = "ADJSKU")
    private String adjSku;
    @Excel(name = "ADJ数量")
    private BigDecimal adjSkuNum;
    @Excel(name = "PY单号")
    private String pyNo;
    @Excel(name = "PYSKU")
    private String pySku;
    @Excel(name = "PY数量")
    private BigDecimal pySkuNum;
    @Excel(name = "类型")
    private String operaType;
}
