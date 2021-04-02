package com.jxx.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Strange
 * @ClassName stickExport.java
 * @Description TODO
 * @createTime 2021年03月20日 11:08:00
 */
@Data
public class StockExport {
    @Excel(name = "入库单号")
    private String 入库单号;
    @Excel(name = "单据类型")
    private String 单据类型;
    @Excel(name = "供应商名称")
    private String 供应商名称;
    @Excel(name = "供应商id")
    private String 供应商ID;
    @Excel(name = "关联单号")
    private String 关联单号;
    @Excel(name = "创建日期")
    private String 创建日期;
    @Excel(name = "生效日期")
    private String 生效日期;
    @Excel(name = "创建人员")
    private String 创建人员;
    @Excel(name = "商品名称")
    private String 商品名称;
    @Excel(name = "SKU")
    private String SKU;
    @Excel(name = "品牌")
    private String 品牌;
    @Excel(name = "规格/型号")
    private String 规格;
    @Excel(name = "计量单位")
    private String 计量单位;
    @Excel(name = "库存数量" ,type = 10)
    private String 库存数量;
    @Excel(name = "成本价",type = 10)
    private String 成本价;
    @Excel(name = "库存金额",type = 10)
    private String 库存金额;
    @Excel(name = "成本价新",type = 10)
    private String 成本价新;
    @Excel(name = "库存金额新",type = 10)
    private String 库存金额新;
    @Excel(name = "效期")
    private String 效期;
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
