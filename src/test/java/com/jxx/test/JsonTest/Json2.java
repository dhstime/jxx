package com.jxx.test.JsonTest;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class Json2 {

    @Test
    public void testPare() throws Exception {
        String path = "/Users/dhs/Downloads/export_result.json";
        paseJosn(path);
    }

    private void paseJosn(String path) throws Exception{
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String json = br.readLine();
        JSONArray jsonArray  = JSONUtil.parseArray(json);
        Iterator<Object> iterator = jsonArray.iterator();
        int occpuyNum = 0;
        int stockNum = 0;
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            JSONObject content = next.getJSONObject("CONTENT");
            JSONArray warehouseStockList = (JSONArray) content.get("warehouseStockList");
            Iterator<Object> iterator1 = warehouseStockList.iterator();
            while(iterator1.hasNext()){
                JSONObject next1 = (JSONObject) iterator1.next();
                if(next1.get("sku").equals("V503905")){
                    Integer integer = Integer.valueOf(StringUtils.isNotBlank(next1.get("occupyNum").toString()) ? next1.get("occupyNum").toString() : "0");
                    occpuyNum +=integer;
                    Integer integer1 = Integer.valueOf(StringUtils.isNotBlank(next1.get("stockNum").toString()) ? next1.get("stockNum").toString() : "0");
                    stockNum +=integer1;
                    if(integer1 == 0){
                        continue;
                    }
//                    System.out.println("occpuyNum :{}"+occpuyNum+"   int  "+integer);
                    System.out.println("stockNum :{}"+stockNum+"   int  "+integer1);

                }
            }
        }
//        System.out.println("occpuyNum :{}"+occpuyNum);
        System.out.println("stockNum :{}"+stockNum);
        br.close();
    }
}
