package com.jxx.dataTest;

import com.alibaba.excel.EasyExcel;
import com.jxx.JxxApplicationTests;
import com.jxx.excel.DateOrderExprot;
import com.jxx.excel.LogDataDo;
import com.jxx.excel.model.NoModelDataListener;
import com.jxx.mapper.LogDataDtoMapper;
import com.jxx.mapper.StockDataDtoMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Strange
 * @ClassName DateExportTest.java
 * @Description TODO
 * @createTime 2021年05月20日 09:29:00
 */
public class DateExportTest extends JxxApplicationTests {

    @Resource
    private LogDataDtoMapper logDataDtoMapper;


    @Test
    public void dateTest(){
        File file = new File("/Users/dhs/Downloads/出口订单相关信息.xls");
        NoModelDataListener<DateOrderExprot> listen = new NoModelDataListener();
        EasyExcel.read(file, DateOrderExprot.class, listen).sheet().headRowNumber(1).doRead();
        List<DateOrderExprot> list = listen.list;
        DateTimeFormatter day = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("yyyy-MM");



        for (DateOrderExprot dateOrderExprot : list) {
            Date chukouDate = dateOrderExprot.getChukouDate();
            Instant instant = chukouDate.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();

//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(chukouDate);
//            calendar.add(Calendar.DAY_OF_MONTH, -3);
//            Date orderTime = calendar.getTime();

            LocalDateTime chukouTime = instant.atZone(zoneId).toLocalDateTime();
            LocalDateTime orderTime = chukouTime.plusDays(-3);
            String orderTimeStr = day.format(orderTime);
            String daychukouTimeStr = day.format(chukouTime);
            String monthchukouTimeStr = month.format(chukouTime);


            String sql = "update V_LOG_DATA set " +
                    "YEAR_MONTH_DAY = \'" +monthchukouTimeStr +
                    "\', `YEAR_MONTH` = \'" +daychukouTimeStr+
                    "\', ADD_TIME = \'" +orderTimeStr+
                    "\', VALID_TIME = \'" + orderTimeStr +
                    "\', LOG_ADD_TIME =\'" +daychukouTimeStr+
                    "\' where  ORDER_NO =  \'" +dateOrderExprot.getSaleOrderNo().trim() + "\'" +" OR ORDER_NO = \'"+ dateOrderExprot.getBuyOrderNo().trim()+"\' ;";

            System.out.println(sql);
        }

    }
}
