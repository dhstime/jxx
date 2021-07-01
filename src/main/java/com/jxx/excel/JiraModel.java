package com.jxx.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Strange
 * @ClassName Jira.java
 * @Description TODO
 * @createTime 2021年07月01日 10:35:00
 */
@Data
public class JiraModel {

    @ExcelProperty("jira编号")
    private String jiraNo;

    @ExcelProperty("任务概要")
    private String jiraName;

    @ExcelProperty("工作时长")
    private BigDecimal time;
}
