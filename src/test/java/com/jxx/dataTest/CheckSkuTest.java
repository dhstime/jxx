package com.jxx.dataTest;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Strange
 * @ClassName CheckSkuTest.java
 * @Description TODO
 * @createTime 2021年05月22日 23:47:00
 */
public class CheckSkuTest {

    @Test
    public void checkSku() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("/Users/dhs/Downloads/进销存库存.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);
        String str;
        HashMap<String,String> jinxiaocun = new HashMap<>();
        while((str = br.readLine() )!= null){
            String[] split = str.split("\t");
            jinxiaocun.put(split[0],split[1]);
        }

        int sum = 0;

//        FileInputStream fileInputStream2 = new FileInputStream("/Users/dhs/Downloads/加权价库存.txt");
        FileInputStream fileInputStream2 = new FileInputStream("/Users/dhs/Downloads/全部加权价.txt");
        InputStreamReader isr2 = new InputStreamReader(fileInputStream2);
        BufferedReader br2 = new BufferedReader(isr2);
        String str2;
        HashMap<String,String> jiaquanjia = new HashMap<>();
        while((str2 = br2.readLine() )!= null){
            String[] split = str2.split("\t");
            jiaquanjia.put(split[0],split[1]);
        }

        HashSet<String> set = new  HashSet(jiaquanjia.keySet());
        set.addAll(jinxiaocun.keySet());

        for (String sku : set) {
            Integer jiaquanNum = Integer.valueOf(jiaquanjia.getOrDefault(sku,"0"));
            Integer jinxiaocunNum = Integer.valueOf(jinxiaocun.getOrDefault(sku,"0"));
            sum += jiaquanNum - jinxiaocunNum;
            if(!jinxiaocunNum.equals(jiaquanNum)){
                System.out.println(sku + ": 加权:"+jiaquanNum + "&进销存:" + jinxiaocunNum);
            }
        }
        System.out.println(sum);
    }
}
