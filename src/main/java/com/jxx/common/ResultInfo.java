package com.jxx.common;

import java.util.List;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class ResultInfo<T> {
    //0 成功 -1 失败
    private Integer code = -1;

    private String message="操作失败";

    private Object param;

    private T data;
    private List<T> listData;

    private Integer status = 0;
    public ResultInfo() {
        super();
    }

    public ResultInfo(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ResultInfo(Integer code, String message, Integer status, T data) {
        super();
        this.code = code;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ResultInfo(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultInfo(Integer code, String message, List<T> listData) {
        super();
        this.code = code;
        this.message = message;
        this.listData = listData;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getListData() {
        return listData;
    }

    public void setListData(List<T> listData) {
        this.listData = listData;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
