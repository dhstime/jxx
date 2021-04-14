package com.jxx.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author Strange
 * @ClassName WarehouseExport.java
 * @Description TODO
 * @createTime 2021年04月13日 23:43:00
 */
@Data
public class WarehouseOutExport {

    @Excel(name = "SKU")
    private String sku;

    @Excel(name = "商品名称")
    private String 商品名称;

    @Excel(name = "出库数量")
    private String num;
}
