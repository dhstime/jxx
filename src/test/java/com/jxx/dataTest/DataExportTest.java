package com.jxx.dataTest;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jxx.JxxApplicationTests;
import com.jxx.common.utils.ThreadPool;
import com.jxx.excel.*;
import com.jxx.excel.easyexcel.BizMergeStrategy;
import com.jxx.excel.easyexcel.TitleSheetWriteHandler;
import com.jxx.mapper.ExportMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Strange
 * @ClassName DataExportTest.java
 * @Description TODO 财务数据导出
 * @createTime 2021年04月12日 11:24:00
 */
public class DataExportTest extends JxxApplicationTests {

    @Resource
    private ExportMapper exportMapper;

    @Test
    public void pufaExport() throws Exception{

        LocalDateTime startTime = LocalDateTime.of(2019,9,1,0,0,0);

        do {
            final CountDownLatch latch = new CountDownLatch(3);

            LocalDateTime endTime = startTime.plusMonths(1);

            LocalDateTime finalStartTime = startTime;

            ThreadPool.submit(new Thread(){
                public void run() {
                    try {
                        //出库明细
                        exportOut(finalStartTime, endTime);
                        //将count值减1
                        latch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            ThreadPool.submit( new Thread(){
                public void run() {
                    try {
                        //入库明细
                        exportIn(finalStartTime, endTime);
                        //将count值减1
                        latch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            ThreadPool.submit(new Thread(){
                public void run() {
                    try {
                        //库存明细
                        exportStock(finalStartTime, endTime);
                        //将count值减1
                        latch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            //等待执行完成
            latch.await();

            System.out.println(startTime.toString());
            startTime = endTime;

        } while (startTime.isBefore(LocalDateTime.of(2021,4,1,0,0,0)));
    }

    private void exportStock(LocalDateTime startTime, LocalDateTime endTime) throws IOException {
        List<StockExport> stockExportList = exportMapper.selectStockSnapshot(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));

        String stockfileName = "库存明细" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String sheetName = "库存" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String path = "/Users/dhs/Downloads/审计数据/库存明细/" + stockfileName + ".xlsx";
        Workbook stockworkbook = ExcelExportUtil.exportExcel(new ExportParams(stockfileName,sheetName),StockExport.class,stockExportList);
        FileOutputStream stockfos = new FileOutputStream(path);
        stockworkbook.write(stockfos);
        stockfos.close();

    }

    private void exportIn(LocalDateTime startTime, LocalDateTime endTime) throws IOException {
        List<InExport> inList = exportMapper.selectInAll(localDatetime2TimeStamp(startTime),localDatetime2TimeStamp(endTime));
        //直发采购
//        List<InExport> inExports = exportMapper.selectDirectInAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
//        inLIst.addAll(inExports);
        String titleName = "入库记录" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String sheetName = "入库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String path = "/Users/dhs/Downloads/审计数据/入库明细/" + sheetName + ".xlsx";
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(titleName,sheetName),InExport.class,inList);
        FileOutputStream infos = new FileOutputStream(path);
        workbook.write(infos);
        infos.close();

    }

    private void  exportOut(LocalDateTime startTime, LocalDateTime endTime) throws IOException {
        List<OutExport> outlist = exportMapper.selectOutAll(localDatetime2TimeStamp(startTime),localDatetime2TimeStamp(endTime));
        //直发销售
//        List<OutExport> outExports = exportMapper.selectDirectOutAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
//        outlist.addAll(outExports);
        String titleName = "出库记录" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String sheetName = "出库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String path = "/Users/dhs/Downloads/审计数据/出库明细/" + sheetName + ".xlsx";
        Workbook  workbook = ExcelExportUtil.exportExcel(new ExportParams(titleName,sheetName),OutExport.class,outlist);
        FileOutputStream outfos = new FileOutputStream(path);
        workbook.write(outfos);
        outfos.close();

    }

    private Long localDatetime2TimeStamp(LocalDateTime time){
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    @Test
    public void zhifaExport() throws Exception{
        LocalDateTime startTime = LocalDateTime.of(2018,12,1,0,0,0);
        do {

            LocalDateTime endTime = startTime.plusMonths(1);

//            exoprtlogIn(startTime, endTime);
//
//            exportlogOut(startTime, endTime);

            exoprtZhifaIn(startTime, endTime);

            exoprtZhifaOut(startTime, endTime);

            System.out.println(startTime.toString());
            startTime = endTime;
        }while (startTime.isBefore(LocalDateTime.of(2021,4,1,0,0,0)));
    }



    private void exoprtZhifaIn(LocalDateTime startTime, LocalDateTime endTime) throws Exception{
        List<InExport> inList = exportMapper.selectDirectInAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
        String titleName = "入库记录" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String infileName = "入库" + startTime.getYear() + "-" + startTime.getMonth().getValue();

        String path = "/Users/dhs/Downloads/审计数据/直发/入库记录/" + infileName + ".xlsx";
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(titleName,infileName),InExport.class,inList);
        FileOutputStream outfos = new FileOutputStream(path);
        workbook.write(outfos);
        outfos.close();

    }


    private void exoprtZhifaOut(LocalDateTime startTime, LocalDateTime endTime) throws Exception{
        List<OutExport> outlist = exportMapper.selectDirectOutAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
        String titleName = "出库记录" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String outfileName = "出库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String path = "/Users/dhs/Downloads/审计数据/直发/出库记录/" + outfileName + ".xlsx";
        Workbook  workbook = ExcelExportUtil.exportExcel(new ExportParams(titleName,outfileName),OutExport.class,outlist);
        FileOutputStream outfos = new FileOutputStream(path);
        workbook.write(outfos);
        outfos.close();

    }
//
//    private void exportlogOut(LocalDateTime startTime, LocalDateTime endTime)throws Exception {
//        List<WarehouseOutExport> outlist = exportMapper.getWarehouseOutLogByTime(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
//
//        Workbook outworkbook = ExcelExportUtil.exportExcel(new ExportParams("出库记录",""),WarehouseOutExport.class,outlist);
//        String outfileName = "出库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
//        FileOutputStream outfos = new FileOutputStream("/Users/dhs/Downloads/审计数据/new/"+outfileName + ".xls");
//        outworkbook.write(outfos);
//        outfos.close();
//    }
//
//    private void exoprtlogIn(LocalDateTime startTime, LocalDateTime endTime) throws Exception{
//        List<WarehouseInExport> inList = exportMapper.getWarehouseInLogByTime(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
//
//        Workbook outworkbook = ExcelExportUtil.exportExcel(new ExportParams("入库记录",""),WarehouseInExport.class,inList);
//        String outfileName = "入库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
//        FileOutputStream outfos = new FileOutputStream("/Users/dhs/Downloads/数据/new/"+outfileName + ".xls");
//        outworkbook.write(outfos);
//        outfos.close();
//    }


}
