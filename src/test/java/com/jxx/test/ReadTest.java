package com.jxx.test;

import com.jxx.common.utils.DateUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author Strange
 * @ClassName ReadTest.java
 * @Description TODO
 * @createTime 2020年08月24日 20:05:00
 */
public class ReadTest {

    @Test
    public void test() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("/Users/dhs/Downloads/1.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);
        String str = null;
        while((str = br.readLine() )!= null){
            String[] split = str.split("\t");
            String sku = split[0];
            String exprdate = split[4];
            String addTimeStr = split[5];
            String code = split[6];
            Integer num = Integer.valueOf(split[10]);

            long exprTime = 0L;
            if(!exprdate.equals("-")){
                exprTime = DateUtil.convertLong(exprdate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            }
            long addTime = 0L;
            if(!addTimeStr.isEmpty()){
                addTime = DateUtil.convertLong(addTimeStr + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            }
        }
    }
}
