package com.jxx.dataTest;

import com.jxx.JxxApplicationTests;
import com.jxx.common.model.WarehouseGoodsOperateLogDto;
import com.jxx.common.model.po.InventoryAdjustmentDetailPo;
import com.jxx.common.model.po.WmsInputOrderGoods;
import com.jxx.common.model.vo.WarehouseGoodsOperateLogVo;
import com.jxx.mapper.WarehouseGoodsOperateLogMapper;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Strange
 * @ClassName AdjToPyTest.java
 * @Description TODO
 * @createTime 2021年04月12日 11:25:00
 */
public class AdjToPyTest extends JxxApplicationTests {

    @Resource
    private WarehouseGoodsOperateLogMapper warehouseGoodsOperateLogMapper;

    @Test
    public void test() throws Exception{

        FileInputStream fileInputStream = new FileInputStream("/Users/dhs/Downloads/AdjToPy.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);
        String str = null;

        while((str = br.readLine() )!= null){

            String[] lineSplit = str.split("\t");
            Integer adjDetilId = Integer.valueOf(lineSplit[0]);
            String adjNo = lineSplit[1];
            String adjSku = lineSplit[2];
            BigDecimal adjSkuNum = new BigDecimal(lineSplit[3]);

            String inNo = lineSplit[4];
            String inSku = lineSplit[5];
            BigDecimal inNum = new BigDecimal(lineSplit[6].trim());

            InventoryAdjustmentDetailPo adjustmentDetailPo = warehouseGoodsOperateLogMapper.getAdjDetilByDetilId(adjDetilId);

            BigDecimal adjSkuprice = adjustmentDetailPo.getPrice();
            BigDecimal totalPirce = adjSkuprice.multiply(adjSkuNum);

            //相应入库单价
            BigDecimal inPrice = totalPirce.divide(inNum, 2, BigDecimal.ROUND_HALF_UP).abs();

            if(inPrice.compareTo(BigDecimal.ZERO) == 0){
                continue;
            }

            if(inNo.contains("ADJ")){

                List<InventoryAdjustmentDetailPo> detailPoList = warehouseGoodsOperateLogMapper.getAdjDetilByorderNoSku(inNo,inSku);
                if(CollectionUtils.isEmpty(detailPoList)){
                    continue;
                }

                WarehouseGoodsOperateLogVo warehouseGoodsOperateLogVo = new WarehouseGoodsOperateLogVo();
                if(inNum.compareTo(BigDecimal.ZERO) < 0){
                    warehouseGoodsOperateLogVo.setOperateType(15);
                }else {
                    warehouseGoodsOperateLogVo.setOperateType(11);
                }
                warehouseGoodsOperateLogVo.setRelatedId(detailPoList.get(0).getInventoryAdjustmentDetailId());

                List<WarehouseGoodsOperateLogDto> warehouseGoodsOperateLogDtoList = warehouseGoodsOperateLogMapper.getWarehouseIn(warehouseGoodsOperateLogVo);
                for (WarehouseGoodsOperateLogDto warehouseGoodsOperateLogDto : warehouseGoodsOperateLogDtoList) {
                    StringBuilder sql = new StringBuilder("UPDATE T_WAREHOUSE_GOODS_OPERATE_LOG SET ");
                    sql = appenSql(sql,inPrice.setScale(4,BigDecimal.ROUND_HALF_UP).toString(),"NEW_COST_PRICE");
                    String substring = sql.substring(0 , sql.length()-1);
                    String endsql =" WHERE WAREHOUSE_GOODS_OPERATE_LOG_ID= "+warehouseGoodsOperateLogDto.getWarehouseGoodsOperateLogId()+";";
                    System.out.println(substring + endsql);
                }
            }else if(inNo.contains("PY")){

                WmsInputOrderGoods wmsInputOrderGoods = warehouseGoodsOperateLogMapper.getInorderDetilsByInNoSku(inNo,inSku);

                if(wmsInputOrderGoods == null){
                    continue;
                }
                WarehouseGoodsOperateLogVo warehouseGoodsOperateLogVo = new WarehouseGoodsOperateLogVo();
                warehouseGoodsOperateLogVo.setOperateType(12);
                warehouseGoodsOperateLogVo.setRelatedId(wmsInputOrderGoods.getWmsInputOrderGoodsId());

                List<WarehouseGoodsOperateLogDto> warehouseGoodsOperateLogDtoList = warehouseGoodsOperateLogMapper.getWarehouseIn(warehouseGoodsOperateLogVo);

                for (WarehouseGoodsOperateLogDto warehouseGoodsOperateLogDto : warehouseGoodsOperateLogDtoList) {
                    StringBuilder sql = new StringBuilder("UPDATE T_WAREHOUSE_GOODS_OPERATE_LOG SET ");
                    sql = appenSql(sql,inPrice.setScale(2,BigDecimal.ROUND_HALF_UP).toString(),"NEW_COST_PRICE");
                    String substring = sql.substring(0 , sql.length()-1);
                    String endsql =" WHERE WAREHOUSE_GOODS_OPERATE_LOG_ID= "+warehouseGoodsOperateLogDto.getWarehouseGoodsOperateLogId()+";";
                    System.out.println(substring + endsql);
                }
            }
        }
    }

    private StringBuilder appenSql(StringBuilder sql, String value, String filed) {
        if(value == null || value.isEmpty()){
            return sql;
        }
        return sql.append(filed + " = " + value +",");
    }

    @Test
    public void test1() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("/Users/dhs/Downloads/33.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);
        String str ;

        HashMap<Integer, Set<String>> hashMap = new HashMap<>();

        while((str = br.readLine() )!= null){
            String[] lineSplit = str.split("\t");
            Integer adjDetilId = Integer.valueOf(lineSplit[0]);
            String inNo = lineSplit[4];

            Set<String> inNoSet = hashMap.get(adjDetilId);
            if(inNoSet == null){
                inNoSet = new HashSet<>();
            }
            inNoSet.add(inNo);
            hashMap.put(adjDetilId,inNoSet);

        }
        for (Integer adjDetilId : hashMap.keySet()) {
            Set<String> inNoSet = hashMap.get(adjDetilId);
            String inNos = "";
            for (String inNo : inNoSet) {
                inNos = inNos +" " +inNo;
            }
            StringBuilder sql = new StringBuilder("UPDATE T_WMS_INVENTORY_ADJUSTMENT_DETAIL_backup1 SET ");
            sql = appenSql(sql,"CONCAT(IFNULL(TAG_SOURCES,''),\'"+inNos+"\')","TAG_SOURCES");
            String substring = sql.substring(0 , sql.length()-1);
            String endsql =" WHERE INVENTORY_ADJUSTMENT_DETAIL_ID = "+ adjDetilId +";";
            System.out.println(substring + endsql);
        }
    }
}
