package com.jxx.dataTest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jxx.JxxApplicationTests;
import com.jxx.excel.InExport;
import com.jxx.excel.OutExport;
import com.jxx.excel.StockExport;
import com.jxx.mapper.ExportMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Strange
 * @ClassName DataCheckExportTest.java
 * @Description TODO
 * @createTime 2021年04月19日 18:46:00
 */

public class DataCheckExportTest extends JxxApplicationTests {

    @Resource
    private ExportMapper exportMapper;

    private static ExecutorService service = new ThreadPoolExecutor(3, 3,
            0L, TimeUnit.MILLISECONDS,  new LinkedBlockingQueue<Runnable>(1024),
            new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());

    @Test
    public void test() throws Exception{
        LocalDateTime startTime = LocalDateTime.of(2018,12,1,0,0,0);

        do {
            final CountDownLatch latch = new CountDownLatch(1);

            LocalDateTime endTime = startTime.plusMonths(1);

            LocalDateTime finalStartTime = startTime;

//            service.submit(new Thread(){
//                public void run() {
//                    try {
//                        //出库明细
//                        writeOut(finalStartTime, endTime);
//                        //将count值减1
//                        latch.countDown();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//            service.submit( new Thread(){
//                public void run() {
//                    try {
//                        //入库明细
//                        writeIn(finalStartTime, endTime);
//                        //将count值减1
//                        latch.countDown();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });

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

    private void writeIn(LocalDateTime startTime, LocalDateTime endTime)throws Exception {
        List<InExport> inLIst = exportMapper.selectInAll(localDatetime2TimeStamp(startTime),localDatetime2TimeStamp(endTime));
        File file = new File("/Users/dhs/Downloads/data/in/in"+startTime.getYear()+"-"+startTime.getMonth().getValue()+".txt");
        OutputStreamWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        for (InExport export : inLIst) {
            bufferedWriter.write( export.getSKU() +"\t"+ export.get数量() +"\t"+export.get入库金额()+"\r\n");
            bufferedWriter.flush();
        }
        bufferedWriter.close();
        writer.close();
    }

    private void writeOut(LocalDateTime startTime, LocalDateTime endTime) throws Exception{
        List<OutExport> outlist = exportMapper.selectOutAll(localDatetime2TimeStamp(startTime),localDatetime2TimeStamp(endTime));
        File file = new File("/Users/dhs/Downloads/data/out/out"+startTime.getYear()+"-"+startTime.getMonth().getValue()+".txt");
        OutputStreamWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        for (OutExport export : outlist) {
            bufferedWriter.write( export.getSKU() +"\t"+ export.get数量() +"\t"+export.get出库金额()+"\r\n");
            bufferedWriter.flush();
        }
        bufferedWriter.close();
        writer.close();
    }

    private void exportStock(LocalDateTime startTime, LocalDateTime endTime) throws IOException {
        List<StockExport> stockExportList = exportMapper.selectStockSnapshot(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));

        File file = new File("/Users/dhs/Downloads/data/stock/stock"+startTime.getYear()+"-"+startTime.getMonth().getValue()+".txt");
        OutputStreamWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        for (StockExport export : stockExportList) {
            bufferedWriter.write( export.getSKU() +"\t"+ export.get库存数量() +"\t"+export.get库存金额新()+"\r\n");
            bufferedWriter.flush();
        }
        bufferedWriter.close();
        writer.close();
    }

    private Long localDatetime2TimeStamp(LocalDateTime time){
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

}
