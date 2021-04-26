package com.jxx.dataTest;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jxx.JxxApplicationTests;
import com.jxx.excel.*;
import com.jxx.mapper.LogDataDtoMapper;
import com.jxx.mapper.StockDataDtoMapper;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Strange
 * @ClassName DatabaseExport.java
 * @Description TODO 保存导出execl数据到表
 * @createTime 2021年04月21日 13:12:00
 */
public class DatabaseExport extends JxxApplicationTests{

    @Resource
    private LogDataDtoMapper logDataDtoMapper;

//    @Test
//    public void exportAll() throws Exception{
//        exportIn();
//        exportOut();
//        importStockData();
//    }

    @Test
    public void exportIn() throws Exception {

        try {
            LocalDateTime startTime = LocalDateTime.of(2018,12,1,0,0,0);
            do {
                String fileName = "入库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
                File file = new File("/Users/dhs/Downloads/审计数据/入库明细/"+fileName + ".xls");
                LocalDateTime endTime = startTime.plusMonths(1);
                boolean flag = true;
                int startSheetIndex = 0;

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat simpleMonthFormat = new SimpleDateFormat("yyyy-MM");

                while (flag){
                    ImportParams params = new ImportParams();
                    params.setTitleRows(1);
                    params.setHeadRows(1);
                    params.setStartSheetIndex(startSheetIndex);
                    params.setSheetNum(1);
                    List<InStockDataDo> list = ExcelImportUtil.importExcel(file, InStockDataDo.class, params);
                    startSheetIndex ++;
                    if(list.size() != 59998){
                        flag = false;
                    }
                    for (InStockDataDo inStockDataDo : list) {
                        inStockDataDo.setYearMonthDay(simpleDateFormat.format(inStockDataDo.getLogAddTime()));
                        inStockDataDo.setYearMonth(simpleMonthFormat.format(inStockDataDo.getLogAddTime()));
                        inStockDataDo.setLogType(0);//入库
                        LogDataDo logDataDo = new LogDataDo();
                        BeanUtils.copyProperties(inStockDataDo,logDataDo);
                        logDataDtoMapper.insertSelective(logDataDo);
                    }
                }

                System.out.println(startTime.toString());
                startTime = endTime;
            } while (startTime.isBefore(LocalDateTime.of(2021,4,1,0,0,0)));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void exportOut() throws Exception {

        try {
            LocalDateTime startTime = LocalDateTime.of(2018,12,1,0,0,0);
            do {
                String fileName = "出库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
                File file = new File("/Users/dhs/Downloads/审计数据/出库明细/"+fileName + ".xls");
                LocalDateTime endTime = startTime.plusMonths(1);
                boolean flag = true;
                int startSheetIndex = 0;

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat simpleMonthFormat = new SimpleDateFormat("yyyy-MM");

                while (flag){
                    ImportParams params = new ImportParams();
                    params.setTitleRows(1);
                    params.setHeadRows(1);
                    params.setStartSheetIndex(startSheetIndex);
                    params.setSheetNum(1);
                    List<OutStockDataDo> list = ExcelImportUtil.importExcel(file, OutStockDataDo.class, params);
                    startSheetIndex ++;
                    if(list.size() != 59998){
                        flag = false;
                    }
                    for (OutStockDataDo outStockDataDo : list) {
                        outStockDataDo.setYearMonthDay(simpleDateFormat.format(outStockDataDo.getLogAddTime()));
                        outStockDataDo.setYearMonth(simpleMonthFormat.format(outStockDataDo.getLogAddTime()));
                        outStockDataDo.setLogType(1);//出库
                        LogDataDo logDataDo = new LogDataDo();
                        BeanUtils.copyProperties(outStockDataDo,logDataDo);
                        logDataDtoMapper.insertSelective(logDataDo);
                    }
                }

                System.out.println(startTime.toString());
                startTime = endTime;
            } while (startTime.isBefore(LocalDateTime.of(2021,4,1,0,0,0)));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Resource
    private StockDataDtoMapper stockDataDtoMapper;

    @Test
    public void importStockData(){
        try {
            LocalDateTime startTime = LocalDateTime.of(2018,12,1,0,0,0);
            do {
                String fileName = "库存明细" + startTime.getYear() + "-" + startTime.getMonth().getValue();
                File file = new File("/Users/dhs/Downloads/审计数据/库存明细/"+fileName + ".xls");
                LocalDateTime endTime = startTime.plusMonths(1);
                boolean flag = true;
                int startSheetIndex = 0;

                DateTimeFormatter day = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter month = DateTimeFormatter.ofPattern("yyyy-MM");

                while (flag){
                    ImportParams params = new ImportParams();
                    params.setTitleRows(1);
                    params.setHeadRows(1);
                    params.setStartSheetIndex(startSheetIndex);
                    params.setSheetNum(1);
                    List<StockDataDo> list = ExcelImportUtil.importExcel(file, StockDataDo.class, params);
                    startSheetIndex ++;
                    if(list.size() != 59998){
                        flag = false;
                    }
                    for (StockDataDo stockDataDo : list) {
                        stockDataDo.setYearMonthDay(day.format(startTime));
                        stockDataDo.setYearMonth(month.format(startTime));
                        stockDataDtoMapper.insertSelective(stockDataDo);
                    }
                }

                System.out.println(startTime.toString());
                startTime = endTime;
            } while (startTime.isBefore(LocalDateTime.of(2021,4,1,0,0,0)));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
