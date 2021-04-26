package com.jxx.dataTest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jxx.JxxApplicationTests;
import com.jxx.common.utils.ThreadPool;
import com.jxx.excel.InStockDataDo;
import com.jxx.excel.OutStockDataDo;
import com.jxx.excel.StockDataDo;
import com.jxx.excel.easyexcel.BizMergeStrategy;
import com.jxx.excel.easyexcel.TitleSheetWriteHandler;
import com.jxx.mapper.LogDataDtoMapper;
import com.jxx.mapper.StockDataDtoMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Strange
 * @ClassName EasyExecl.java
 * @Description TODO 导出本地出入库明细
 * @createTime 2021年04月26日 15:16:00
 */
public class EasyExecl extends JxxApplicationTests {

    @Resource
    private LogDataDtoMapper logDataDtoMapper;

    @Resource
    private StockDataDtoMapper stockDataDtoMapper;

    @Test
    public void test() throws Exception{
        LocalDateTime startTime = LocalDateTime.of(2018,12,1,0,0,0);
        do {
            final CountDownLatch latch = new CountDownLatch(3);

            LocalDateTime finalStartTime = startTime;
            ThreadPool.submit(new Thread(){
                public void run() {
                    try {
                        exprotIn(finalStartTime);
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
                        exprotOut(finalStartTime);
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
                        exprotStock(finalStartTime);
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

            startTime = startTime.plusMonths(1);
        } while (startTime.isBefore(LocalDateTime.of(2021,4,1,0,0,0)));
    }

    private void exprotStock(LocalDateTime startTime) {
        List<StockDataDo> list = stockDataDtoMapper.selectStockData(yearMonthStr(startTime));
        String stockfileName = "库存明细" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String path = "/Users/dhs/Downloads/审计数据/库存明细/" + stockfileName + ".xlsx";

        EasyExcel.write(path, StockDataDo.class)
                .excelType(ExcelTypeEnum.XLSX).head(StockDataDo.class)
                .registerWriteHandler(new TitleSheetWriteHandler(stockfileName,31)) // 标题及样式，lastCol为标题第0列到底lastCol列的宽度
                //设置默认样式及写入头信息开始的行数
                .relativeHeadRowIndex(1)
                .registerWriteHandler(BizMergeStrategy.CellStyleStrategy()) // 设置样式
                .sheet(stockfileName)
                .doWrite(list);
    }

    private void exprotOut(LocalDateTime startTime) {
        List<OutStockDataDo> list = logDataDtoMapper.selectOutLogData(yearMonthStr(startTime));

        String sheetName = "出库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String path = "/Users/dhs/Downloads/审计数据/出库明细/" + sheetName + ".xlsx";

        EasyExcel.write(path, OutStockDataDo.class)
                .excelType(ExcelTypeEnum.XLSX).head(OutStockDataDo.class)
                .registerWriteHandler(new TitleSheetWriteHandler(sheetName,31)) // 标题及样式，lastCol为标题第0列到底lastCol列的宽度
                //设置默认样式及写入头信息开始的行数
                .relativeHeadRowIndex(1)
                .registerWriteHandler(BizMergeStrategy.CellStyleStrategy()) // 设置样式
                .sheet(sheetName)
                .doWrite(list);
    }

    private void exprotIn(LocalDateTime startTime) {
        List<InStockDataDo> list = logDataDtoMapper.selectInLogData(yearMonthStr(startTime));

        String sheetName = "入库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String path = "/Users/dhs/Downloads/审计数据/入库明细/" + sheetName + ".xlsx";

        EasyExcel.write(path, InStockDataDo.class)
                .excelType(ExcelTypeEnum.XLSX).head(InStockDataDo.class)
                .registerWriteHandler(new TitleSheetWriteHandler(sheetName,31)) // 标题及样式，lastCol为标题第0列到底lastCol列的宽度
                //设置默认样式及写入头信息开始的行数
                .relativeHeadRowIndex(1)
                .registerWriteHandler(BizMergeStrategy.CellStyleStrategy()) // 设置样式
                .sheet(sheetName)
                .doWrite(list);
    }

    private String yearMonthStr(LocalDateTime startTime) {
        int value = startTime.getMonth().getValue();
        String month = value +"";
        if (value < 10){
            month = "0"+month;
        }
        return startTime.getYear() + "-" + month;
    }


}
