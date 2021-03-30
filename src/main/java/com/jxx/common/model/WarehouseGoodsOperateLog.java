package com.jxx.common.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class WarehouseGoodsOperateLog implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2822391713822945210L;

	private Integer warehouseGoodsOperateLogId;

	private Integer barcodeId;

	private Integer companyId;

	private Integer operateType;

	private Integer relatedId;

	private Integer goodsId;

	private Integer num;

	private String comments;

	private Long addTime;

	private Long modTime;

	private Integer isEnable;

	private Integer logicalWarehouseId; //逻辑仓id

	/**
	 * 贝登批次码
	 */
	private String vedengBatchNumer;

	/**
	 * 剩余库存数量'
	 */
	private Integer lastStockNum;

	/**
	 * 入库条码是否使用  0未使用 1使用'
	 */
	private Integer isUse;
	/**
	 * 日志类型  0入库   1出库
	 */
	private Integer logType;

	private String inventoryAdjustmentNo;

	private String orderNo;

	private String skuNo;
	//ORDER_ARRIVAL_NUM
	private Integer orderArrivalNum;

	private Integer logNum;


}