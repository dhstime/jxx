package com.jxx.test.read;

import com.jxx.common.utils.DateUtil;
import com.jxx.study.Intege;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Strange
 * @ClassName ReadTest1.java
 * @Description TODO
 * @createTime 2020年08月26日 22:00:00
 */
public class ReadTest1 {
    @Test
    public void test() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("/Users/dhs/Downloads/12.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);
        Map<String, Integer[]> map = new HashMap<>();
        String str = null;
        while((str = br.readLine() )!= null){
            String[] split = str.split("\t");
            String sku = split[0];
            Integer num = Integer.valueOf(split[1]);
            Integer allNum = Integer.valueOf(split[2]);
            Integer[] in = new Integer[]{num, allNum};
            Integer[] integers = map.get(sku);
            if(integers == null){
                map.put(sku,in);
            }else{
                integers[0] = integers[0] + num;
                map.put(sku,integers);
            }
        }
        for (String sku : map.keySet()) {
            Integer[] integers = map.get(sku);
            if(!integers[0].equals(integers[1])){
                System.out.println("!!!!!!!!:"+sku);
            }
        }
    }
}
