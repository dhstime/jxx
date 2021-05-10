package com.jxx.dataTest;

import com.jxx.JxxApplicationTests;
import com.jxx.common.model.AdjPyCheck;
import com.jxx.common.model.Order;
import com.jxx.mapper.WarehouseGoodsOperateLogMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.*;

/**
 * @author Strange
 * @ClassName NoTraderId.java
 * @Description TODO
 * @createTime 2021年05月08日 14:23:00
 */
public class NoTraderId extends JxxApplicationTests {

    @Resource
    private WarehouseGoodsOperateLogMapper warehouseGoodsOperateLogMapper;

    @Test
    public void test() throws Exception{
        FileInputStream fileInputStream  = new FileInputStream("/Users/dhs/Downloads/1.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);

        OutputStreamWriter writer = new FileWriter("/Users/dhs/Downloads/2.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        String str ;
        while((str = br.readLine() )!= null){
            String[] split1 = str.split("\t");
            Integer id = Integer.valueOf(split1[0]);
            String orderNo = split1[1];
//            Order order  = warehouseGoodsOperateLogMapper.getBuyorderInfo(orderNo);
            Order order  = warehouseGoodsOperateLogMapper.getAfterSaleInfo(orderNo);
            String sql = "UPDATE V_LOG_DATA SET TRADER_ID = " + order.getTraderId() + "," +
                    "TRADER_NAME =\'" + order.getTraderName() + "\' WHERE IN_LOG_ID= " + id + " ;" + "\r\n";
            bufferedWriter.write(sql);
            bufferedWriter.flush();
        }

    }
}

