package com.jxx.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Strange
 * @ClassName DateOrder.java
 * @Description TODO
 * @createTime 2021年05月20日 09:25:00
 */

@Data
public class DateOrderExprot {

    @ExcelProperty("销售单号")
    private String saleOrderNo;
    @ExcelProperty("采购单号")
    private String buyOrderNo;

    @ExcelProperty("出口日期")
    private Date chukouDate;

    private Date orderDate;
}
