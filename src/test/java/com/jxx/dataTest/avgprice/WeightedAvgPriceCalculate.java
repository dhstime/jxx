package com.jxx.dataTest.avgprice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: daniel
 * @Date: 2021/5/20 16 21
 * @Description:
 */
public class WeightedAvgPriceCalculate {

    public  void weightedAvgPriceCalculate(){
        String pathPrefix = "/Users/daniel/desktop/归档/";

        //yearMonth-sku-avgPrice
        Map<String, Map<String, BigDecimal>> skuWeightedAvgMap = new HashMap<>();

        //根据2018-12库存明细，获取2018-12期末
        Map<String, ExcelFileUtil.Result> lastFinalEnding = FinalEndingCalculate.getGroupCountBySku(pathPrefix+"库存明细/库存明细2018-12.xlsx",8,13,17);

        LocalDate start = LocalDate.of(2019,1,1);
        do {
            String yearMonth = start.getYear() + "-" + start.getMonthValue();
            skuWeightedAvgMap.put(yearMonth,getWeightedAvgPriceEveryMonth(pathPrefix,yearMonth,lastFinalEnding));
            lastFinalEnding = getFinalEndingEveryMonth(pathPrefix,yearMonth,lastFinalEnding,skuWeightedAvgMap.get(yearMonth));
            start = start.plusMonths(1);
        } while (start.isBefore(LocalDate.of(2021,4,1)));


    }


    /**
     * 期末 = 上期期末 + 入库 - 出库
     * @param pathPrefix 文件路径前缀
     * @param yearMonth 年月
     * @param lastFinalEnding 上月期末
     * @return 本月期末
     */
    public  Map<String, ExcelFileUtil.Result> getFinalEndingEveryMonth(String pathPrefix, String yearMonth,
                                                                             Map<String, ExcelFileUtil.Result> lastFinalEnding, Map<String,
            BigDecimal> weightedAvgPriceMap){
        Map<String, Map<String, BigDecimal>> weightedAvgPriceInMonthMap = new HashMap<>();
        weightedAvgPriceMap.keySet().parallelStream().forEach(
                sku -> {
                    Map<String, BigDecimal> yearMonthAvgPrice = new HashMap<>();
                    yearMonthAvgPrice.put(yearMonth,weightedAvgPriceMap.get(sku));
                    weightedAvgPriceInMonthMap.put(sku,yearMonthAvgPrice);
                }
        );

        //根据当月加权价获取当月出入库明细
        //TODO
        Map<String,Map<String, ExcelFileUtil.Result>> inputAndOutPutMap = null;

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
    public  Map<String, BigDecimal> getWeightedAvgPriceEveryMonth(String pathPrefix, String yearMonth,
                                                                        Map<String, ExcelFileUtil.Result> lastFinalEnding){
        Map<String, ExcelFileUtil.Result> input = FinalEndingCalculate.getGroupCountBySku(pathPrefix+"入库明细/入库" + yearMonth + ".xlsx",15,25,27);

        Map<String, BigDecimal> weightedAvgPrice = new HashMap<>();

        Set<String> skuSet = new HashSet<>(lastFinalEnding.keySet());
        skuSet.addAll(input.keySet());
        skuSet.parallelStream()
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
                    weightedAvgPrice.put(key,sum.divide(count,2, RoundingMode.HALF_UP));
                });

        return weightedAvgPrice;
    }


    /**
     * 将每月sku加权价保存到excel文件
     * @param weightedAvgPrice 加权价 yearMonth,sku,Result
     * @param excelPath excel文件路径
     */
    public  void weightedAvgPrice2Excel(Map<String,Map<String, ExcelFileUtil.Result>> weightedAvgPrice, String excelPath){
        Set<String> skuSet = weightedAvgPrice.keySet()
                .stream()
                .flatMap(key -> weightedAvgPrice.get(key).keySet().stream())
                .collect(Collectors.toSet());

        List<List<Object>> excelResult = skuSet.parallelStream()
                .map(sku -> {
                    List<Object> row = new ArrayList<>();
                    LocalDate start = LocalDate.of(2019,1,1);
                    do {
                        String yearMonth = start.getYear() + "-" + start.getMonthValue();
                        if (weightedAvgPrice.containsKey(yearMonth) && weightedAvgPrice.get(yearMonth).containsKey(sku)){
                            row.add(weightedAvgPrice.get(yearMonth).get(sku));
                        }
                        start = start.plusMonths(1);
                    } while (start.isBefore(LocalDate.of(2021,4,1)));
                    return row;
                })
                .collect(Collectors.toList());

        ExcelFileUtil.generateExcel(excelPath + "/sku加权价.xlsx",header(),excelResult);
    }


    private  List<String> header(){
        List<String> header = new ArrayList<>();
        header.add("SKU");
        LocalDate start = LocalDate.of(2019,1,1);
        do {
            header.add(start.getYear() + "-" + start.getMonthValue());
            start = start.plusMonths(1);
        } while (start.isBefore(LocalDate.of(2021,4,1)));
        return header;
    }
}
