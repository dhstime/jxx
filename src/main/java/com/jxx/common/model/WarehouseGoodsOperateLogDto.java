package com.jxx.common.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WarehouseGoodsOperateLogDto {
    /**   WAREHOUSE_GOODS_OPERATE_LOG_ID **/
    private Integer warehouseGoodsOperateLogId;

    /** 产品条码ID  BARCODE_ID **/
    private Integer barcodeId;

    /** 公司ID  COMPANY_ID **/
    private Integer companyId;

    /** 操作类型1入库 2出库3销售换货入库4销售换货出库5销售退货入库6采购退货出库7采购换货出库8采购换货入库 9外借入库 10外借出库 11调整盘盈入库  12盘盈入库  13报废出库 14领用出库 15 盘亏出库  OPERATE_TYPE **/
    private Integer operateType;

    /** 关联采购、销售、售后产品ID  RELATED_ID **/
    private Integer relatedId;

    /** 出库时对应拣货单详细ID  WAREHOUSE_PICKING_DETAIL_ID **/
    private Integer warehousePickingDetailId;

    /** 商品ID  GOODS_ID **/
    private Integer goodsId;

    /** 厂商条码  BARCODE_FACTORY **/
    private String barcodeFactory;

    /** 产品数量  NUM **/
    private Integer num;

    /** 仓库ID  WAREHOUSE_ID **/
    private Integer warehouseId;

    /** 库房ID  STORAGE_ROOM_ID **/
    private Integer storageRoomId;

    /** 货区ID  STORAGE_AREA_ID **/
    private Integer storageAreaId;

    /** 库位ID  STORAGE_LOCATION_ID **/
    private Integer storageLocationId;

    /** 货架ID  STORAGE_RACK_ID **/
    private Integer storageRackId;

    /** 批次号  BATCH_NUMBER **/
    private String batchNumber;

    /** 效期  EXPIRATION_DATE **/
    private Long expirationDate;

    /** 入库验收0未验收1验收通过2不通过  CHECK_STATUS **/
    private Integer checkStatus;

    /** 入库验收人  CHECK_STATUS_USER **/
    private Integer checkStatusUser;

    /** 入库时间  CHECK_STATUS_TIME **/
    private Long checkStatusTime;

    /** 出库复核0未复核1通过2不通过  RECHECK_STATUS **/
    private Boolean recheckStatus;

    /** 出库复核人  RECHECK_STATUS_USER **/
    private Integer recheckStatusUser;

    /** 出库复核时间  RECHECK_STATUS_TIME **/
    private Long recheckStatusTime;

    /** 备注  COMMENTS **/
    private String comments;

    /** 是否有效 0否 1是  IS_ENABLE **/
    private Integer isEnable;

    /** 是否已绑定快递0否 1是  IS_EXPRESS **/
    private Boolean isExpress;

    /** 创建时间  ADD_TIME **/
    private Long addTime;

    /** 创建人  CREATOR **/
    private Integer creator;

    /** 更新时间  MOD_TIME **/
    private Long modTime;

    /** 更新人  UPDATER **/
    private Integer updater;

    /** 0 否  1 是   IS_PROBLEM **/
    private Integer isProblem;

    /** 问题备注  PROBLEM_REMARK **/
    private String problemRemark;

    /** 生产日期  PRODUCT_DATE **/
    private Long productDate;

    /** 成本价  COST_PRICE **/
    private BigDecimal costPrice;

    /** 入库条码是否使用  0未使用 1使用  IS_USE **/
    private Integer isUse;

    /** 逻辑仓id   LOGICAL_WAREHOUSE_ID **/
    private Integer logicalWarehouseId;

    /** 贝登批次码  VEDENG_BATCH_NUMER **/
    private String vedengBatchNumer;

    /** 剩余库存数量  LAST_STOCK_NUM **/
    private Integer lastStockNum;

    /** 灭菌批号  STERILZATION_BATCH_NUMBER **/
    private String sterilzationBatchNumber;

    /** 日志类型  0入库  1出库  LOG_TYPE **/
    private Integer logType;

    /** 成本价(新)  NEW_COST_PRICE **/
    private BigDecimal newCostPrice;

    /** 来源信息  TAG_SOURCES **/
    private String tagSources;


}