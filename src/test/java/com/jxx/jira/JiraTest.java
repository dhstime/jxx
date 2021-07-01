package com.jxx.jira;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.jxx.excel.JiraModel;
import com.jxx.excel.OutStockDataDo;
import com.jxx.excel.easyexcel.BizMergeStrategy;
import com.jxx.excel.easyexcel.TitleSheetWriteHandler;
import com.jxx.excel.model.NoModelDataListener;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Strange
 * @ClassName JiraTest.java
 * @Description TODO
 * @createTime 2021年07月01日 10:00:00
 */
public class JiraTest {

    public static void main(String[] args) {
        paresJiraExecl("/Users/dhs/Downloads/jira.xlsx");
    }

    private static void paresJiraExecl(String path) {
        File file = new File(path);
        NoModelDataListener<JiraModel> listen = new NoModelDataListener();
        EasyExcel.read(file, JiraModel.class, listen).sheet().headRowNumber(1).doRead();
        List<JiraModel> list = listen.list;


       List<JiraModel> result =list.stream()
               .collect(Collectors.groupingBy(JiraModel ::getJiraNo))
               .values().stream().map(item ->{
                   JiraModel jiraModel = new JiraModel();
                   jiraModel.setTime(BigDecimal.ZERO);
                   for (JiraModel model : item) {
                        jiraModel.setJiraName(model.getJiraNo() +"  "+model.getJiraName());
                        jiraModel.setTime(jiraModel.getTime().add(model.getTime()));
                   }
                   return jiraModel;
               }).collect(Collectors.toList());

       String outPath =  "/Users/dhs/Downloads/jira1.xlsx";

        EasyExcel.write(outPath, JiraModel.class)
                .excelType(ExcelTypeEnum.XLSX).head(JiraModel.class)
                //设置默认样式及写入头信息开始的行数
                .sheet("")
                .doWrite(result);

    }
}
