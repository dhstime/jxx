package com.jxx.crawler.model;

import com.jxx.crawler.service.CrawlerService;
import com.jxx.queue.QueueTaskHandler;
import com.jxx.queue.SpringUtils;

/**
*国产备案信息
* @Author:strange
* @Date:13:01 2020-03-06
*/
public class ChnRecord implements QueueTaskHandler {
    /** 国产备案表id  CHN_RECORD_ID **/
    private Integer chnRecordId;

    /** 备案号  CHN_RECORD_NUMBER **/
    private String chnRecordNumber;

    /** 备案人名称  RECORD_NAME **/
    private String recordName;

    /** 产品名称  GOODS_NAME **/
    private String goodsName;

    /** 备注  NOTE **/
    private String note;

    /** 备案单位	  RECORD_DEPA **/
    private String recordDepa;

    /** 备案日期  RECORD_DATE **/
    private String recordDate;

    /** 产品储存条件及有效期  EXPIRATION_STORAGE **/
    private String expirationStorage;

    /** 备案人注册地址  RECORD_ADDRESS **/
    private String recordAddress;

    /** 生产地址  PRODUCT_ADDRESS **/
    private String productAddress;

    /** 型号规格  MODEL **/
    private String model;

    /** 产品描述  DESCRIPTION **/
    private String description;

    /** 预期用途  INTENDED **/
    private String intended;

    /** 变更情况  UPDATE_CONTENT **/
    private String updateContent;

    /**   备案人注册地址  RECORD_ADDRESS   **/
    public String getRecordAddress() {
        return recordAddress;
    }

    /**   备案人注册地址  RECORD_ADDRESS   **/
    public void setRecordAddress(String recordAddress) {
        this.recordAddress = recordAddress == null ? null : recordAddress.trim();
    }

    /**   生产地址  PRODUCT_ADDRESS   **/
    public String getProductAddress() {
        return productAddress;
    }

    /**   生产地址  PRODUCT_ADDRESS   **/
    public void setProductAddress(String productAddress) {
        this.productAddress = productAddress == null ? null : productAddress.trim();
    }

    /**   型号规格  MODEL   **/
    public String getModel() {
        return model;
    }

    /**   型号规格  MODEL   **/
    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    /**   产品描述  DESCRIPTION   **/
    public String getDescription() {
        return description;
    }

    /**   产品描述  DESCRIPTION   **/
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**   预期用途  INTENDED   **/
    public String getIntended() {
        return intended;
    }

    /**   预期用途  INTENDED   **/
    public void setIntended(String intended) {
        this.intended = intended == null ? null : intended.trim();
    }

    /**   变更情况  UPDATE_CONTENT   **/
    public String getUpdateContent() {
        return updateContent;
    }

    /**   变更情况  UPDATE_CONTENT   **/
    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent == null ? null : updateContent.trim();
    }

    /**   国产备案表id  CHN_RECORD_ID   **/
    public Integer getChnRecordId() {
        return chnRecordId;
    }

    /**   国产备案表id  CHN_RECORD_ID   **/
    public void setChnRecordId(Integer chnRecordId) {
        this.chnRecordId = chnRecordId;
    }

    /**   备案号  CHN_RECORD_NUMBER   **/
    public String getChnRecordNumber() {
        return chnRecordNumber;
    }

    /**   备案号  CHN_RECORD_NUMBER   **/
    public void setChnRecordNumber(String chnRecordNumber) {
        this.chnRecordNumber = chnRecordNumber == null ? null : chnRecordNumber.trim();
    }

    /**   备案人名称  RECORD_NAME   **/
    public String getRecordName() {
        return recordName;
    }

    /**   备案人名称  RECORD_NAME   **/
    public void setRecordName(String recordName) {
        this.recordName = recordName == null ? null : recordName.trim();
    }

    /**   产品名称  GOODS_NAME   **/
    public String getGoodsName() {
        return goodsName;
    }

    /**   产品名称  GOODS_NAME   **/
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**   备注  NOTE   **/
    public String getNote() {
        return note;
    }

    /**   备注  NOTE   **/
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**   备案单位	  RECORD_DEPA   **/
    public String getRecordDepa() {
        return recordDepa;
    }

    /**   备案单位	  RECORD_DEPA   **/
    public void setRecordDepa(String recordDepa) {
        this.recordDepa = recordDepa == null ? null : recordDepa.trim();
    }

    /**   备案日期  RECORD_DATE   **/
    public String getRecordDate() {
        return recordDate;
    }

    /**   备案日期  RECORD_DATE   **/
    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate == null ? null : recordDate.trim();
    }

    /**   产品储存条件及有效期  EXPIRATION_STORAGE   **/
    public String getExpirationStorage() {
        return expirationStorage;
    }

    /**   产品储存条件及有效期  EXPIRATION_STORAGE   **/
    public void setExpirationStorage(String expirationStorage) {
        this.expirationStorage = expirationStorage == null ? null : expirationStorage.trim();
    }

    @Override
    public void processData() {
        CrawlerService crawlerService = SpringUtils.getBean(CrawlerService.class);
        crawlerService.saveChnRecord(this);
    }
}