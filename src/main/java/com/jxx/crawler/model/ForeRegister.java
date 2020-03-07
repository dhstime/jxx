package com.jxx.crawler.model;
/**
*进口注册信息
* @Author:strange
* @Date:13:02 2020-03-06
*/
public class ForeRegister {
    /** 进口注册表id  FORE_REGISTER_ID **/
    private Integer foreRegisterId;

    /** 注册证编号  FORE_REGISTER_NUMBER **/
    private String foreRegisterNumber;

    /** 注册人名称  REGISTER_NAME **/
    private String registerName;

    /** 产品名称  GOODS_NAME **/
    private String goodsName;

    /** 管理类别  MANAGE_TYPE **/
    private String manageType;

    /** 产品储存条件及有效期  EXPIRATION_STORAGE **/
    private String expirationStorage;

    /** 附件  ATTACHMENT **/
    private String attachment;

    /** 其他内容  OTHER_CONTENT **/
    private String otherContent;

    /** 备注  NOTE **/
    private String note;

    /** 审批部门	  APPROVAL_DEPA **/
    private String approvalDepa;

    /** 批准日期  APPROVAL_DATE **/
    private String approvalDate;

    /** 有效期至  EXPIRATION_DATE **/
    private String expirationDate;

    /** 注册人住所  REGISTER_ADDRESS **/
    private String registerAddress;

    /** 生产地址  PRODUCT_ADDRESS **/
    private String productAddress;

    /** 代理人名称  PROXY_NAME **/
    private String proxyName;

    /** 代理人住所  PROXY_ADDRESS **/
    private String proxyAddress;

    /** 型号规格  MODEL **/
    private String model;

    /** 结构及组成/主要组成成分  COMPOSITION **/
    private String composition;

    /** 适用范围/预期用途  SCOPE **/
    private String scope;

    /** 变更情况  UPDATE_CONTENT **/
    private String updateContent;

    /**   注册人住所  REGISTER_ADDRESS   **/
    public String getRegisterAddress() {
        return registerAddress;
    }

    /**   注册人住所  REGISTER_ADDRESS   **/
    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress == null ? null : registerAddress.trim();
    }

    /**   生产地址  PRODUCT_ADDRESS   **/
    public String getProductAddress() {
        return productAddress;
    }

    /**   生产地址  PRODUCT_ADDRESS   **/
    public void setProductAddress(String productAddress) {
        this.productAddress = productAddress == null ? null : productAddress.trim();
    }

    /**   代理人名称  PROXY_NAME   **/
    public String getProxyName() {
        return proxyName;
    }

    /**   代理人名称  PROXY_NAME   **/
    public void setProxyName(String proxyName) {
        this.proxyName = proxyName == null ? null : proxyName.trim();
    }

    /**   代理人住所  PROXY_ADDRESS   **/
    public String getProxyAddress() {
        return proxyAddress;
    }

    /**   代理人住所  PROXY_ADDRESS   **/
    public void setProxyAddress(String proxyAddress) {
        this.proxyAddress = proxyAddress == null ? null : proxyAddress.trim();
    }

    /**   型号规格  MODEL   **/
    public String getModel() {
        return model;
    }

    /**   型号规格  MODEL   **/
    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    /**   结构及组成/主要组成成分  COMPOSITION   **/
    public String getComposition() {
        return composition;
    }

    /**   结构及组成/主要组成成分  COMPOSITION   **/
    public void setComposition(String composition) {
        this.composition = composition == null ? null : composition.trim();
    }

    /**   适用范围/预期用途  SCOPE   **/
    public String getScope() {
        return scope;
    }

    /**   适用范围/预期用途  SCOPE   **/
    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    /**   变更情况  UPDATE_CONTENT   **/
    public String getUpdateContent() {
        return updateContent;
    }

    /**   变更情况  UPDATE_CONTENT   **/
    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent == null ? null : updateContent.trim();
    }

    /**   进口注册表id  FORE_REGISTER_ID   **/
    public Integer getForeRegisterId() {
        return foreRegisterId;
    }

    /**   进口注册表id  FORE_REGISTER_ID   **/
    public void setForeRegisterId(Integer foreRegisterId) {
        this.foreRegisterId = foreRegisterId;
    }

    /**   注册证编号  FORE_REGISTER_NUMBER   **/
    public String getForeRegisterNumber() {
        return foreRegisterNumber;
    }

    /**   注册证编号  FORE_REGISTER_NUMBER   **/
    public void setForeRegisterNumber(String foreRegisterNumber) {
        this.foreRegisterNumber = foreRegisterNumber == null ? null : foreRegisterNumber.trim();
    }

    /**   注册人名称  REGISTER_NAME   **/
    public String getRegisterName() {
        return registerName;
    }

    /**   注册人名称  REGISTER_NAME   **/
    public void setRegisterName(String registerName) {
        this.registerName = registerName == null ? null : registerName.trim();
    }

    /**   产品名称  GOODS_NAME   **/
    public String getGoodsName() {
        return goodsName;
    }

    /**   产品名称  GOODS_NAME   **/
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**   管理类别  MANAGE_TYPE   **/
    public String getManageType() {
        return manageType;
    }

    /**   管理类别  MANAGE_TYPE   **/
    public void setManageType(String manageType) {
        this.manageType = manageType == null ? null : manageType.trim();
    }

    /**   产品储存条件及有效期  EXPIRATION_STORAGE   **/
    public String getExpirationStorage() {
        return expirationStorage;
    }

    /**   产品储存条件及有效期  EXPIRATION_STORAGE   **/
    public void setExpirationStorage(String expirationStorage) {
        this.expirationStorage = expirationStorage == null ? null : expirationStorage.trim();
    }

    /**   附件  ATTACHMENT   **/
    public String getAttachment() {
        return attachment;
    }

    /**   附件  ATTACHMENT   **/
    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    /**   其他内容  OTHER_CONTENT   **/
    public String getOtherContent() {
        return otherContent;
    }

    /**   其他内容  OTHER_CONTENT   **/
    public void setOtherContent(String otherContent) {
        this.otherContent = otherContent == null ? null : otherContent.trim();
    }

    /**   备注  NOTE   **/
    public String getNote() {
        return note;
    }

    /**   备注  NOTE   **/
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**   审批部门	  APPROVAL_DEPA   **/
    public String getApprovalDepa() {
        return approvalDepa;
    }

    /**   审批部门	  APPROVAL_DEPA   **/
    public void setApprovalDepa(String approvalDepa) {
        this.approvalDepa = approvalDepa == null ? null : approvalDepa.trim();
    }

    /**   批准日期  APPROVAL_DATE   **/
    public String getApprovalDate() {
        return approvalDate;
    }

    /**   批准日期  APPROVAL_DATE   **/
    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate == null ? null : approvalDate.trim();
    }

    /**   有效期至  EXPIRATION_DATE   **/
    public String getExpirationDate() {
        return expirationDate;
    }

    /**   有效期至  EXPIRATION_DATE   **/
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate == null ? null : expirationDate.trim();
    }
}