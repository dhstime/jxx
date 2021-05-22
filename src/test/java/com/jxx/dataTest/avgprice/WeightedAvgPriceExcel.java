package com.jxx.dataTest.avgprice;


import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 求每个月入库明细的加权平均值价格
 * @Author: daniel
 * @Date: 2021/5/11 18 07
 * @Description:
 */
public class WeightedAvgPriceExcel {

    private static final Logger logger = LoggerFactory.getLogger(WeightedAvgPriceExcel.class);

//    public static void main(String[] args) {
//        calculateWeightedPrice("/Users/daniel/desktop/归档/");
//    }


    public static void weightedPrice2Excel(String prefix){
        Map<String,Map<String, ExcelFileUtil.Result>> finalEnding = FinalEndingCalculate.getFinalEndingPriceEveryMonth(prefix);

        Map<String,Map<String, ExcelFileUtil.Result>> inputExclude = InputCalculateExclude.getInputPriceExclude(prefix);
        Set<String> skuList = finalEnding.keySet().stream().flatMap(item -> finalEnding.get(item).keySet().stream()).collect(Collectors.toSet());
        skuList.addAll(inputExclude.keySet().stream().flatMap(item -> inputExclude.get(item).keySet().stream()).collect(Collectors.toSet()));

        List<List<Object>> result = skuList.parallelStream()
                .map(sku -> {
                    List<Object> item = new ArrayList<>();
                    item.add(sku);
                    LocalDate start = LocalDate.of(2019,1,1);
                    do {
                        String yearMonth = start.getYear() + "-" + start.getMonthValue();
                        String lastYearMonth = start.plusMonths(-1).getYear() + "-" + start.plusMonths(-1).getMonthValue();
                        ExcelFileUtil.Result finalEndingItem = new ExcelFileUtil.Result();
                        if (finalEnding.get(lastYearMonth).containsKey(sku)){
                            finalEndingItem.setSum(finalEnding.get(lastYearMonth).get(sku).getSum());
                            finalEndingItem.setCount(finalEnding.get(lastYearMonth).get(sku).getCount());
                        } else {
                            finalEndingItem.setCount(BigDecimal.ZERO);
                            finalEndingItem.setSum(BigDecimal.ZERO);
                        }

                        ExcelFileUtil.Result inputExcludeItem = new ExcelFileUtil.Result();
                        if (inputExclude.get(yearMonth).containsKey(sku)){
                            inputExcludeItem.setSum(inputExclude.get(yearMonth).get(sku).getSum());
                            inputExcludeItem.setCount(inputExclude.get(yearMonth).get(sku).getCount());
                        } else {
                            inputExcludeItem.setCount(BigDecimal.ZERO);
                            inputExcludeItem.setSum(BigDecimal.ZERO);
                        }
                        if (finalEndingItem.getCount().compareTo(BigDecimal.ZERO) <=0 && inputExcludeItem.getCount().compareTo(BigDecimal.ZERO) <= 0){
                            item.add(null);
                        } else {
                            try {
                                BigDecimal avgPrice =
                                        finalEndingItem.getSum().add(inputExcludeItem.getSum()).divide(finalEndingItem.getCount().add(inputExcludeItem.getCount()),2,BigDecimal.ROUND_HALF_UP);
                                item.add(avgPrice);
                            } catch (Exception e){
                                logger.error("sku:{},时间：{},e:",sku,yearMonth,e);
                            }

                        }

                        start = start.plusMonths(1);
                    } while (start.isBefore(LocalDate.of(2021,4,1)));
                    return item;
                })
                .collect(Collectors.toList());

        ExcelFileUtil.generateExcel("/Users/daniel/desktop/加权价.xlsx",header(),result);
    }


    private static List<String> header(){
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
