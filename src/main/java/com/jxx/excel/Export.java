package com.jxx.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author Strange
 * @ClassName Export.java
 * @Description TODO
 * @createTime 2021年03月10日 21:43:00
 */
@Data
public class Export {

    @Excel(name = "单号")
    private String 单号;
    @Excel(name = "订单状态")
    private String 订单状态;
    @Excel(name = "创建时间")
    private String 创建时间;
    @Excel(name = "生效时间")
    private String 生效时间;
    @Excel(name = "客户名称")
    private String 客户名称;
    @Excel(name = "客户id")
    private String 客户id;
    @Excel(name = "客户等级")
    private String 客户等级;
    @Excel(name = "客户类别")
    private String 客户类别;
    @Excel(name = "客户性质")
    private String 客户性质;
    @Excel(name = "归属部门")
    private String 归属部门;
    @Excel(name = "发票类型")
    private String 发票类型;
    @Excel(name = "客户归属省")
    private String 客户归属省;
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
    @Excel(name = "贝登条码")
    private String 贝登条码;
    @Excel(name = "厂商条码")
    private String 厂商条码;
    @Excel(name = "数量")
    private String 数量;
    @Excel(name = "金额")
    private String 金额;
    @Excel(name = "批次")
    private String 批次;
    @Excel(name = "出库时间")
    private String 出库时间;




}
