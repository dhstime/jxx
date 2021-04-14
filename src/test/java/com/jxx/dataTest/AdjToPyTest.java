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
import java.util.List;

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

        //当前已更新多少条
        HashMap<Integer,Integer> nowNumMap = new HashMap<>();

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
            BigDecimal inPrice = totalPirce.divide(inNum, 2, BigDecimal.ROUND_HALF_UP);

//            if(adjNo.contains("VB") || adjNo.contains("VP")){
//
//            }else
            if(inNo.contains("ADJ")){

                List<InventoryAdjustmentDetailPo> detailPoList = warehouseGoodsOperateLogMapper.getAdjDetilByorderNoSku(inNo,inSku);
                if(CollectionUtils.isEmpty(detailPoList)){
                    System.out.println(str);
                    continue;
                }

                Integer detailNum = detailPoList.stream().mapToInt(item -> item.getNum().intValue()).sum();

//                if(detailNum != inNum.intValue()){
//                    System.out.println("数量不等 :"+inNum.intValue() +","+ detailNum +"str:"+str);
//                }

            }else if(inNo.contains("PY")){

                WmsInputOrderGoods wmsInputOrderGoods = warehouseGoodsOperateLogMapper.getInorderDetilsByInNoSku(inNo,inSku);

                if(wmsInputOrderGoods == null){
                    System.out.println(str);
                    continue;
                }
//                if(inNum.intValue() != wmsInputOrderGoods.getInputNum()){
//                    System.out.println("数量不等 :"+inNum.intValue() +","+ wmsInputOrderGoods.getInputNum() +"str:"+str);
//                }
                WarehouseGoodsOperateLogVo warehouseGoodsOperateLogVo = new WarehouseGoodsOperateLogVo();
                warehouseGoodsOperateLogVo.setOperateType(12);
                warehouseGoodsOperateLogVo.setRelatedId(wmsInputOrderGoods.getWmsInputOrderGoodsId());

                List<WarehouseGoodsOperateLogDto> warehouseGoodsOperateLogDtoList = warehouseGoodsOperateLogMapper.getWarehouseIn(warehouseGoodsOperateLogVo);

//                System.out.println(warehouseGoodsOperateLogDtoList.toString());

            }

        }
    }

}
