package com.jxx.dataTest;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.json.JSONUtil;
import com.jxx.common.model.AdjPyCheck;
import com.jxx.common.model.po.InventoryAdjustmentDetailPo;
import com.jxx.common.model.po.WmsInputOrderGoods;
import com.jxx.excel.InExport;
import com.jxx.mapper.WarehouseGoodsOperateLogMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdjPyCheckTest {

    @Resource
    private WarehouseGoodsOperateLogMapper warehouseGoodsOperateLogMapper;

    @Test
    public void test () throws Exception{
        FileInputStream fileInputStream  = new FileInputStream("/Users/dhs/Downloads/1.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);

//        File file = new File("/Users/dhs/Downloads/盘亏单和盘盈单校对结果.txt");
//        OutputStreamWriter writer = new FileWriter(file);
//        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        List<AdjPyCheck> result = new ArrayList<>();

        String str ;
        while((str = br.readLine() )!= null){
            String[] split = str.split("\t");
            String adjNo = split[0];
            List<InventoryAdjustmentDetailPo> detailPoList = warehouseGoodsOperateLogMapper.getAdjDetilsByAdjNo(adjNo);

            //未匹配spu信息
            HashMap<Integer, InventoryAdjustmentDetailPo> spuMap = new HashMap<>();

            for (InventoryAdjustmentDetailPo detailPo : detailPoList) {
                spuMap.put(detailPo.getSpuId(),detailPo);
            }

            HashMap<Integer,WmsInputOrderGoods> wmsInputOrderGoodsHashMap = new HashMap<>();
            for (String inNo : split) {
                if(inNo.contains("ADJ")){
                    continue;
                }
                List<WmsInputOrderGoods> inputOrderGoods = warehouseGoodsOperateLogMapper.getInorderDetilsByInNo(inNo);
                for (WmsInputOrderGoods inputOrderGood : inputOrderGoods) {
                    wmsInputOrderGoodsHashMap.put(inputOrderGood.getSpuId(),inputOrderGood);
                }
            }
            for (Integer spuId : spuMap.keySet()) {
                InventoryAdjustmentDetailPo detailPo = spuMap.get(spuId);
                WmsInputOrderGoods wmsInputOrderGoods = wmsInputOrderGoodsHashMap.get(spuId);
                AdjPyCheck adjPyCheck = new AdjPyCheck();
                if(wmsInputOrderGoods == null){
                    adjPyCheck.setAdjNo(detailPo.getInventoryAdjustmentNo());
                    adjPyCheck.setAdjSku(detailPo.getSkuNo());
                    adjPyCheck.setAdjSkuName(detailPo.getGoodsName());
                    adjPyCheck.setResult("不匹配");
                }else {
                    adjPyCheck.setAdjNo(detailPo.getInventoryAdjustmentNo());
                    adjPyCheck.setPyNo(wmsInputOrderGoods.getOrderNo());
                    adjPyCheck.setAdjSku(detailPo.getSkuNo());
                    adjPyCheck.setAdjSkuName(detailPo.getGoodsName());
                    adjPyCheck.setPySku(wmsInputOrderGoods.getSkuNo());
                    adjPyCheck.setPySkuName(wmsInputOrderGoods.getSkuName());
                    adjPyCheck.setResult("匹配");
                }
                result.add(adjPyCheck);
            }
        }
        Workbook inworkbook = ExcelExportUtil.exportExcel(new ExportParams("盘亏单和盘盈单校对结果",""), AdjPyCheck.class,result);
        FileOutputStream infos = new FileOutputStream("/Users/dhs/Downloads/盘亏单和盘盈单校对结果.xlsx");
        inworkbook.write(infos);
        infos.close();
    }
}
