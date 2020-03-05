package com.jxx.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @author Daniel
 * @date created in 2019/12/18 6:17 下午
 */
@Data
public class SkuDto {

    @Excel(name="sku号")
    private String skuNo;

    @Excel(name = "产品名称")
    private String skuName;


    @Excel(name = "品牌")
    private String brand;

    @Excel(name = "初试上架时间")
    private Date createTime;

    @Excel(name = "1月总成交金额")
    private Integer money1;

    private Integer money2;

    private Integer money3;

    private Integer money4;

    private Integer money5;

    private Integer money6;

    private Integer money7;

    private Integer money8;

    private Integer money9;

    private Integer money10;

    private Integer money11;

    private Integer money12;

    private Integer month1;

    private Integer month2;

    private Integer month3;

    private Integer month4;

    private Integer month5;

    private Integer month6;

    private Integer month7;

    private Integer month8;

    private Integer month9;

    private Integer month10;

    private Integer month11;

    private Integer month12;
}
