package com.jxx.test;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.math.BigDecimal;

/**
 * @author Strange
 * @ClassName Test2.java
 * @Description TODO
 * @createTime 2020年10月15日 10:31:00
 */
public class Test2 {
    //UPDATE T_ATTACHMENT SET URI = '/upload/default/2019-08/15/image/1565862651000_3397_bf.jpg',ORIGINAL_FILEPATH = '/upload/default/2019-08/15/image/1565862651000_3397.jpg' WHERE ATTACHMENT_ID = 4262656 ;
    @Test
    public void test() throws Exception{
        FileInputStream fileInputStream  = new FileInputStream("/Users/dhs/Downloads/erp2.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);
        RestTemplate restTemplate = new RestTemplate();

        File file = new File("/Users/dhs/Downloads/id2.txt");
        OutputStreamWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        String str = null;
        while((str = br.readLine() )!= null){
            try {
                String[] split = str.split(";");
                String id = split[0];
                String uri = split[1];
                ResponseEntity<String> entity = restTemplate.getForEntity("https://file1.vedeng.com"+uri,String.class);
            }catch (Exception e){
                bufferedWriter.write(str +"\r\n");
                bufferedWriter.flush();
            }
        }
        bufferedWriter.close();
        writer.close();
    }

    @Test
    public void test1(){
        BigDecimal a = new BigDecimal("7.00");
        System.out.println(a.toString());
    }
}
