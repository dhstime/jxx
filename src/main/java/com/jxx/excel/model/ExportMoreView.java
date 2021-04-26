package com.jxx.excel.model;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Strange
 * @ClassName ExportMoreView.java
 * @Description TODO
 * @createTime 2021年04月26日 14:51:00
 */
public class ExportMoreView {
    private List<ExportView> moreViewList= Lists.newArrayList();

    public List<ExportView> getMoreViewList() {
        return moreViewList;
    }

    public void setMoreViewList(List<ExportView> moreViewList) {
        this.moreViewList = moreViewList;
    }
}
