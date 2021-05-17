package com.jxx.dataTest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jxx.JxxApplicationTests;
import com.jxx.common.utils.DateUtil;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Strange
 * @ClassName EasyExecl.java
 * @Description TODO 导出本地出入库明细
 * @createTime 2021年04月26日 15:16:00
 */
public class EasyExeclLocalData extends JxxApplicationTests {

    @Resource
    private LogDataDtoMapper logDataDtoMapper;

    @Resource
    private StockDataDtoMapper stockDataDtoMapper;

    @Test
    public void exportAll() throws Exception{
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
        List<StockDataDo> list = stockDataDtoMapper.selectStockData(DateUtil.yearMonthStr(startTime));
        String stockfileName = "库存明细" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String path = "/Users/dhs/Downloads/审计数据/库存明细/" + stockfileName + ".xlsx";

        exportStockExcel(list, stockfileName, path);
    }

    private void exportStockExcel(List<StockDataDo> list, String stockfileName, String path) {
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
        List<OutStockDataDo> list = logDataDtoMapper.selectOutLogData(DateUtil.yearMonthStr(startTime));

        String sheetName = "出库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String path = "/Users/dhs/Downloads/审计数据/出库明细/" + sheetName + ".xlsx";

        exportOutExcel(list, sheetName, path);
    }

    private void exprotIn(LocalDateTime startTime) {
        List<InStockDataDo> list = logDataDtoMapper.selectInLogData(DateUtil.yearMonthStr(startTime));

        String sheetName = "入库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String path = "/Users/dhs/Downloads/审计数据/入库明细/" + sheetName + ".xlsx";

        exportInExcel(list, sheetName, path);
    }


    @Test
    public void testExecl(){
        List<InStockDataDo> list = logDataDtoMapper.selectInLogData("2018-12");
        String sheetName = "入库" + 2018 + "-" + 12;
        String path = "/Users/dhs/Downloads/" + sheetName + ".xlsx";

        exportInExcel(list, sheetName, path);
    }

    @Test
    public void customTest(){
        List<InStockDataDo> inList = logDataDtoMapper.selectInSometing();
        String inSheetName = "入库记录";
        String inPath = "/Users/dhs/Downloads/" + "盘盈入库记录" + ".xlsx";
        exportInExcel(inList, inSheetName, inPath);

        List<OutStockDataDo> outList = logDataDtoMapper.selectOutSometing();
        String outSheetName = "出库记录";
        String outPath = "/Users/dhs/Downloads/" + "盘亏出库记录" + ".xlsx";

        exportOutExcel(outList, outSheetName, outPath);
    }

    private void exportOutExcel(List<OutStockDataDo> outList, String outSheetName, String outPath) {
        EasyExcel.write(outPath, OutStockDataDo.class)
                .excelType(ExcelTypeEnum.XLSX).head(OutStockDataDo.class)
                .registerWriteHandler(new TitleSheetWriteHandler(outSheetName, 31)) // 标题及样式，lastCol为标题第0列到底lastCol列的宽度
                //设置默认样式及写入头信息开始的行数
                .relativeHeadRowIndex(1)
                .registerWriteHandler(BizMergeStrategy.CellStyleStrategy()) // 设置样式
                .sheet(outSheetName)
                .doWrite(outList);
    }

    private void exportInExcel(List<InStockDataDo> inList, String inSheetName, String inPath) {
        EasyExcel.write(inPath, InStockDataDo.class)
                .excelType(ExcelTypeEnum.XLSX).head(InStockDataDo.class)
                .registerWriteHandler(new TitleSheetWriteHandler(inSheetName, 31)) // 标题及样式，lastCol为标题第0列到底lastCol列的宽度
                //设置默认样式及写入头信息开始的行数
                .relativeHeadRowIndex(1)
                .registerWriteHandler(BizMergeStrategy.CellStyleStrategy()) // 设置样式
                .sheet(inSheetName)
                .doWrite(inList);
    }

    /**
     * @description: 导出特定SKU
     * @return:
     * @author: Strange
     * @date: 2021/5/13
     **/
    @Test
    public void searchSku(){

        List<String> skuList = Arrays.asList("V274799,V273501,V274272".split(","));
        String inSheetName = "入库记录";
        String outSheetName = "出库记录";
        for (String sku : skuList) {
            String inPath = "/Users/dhs/Downloads/审计数据/SKU/入库/" + sku+"入库记录" + ".xlsx";
            List<InStockDataDo> inList = logDataDtoMapper.getInLogListBySku(sku);
            exportInExcel(inList, inSheetName, inPath);

            List<OutStockDataDo> outList = logDataDtoMapper.getOutLogListBySku(sku);

            String outPath = "/Users/dhs/Downloads/审计数据/SKU/出库/" + sku+"出库记录" + ".xlsx";
            exportOutExcel(outList, outSheetName, outPath);
        }
    }

}
