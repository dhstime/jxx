package com.jxx.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Strange
 * @ClassName NoNumberVo.java
 * @Description TODO
 * @createTime 2021年04月23日 17:22:00
 */
@Data
public class NoNumberVo {

    @Excel(name = "单号")
    private String orderNo;
    @Excel(name = "单据类型")
    private String optType;
    @Excel(name = "SKU")
    private String sku;
    @Excel(name = "数量")
    private Integer num;
    @Excel(name = "入库时间",importFormat = "yyyy-MM-dd")
    private Date addTime;
}
