package com.jxx.dataTest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.jxx.JxxApplicationTests;
import com.jxx.common.utils.DateUtil;
import com.jxx.excel.LogDataDo;
import com.jxx.excel.model.NoModelDataListener;
import com.jxx.mapper.LogDataDtoMapper;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Strange
 * @ClassName WeightedPriceTest.java
 * @Description TODO 赋值加权价
 * @createTime 2021年05月13日 14:47:00
 */
public class WeightedPriceTest  extends JxxApplicationTests {

    @Resource
    private LogDataDtoMapper logDataDtoMapper;

    @Test
    public void weightedPriceTest(){
        String path = "/Users/dhs/Downloads/进销存.xlsx";
        NoModelDataListener<HashMap<Integer,String>> listen = new NoModelDataListener();
        ExcelReaderBuilder readerBuilder = EasyExcel.read(path, listen);
        readerBuilder.doReadAll();
        Map<String, Map<String, BigDecimal>> stockpriceMap = new HashMap<>();
        AtomicInteger count = new AtomicInteger();
        //读取每个sku每个月的出库单价
        listen.list.forEach(item-> {
            LocalDateTime startTime = LocalDateTime.of(2018, 12, 1, 0, 0, 0);
            String sku = item.get(0);
            Map<String, BigDecimal> monthPrice = new HashMap<>();
            int index = 12;
            do {
                LocalDateTime endTime = startTime.plusMonths(1);
                String time = DateUtil.yearMonthStr(startTime);
                BigDecimal outPrice = new BigDecimal(item.get(index));
                if(outPrice.compareTo(BigDecimal.ZERO) > 0) {
                    monthPrice.put(time, outPrice);
                    count.getAndIncrement();
                }
                index+=9;
                startTime = endTime;
            } while (startTime.isBefore(LocalDateTime.of(2021,4,1,0,0,0)));
            if(!monthPrice.isEmpty()){
                stockpriceMap.put(sku,monthPrice);
            }
        });
        LogDataDo search = new LogDataDo();

        LogDataDo update = new LogDataDo();

        //获取直接赋予加权价
        List<LogDataDo> outWeightedPriceLogList = logDataDtoMapper.getOutWeightedPriceLogInfo(search);
        for (LogDataDo logDataDo : outWeightedPriceLogList) {
            Map<String, BigDecimal> monthPrice = stockpriceMap.get(logDataDo.getSku());
            BigDecimal weightedPrice = monthPrice.get(logDataDo.getYearMonth());
            update.setInLogId(logDataDo.getInLogId());
            update.setNewCostPrice(weightedPrice);
            update.setTotalAmount(weightedPrice.multiply(new BigDecimal(logDataDo.getNum())));
        }

        //所有有售后部分的销售单
        HashMap<String,List<LogDataDo>> saleOutMap = new HashMap<>();
        //销售售后入库类型
        List<LogDataDo> saleAfterInLogList = logDataDtoMapper.getSaleAfterInLogList(search);
        for (LogDataDo logDataDo : saleAfterInLogList) {
            String key = logDataDo.getAssOrderNo() + "," + logDataDo.getSku();
            List<LogDataDo> logDataDoList = saleOutMap.get(key);
            if(CollectionUtils.isEmpty(logDataDoList)){
                logDataDoList =  logDataDtoMapper.getSaleOutLogList(logDataDo.getAssOrderNo(), logDataDo.getAssOrderNo(),logDataDo.getSku());;
                saleOutMap.put(key,logDataDoList);
            }
        }

        for (LogDataDo logDataDo : saleAfterInLogList) {
            String key = logDataDo.getAssOrderNo() + "," + logDataDo.getSku();
            List<LogDataDo> saleList = saleOutMap.get(key);
            if(CollectionUtils.isEmpty(saleList)){
                continue;
            }
            //售后总数
            Integer allAfterNum = logDataDo.getNum();
            //以使用数据
            List<LogDataDo> useList = new ArrayList<>();

            for (LogDataDo dataDo : saleList) {
                Integer saleNum = dataDo.getNum();
                if(saleNum == 0 || allAfterNum == 0){
                    continue;
                }
                LogDataDo useLog = new LogDataDo();
                useLog.setYearMonth(dataDo.getYearMonth());
                useLog.setNewCostPrice(dataDo.getNewCostPrice());
                useLog.setOrderNo(dataDo.getOrderNo());
                useLog.setInLogId(dataDo.getInLogId());
                if(saleNum >= allAfterNum){
                    dataDo.setNum(saleNum - allAfterNum);
                    useLog.setNum(allAfterNum);
                    allAfterNum = 0;
                }else{
                    allAfterNum = allAfterNum - dataDo.getNum();
                    useLog.setNum(dataDo.getNum());
                    dataDo.setNum(0);
                }
                useLog.setTotalAmount(useLog.getNewCostPrice().multiply(new BigDecimal(useLog.getNum())));
                useList.add(useLog);
            }
            if(CollectionUtils.isEmpty(useList)){
                continue;
            }
            BigDecimal allTotalAmount = BigDecimal.ZERO;
            Integer allNum = 0;
            for (LogDataDo dataDo : useList) {
                allTotalAmount = allTotalAmount.add(dataDo.getTotalAmount());
                allNum = allNum + dataDo.getNum();
            }
            BigDecimal result = allTotalAmount.divide(new BigDecimal(allNum), 2, BigDecimal.ROUND_HALF_UP);
            update.setInLogId(logDataDo.getInLogId());
            update.setNewCostPrice(result);
            update.setTotalAmount(result.multiply(new BigDecimal(logDataDo.getNum())));
        }
    }

}
