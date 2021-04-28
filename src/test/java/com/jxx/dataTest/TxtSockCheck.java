package com.jxx.dataTest;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author Strange
 * @ClassName TxtSockCheck.java
 * @Description TODO
 * @createTime 2021年04月27日 13:23:00
 */
public class TxtSockCheck {

    @Test
    public void txtSockCheck() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("/Users/dhs/Downloads/txtSockCheck.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);
        String str = null;

        HashMap<String,String> skuMap = new HashMap<>();

        HashMap<String,Integer> hashMap = new HashMap<>();

        while((str = br.readLine() )!= null){
            String[] split = str.split("##");
            String date = split[0].split("：")[1].trim();

            String sku = split[1];
            Integer newNum = Integer.valueOf(split[2].split("：")[1].trim());
            Integer oldNum = Integer.valueOf(split[3].split("：")[1].trim());

            Integer resultNum = hashMap.get(date) == null ? 0 :  hashMap.get(date);

            resultNum += newNum - oldNum;

            hashMap.put(date,resultNum);

            if(date.equals("2020-8")){
                skuMap.put(date+":"+sku,str);
            }

        }
        for (String date : hashMap.keySet()) {
            System.out.println(date +":" + hashMap.get(date));
        }
//        for (String date : skuMap.keySet()) {
//            System.out.println(skuMap.get(date));
//        }
    }
}
