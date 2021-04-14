package com.jxx.designfuction.clone.Test;

public class WorkExperience implements Cloneable{

    private String workDate;

    public WorkExperience(String workDate) {
        this.workDate = workDate;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "WorkExperience{" +
                "workDate='" + workDate + '\'' +
                '}';
    }
}
