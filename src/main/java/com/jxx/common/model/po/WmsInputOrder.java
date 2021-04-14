package com.jxx.common.model.po;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WmsInputOrder {
    /** 主键  WMS_INPUT_ORDER_ID **/
    private Integer wmsInputOrderId;

    /** 入库单类型:1-盘盈入库单,  ORDER_TYPE **/
    private Integer orderType;

    /** 入库单号  ORDER_NO **/
    private String orderNo;

    /** 审核状态:0:待审核,1:审核中,2:审核通过,3:审核不通过  VERIFY_STATUS **/
    private Integer verifyStatus;

    /** 入库状态:0-未入库 1-部分入库 2 全部入库  ARRIVAL_STATUS **/
    private Integer arrivalStatus;

    /** 申请入库时间  APPLY_INTIME **/
    private Date applyIntime;

    /** 备注  REMARK **/
    private String remark;

    /** 申请人  APPLYER **/
    private String applyer;

    /** 申请人id  APPLYER_USERID **/
    private Integer applyerUserid;

    /** 申请人部门  APPLYER_DEPARTMENT **/
    private String applyerDepartment;

    /** 申请人部门id  APPLYER_DEPARTMENT_ID **/
    private Integer applyerDepartmentId;

    /** 创建时间  ADD_TIME **/
    private Date addTime;

    /** 更新时间  MODE_TIME **/
    private Date modeTime;

    /** 创建人  CREATOR **/
    private Integer creator;

    /** 更新人  UPDATER **/
    private Integer updater;

    /** 是否删除 0否 1是  IS_DELETE **/
    private Integer isDelete;

    private List<WmsInputOrderGoods> wmsInputOrderGoods;

}