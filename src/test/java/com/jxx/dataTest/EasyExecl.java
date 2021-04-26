package com.jxx.dataTest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.jxx.JxxApplicationTests;
import com.jxx.excel.InExport;
import com.jxx.excel.InStockDataDo;
import com.jxx.excel.OutExport;
import com.jxx.excel.easyexcel.BizMergeStrategy;
import com.jxx.excel.easyexcel.RowRangeDto;
import com.jxx.excel.easyexcel.TitleSheetWriteHandler;
import com.jxx.mapper.ExportMapper;
import com.jxx.mapper.LogDataDtoMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Strange
 * @ClassName EasyExecl.java
 * @Description TODO
 * @createTime 2021年04月26日 15:16:00
 */
public class EasyExecl extends JxxApplicationTests {

    @Resource
    private ExportMapper exportMapper;

    @Resource
    private LogDataDtoMapper logDataDtoMapper;

    @Test
    public void test(){
        LocalDateTime startTime = LocalDateTime.of(2019,4,1,0,0,0);

        List<InStockDataDo> list = logDataDtoMapper.selectLogData(yearMonthStr(startTime));
//        List<InExport> list = exportMapper.selectInAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
//        EasyExcel.write("/Users/dhs/Downloads/入库.xlsx",InExport.class).
//                registerWriteHandler(new TitleSheetWriteHandler("我是一个小标题",30)).
//                sheet("入库").doWrite(list);
        EasyExcel.write("/Users/dhs/Downloads/入库.xlsx", InStockDataDo.class)
                .excelType(ExcelTypeEnum.XLSX).head(InStockDataDo.class)
                .registerWriteHandler(new TitleSheetWriteHandler("我是一个小标题",31)) // 标题及样式，lastCol为标题第0列到底lastCol列的宽度
                //设置默认样式及写入头信息开始的行数
                .relativeHeadRowIndex(1)
                .registerWriteHandler(BizMergeStrategy.CellStyleStrategy()) // 设置样式
                .sheet("测试")
                .doWrite(list);

        startTime = startTime.plusMonths(1);
    }

    private String yearMonthStr(LocalDateTime startTime) {
        int value = startTime.getMonth().getValue();
        String month = value +"";
        if (value < 10){
            month = "0"+month;
        }
        return startTime.getYear() + "-" + month;
    }

    private List<List<String>> head(String s) {
        List<List<String>> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list1.add(s);
        list.add(list1);
        return list;
    }


    private Long localDatetime2TimeStamp(LocalDateTime time){
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
