package com.jxx.dataTest;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jxx.JxxApplicationTests;
import com.jxx.excel.*;
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

    private static ExecutorService service = new ThreadPoolExecutor(3, 3,
            0L, TimeUnit.MILLISECONDS,  new LinkedBlockingQueue<Runnable>(1024),
            new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());

    @Test
    public void test() throws Exception{

        LocalDateTime startTime = LocalDateTime.of(2020,12,1,0,0,0);

        do {
            final CountDownLatch latch = new CountDownLatch(3);

            LocalDateTime endTime = startTime.plusMonths(1);

            LocalDateTime finalStartTime = startTime;

            service.submit(new Thread(){
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

            service.submit( new Thread(){
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

            service.submit(new Thread(){
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

        Workbook stockworkbook = ExcelExportUtil.exportExcel(new ExportParams("库存明细",""),StockExport.class,stockExportList);
        String stockfileName = "库存明细" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        FileOutputStream stockfos = new FileOutputStream("/Users/dhs/Downloads/数据/库存明细/"+stockfileName + ".xls");
        stockworkbook.write(stockfos);
        stockfos.close();
    }

    private void exportIn(LocalDateTime startTime, LocalDateTime endTime) throws IOException {
        List<InExport> inLIst = exportMapper.selectInAll(localDatetime2TimeStamp(startTime),localDatetime2TimeStamp(endTime));
        //直发采购
//        List<InExport> inExports = exportMapper.selectDirectInAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
//        inLIst.addAll(inExports);

        Workbook inworkbook = ExcelExportUtil.exportExcel(new ExportParams("入库记录",""),InExport.class,inLIst);
        String infileName = "入库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        FileOutputStream infos = new FileOutputStream("/Users/dhs/Downloads/数据/入库明细/"+infileName + ".xls");
        inworkbook.write(infos);
        infos.close();
    }

    private void  exportOut(LocalDateTime startTime, LocalDateTime endTime) throws IOException {
        List<OutExport> outlist = exportMapper.selectOutAll(localDatetime2TimeStamp(startTime),localDatetime2TimeStamp(endTime));
        //直发销售
//        List<OutExport> outExports = exportMapper.selectDirectOutAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
//        outlist.addAll(outExports);

        Workbook outworkbook = ExcelExportUtil.exportExcel(new ExportParams("出库记录",""),OutExport.class,outlist);
        String outfileName = "出库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        FileOutputStream outfos = new FileOutputStream("/Users/dhs/Downloads/数据/出库明细/"+outfileName + ".xls");
        outworkbook.write(outfos);
        outfos.close();
    }

    private Long localDatetime2TimeStamp(LocalDateTime time){
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    @Test
    public void test1() throws Exception{
        LocalDateTime startTime = LocalDateTime.of(2021,1,1,0,0,0);
        do {

            LocalDateTime endTime = startTime.plusMonths(1);

            exoprtlogIn(startTime, endTime);

            exportlogOut(startTime, endTime);

            System.out.println(startTime.toString());
            startTime = endTime;
        }while (startTime.isBefore(LocalDateTime.of(2021,5,1,0,0,0)));
    }

    private void exportlogOut(LocalDateTime startTime, LocalDateTime endTime)throws Exception {
        List<WarehouseOutExport> outlist = exportMapper.getWarehouseOutLogByTime(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));

        Workbook outworkbook = ExcelExportUtil.exportExcel(new ExportParams("出库记录",""),WarehouseOutExport.class,outlist);
        String outfileName = "出库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        FileOutputStream outfos = new FileOutputStream("/Users/dhs/Downloads/数据/new/"+outfileName + ".xls");
        outworkbook.write(outfos);
        outfos.close();
    }

    private void exoprtlogIn(LocalDateTime startTime, LocalDateTime endTime) throws Exception{
        List<WarehouseInExport> inList = exportMapper.getWarehouseInLogByTime(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));

        Workbook outworkbook = ExcelExportUtil.exportExcel(new ExportParams("入库记录",""),WarehouseInExport.class,inList);
        String outfileName = "入库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        FileOutputStream outfos = new FileOutputStream("/Users/dhs/Downloads/数据/new/"+outfileName + ".xls");
        outworkbook.write(outfos);
        outfos.close();
    }
}
