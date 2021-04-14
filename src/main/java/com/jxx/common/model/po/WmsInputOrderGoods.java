package com.jxx.common.model.po;

import lombok.Data;

import java.util.Date;

@Data
public class WmsInputOrderGoods {
    /**   WMS_INPUT_ORDER_GOODS_ID **/
    private Integer wmsInputOrderGoodsId;

    /** 入库单的id  WMS_INPUT_ORDER_ID **/
    private Integer wmsInputOrderId;

    /** sku编号  SKU_NO **/
    private String skuNo;

    /** 商品id  GOODS_ID **/
    private Integer goodsId;

    /** 需入库数量  INPUT_NUM **/
    private Integer inputNum;

    /** 入库数量  ARRIVAL_NUM **/
    private Integer arrivalNum;

    /** 入库状态:0-未入库 1-部分入库 2 全部入库  ARRIVAL_STATUS **/
    private Integer arrivalStatus;

    /** 创建时间  ADD_TIME **/
    private Date addTime;

    /** 更新时间  MODE_TIME **/
    private Date modeTime;

    /** 创建人  CREATOR **/
    private Integer creator;

    /** 更新人  UPDATER **/
    private Integer updater;

    /** 是否删除:0-未删除 1-已删除  IS_DELTET **/
    private Integer isDeltet;

    private Integer spuId;

    private String orderNo;

    private String skuName;
}