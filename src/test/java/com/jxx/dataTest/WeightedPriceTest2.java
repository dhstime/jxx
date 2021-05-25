package com.jxx.dataTest;

import com.jxx.JxxApplicationTests;
import com.jxx.common.utils.DateUtil;
import com.jxx.dataTest.avgprice.ExcelFileUtil;
import com.jxx.dataTest.avgprice.FinalEndingCalculate;
import com.jxx.excel.LogDataDo;
import com.jxx.mapper.LogDataDtoMapper;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Strange
 * @ClassName WeightedPriceTest2.java
 * @Description TODO
 * @createTime 2021年05月20日 16:29:00
 */
public class WeightedPriceTest2 extends JxxApplicationTests {

    @Resource
    private LogDataDtoMapper logDataDtoMapper;

    @Test
    public  void weightedAvgPriceCalculate(){
        String pathPrefix = "/Users/dhs/Downloads/审计数据/";

        //yearMonth-sku-avgPrice
        Map<String, Map<String, BigDecimal>> skuWeightedAvgMap = new HashMap<>();

        Map<String,Map<String, ExcelFileUtil.Result>> stockMap = new HashMap<>();
        Map<String,Map<String, ExcelFileUtil.Result>> inputMap = new HashMap<>();
        Map<String,Map<String, ExcelFileUtil.Result>> outputMap = new HashMap<>();

        //根据2018-12库存明细，获取2018-12期末
        Map<String, ExcelFileUtil.Result> lastFinalEnding = FinalEndingCalculate.getGroupCountBySku(pathPrefix+"库存明细/库存明细2018-12.xlsx",8,13,17);
        Map<String, ExcelFileUtil.Result> stock201812 = lastFinalEnding;
        LocalDate start = LocalDate.of(2019,1,1);

        try {
            do {
                String yearMonth = DateUtil.yearMonthStr(start);
                Map<String,BigDecimal> weightAvgPriceInMonth = getWeightedAvgPriceEveryMonth(pathPrefix,yearMonth,lastFinalEnding);
                skuWeightedAvgMap.put(yearMonth,weightAvgPriceInMonth);
                lastFinalEnding = getFinalEndingEveryMonth(pathPrefix,yearMonth,lastFinalEnding,skuWeightedAvgMap.get(yearMonth));

                LogDataDo search = new LogDataDo();
                search.setYearMonth(yearMonth);

                Map<String, ExcelFileUtil.Result> inMap = new HashMap<>();
                //入库
                search.setLogType(0);
                List<LogDataDo> inTotalInfoList  = logDataDtoMapper.getWeihthedInTotalInfo(search);
                putPriceMap(inTotalInfoList,inMap);

                Map<String, ExcelFileUtil.Result> outMap = new HashMap<>();
                //出库
                search.setLogType(1);
                List<LogDataDo> outTotalInfoList  = logDataDtoMapper.getWeihthedInTotalInfo(search);
                putPriceMap(outTotalInfoList,outMap);


                stockMap.put(yearMonth,lastFinalEnding);
                inputMap.put(yearMonth,inMap);
                outputMap.put(yearMonth,outMap);

                System.out.println(yearMonth);

                start = start.plusMonths(1);
            } while (start.isBefore(LocalDate.of(2021,4,1)));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            weightedAvgPrice2Excel(stock201812,stockMap,inputMap,outputMap,"/Users/dhs/Downloads/");
        }

    }


    /**
     * 期末 = 上期期末 + 入库 - 出库
     * @param pathPrefix 文件路径前缀
     * @param yearMonth 年月
     * @param lastFinalEnding 上月期末
     * @return 本月期末
     */
    private  Map<String, ExcelFileUtil.Result> getFinalEndingEveryMonth(String pathPrefix, String yearMonth,
                                                                        Map<String, ExcelFileUtil.Result> lastFinalEnding, Map<String,
            BigDecimal> weightedAvgPriceMap){
        Map<String, Map<String, BigDecimal>> weightedAvgPriceInMonthMap = new HashMap<>();
        weightedAvgPriceMap.keySet().stream().forEach(
                sku -> {
                    Map<String, BigDecimal> yearMonthAvgPrice = new HashMap<>();
                    yearMonthAvgPrice.put(yearMonth,weightedAvgPriceMap.get(sku));
                    weightedAvgPriceInMonthMap.put(sku,yearMonthAvgPrice);
                }
        );

        //根据当月加权价获取当月出入库明细
        //TODO
        Map<String,Map<String, ExcelFileUtil.Result>> inputAndOutPutMap = updateWeightedPrice(weightedAvgPriceInMonthMap);
//        LogDataDo search = new LogDataDo();
//        search.setYearMonth(yearMonth);
//        HashMap<String, Map<String, ExcelFileUtil.Result>> inputAndOutPutMap = getMonthStockPriceInfo(search);

        Map<String, ExcelFileUtil.Result> input = inputAndOutPutMap.get("input");
        Map<String, ExcelFileUtil.Result> output = inputAndOutPutMap.get("output");

        return FinalEndingCalculate.skuStatistics(yearMonth,lastFinalEnding,input,output);

    }


    /**
     * 每月的加权价
     * 加权价 = （上月期末 + 入库） / 数量
     * @param pathPrefix 文件路径前缀
     * @param yearMonth 年月
     * @return 当月加权价
     */
    private  Map<String, BigDecimal> getWeightedAvgPriceEveryMonth(String pathPrefix, String yearMonth,
                                                                   Map<String, ExcelFileUtil.Result> lastFinalEnding){
//        Map<String, ExcelFileUtil.Result> input = getGroupCountBySku(pathPrefix+"入库明细/入库" + yearMonth + ".xlsx",15,25,29);
        Map<String, ExcelFileUtil.Result> input = inLogGroupBySku(yearMonth);

        Map<String, BigDecimal> weightedAvgPrice = new HashMap<>();

        Set<String> skuSet = new HashSet<>(lastFinalEnding.keySet());
        skuSet.addAll(input.keySet());
        skuSet.stream()
                .forEach(key -> {
                    BigDecimal count = BigDecimal.ZERO;
                    BigDecimal sum = BigDecimal.ZERO;
                    if (lastFinalEnding.containsKey(key)){
                        sum = sum.add(lastFinalEnding.get(key).getSum());
                        count = count.add(lastFinalEnding.get(key).getCount());
                    }
                    if (input.containsKey(key)){
                        sum = sum.add(input.get(key).getSum());
                        count = count.add(input.get(key).getCount());
                    }
                    if(count.compareTo(BigDecimal.ZERO) != 0){
                        weightedAvgPrice.put(key,sum.divide(count,2, RoundingMode.HALF_UP));
                    }
                });

        return weightedAvgPrice;
    }

    private Map<String, ExcelFileUtil.Result> inLogGroupBySku(String yearMonth) {
        LogDataDo search = new LogDataDo();
        LogDataDo update = new LogDataDo();

        //获取采购单入库类型
        List<LogDataDo> inLogList = logDataDtoMapper.getWeightPriceSource(yearMonth);
        //获取销售售后入库类型
        List<LogDataDo> afterSaleInlogList =  logDataDtoMapper.getAfterSaleInlogList(yearMonth);

        inLogList.addAll(afterSaleInlogList);

        Map<String, List<ExcelFileUtil.Result>> skuMap = inLogList.stream()
                .map(item ->{
                    ExcelFileUtil.Result result = new ExcelFileUtil.Result();
                    result.setKey(item.getSku());
                    result.setCount(new BigDecimal(item.getNum()));
                    if(equesOrderType(item.getOrderType())){

                        updateSaleAfterInWeightPrice(search, update, result, item.getWarehouseLogId());
                    }else{
                        result.setSum(item.getRealTotalAmount());
                    }

                    return result;
                }).collect(Collectors.groupingBy(ExcelFileUtil.Result::getKey));


        return  skuMap.keySet().stream()
                .map(key -> {
                    BigDecimal count = BigDecimal.ZERO;
                    BigDecimal sum = BigDecimal.ZERO;
                    for (ExcelFileUtil.Result r : skuMap.get(key)){
                        count = count.add(r.getCount());
                        sum = sum.add(r.getSum());
                    }
                    ExcelFileUtil.Result result = new ExcelFileUtil.Result();
                    result.setKey(key);
                    result.setSum(sum);
                    result.setCount(count);
                    return result;
                }).collect(Collectors.toMap(ExcelFileUtil.Result::getKey,item->item));
    }

    private   Map<String, ExcelFileUtil.Result> getGroupCountBySku(String path, Integer keyIndex, Integer countIndex,
                                                                   Integer sumIndex){
        LogDataDo search = new LogDataDo();
        LogDataDo update = new LogDataDo();
        Map<String, List<ExcelFileUtil.Result>> skuMap = ExcelFileUtil.getDataFromExcelFile(path)
                .stream()
                .filter(item -> !item.get(2).equalsIgnoreCase("单据类型"))
                .filter(item -> !excludeOrderTypesList(item.get(2)))
                .map(item -> {
                    ExcelFileUtil.Result result = new ExcelFileUtil.Result();
                    result.setKey(item.get(keyIndex));
                    result.setCount(new BigDecimal(item.get(countIndex)));

                    if(equesOrderType(item.get(2))){
                        Integer warehouseId =Integer.valueOf(item.get(0));
                        updateSaleAfterInWeightPrice(search, update, result, warehouseId);

                    }else{
                        result.setSum(new BigDecimal(item.get(sumIndex)));
                    }
                    return result;
                })
                .collect(Collectors.groupingBy(ExcelFileUtil.Result::getKey));
        return skuMap.keySet().stream()
                .map(key -> {
                    BigDecimal count = BigDecimal.ZERO;
                    BigDecimal sum = BigDecimal.ZERO;
                    for (ExcelFileUtil.Result r : skuMap.get(key)){
                        count = count.add(r.getCount());
                        sum = sum.add(r.getSum());
                    }
                    ExcelFileUtil.Result result = new ExcelFileUtil.Result();
                    result.setKey(key);
                    result.setSum(sum);
                    result.setCount(count);
                    return result;
                }).collect(Collectors.toMap(ExcelFileUtil.Result::getKey,item->item));
    }

    /**
     * @description: 赋值处理销售售后入库类型加权价
     * @return:
     * @author: Strange
     * @date: 2021/5/22
     **/
    private void updateSaleAfterInWeightPrice(LogDataDo search, LogDataDo update, ExcelFileUtil.Result result, Integer warehouseId) {
        LogDataDo afterOrder = logDataDtoMapper.getInfoByWarehouseId(warehouseId);
        search.setOrderNo(afterOrder.getAssOrderNo());
        search.setSku(afterOrder.getSku());
        search.setYearMonth(afterOrder.getYearMonth());
        List<LogDataDo> saleOutLogList = logDataDtoMapper.getSaleOutPriceLogList(search);
        if(CollectionUtils.isEmpty(saleOutLogList)){
            update.setInLogId(afterOrder.getInLogId());
            update.setRealPrice(afterOrder.getNewCostPrice());
            update.setRealTotalAmount(afterOrder.getTotalAmount());
            updateLogData(update);
            result.setSum(afterOrder.getTotalAmount());
        }else{
            updateCalculateShareOrder(update, search, Collections.singletonList(afterOrder));
            afterOrder = logDataDtoMapper.getInfoByWarehouseId(warehouseId);
            result.setSum(afterOrder.getRealTotalAmount());
        }
    }

    private boolean equesOrderType(String orderType) {
        return "销售换货入库".equals(orderType) || "销售退货入库".equals(orderType);
    }


    /**
     * @description: 更新加权价获取当月全量出入库记录
     * @return:
     * @author: Strange
     * @date: 2021/5/22
     **/
    private HashMap<String,Map<String, ExcelFileUtil.Result>> updateWeightedPrice(Map<String, Map<String, BigDecimal>> stockpriceMap) {
        LogDataDo update = new LogDataDo();
        LogDataDo search = new LogDataDo();

        for (String key : stockpriceMap.keySet()) {
            Map<String, BigDecimal> stringBigDecimalMap = stockpriceMap.get(key);
            for (String month : stringBigDecimalMap.keySet()) {
                search.setYearMonth(month);
                break;
            }
        }
        //更新加权价
        updateWeightPrice(stockpriceMap, update, search);

        return getMonthStockPriceInfo(search);

    }

    /**
     * @description: 更新加权价
     * @return:
     * @author: Strange
     * @date: 2021/5/22
     **/
    private void updateWeightPrice(Map<String, Map<String, BigDecimal>> stockpriceMap, LogDataDo update, LogDataDo search) {
        //直接赋值部分处理------------------------------
        List<LogDataDo> outWeightedPriceLogList = logDataDtoMapper.getOutWeightedPriceLogInfo(search);
        for (LogDataDo logDataDo : outWeightedPriceLogList) {
            Map<String, BigDecimal> monthPrice = stockpriceMap.get(logDataDo.getSku());
            update.setInLogId(logDataDo.getInLogId());
            //类型为SKU转化入库并且当月加权价为空
            if( monthPrice == null){
                update.setRealPrice(logDataDo.getNewCostPrice());
                update.setRealTotalAmount(logDataDo.getTotalAmount());
            }else{
                BigDecimal weightedPrice = monthPrice.get(logDataDo.getYearMonth());
                update.setRealPrice(weightedPrice);
                update.setRealTotalAmount(weightedPrice.multiply(new BigDecimal(logDataDo.getNum())));
            }
            updateLogData(update);
        }
        //直接赋值部分处理------------------------------

        //批次调整出库  批次调整入库
        List<LogDataDo> adjLogInfoList = logDataDtoMapper.getAdjLogInfo(search);
        for (LogDataDo logDataDo : adjLogInfoList) {
            update.setInLogId(logDataDo.getInLogId());
            update.setRealPrice(logDataDo.getNewCostPrice());
            update.setRealTotalAmount(logDataDo.getTotalAmount());
            updateLogData(update);
        }
        //批次调整出库  批次调整入库

        //-------------分摊算法-----------------------------

        //这个月的需分摊赋值的类型
        List<LogDataDo> saleAfterInLogList = logDataDtoMapper.getSaleAfterInLogList(search);

        updateCalculateShareOrder(update, search, saleAfterInLogList);
    }

    /**
     * @description: 分摊并赋值销售售后入库类型的加权价
     * @return:
     * @date: 2021/5/22
     **/
    private void updateCalculateShareOrder(LogDataDo update, LogDataDo search, List<LogDataDo> saleAfterInLogList) {
        //所有有售后部分的销售单
        HashMap<String,List<LogDataDo>> saleOutMap = new HashMap<>();
        for (LogDataDo logDataDo : saleAfterInLogList) {
            String key = logDataDo.getAssOrderNo() + "," + logDataDo.getSku();
            List<LogDataDo> logDataDoList = saleOutMap.get(key);
            if(CollectionUtils.isEmpty(logDataDoList)){
                logDataDoList =  logDataDtoMapper.getSaleOutLogList(logDataDo.getAssOrderNo(),logDataDo.getSku(), search.getYearMonth());
                saleOutMap.put(key,logDataDoList);
            }
        }
        //开始分摊
        for (LogDataDo after : saleAfterInLogList) {
            String key = after.getAssOrderNo() + "," + after.getSku();
            List<LogDataDo> saleList = saleOutMap.get(key);
            //售后总数
            Integer allAfterNum = after.getNum();
            //以使用数据
            List<LogDataDo> useList = new ArrayList<>();
            //分摊销售单
            for (LogDataDo sale : saleList) {
                Integer saleNum = sale.getNum();
                if(saleNum == 0 || allAfterNum == 0){
                    continue;
                }
                LogDataDo useLog = new LogDataDo();
                useLog.setYearMonth(sale.getYearMonth());
                useLog.setRealPrice(sale.getRealPrice());
                useLog.setOrderNo(sale.getOrderNo());
                useLog.setInLogId(sale.getInLogId());
                if(saleNum >= allAfterNum){
                    sale.setNum(saleNum - allAfterNum);
                    useLog.setNum(allAfterNum);
                    allAfterNum = 0;
                }else{
                    allAfterNum = allAfterNum - sale.getNum();
                    useLog.setNum(sale.getNum());
                    sale.setNum(0);
                }
                useLog.setRealTotalAmount(useLog.getRealPrice().multiply(new BigDecimal(useLog.getNum())));
                useList.add(useLog);
            }
            //如果没有对于销售单,则取原来价格
            if(CollectionUtils.isEmpty(useList)){
                update.setInLogId(after.getInLogId());
                update.setRealPrice(after.getNewCostPrice());
                update.setRealTotalAmount(after.getTotalAmount());
                updateLogData(update);
                continue;
            }
            BigDecimal allTotalAmount = BigDecimal.ZERO;
            Integer allNum = 0;
            for (LogDataDo dataDo : useList) {
                allTotalAmount = allTotalAmount.add(dataDo.getRealTotalAmount());
                allNum = allNum + dataDo.getNum();
            }
            BigDecimal result = allTotalAmount.divide(new BigDecimal(allNum), 2, BigDecimal.ROUND_HALF_UP);
            update.setInLogId(after.getInLogId());
            update.setRealPrice(result);
            update.setRealTotalAmount(result.multiply(new BigDecimal(after.getNum())));
            updateLogData(update);
        }
    }

    private void updateLogData(LogDataDo update) {
        update.setRealPrice(update.getRealPrice().abs());
        update.setRealTotalAmount(update.getRealTotalAmount().abs());
        logDataDtoMapper.updateRealPriceById(update);
    }

    /**
     * @description: 获取全量出入库数据以计算库存量
     * @return:
     * @author: Strange
     * @date: 2021/5/22
     **/
    private HashMap<String, Map<String, ExcelFileUtil.Result>> getMonthStockPriceInfo(LogDataDo search) {
        HashMap<String,Map<String, ExcelFileUtil.Result>> result = new HashMap<>();
        search.setLogType(0);
        List<LogDataDo> inlogDataDoList = logDataDtoMapper.getTotalInfo(search);
        HashMap<String, ExcelFileUtil.Result> inMap = new HashMap<>();

        putPriceMap(inlogDataDoList, inMap);
        //入库
        result.put("input",inMap);

        HashMap<String, ExcelFileUtil.Result> outMap = new HashMap<>();
        search.setLogType(1);
        List<LogDataDo> outlogDataDoList = logDataDtoMapper.getTotalInfo(search);
        putPriceMap(outlogDataDoList, outMap);
        //出库
        result.put("output",outMap);
        return result;
    }

    private void putPriceMap(List<LogDataDo> inlogDataDoList, Map<String, ExcelFileUtil.Result> inMap) {
        for (LogDataDo logDataDo : inlogDataDoList) {
            if(logDataDo.getRealTotalAmount() == null || logDataDo.getNum() == 0){
                continue;
            }
            ExcelFileUtil.Result priceInfo = new ExcelFileUtil.Result();
            String sku = logDataDo.getSku();
            priceInfo.setKey(sku);
            priceInfo.setCount(new BigDecimal(logDataDo.getNum()));
            priceInfo.setSum(logDataDo.getRealTotalAmount());
            priceInfo.setPrice(logDataDo.getRealTotalAmount().divide(new BigDecimal(logDataDo.getNum()),2,BigDecimal.ROUND_HALF_UP));
            inMap.put(sku,priceInfo);
        }
    }

    private static Boolean excludeOrderTypesList(String type){
        List<String> types = new ArrayList<>();
        types.add("盘盈入库");
        types.add("批次调整入库");
        types.add("外借入库");
        types.add("调整盘盈入库");
        types.add("SKU转化入库");
        types.add("样品入库");
        return types.contains(type);
    }

    /**
     * 将每月sku加权价保存到excel文件
     * @param stock 库存 yearMonth,sku,Result
     * @param output 出库
     * @param input 入库
     */
    private void weightedAvgPrice2Excel(Map<String, ExcelFileUtil.Result> stock201812, Map<String,Map<String, ExcelFileUtil.Result>> stock,
                                        Map<String, Map<String, ExcelFileUtil.Result>> input,
                                        Map<String,Map<String, ExcelFileUtil.Result>> output, String excelPath){
        Set<String> skuSet = stock.keySet().stream().flatMap(key -> stock.get(key).keySet().stream()).collect(Collectors.toSet());
        skuSet.addAll(input.keySet().stream().flatMap(key -> input.get(key).keySet().stream()).collect(Collectors.toSet()));
        skuSet.addAll(output.keySet().stream().flatMap(key -> output.get(key).keySet().stream()).collect(Collectors.toSet()));
        skuSet.addAll(stock201812.keySet());

        List<List<Object>> excelResult = skuSet.stream()
                .map(sku -> {
                    List<Object> row = new ArrayList<>();
                    row.add(sku);
                    if (stock201812.containsKey(sku)){
                        ExcelFileUtil.Result item201812 = stock201812.get(sku);
                        row.add(item201812.getCount());
                        row.add(item201812.getSum().divide(item201812.getCount(),2,RoundingMode.HALF_UP));
                        row.add(item201812.getSum());
                    } else {
                        row.add(0);
                        row.add(0);
                        row.add(0);
                    }
                    LocalDate start = LocalDate.of(2019,1,1);
                    do {
                        String yearMonth = DateUtil.yearMonthStr(start);
                        if (input.containsKey(yearMonth) && input.get(yearMonth).containsKey(sku)){
                            ExcelFileUtil.Result inputItem = input.get(yearMonth).get(sku);
                            row.add(inputItem.getCount());
                            row.add(inputItem.getPrice());
                            row.add(inputItem.getSum());
                        } else {
                            row.add(0);
                            row.add(0);
                            row.add(0);
                        }

                        if (output.containsKey(yearMonth) && output.get(yearMonth).containsKey(sku)){
                            ExcelFileUtil.Result outItem = output.get(yearMonth).get(sku);
                            row.add(outItem.getCount());
                            row.add(outItem.getPrice());
                            row.add(outItem.getSum());
                        }   else {
                            row.add(0);
                            row.add(0);
                            row.add(0);
                        }
                        if (stock.containsKey(yearMonth) && stock.get(yearMonth).containsKey(sku)
                                && stock.get(yearMonth).get(sku).getCount().compareTo(BigDecimal.ZERO) != 0){

                            ExcelFileUtil.Result stockItem = stock.get(yearMonth).get(sku);
                            row.add(stockItem.getCount());
                            row.add(stockItem.getSum().divide(stockItem.getCount(),2,RoundingMode.HALF_UP));
                            row.add(stockItem.getSum());
                        } else {
                            row.add(0);
                            row.add(0);
                            row.add(0);
                        }
                        start = start.plusMonths(1);
                    } while (start.isBefore(LocalDate.of(2021,4,1)));
                    return row;
                })
                .collect(Collectors.toList());

        ExcelFileUtil.generateExcel(excelPath + "/sku加权价.xlsx",header(),excelResult);
        System.out.println("完成");
    }




    private  List<String> header(){
        List<String> head = new ArrayList<String>();
        head.add("sku");
        head.add("2018-12库存数量");
        head.add("2018-12库存单价");
        head.add("2018-12库存金额");
        LocalDate start = LocalDate.of(2019,1,1);
        do {
            String yearMonth = DateUtil.yearMonthStr(start);
            head.add(yearMonth+"入库数量");
            head.add(yearMonth+"入库单价");
            head.add(yearMonth+"入库金额");
            head.add(yearMonth+"出库数量");
            head.add(yearMonth+"出库单价");
            head.add(yearMonth+"出库金额");
            head.add(yearMonth+"库存数量");
            head.add(yearMonth+"库存单价");
            head.add(yearMonth+"库存金额");
            start = start.plusMonths(1);
        } while (start.isBefore(LocalDate.of(2021,4,1)));
        return head;
    }
}
