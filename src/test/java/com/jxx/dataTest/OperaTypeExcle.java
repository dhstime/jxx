package com.jxx.dataTest;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.jxx.JxxApplicationTests;
import com.jxx.common.utils.StringUtil;
import com.jxx.excel.LogDataDo;
import com.jxx.excel.OperaTypeExcleVo;
import com.jxx.excel.OutStockDataDo;
import com.jxx.mapper.LogDataDtoMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author Strange
 * @ClassName OperaTypeExecl.java
 * @Description TODO 类型转化处理
 * @createTime 2021年04月28日 09:21:00
 */
public class OperaTypeExcle extends JxxApplicationTests {

    @Resource
    private LogDataDtoMapper logDataDtoMapper;

    @Test
    public void pare(){
        File file = new File("/Users/dhs/Downloads/需处理数据合集.xlsx");
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setStartSheetIndex(4);
        params.setSheetNum(1);
        List<OperaTypeExcleVo> list = ExcelImportUtil.importExcel(file, OperaTypeExcleVo.class, params);

        HashMap<String, BigDecimal> adjMap = new HashMap<>();
        HashMap<String, BigDecimal> pyMap = new HashMap<>();
        for (OperaTypeExcleVo operaTypeExcleVo : list) {
            if(StringUtil.isBlank(operaTypeExcleVo.getAdjNo())){
                continue;
            }

            String adjNo = operaTypeExcleVo.getAdjNo();
            String adjSku = operaTypeExcleVo.getAdjSku();
            BigDecimal adjSkuNum = operaTypeExcleVo.getAdjSkuNum();
            String adjKey = adjNo + ":" + adjSku;

            String pyNo = operaTypeExcleVo.getPyNo();
            String pySku = operaTypeExcleVo.getPySku();
            BigDecimal pySkuNum = operaTypeExcleVo.getPySkuNum();
            String pyKey = pyNo + ":" + pySku;

            BigDecimal adjSkuNumSum = adjMap.get(adjKey);
            if(adjSkuNumSum == null){
                adjSkuNumSum = BigDecimal.ZERO;
            }
            adjMap.put(adjKey,adjSkuNumSum.add(adjSkuNum));


            BigDecimal pySkuNumSum = pyMap.get(pyKey);
            if(pySkuNumSum == null){
                pySkuNumSum = BigDecimal.ZERO;
            }
            pyMap.put(pyKey,pySkuNumSum.add(pySkuNum));
        }
        executePy(pyMap);
        executeAdj(adjMap);
    }

    private void executePy(HashMap<String, BigDecimal> pyMap) {
        for (String key : pyMap.keySet()) {
            String[] split = key.split(":");
            String pyNo = split[0];
            String pySku = split[1];

            BigDecimal pySkuNum = pyMap.get(key);

            LogDataDo update = new LogDataDo();
            update.setOrderType("批次调整入库");

            List<LogDataDo> logDataDoList = logDataDtoMapper.getLogDataByOrderNoSku(pyNo, pySku,0);
            int sum = logDataDoList.stream().mapToInt(value -> value.getNum()).sum();
            if(sum != pySkuNum.intValue()) {
                System.out.println(pyNo + " : " + pySku + " : " + pySkuNum + " : " + sum);
            }else{
                for (LogDataDo logDataDo : logDataDoList) {
                    update.setInLogId(logDataDo.getInLogId());
                    logDataDtoMapper.updateByPrimaryKeySelective(update);
                }
            }
        }
    }

    private void executeAdj(HashMap<String, BigDecimal> adjMap) {
        for (String key : adjMap.keySet()) {
            String[] split = key.split(":");
            String adjNo = split[0];
            String adjSku = split[1];

            BigDecimal adjSkuNum = adjMap.get(key);

            List<LogDataDo> logDataDoList = logDataDtoMapper.getLogDataByOrderNoSku(adjNo, adjSku,1);
            int sum = logDataDoList.stream().mapToInt(value -> value.getNum()).sum();

            LogDataDo update = new LogDataDo();
            update.setOrderType("批次调整出库");

            if(sum != adjSkuNum.abs().intValue()) {
                System.out.println(adjNo + " : " + adjSku + " : " + adjSkuNum + " : " + sum);
            }else{
                for (LogDataDo logDataDo : logDataDoList) {
                    update.setInLogId(logDataDo.getInLogId());
                    logDataDtoMapper.updateByPrimaryKeySelective(update);
                }
            }
        }
    }

}
