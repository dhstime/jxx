package com.jxx.dataTest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.jxx.excel.model.NoModelDataListener;
import com.ttqia.ez.list.dao.Dao;
import com.ttqia.ez.list.util.SqlUtils;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Strange
 * @ClassName OutCostPriceRead.java
 * @Description TODO 出库价保存脚本
 * @createTime 2021年04月22日 19:10:00
 */
public class OutCostPriceReadTest {
    static DataSource origin = SqlUtils.datasourc("jdbc:mysql://192.168.1.53:3306/erp_ivedeng_com?tinyInt1isBit=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true",
            "erpuser",
            "neweu3453563", "com.mysql.jdbc.Driver", "0000-10000");
    static Dao dao = Dao.getInstance();


    public static Map<String, Object> selectOne(String sql, Object[] obj) throws SQLException {
        return dao.executeQueryOne(origin, sql, obj);
    }

    public static List<Map<String, Object>> selectList(String sql, Object[] obj) throws SQLException {
        return dao.executeQuery(origin, sql, obj);
    }

    public static void main(String[] args) {
        String path = "/Users/dhs/Downloads/stock1619000717008.xlsx";
        NoModelDataListener listen = new NoModelDataListener();
        ExcelReaderBuilder readerBuilder = EasyExcel.read(path, listen);
        readerBuilder.doReadAll();
        Map<String, Map<String,BigDecimal>> stockpriceMap = new HashMap<>();
        AtomicInteger count = new AtomicInteger();
        //读取每个sku每个月的出库单价
        listen.list.forEach(item->{
            LocalDateTime startTime = LocalDateTime.of(2018,12,1,0,0,0);
            String sku = item.get(0);
            Map<String, BigDecimal> monthPrice = new HashMap<>();
            int index = 12;
            do {
                LocalDateTime endTime = startTime.plusMonths(1);
                String time = startTime.getYear() + "-" + startTime.getMonth().getValue();
                BigDecimal outPrice = new BigDecimal(item.get(index));
                if(outPrice.compareTo(BigDecimal.ZERO) > 0) {
                    monthPrice.put(time, outPrice);
                    count.getAndIncrement();
                }
                index+=9;
                startTime = endTime;
            } while (startTime.isBefore(LocalDateTime.of(2021,4,1,0,0,0)));
            if(!monthPrice.isEmpty()){
                stockpriceMap.put(sku,monthPrice);
            }
        });
        System.out.println(count);
        for (String sku : stockpriceMap.keySet()) {

//            selectOne();

            Map<String, BigDecimal> stringBigDecimalMap = stockpriceMap.get(sku);
            for (String dateStr : stringBigDecimalMap.keySet()) {
                String[] split = dateStr.split("-");
                LocalDateTime startTime = LocalDateTime.of(Integer.valueOf(split[0]),Integer.valueOf(split[1]),1,0,0,0);
                LocalDateTime endTime = startTime.plusMonths(1);

                BigDecimal bigDecimal = stringBigDecimalMap.get(dateStr);

                StringBuilder sql = new StringBuilder("UPDATE T_WAREHOUSE_GOODS_OPERATE_LOG SET ");
                sql = appenSql(sql,bigDecimal.toString(),"NEW_COST_PRICE");
                String substring = sql.substring(0 , sql.length()-1);
                String endsql =" WHERE  GOODS_ID = " ;
            }

        }

    }

    private Long localDatetime2TimeStamp(LocalDateTime time){
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    private static StringBuilder appenSql(StringBuilder sql, String value, String filed) {
        if(value == null || value.isEmpty()){
            return sql;
        }
        return sql.append(filed + " = " + value +",");
    }
}
