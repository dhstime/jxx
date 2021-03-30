package com.jxx.test;

import cn.hutool.json.JSONUtil;
import com.jxx.common.model.LogInventDetils;
import com.jxx.common.model.MyClass;
import com.jxx.common.model.WarehouseGoodsOperateLog;
import com.jxx.common.model.po.InventoryAdjustmentDetailPo;
import com.jxx.mapper.WarehouseGoodsOperateLogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Strange
 * @ClassName Test3.java
 * @Description TODO
 * @createTime 2021年01月14日 16:36:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test3 {

    @Resource
    private WarehouseGoodsOperateLogMapper warehouseGoodsOperateLogMapper;

    @Test
    public void test() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("/Users/dhs/Downloads/差值.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);
        String str = null;
        List<LogInventDetils> detilsList = new ArrayList<>();
        while((str = br.readLine() )!= null){
            String[] s = str.split("\t");
            String buyorderNo = s[0];
            String sku = s[1];
            Integer id = Integer.valueOf(s[2]);
            Integer arrNum = Integer.valueOf(s[4]);
            Integer inNum = Integer.valueOf(s[5]);

            Integer diffNum = arrNum - inNum;
            LogInventDetils detailPo = new LogInventDetils();

            List<WarehouseGoodsOperateLog> result = warehouseGoodsOperateLogMapper.getBuyorder(buyorderNo,sku);
            List<WarehouseGoodsOperateLog> list = new ArrayList<>();
            for (WarehouseGoodsOperateLog warehouseGoodsOperateLog : result) {
                if(warehouseGoodsOperateLog.getComments().contains("盘亏")){
                    list.add(warehouseGoodsOperateLog);
                }
            }
            detailPo.setOrderNo(buyorderNo);
            detailPo.setValue(sku);
            detailPo.setLogList(list);
            detailPo.setDiffNum(diffNum);
            detilsList.add(detailPo);
        }
    }

    @Test
    public void test1() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("/Users/dhs/Downloads/1.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);
    }
}
