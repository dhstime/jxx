package com.jxx.test;

import com.jxx.JxxApplicationTests;
import com.jxx.common.utils.DateUtil;
import com.jxx.mapper.UnitMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author Strange
 * @ClassName ReadTest.java
 * @Description TODO
 * @createTime 2020年08月24日 20:05:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReadTest{

    @Resource
    private UnitMapper unitMapper ;

    @Test
    public void test() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("/Users/dhs/Downloads/1.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);
        String str;
        while((str = br.readLine() )!= null){
            String[] split = str.split("\t");
            String sku = split[0];
            String unit = split[1];//商品最小单位
            String UNIT_ID = null;
            if(!unit.isEmpty()){
                UNIT_ID = unitMapper.getUnitName(unit);
            }
            String MIN_ORDER = split[2];//最小起订量
            String CHANGE_NUM = split[3];//内含最小商品数量
            String CURING_TYPE = "2";//养护类型
            if(split[4].equals("一般养护")){
                CURING_TYPE= "1";
            }else if(split[4].equals("重点养护")){
                CURING_TYPE = "0";
            }
            String CURING_REASON =  "\""+split[5] +"\"";//养护原因
            String IS_NEED_TEST_REPROT = split[6].equals("是") ? "1" : "0";//是否必须检测报告
            String IS_KIT = split[7].equals("是") ? "1" : "0";//是否套件
            String KIT_DESC = "\""+split[8] +"\""; //有哪几个套件
            String IS_SAME_SN_CODE = split[9].equals("否") ? "0" : null;
            String IS_FACTORY_SN_CODE = split[10].equals("是") ? "1" : "0";
            String IS_MANAGE_VEDENG_CODE = split[11].equals("是") ? "1" : "0";
            String IS_BAD_GOODS = split[12].equals("是") ? "1" : "0";
            String IS_ENABLE_FACTORY_BATCHNUM = split[13].equals("是") ? "1" : "0";
            String IS_ENABLE_MULTISTAGE_PACKAGE = split[14].equals("是") ? "1" : "0";
            String MID_PACKAGE_NUM = null;
            String BOX_PACKAGE_NUM = null;
            if(split.length == 17){
                MID_PACKAGE_NUM = split[15];
                BOX_PACKAGE_NUM = split[16];
            }
            StringBuilder sql = new StringBuilder("UPDATE V_CORE_SKU SET ");
            sql = appenSql(sql,UNIT_ID,"UNIT_ID");
            sql = appenSql(sql,MIN_ORDER,"MIN_ORDER");
            sql = appenSql(sql,CHANGE_NUM,"CHANGE_NUM");
            sql = appenSql(sql,CURING_TYPE,"CURING_TYPE");
            sql = appenSql(sql,CURING_REASON,"CURING_REASON");
            sql = appenSql(sql,IS_NEED_TEST_REPROT,"IS_NEED_TEST_REPROT");
            sql = appenSql(sql,IS_KIT,"IS_KIT");
            sql = appenSql(sql,KIT_DESC,"KIT_DESC");
            sql = appenSql(sql,IS_SAME_SN_CODE,"IS_SAME_SN_CODE");
            sql = appenSql(sql,IS_FACTORY_SN_CODE,"IS_FACTORY_SN_CODE");
            sql = appenSql(sql,IS_MANAGE_VEDENG_CODE,"IS_MANAGE_VEDENG_CODE");
            sql = appenSql(sql,IS_BAD_GOODS,"IS_BAD_GOODS");
            sql = appenSql(sql,IS_ENABLE_FACTORY_BATCHNUM,"IS_ENABLE_FACTORY_BATCHNUM");
            sql = appenSql(sql,IS_ENABLE_MULTISTAGE_PACKAGE,"IS_ENABLE_MULTISTAGE_PACKAGE");
            sql = appenSql(sql,MID_PACKAGE_NUM,"MID_PACKAGE_NUM");
            sql = appenSql(sql,BOX_PACKAGE_NUM,"BOX_PACKAGE_NUM");

            String substring = sql.substring(0 , sql.length()-1);
            String endsql =" WHERE SKU_NO= \'"+sku+"\';";
            System.out.println(substring + endsql);
        }
    }

    private StringBuilder appenSql(StringBuilder sql, String value, String filed) {
        if(value == null || value.isEmpty()){
            return sql;
        }
        return sql.append(filed + " = " + value +",");
    }
}
