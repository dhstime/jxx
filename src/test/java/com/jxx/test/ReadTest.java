package com.jxx.test;

import com.jxx.JxxApplicationTests;
import com.jxx.common.model.WarehouseGoodsOperateLog;
import com.jxx.common.model.WarehouseGoodsOperateLogDto;
import com.jxx.common.utils.DateUtil;
import com.jxx.mapper.UnitMapper;
import com.jxx.mapper.WarehouseGoodsOperateLogMapper;
import com.ttqia.ez.list.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Strange
 * @ClassName ReadTest.java
 * @Description TODO
 * @createTime 2020年08月24日 20:05:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@MapperScan(basePackages = {"com.jxx.crawler.mapper","com.jxx.mapper"}, sqlSessionFactoryRef = "dataSourceTarget")
public class ReadTest{

    @Resource
    private UnitMapper unitMapper ;

    @Resource
    private WarehouseGoodsOperateLogMapper warehouseGoodsOperateLogMapper;

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

    @Test
    public void test2() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("/Users/dhs/Downloads/2.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);
        String str;
        while((str = br.readLine() )!= null){
            String[] split = str.split("\t");
            String id = split[0];
            String costPrice = split[1];

            StringBuilder sql = new StringBuilder("UPDATE T_WAREHOUSE_GOODS_OPERATE_LOG SET ");
            sql = appenSql(sql,costPrice,"NEW_COST_PRICE");
            String substring = sql.substring(0 , sql.length()-1);
            String endsql =" WHERE WAREHOUSE_GOODS_OPERATE_LOG_ID= "+id+";";
            System.out.println(substring + endsql);
        }
    }

    @Test
    public void test3() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("/Users/dhs/Downloads/3.txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);
        String str;
        while((str = br.readLine() )!= null){
            String[] split = str.split("\t");
            String id = split[0];
            String tagId = split[1];
            String barcodeId = split[2];

            StringBuilder sql = new StringBuilder("UPDATE T_WAREHOUSE_GOODS_OPERATE_LOG SET ");
            sql = appenSql(sql,tagId,"TAG_SOURCES");
            sql = appenSql(sql,barcodeId,"BARCODE_ID");
            String substring = sql.substring(0 , sql.length()-1);
            String endsql =" WHERE WAREHOUSE_GOODS_OPERATE_LOG_ID= "+ id +";";
            System.out.println(substring + endsql);
        }
    }
}
