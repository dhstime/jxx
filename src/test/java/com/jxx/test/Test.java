package com.jxx.test;

import com.jxx.common.MoneyUtil;
import com.jxx.test.MachineCode.DigitToChineseUppercaseNumberUtils;

import java.io.*;
import java.math.BigDecimal;

public class Test {
    public static void main(String [] args) throws Exception {
        File file = new File("/Users/dhs/Downloads/1.TXT");
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        String str = null;
        while((str = br.readLine()) != null ){
            String[] s1 = str.split("\t");
            String sql = "UPDATE T_SALEORDER_GOODS \n" +
                    "SET REFERENCE_COST_PRICE = "+s1[2]+" \n" +
                    "WHERE\n" +
                    "\tSKU = '"+s1[1]+"' \n" +
                    "\tAND SALEORDER_ID = ( SELECT SALEORDER_ID FROM T_SALEORDER WHERE SALEORDER_NO = '"+s1[0]+"' );";
            System.out.println(sql);
        }
        br.close();
    }

}
