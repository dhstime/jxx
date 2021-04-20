package com.jxx.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author Strange
 * @ClassName OutExport.java
 * @Description TODO
 * @createTime 2021年03月10日 21:43:00
 */
@Data
public class OutExport {

    @Excel(name = "单号")
    private String 单号;
    @Excel(name = "单据类型")
    private String 单据类型;
    @Excel(name = "关联单号")
    private String 关联单号;
    @Excel(name = "备注")
    private String 备注;
    @Excel(name = "订单状态")
    private String 订单状态;
    @Excel(name = "是否直发")
    private String 是否直发;
    @Excel(name = "创建时间")
    private String 创建时间;
    @Excel(name = "生效时间")
    private String 生效时间;
    @Excel(name = "客户名称")
    private String 客户名称;
    @Excel(name = "客户ID")
    private String 客户ID;
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
    @Excel(name = "SKU")
    private String SKU;
    @Excel(name = "商品名称")
    private String 商品名称;
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
    @Excel(name = "数量",type = 10)
    private String 数量;
    @Excel(name = "单价",type = 10)
    private String 单价;
    @Excel(name = "出库金额",type = 10)
    private String 出库金额;
    @Excel(name = "批次")
    private String 批次;
    @Excel(name = "出库时间")
    private String 出库时间;




}
