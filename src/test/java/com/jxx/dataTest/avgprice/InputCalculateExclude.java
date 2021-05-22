package com.jxx.dataTest.avgprice;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: daniel
 * @Date: 2021/5/13 16 46
 * @Description: 排除计价的入库计算
 */
public class InputCalculateExclude {

    public static Map<String,Map<String, ExcelFileUtil.Result>> getInputPriceExclude(String prefix){
        List<String> yearMonthList = new ArrayList<>();
        LocalDate start = LocalDate.of(2018,12,1);
        do {
            yearMonthList.add(start.getYear()+"-"+start.getMonthValue());
            start = start.plusMonths(1);
        } while (start.isBefore(LocalDate.of(2021,4,1)));

        Map<String,Map<String,ExcelFileUtil.Result>> result = new HashMap<>();
        yearMonthList.parallelStream()
                .forEach(yearMonth -> result.put(yearMonth,getGroupCountBySkuOfInput(prefix+"入库明细/入库"+yearMonth+".xlsx",15,25,27,29)));

        return result;
    }


    public static Map<String, ExcelFileUtil.Result> getGroupCountBySkuOfInput(String path, Integer keyIndex, Integer countIndex,
                                                                       Integer sumIndex, Integer sumNoTaxIndex){
        Map<String, List<ExcelFileUtil.Result>> skuMap = ExcelFileUtil.getDataFromExcelFile(path)
                .parallelStream()
                .filter(item -> !item.get(2).equalsIgnoreCase("单据类型"))
                .filter(item -> !excludeOrderTypesList(item.get(2)))
                .map(item -> {
                    ExcelFileUtil.Result result = new ExcelFileUtil.Result();
                    result.setKey(item.get(keyIndex));
                    Integer sumRealIndex = sumNoTaxIndex;
                    if (StringUtils.isBlank(item.get(sumNoTaxIndex))){
                        sumRealIndex = sumIndex;
                    }
                    if (item.get(2).equals("采购换货出库") || item.get(2).equals("采购退货出库")){
                        result.setCount(new BigDecimal(item.get(countIndex)).multiply(new BigDecimal("-1")));
                        result.setSum(new BigDecimal(item.get(sumRealIndex)).multiply(new BigDecimal("-1")));
                    }
                    result.setCount(new BigDecimal(item.get(countIndex)));
                    result.setSum(new BigDecimal(item.get(sumRealIndex)));
                    return result;
                })
                .collect(Collectors.groupingBy(ExcelFileUtil.Result::getKey));
        return skuMap.keySet().parallelStream()
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
                })
                .collect(Collectors.toMap(ExcelFileUtil.Result::getKey,item->item));
    }


    private static Boolean excludeOrderTypesList(String type){
        List<String> types = new ArrayList<>();
        types.add("盘盈入库");
        types.add("批次调整入库");
        types.add("外借入库");
        types.add("调整盘盈入库");
        types.add("SKU转化入库");
        return types.contains(type);
    }
}
