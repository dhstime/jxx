package com.jxx.dataTest;

import com.jxx.JxxApplicationTests;
import com.jxx.common.model.Order;
import com.jxx.common.model.WarehouseGoodsOperateLogDto;
import com.jxx.mapper.WarehouseGoodsOperateLogMapper;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author Strange
 * @ClassName PriceTest.java
 * @Description TODO
 * @createTime 2021年04月15日 18:32:00
 */
public class PriceTest extends JxxApplicationTests {

    @Resource
    private WarehouseGoodsOperateLogMapper warehouseGoodsOperateLogMapper;

    //

    @Test
    public void test() throws Exception{

        FileInputStream fileInputStream = new FileInputStream("/Users/dhs/Downloads/price.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);
        String str = null;

        while((str = br.readLine() )!= null){
            String[] split = str.split("\t");
            Integer deatilId = Integer.valueOf(split[0]);
            BigDecimal price = new BigDecimal(split[1]);
            BigDecimal num = new BigDecimal(split[2]).abs();
            BigDecimal costPrice = new BigDecimal(split[3]);

            if(costPrice.compareTo(BigDecimal.ZERO) == 0){
                costPrice = price;
            }
            StringBuilder sql = new StringBuilder("UPDATE T_WMS_INVENTORY_ADJUSTMENT_DETAIL_backup1 SET ");
            sql = appenSql(sql,costPrice.setScale(2,BigDecimal.ROUND_HALF_UP).toString(),"PRICE");
            sql = appenSql(sql,"ABS(NUM)*PRICE","TOTAL_PRICE");
            String substring = sql.substring(0 , sql.length()-1);
            String endsql =" WHERE INVENTORY_ADJUSTMENT_DETAIL_ID = "+deatilId+";";
            System.out.println(substring + endsql);
        }
    }

    private StringBuilder appenSql(StringBuilder sql, String value, String filed) {
        if(value == null || value.isEmpty()){
            return sql;
        }
        return sql.append(filed + " = " + value +",");
    }

    @Test
    public void test1(){
        HashMap<Integer,BigDecimal> hashMap = new HashMap<>();
        List<WarehouseGoodsOperateLogDto> warehouseGoodsOperateLogDtoList = warehouseGoodsOperateLogMapper.getZeroPriceLog();

        for (WarehouseGoodsOperateLogDto warehouseGoodsOperateLogDto : warehouseGoodsOperateLogDtoList) {
            Integer id = warehouseGoodsOperateLogDto.getWarehouseGoodsOperateLogId();
            Integer goodsId = warehouseGoodsOperateLogDto.getGoodsId();
            if(hashMap.get(goodsId) == null) {
                List<Order> avgpriceList = warehouseGoodsOperateLogMapper.getAvgPrice(goodsId);
                if(CollectionUtils.isEmpty(avgpriceList)){
                    continue;
                }
                BigDecimal totalAmount = avgpriceList.stream().map(Order::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                int num = avgpriceList.stream().mapToInt(item -> item.getNum()).sum();
                BigDecimal avgPrice = totalAmount.divide(new BigDecimal(num), 2, BigDecimal.ROUND_HALF_UP);
                hashMap.put(goodsId, avgPrice);
            }
            BigDecimal avgPirce = hashMap.get(goodsId);
            StringBuilder sql = new StringBuilder("UPDATE T_WAREHOUSE_GOODS_OPERATE_LOG SET ");
            sql = appenSql(sql,avgPirce.toString(),"NEW_COST_PRICE");
            String substring = sql.substring(0 , sql.length()-1);
            String endsql =" WHERE WAREHOUSE_GOODS_OPERATE_LOG_ID= "+id+";";
            System.out.println(substring + endsql);
        }

    }
}
