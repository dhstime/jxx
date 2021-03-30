package com.jxx.test;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jxx.common.model.LogInventDetils;
import com.jxx.common.model.MyClass;
import com.jxx.common.model.WarehouseGoodsOperateLog;
import com.jxx.common.model.po.InventoryAdjustmentDetailPo;
import com.jxx.mapper.InventoryAdjustmentMapper;
import com.jxx.mapper.WarehouseGoodsOperateLogMapper;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Strange
 * @ClassName Test3.java
 * @Description TODO
 * @createTime 2021年01月14日 16:36:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WarehouseTest4 {

    @Resource
    private WarehouseGoodsOperateLogMapper warehouseGoodsOperateLogMapper;

    @Resource
    private InventoryAdjustmentMapper inventoryAdjustmentMapper;


    @Test
    public void test() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("/Users/dhs/Downloads/1616573971_16090.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);

        File file = new File("/Users/dhs/Downloads/盘亏数据恢复11月之前.txt");
        OutputStreamWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        File lastfile = new File("/Users/dhs/Downloads/盘亏数据剩余11月之前.txt");
        OutputStreamWriter lastwriter = new FileWriter(lastfile);
        BufferedWriter lastbufferedWriter = new BufferedWriter(lastwriter);


        Map<String,LogInventDetils> map = new HashMap<>();

        String str = null;
        while((str = br.readLine() )!= null){
            String[] split = str.split("盘亏情况更新ERP库存log信息 单号:");
            String orderNo = split[1].split(",")[0];
            String[] split1 = str.split("warehouseGoodsOperateLog:");
            JSON json = new JSONObject(split1[1]);
            WarehouseGoodsOperateLog log = json.toBean(WarehouseGoodsOperateLog.class);
            Integer logId = log.getLogicalWarehouseId();
//        JSONArray jsonArray = JSONUtil.parseArray(js);
//        for (int i = 0; i < jsonArray.size(); i++) {
//            MyClass myClass = jsonArray.get(i, MyClass.class);
//        }
//
//        for (MyClass myClass : myClasses) {
//            String orderNo = myClass.getAdjustmrntNo();
//            List<Integer> warehouseLogIds = myClass.getWarehouseLogIds();
//            for (Integer logId : warehouseLogIds) {

            WarehouseGoodsOperateLog result = warehouseGoodsOperateLogMapper.selectById(logId);
//            if(result.getAddTime() > 1598803200000L){
            InventoryAdjustmentDetailPo search = new InventoryAdjustmentDetailPo();
            search.setInventoryAdjustmentNo(orderNo);
            search.setSkuNo("V"+result.getGoodsId());
            search.setVedengBatchNumer(result.getVedengBatchNumer());
            List<InventoryAdjustmentDetailPo> detailPoList = inventoryAdjustmentMapper.getDetailsByInfo(search);
            String key = search.getInventoryAdjustmentNo() + "," + search.getSkuNo() + "," + search.getVedengBatchNumer();

            if(CollectionUtils.isEmpty(detailPoList)){
                System.out.println("调整详情为空  "+key+" id " +logId);
                continue;
            }

            LogInventDetils logInventDetils = map.get(key);
            if(logInventDetils == null){
                Set<InventoryAdjustmentDetailPo> adjustmentDetailPoSet = new HashSet<>();
                adjustmentDetailPoSet.addAll(detailPoList);
                logInventDetils = new LogInventDetils();
                logInventDetils.setValue(key);
                logInventDetils.setOrderNo(orderNo);
                List<WarehouseGoodsOperateLog> logList = new ArrayList<>();
                logList.add(result);
                logInventDetils.setLogList(logList);
                logInventDetils.setDetailPoList(adjustmentDetailPoSet);
                logInventDetils.setInventoryAdjustmentDetailPo(detailPoList.get(0));
                map.put(key,logInventDetils);
            }else{
                logInventDetils.getDetailPoList().addAll(detailPoList);
                logInventDetils.getLogList().add(result);
            }
//                bufferedWriter.write(orderNo+" &&  "+result.toString() +"\r\n");
//                bufferedWriter.flush();
            }
//        }
//        bufferedWriter.write(jsonObject.toString());
//        bufferedWriter.flush();
        for (String key : map.keySet()) {
            LogInventDetils logInventDetils = map.get(key);
            String orderNo = logInventDetils.getOrderNo();
            InventoryAdjustmentDetailPo inventoryAdjustmentDetailPo = logInventDetils.getInventoryAdjustmentDetailPo();
            List<WarehouseGoodsOperateLog> logList = logInventDetils.getLogList();
            if(logList.size() == 1 || inventoryAdjustmentDetailPo.getNum().compareTo(new BigDecimal(logList.size())) == 0){
                if(logList.size() == 1){
                    MyClass myClass = new MyClass();
                    myClass.setAdjustmrntNo(orderNo);
                    myClass.setNum(inventoryAdjustmentDetailPo.getNum().intValue());
                    myClass.setId(logList.get(0).getWarehouseGoodsOperateLogId());
                    bufferedWriter.write( JSONUtil.parse(myClass).toString() +"\r\n");
                    bufferedWriter.flush();
                }else{
                    for (WarehouseGoodsOperateLog warehouseGoodsOperateLog : logList) {
                        MyClass myClass = new MyClass();
                        myClass.setAdjustmrntNo(orderNo);
                        myClass.setNum(1);
                        myClass.setId(warehouseGoodsOperateLog.getWarehouseGoodsOperateLogId());
                        bufferedWriter.write( JSONUtil.parse(myClass).toString() +"\r\n");
                        bufferedWriter.flush();
                    }

                }
            }else{
                List<Integer> ids = new ArrayList<>();
                for (WarehouseGoodsOperateLog warehouseGoodsOperateLog : logList) {
                    ids.add(warehouseGoodsOperateLog.getWarehouseGoodsOperateLogId());
                }
                lastbufferedWriter.write(inventoryAdjustmentDetailPo.getInventoryAdjustmentNo() +
                        " & 转移数量"+inventoryAdjustmentDetailPo.getNum() + "& 条数"+logList.size()  + " & id :"+ ids.toString()
                        +"\r\n");
                lastbufferedWriter.flush();
            }
        }

        bufferedWriter.close();
        writer.close();
        lastbufferedWriter.close();
        lastwriter.close();
    }


}
