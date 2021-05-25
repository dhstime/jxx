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

        String stockfileName = "库存明细" + DateUtil.yearMonthStr(startTime);
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

        String sheetName = "出库" + DateUtil.yearMonthStr(startTime);
        String path = "/Users/dhs/Downloads/审计数据/出库明细/" + sheetName + ".xlsx";

        exportOutExcel(list, sheetName, path);
    }

    private void exprotIn(LocalDateTime startTime) {
        List<InStockDataDo> list = logDataDtoMapper.selectInLogData(DateUtil.yearMonthStr(startTime));

        String sheetName = "入库" + DateUtil.yearMonthStr(startTime);
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
        String skus = "V111126,V112006,V116824,V116830,V116832,V116833,V116837,V116838,V125582,V125586,V135437,V135443,V135448,V146683,V147223,V148746,V149732,V149751,V149753,V149756,V149757,V149769,V150076,V151173,V151214,V250897,V250899,V251224,V251297,V251831,V251835,V251838,V252277,V252864,V253410,V253790,V253951,V254199,V255193,V255670,V257753,V258214,V271788,V271918,V271983,V272058,V272590,V111444,V116291,V116842";
        List<String> skuList = Arrays.asList(skus.split(","));
        String inSheetName = "入库记录";
        String outSheetName = "出库记录";
        String inPath = "/Users/dhs/Downloads/审计数据/SKU/入库/" + "入库记录" + ".xlsx";
        List<InStockDataDo> inList = logDataDtoMapper.getInLogListBySku(skuList);
        exportInExcel(inList, inSheetName, inPath);

        List<OutStockDataDo> outList = logDataDtoMapper.getOutLogListBySku(skuList);

        String outPath = "/Users/dhs/Downloads/审计数据/SKU/出库/" + "出库记录" + ".xlsx";
        exportOutExcel(outList, outSheetName, outPath);
    }

}
