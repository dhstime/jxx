package com.jxx.excel.easyexcel;

/**
 * @author Strange
 * @ClassName RowRangeDto.java
 * @Description TODO
 * @createTime 2021年04月26日 15:57:00
 */
public class RowRangeDto {
    private int start;
    private int end;

    public RowRangeDto(int start,int end){
        this.start = start;
        this.end = end;
    }
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
