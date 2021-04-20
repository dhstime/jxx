package com.jxx.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author Strange
 * @ClassName StockCkeck.java
 * @Description TODO
 * @createTime 2021年04月19日 19:13:00
 */
@Data
public class StockCkeck {

    @Excel(name = "SKU")
    private String SKU;
    @Excel(name = "商品名称")
    private String 商品名称;
    @Excel(name = "品牌")
    private String 品牌;
    @Excel(name = "一级分类")
    private String 一级分类;
    @Excel(name = "二级分类")
    private String 二级分类;
    @Excel(name = "三级分类")
    private String 三级分类;
    @Excel(name = "老一级分类")
    private String 老一级分类;
    @Excel(name = "老二级分类")
    private String 老二级分类;
    @Excel(name = "老三级分类")
    private String 老三级分类;


}
