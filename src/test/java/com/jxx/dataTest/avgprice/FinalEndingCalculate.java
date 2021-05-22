package com.jxx.dataTest.avgprice;



import net.minidev.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: daniel
 * @Date: 2021/5/11 14 56
 * @Description:
 */
public class FinalEndingCalculate {

    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(getFirstTime201812("/Users/daniel/desktop/归档/")));
    }



    public static Map<String, Map<String, ExcelFileUtil.Result>> getFinalEndingPriceEveryMonth(String prefix){
        Map<String, Map<String,ExcelFileUtil.Result>> resultEndTimeMap = new HashMap<>();
//        resultEndTimeMap.put("2018-11",getFirstTime201812(prefix));
        resultEndTimeMap.put("2018-12",getGroupCountBySku(prefix+"库存明细/库存明细2018-12.xlsx",8,13,15));
        LocalDate start = LocalDate.of(2019,1,1);
        do {
            //当月期末 = 上月期末 + 当月入库 - 当月出库
            Map<String, ExcelFileUtil.Result> lastMonth = resultEndTimeMap.get(start.plusMonths(-1).getYear()+"-"+start.plusMonths(-1).getMonthValue());
            Map<String, ExcelFileUtil.Result> input = getGroupCountBySku(prefix+"入库明细/入库" + start.getYear()+"-"+start.getMonthValue() +".xlsx",15,
                    25,27);
            Map<String,ExcelFileUtil.Result> output = getGroupCountBySku(prefix+"出库明细/出库"+start.getYear()+"-"+start.getMonthValue() +".xlsx",17,27,
                    29);
            resultEndTimeMap.put(start.getYear()+"-"+start.getMonthValue(),skuStatistics(start.getYear()+"-"+start.getMonthValue(),lastMonth,input,
                    output));
            start = start.plusMonths(1);
        } while (start.isBefore(LocalDate.of(2021,4,1)));
        return resultEndTimeMap;
    }


    public static Map<String, ExcelFileUtil.Result> getFirstTime201812(String prefix){
        //201812的期初 = 201812库存 - 入库 + 出库
        Map<String, ExcelFileUtil.Result> stock = getGroupCountBySku(prefix+"库存明细/库存明细2018-12.xlsx",8,13,15);
        Map<String, ExcelFileUtil.Result> input = getGroupCountBySku(prefix+"入库明细/入库2018-12.xlsx",15,25,27);
        Map<String, ExcelFileUtil.Result> output = getGroupCountBySku(prefix+"出库明细/出库2018-12.xlsx",17,27,29);

        return skuStatistics("2018-12",stock,output,input);
    }

    public static Map<String, ExcelFileUtil.Result> skuStatistics(String yearMonth, Map<String, ExcelFileUtil.Result> base,
                                                                  Map<String, ExcelFileUtil.Result> add,
                                                                  Map<String, ExcelFileUtil.Result> sub){

        Map<String, ExcelFileUtil.Result> resultMap = new HashMap<>();

        Set<String> keys = new HashSet<>();
        keys.addAll(new HashSet<>(base.keySet()));
        keys.addAll(new HashSet<>(add.keySet()));
        keys.addAll(new HashSet<>(sub.keySet()));

        for (String key : keys){
            ExcelFileUtil.Result baseItem = new ExcelFileUtil.Result(BigDecimal.ZERO,BigDecimal.ZERO);
            ExcelFileUtil.Result addItem = new ExcelFileUtil.Result(BigDecimal.ZERO,BigDecimal.ZERO);
            ExcelFileUtil.Result subItem = new ExcelFileUtil.Result(BigDecimal.ZERO,BigDecimal.ZERO);
            if (base.containsKey(key)){
                baseItem.setSum(base.get(key).getSum());
                baseItem.setCount(base.get(key).getCount());
            }
            if (add.containsKey(key)){
                addItem.setCount(add.get(key).getCount());
                addItem.setSum(add.get(key).getSum());
            }
            if (sub.containsKey(key)){
                subItem.setSum(sub.get(key).getSum());
                subItem.setCount(sub.get(key).getCount());
            }
            ExcelFileUtil.Result resultItem = new ExcelFileUtil.Result();
            resultItem.setCount(baseItem.getCount().add(addItem.getCount()).subtract(subItem.getCount()));
            resultItem.setSum(baseItem.getSum().add(addItem.getSum()).subtract(subItem.getSum()));
            resultMap.put(key,resultItem);
            if (resultItem.getCount().compareTo(BigDecimal.ZERO) < 0){
//                write2Txt(yearMonth+"-KEY:"+key+",base:"+baseItem.getCount()+",add:"+addItem.getCount()+",sub:"+subItem.getCount());
                System.out.println(yearMonth+"-KEY:"+key+",base:"+baseItem.getCount()+",add:"+addItem.getCount()+",sub:"+subItem.getCount());
            }

        }

        return resultMap;
    }



    public static Map<String, ExcelFileUtil.Result> getGroupCountBySku(String path, Integer keyIndex, Integer countIndex,
                                                                       Integer sumIndex){
        System.out.println(path);
        Map<String, List<ExcelFileUtil.Result>> skuMap = ExcelFileUtil.getDataFromExcelFile(path)
                .stream()
                .filter(item -> !item.get(1).equalsIgnoreCase("单据类型") && !item.get(2).equalsIgnoreCase("单据类型"))
                .map(item -> {
                    ExcelFileUtil.Result result = new ExcelFileUtil.Result();
                    result.setKey(item.get(keyIndex));

                    result.setCount(new BigDecimal(item.get(countIndex)));
                    result.setSum(new BigDecimal(item.get(sumIndex)));

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

    private static void write2Txt(String txt){
        String fileName = "/Users/dhs/Downloads/sku.txt";
        Path path = Paths.get(fileName);
        try {
            BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            writer.write(txt+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
