package com.jxx.dataTest.avgprice;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.jxx.excel.model.NoModelDataListener;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: daniel
 * @Date: 2021/5/13 13 15
 * @Description:
 */
public class ExcelFileUtil {

    public static List<Map<Integer,String>> getDataFromExcelFile(String filePath){
        NoModelDataListener<Map<Integer,String>> listen=new NoModelDataListener();
        ExcelReaderBuilder readerBuilder= EasyExcel.read(filePath, listen);
        readerBuilder.doReadAll();
        return listen.list;
    }


    public static List<String> traverseFileFolder(String filePrefix){
        List<String> filePathList = new ArrayList<>();
        LocalDate start = LocalDate.of(2018,12,1);
        do {
            filePathList.add(start.getYear()+"-"+start.getMonthValue()+".xlsx");
            start = start.plusMonths(1);
        } while (start.isBefore(LocalDate.of(2021,4,1)));
        return filePathList;
    }


    public static void generateExcel(String filePath, List<String> headList, List<List<Object>> source){
        EasyExcel.write(filePath).head(head(headList)).sheet("Sheet1").doWrite(source);
    }

    private static List<List<String>> head(List<String> title) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (String s : title) {
            List<String> head0 = new ArrayList<String>();
            head0.add(s);
            list.add(head0);
        }
        return list;
    }

    @Data
    public static class Result{

        private String key;

        private BigDecimal count;

        private BigDecimal sum;

        private BigDecimal price;

        public Result(BigDecimal count, BigDecimal sum) {
            this.count = count;
            this.sum = sum;
        }
        public Result(BigDecimal count,BigDecimal price, BigDecimal sum) {
            this.count = count;
            this.sum = sum;
            this.price = price;
        }

        public Result() {
        }
    }
}
