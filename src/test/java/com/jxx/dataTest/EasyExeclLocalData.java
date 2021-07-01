package com.jxx.dataTest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
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
        String skus = "V254558,V148766,V278327,V253744,V148724,V252331,V149640,V273321,V501506,V255666,V273316,V272058,V111126,V276660,V257758,V277221,V277370,V271757,V277352,V277019,V251807,V254648,V278331,V277537,V148676,V276439,V277361,V117157,V254082,V116830,V254688,V118985,V254136,V500038,V503082,V140992,V253495,V276866,V276893,V254641,V116867,V253494,V253492,V254736,V254696,V116842,V252961,V277346,V277357,V276524";
        List<String> skuList = Arrays.asList(skus.split(","));
        String inSheetName = "入库记录";
        String outSheetName = "出库记录";
        String inPath = "/Users/dhs/Downloads/审计数据/SKU/入库/" + "入库记录" + ".xlsx";
        List<InStockDataDo> inList = logDataDtoMapper.getInLogListBySku(skuList);
        exportInExcel(inList, inSheetName, inPath);

        List<OutStockDataDo> outList = logDataDtoMapper.getOutLogListBySku(skuList);

        String outPath = "/Users/dhs/Downloads/审计数据/SKU/出库/" + "出库记录" + ".xlsx";
        exportOutExcel(outList, outSheetName, outPath);
        ExcelWriter excelWriter = EasyExcel.write("/Users/dhs/Downloads/123.xlsx").build();
        WriteSheet writeSheet = EasyExcel.writerSheet( 1,"入库记录" ).head(InStockDataDo.class).build();
        excelWriter.write(inList, writeSheet);
        WriteSheet writeSheet2 = EasyExcel.writerSheet( 2,"出库记录" ).head(OutStockDataDo.class).build();
        excelWriter.write(outList, writeSheet2);

//        ExcelWriter excelWriter = EasyExcel.write("/Users/dhs/Downloads/123.xlsx").build();
//        for (int i = 0; i < 2; i++) {
//            if(i == 0){
//                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
//                WriteSheet writeSheet = EasyExcel.writerSheet(i, "入库记录" ).head(InStockDataDo.class).build();
//                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
//                excelWriter.write(inList, writeSheet);
//            }
//            if(i == 1){
//                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
//                WriteSheet writeSheet = EasyExcel.writerSheet(i, "出库记录" ).head(OutStockDataDo.class).build();
//                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
//                excelWriter.write(outList, writeSheet);
//            }
//
//        }
//        if(excelWriter != null){
//            excelWriter.finish();
//        }
    }

}
