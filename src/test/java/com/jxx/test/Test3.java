package com.jxx.test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.jxx.mapper.ExportMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author Strange
 * @ClassName Test3.java
 * @Description TODO
 * @createTime 2021年01月14日 16:36:00
 */

public class Test3 {

    @Autowired
    private ExportMapper exportMapper;
    @Test
    public void test() throws Exception{
//        Order order = new Order();
//        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(order);
//        System.out.println(bw);


    }
}
