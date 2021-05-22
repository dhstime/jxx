package com.jxx.dataTest;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jxx.JxxApplicationTests;
import com.jxx.excel.*;
import com.jxx.excel.model.NoModelDataListener;
import com.jxx.mapper.LogDataDtoMapper;
import com.jxx.mapper.StockDataDtoMapper;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Strange
 * @ClassName DatabaseExport.java
 * @Description TODO 保存导出execl数据落表
 * @createTime 2021年04月21日 13:12:00
 */
public class DatabaseExport extends JxxApplicationTests{

    @Resource
    private LogDataDtoMapper logDataDtoMapper;

    @Resource
    private StockDataDtoMapper stockDataDtoMapper;


    @Test
    public void exportIn() throws Exception {

        try {
            LocalDateTime startTime = LocalDateTime.of(2018,12,1,0,0,0);
            do {
                LocalDateTime endTime = startTime.plusMonths(1);
                String fileName = "入库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
                File file = new File("/Users/dhs/Downloads/审计数据/入库明细/"+fileName + ".xlsx");

                SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat month = new SimpleDateFormat("yyyy-MM");

                ImportParams params = new ImportParams();
                params.setTitleRows(1);
                params.setHeadRows(1);
                params.setStartSheetIndex(0);
                params.setSheetNum(1);
                LogDataDo logDataDo = new LogDataDo();
//                List<InStockDataDo> list = ExcelImportUtil.importExcel(file, InStockDataDo.class, params);
                NoModelDataListener<InStockDataDo> listen = new NoModelDataListener();
                EasyExcel.read(file, InStockDataDo.class, listen).sheet().headRowNumber(2).doRead();
                List<InStockDataDo> list = listen.list;
                for (InStockDataDo inStockDataDo : list) {
                    Date date = day.parse(inStockDataDo.getLogAddTime());
                    inStockDataDo.setYearMonthDay(day.format(date));
                    inStockDataDo.setYearMonth(month.format(date));
                    inStockDataDo.setLogType(0);//入库
                    inStockDataDo.setDeliveryDirect("否");
                    BeanUtils.copyProperties(inStockDataDo,logDataDo);
                    logDataDo.setLogAddTime(date);
                    logDataDtoMapper.insertSelective(logDataDo);
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
                File file = new File("/Users/dhs/Downloads/审计数据/出库明细/"+fileName + ".xlsx");
                LocalDateTime endTime = startTime.plusMonths(1);


                SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat month = new SimpleDateFormat("yyyy-MM");

                ImportParams params = new ImportParams();
                params.setTitleRows(1);
                params.setHeadRows(1);
                params.setStartSheetIndex(0);
                params.setSheetNum(1);
                LogDataDo logDataDo = new LogDataDo();
//                List<OutStockDataDo> list = ExcelImportUtil.importExcel(file, OutStockDataDo.class, params);

                NoModelDataListener<OutStockDataDo> listen = new NoModelDataListener();
                EasyExcel.read(file, OutStockDataDo.class, listen).sheet().headRowNumber(2).doRead();
                List<OutStockDataDo> list = listen.list;
                for (OutStockDataDo outStockDataDo : list) {
                    Date date = day.parse(outStockDataDo.getLogAddTime());
                    outStockDataDo.setYearMonthDay(day.format(date));
                    outStockDataDo.setYearMonth(month.format(date));
                    outStockDataDo.setLogType(1);//出库
                    outStockDataDo.setDeliveryDirect("否");
                    BeanUtils.copyProperties(outStockDataDo,logDataDo);
                    logDataDo.setLogAddTime(date);
                    logDataDtoMapper.insertSelective(logDataDo);
                }

                System.out.println(startTime.toString());
                startTime = endTime;
            } while (startTime.isBefore(LocalDateTime.of(2021,4,1,0,0,0)));

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Test
    public void importStockData(){
        try {
            LocalDateTime startTime = LocalDateTime.of(2018,12,1,0,0,0);
            do {
                String fileName = "库存明细" + startTime.getYear() + "-" + startTime.getMonth().getValue();
                File file = new File("/Users/dhs/Downloads/审计数据/库存明细/"+fileName + ".xlsx");
                LocalDateTime endTime = startTime.plusMonths(1);

                DateTimeFormatter day = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter month = DateTimeFormatter.ofPattern("yyyy-MM");

                ImportParams params = new ImportParams();
                params.setTitleRows(1);
                params.setHeadRows(1);
                params.setStartSheetIndex(0);
                params.setSheetNum(1);
//                List<StockDataDo> list = ExcelImportUtil.importExcel(file, StockDataDo.class, params);
                NoModelDataListener<StockDataDo> listen = new NoModelDataListener();
                EasyExcel.read(file, StockDataDo.class, listen).sheet().headRowNumber(2).doRead();
                List<StockDataDo> list = listen.list;
                for (StockDataDo stockDataDo : list) {
                    stockDataDo.setYearMonthDay(day.format(startTime));
                    stockDataDo.setYearMonth(month.format(startTime));
                    stockDataDtoMapper.insertSelective(stockDataDo);
                }

                System.out.println(startTime.toString());
                startTime = endTime;
            } while (startTime.isBefore(LocalDateTime.of(2021,4,1,0,0,0)));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void exoprtZhifaOut() throws Exception{
        File file = new File("/Users/dhs/Downloads/审计数据/直发/直发出库2.xlsx");

        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat month = new SimpleDateFormat("yyyy-MM");

        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        params.setStartSheetIndex(0);
        params.setSheetNum(1);
        List<OutStockDataDo> list = ExcelImportUtil.importExcel(file, OutStockDataDo.class, params);

        int warehouseLogId = 150000;

        for (OutStockDataDo outStockDataDo : list) {
            Date date = day.parse(outStockDataDo.getLogAddTime());
            outStockDataDo.setYearMonthDay(day.format(date));
            outStockDataDo.setYearMonth(month.format(date));
            outStockDataDo.setLogType(1);//出库
            outStockDataDo.setDeliveryDirect("是");
            LogDataDo logDataDo = new LogDataDo();
            BeanUtils.copyProperties(outStockDataDo,logDataDo);
            logDataDo.setLogAddTime(date);
            logDataDo.setWarehouseLogId(warehouseLogId);
//            logDataDtoMapper.insertSelective(logDataDo);
            warehouseLogId++;
//            System.out.println(logDataDo.toString());
        }
        System.out.println("exoprtZhifaOut  end");
    }

    @Test
    public void exoprtZhifaIn() throws Exception {
        File file = new File("/Users/dhs/Downloads/审计数据/直发/直发入库2.xlsx");

        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat month = new SimpleDateFormat("yyyy-MM");

        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        params.setStartSheetIndex(0);
        params.setSheetNum(1);
        List<InStockDataDo> list = ExcelImportUtil.importExcel(file, InStockDataDo.class, params);

        int warehouseLogId = 250000;

        for (InStockDataDo inStockDataDo : list) {
            Date date = day.parse(inStockDataDo.getLogAddTime());
            inStockDataDo.setYearMonthDay(day.format(date));
            inStockDataDo.setYearMonth(month.format(date));
            inStockDataDo.setLogType(0);//入库
            inStockDataDo.setDeliveryDirect("是");
            LogDataDo logDataDo = new LogDataDo();
            BeanUtils.copyProperties(inStockDataDo,logDataDo);
            logDataDo.setLogAddTime(date);
            logDataDo.setWarehouseLogId(warehouseLogId);
//            logDataDtoMapper.insertSelective(logDataDo);
            warehouseLogId++;
//            System.out.println(logDataDo.toString());

        }
        System.out.println("exoprtZhifaIn  end");
    }




}
