package com.jxx.dataTest;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.excel.EasyExcel;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author Strange
 * @ClassName DataExportTest.java
 * @Description TODO 财务数据导出
 * @createTime 2021年04月12日 11:24:00
 */
public class DataExportTest extends JxxApplicationTests {

    @Resource
    private ExportMapper exportMapper;

//    @Test
    public void pufaExport() throws Exception{

        LocalDateTime startTime = LocalDateTime.of(2018,12,1,0,0,0);

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
        LocalDateTime endTime = LocalDateTime.of(2021,4,1,0,0,0);
        List<OutExport> salelist = exportMapper.selectDirectOutAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
        List<InExport> buyList = exportMapper.selectDirectInAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
        List<InExport> saleShList = exportMapper.getSaleAfterIn(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
        List<OutExport> buyShlist = exportMapper.getBuyorderAfterOut(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));

        exoprtZhifaIn(startTime, endTime,buyList,saleShList);
        exoprtZhifaOut(startTime, endTime,salelist,buyShlist);
//        zhifaDiff(startTime, endTime,salelist,buyList);
        exoprtZhifaInSh(saleShList);
        exoprtZhifaOutSh(buyShlist);
//
//        List<SaleorderInvoice> saleorderInvoices = exportMapper.getSaleorderInvoice();
//        List<SaleorderInvoice> buyorderInvoices = exportMapper.getBuyorderInvoice();
//        getInvoiceNums(saleorderInvoices,buyorderInvoices);
        System.out.println("直发完成");
    }

    private void zhifaDiff(LocalDateTime startTime, LocalDateTime endTime,List<OutExport> salelist, List<InExport> buyList){
//        List<OutExport> salelist = exportMapper.selectDirectOutAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
//        List<InExport> buyList = exportMapper.selectDirectInAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
        List<SaleorderDiff> afterList = exportMapper.getSaleorderBefore();
        List<InExport> filterList = new ArrayList<>();
        for (InExport buy : buyList){
            BigDecimal afterSaleNum = afterList.stream().filter(a -> a.getSaleorderGoodsId().equals(buy.getBuyorderGoodsId()))
                    .map( a -> a.getNum()).findFirst().orElse(BigDecimal.ZERO);
            if (afterSaleNum.compareTo(BigDecimal.ZERO) == 1) {
                if (buy.get数量().compareTo(afterSaleNum) == 1) {
                    buy.set数量(buy.get数量().subtract(afterSaleNum));
                }
            }
            filterList.add(buy);
        }

        List<DiffExport> diffExportList = new ArrayList<>();
        for (OutExport out : salelist){


            //取采购单list
            List<InExport> inList = filterList.stream().filter(c -> c.getSaleorderGoodsId().equals(out.getSaleorderGoodsId()))
                    .collect(Collectors.toList());

            //取采购单数量总和
            Double sum = inList.stream().mapToDouble(c -> c.get数量().doubleValue()).sum();

            if (sum.compareTo(out.get数量().doubleValue()) != 0) {
                for (InExport a : inList) {
                    DiffExport diffExport = new DiffExport();
                    diffExport.setOrderNo(out.get单号());
                    diffExport.setOutOrderStatus(out.get订单状态());
                    diffExport.setOutTraderName(out.get客户名称());
                    diffExport.setOutNum(out.get数量());
                    diffExport.setOutSku(out.getSKU());
                    diffExport.setGg(out.get规格());
                    diffExport.setDw(out.get计量单位());
                    diffExport.setBrand(out.get品牌());
                    diffExport.setUser(out.getUsername());
                    diffExport.setDepart(out.get归属部门());
                    diffExport.setSkuName(out.get商品名称());

                    diffExport.setBelongUser(a.getUsername());
                    diffExport.setBuyorderGoodsId(a.getBuyorderGoodsId());
                    diffExport.setSaleorderGoodsId(a.getSaleorderGoodsId());
                    diffExport.setBuyorderNo(a.get单号());
                    diffExport.setInOrderStatus(a.get订单状态());
                    diffExport.setInTraderName(a.get客户名称());
                    diffExport.setInNum(a.get数量());
                    diffExport.setOutTime(out.get出库时间());

                    diffExportList.add(diffExport);
                }
            }

            if (filterList.stream().noneMatch(t -> t.get关联单号().equals(out.get单号()) && t.getSaleorderGoodsId().equals(out.getSaleorderGoodsId()))) {
                DiffExport diffExport = new DiffExport();
                diffExport.setOrderNo(out.get单号());
                diffExport.setOutNum(out.get数量());
                diffExport.setOutTime(out.get出库时间());
                diffExport.setOutSku(out.getSKU());
                diffExport.setOutTraderName(out.get客户名称());
                diffExport.setGg(out.get规格());
                diffExport.setDw(out.get计量单位());
                diffExport.setBrand(out.get品牌());
                diffExport.setUser(out.getUsername());
                diffExport.setDepart(out.get归属部门());
                diffExport.setSkuName(out.get商品名称());
                diffExport.setOutOrderStatus(out.get订单状态());
                diffExportList.add(diffExport);

            }

        }

        String titleName = "差异数据";
        String path = "/Users/dhs/Downloads/直发出入库含售后/差异/采购销售差异.xlsx";
        String infileName = "差异数据";

        EasyExcel.write(path, DiffExport.class)
                .registerWriteHandler(new TitleSheetWriteHandler(titleName,31)) // 标题及样式，lastCol为标题第0列到底lastCol列的宽度
                //设置默认样式及写入头信息开始的行数
                .relativeHeadRowIndex(1)
                .registerWriteHandler(BizMergeStrategy.CellStyleStrategy()) // 设置样式
                .sheet(infileName)
                .doWrite(diffExportList);
    }



    private void exoprtZhifaIn(LocalDateTime startTime, LocalDateTime endTime,List<InExport> inList,List<InExport> saleShList) throws Exception{
//        List<InExport> inList = exportMapper.selectDirectInAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));

        List<Integer> afterIdList = exportMapper.getSaleorderBeforeId();
        List<InExport> filterList = saleShList.stream().filter(c -> !afterIdList.contains(c.getAfterSalesGoodsId())).collect(Collectors.toList());

        inList.addAll(filterList);

        String titleName = "入库记录" ;
        String infileName = "入库" ;

        String path = "/Users/dhs/Downloads/直发出入库含售后/入库/直发入库.xlsx";

        EasyExcel.write(path, InExport.class)
                .registerWriteHandler(new TitleSheetWriteHandler(titleName,31)) // 标题及样式，lastCol为标题第0列到底lastCol列的宽度
                //设置默认样式及写入头信息开始的行数
                .relativeHeadRowIndex(1)
                .registerWriteHandler(BizMergeStrategy.CellStyleStrategy()) // 设置样式
                .sheet(infileName)
                .doWrite(inList);
    }


    private void exoprtZhifaOut(LocalDateTime startTime, LocalDateTime endTime,List<OutExport> outlist,List<OutExport> buyShlist) throws Exception{
//        List<OutExport> outlist = exportMapper.selectDirectOutAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));

        List<SaleorderDiff> afterList = exportMapper.getSaleorderBefore();
        List<OutExport> filterList = new ArrayList<>();
        for (OutExport out : outlist){
            BigDecimal afterSaleNum = afterList.stream().filter(a -> a.getSaleorderGoodsId().equals(out.getSaleorderGoodsId()))
                    .map( a -> a.getNum()).findFirst().orElse(BigDecimal.ZERO);
            if (afterSaleNum.compareTo(BigDecimal.ZERO) == 1) {
                if (out.get数量().compareTo(afterSaleNum) == 1) {
                    out.set数量(out.get数量().subtract(afterSaleNum));
                }
            }
            filterList.add(out);
        }

        filterList.addAll(buyShlist);

        String titleName = "出库记录" ;
        String outfileName = "出库" ;
        String path = "/Users/dhs/Downloads/直发出入库含售后/出库/直发出库.xlsx";


        EasyExcel.write(path, OutExport.class)
                .registerWriteHandler(new TitleSheetWriteHandler(titleName,31)) // 标题及样式，lastCol为标题第0列到底lastCol列的宽度
                //设置默认样式及写入头信息开始的行数
                .relativeHeadRowIndex(1)
                .registerWriteHandler(BizMergeStrategy.CellStyleStrategy()) // 设置样式
                .sheet(outfileName)
                .doWrite(filterList);
    }

    private void exoprtZhifaOutSh(List<OutExport> outlist) throws Exception{
        String titleName = "直发采购售后出库记录";
        String outfileName = "直发采购售后出库";
        String path = "/Users/dhs/Downloads/直发出入库含售后/出库/直发采购售后出库.xlsx";


        EasyExcel.write(path, OutExport.class)
                .registerWriteHandler(new TitleSheetWriteHandler(titleName,31)) // 标题及样式，lastCol为标题第0列到底lastCol列的宽度
                //设置默认样式及写入头信息开始的行数
                .relativeHeadRowIndex(1)
                .registerWriteHandler(BizMergeStrategy.CellStyleStrategy()) // 设置样式
                .sheet(outfileName)
                .doWrite(outlist);
    }

    private void exoprtZhifaInSh(List<InExport> inList) throws Exception{
        String titleName = "直发销售售后入库记录";
        String infileName = "直发销售售后入库" ;

        List<Integer> afterIdList = exportMapper.getSaleorderBeforeId();
        List<InExport> filterList = inList.stream().filter(c -> !afterIdList.contains(c.getAfterSalesGoodsId())).collect(Collectors.toList());

        String path = "/Users/dhs/Downloads/直发出入库含售后/入库/直发销售售后入库.xlsx";

        EasyExcel.write(path, InExport.class)
                .registerWriteHandler(new TitleSheetWriteHandler(titleName,31)) // 标题及样式，lastCol为标题第0列到底lastCol列的宽度
                //设置默认样式及写入头信息开始的行数
                .relativeHeadRowIndex(1)
                .registerWriteHandler(BizMergeStrategy.CellStyleStrategy()) // 设置样式
                .sheet(infileName)
                .doWrite(filterList);
    }

    private void getInvoiceNums(List<SaleorderInvoice> sale,List<SaleorderInvoice> buy){
        String titleName = "直发订单销售采购对应表";
        String infileName = "直发订单销售采购对应表" ;

        String path = "/Users/dhs/Downloads/直发出入库含售后/开票/直发订单销售采购对应表.xlsx";

        for (SaleorderInvoice b : buy) {
            SaleorderInvoice saleNums = sale.stream().filter(s -> s.getSaleorderGoodsId().equals(b.getSaleorderGoodsId()))
                    .findFirst().orElse(null);
            if (null != saleNums) {
                b.setSaleorderNo(saleNums.getSaleorderNo());
                b.setSaleAfNum(saleNums.getSaleAfNum());
                b.setSaleNum(saleNums.getSaleNum());
                b.setSaleDept(saleNums.getSaleDept());
                b.setSaleInvoNum(saleNums.getSaleInvoNum());
                b.setSaleUserName(saleNums.getSaleUserName());
                b.setSalePrice(saleNums.getSalePrice());
                b.setSaleTraderName(saleNums.getSaleTraderName());
                b.setSaleValidTime(saleNums.getSaleValidTime());
                b.setBuyNumAct(b.getBuyNum()-b.getBuyAfNum());
                b.setBrandname(saleNums.getBrandname());
                b.setSku(saleNums.getSku());
                b.setSkuname(saleNums.getSkuname());
                b.setModel(saleNums.getModel());
                b.setUnitname(saleNums.getUnitname());
            }
        }

        EasyExcel.write(path, SaleorderInvoice.class)
                .registerWriteHandler(new TitleSheetWriteHandler(titleName,31)) // 标题及样式，lastCol为标题第0列到底lastCol列的宽度
                //设置默认样式及写入头信息开始的行数
                .relativeHeadRowIndex(1)
                .registerWriteHandler(BizMergeStrategy.CellStyleStrategy()) // 设置样式
                .sheet(infileName)
                .doWrite(buy);

    }

}
