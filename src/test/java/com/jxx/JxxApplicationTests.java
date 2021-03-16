package com.jxx;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.jxx.Service.DataService;
import com.jxx.excel.Export;
import com.jxx.mapper.ExportMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JxxApplicationTests {

    @Resource
    private ExportMapper exportMapper;
    @Test
    public void test() throws Exception{

        LocalDateTime startTime = LocalDateTime.of(2018,12,1,0,0,0);

        do {
            LocalDateTime endTime = startTime.plusMonths(1);
            List<Export> list = exportMapper.selectAll(localDatetime2TimeStamp(startTime),localDatetime2TimeStamp(endTime));
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("出库记录",""),Export.class,list);
            String fileName = "出库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
            FileOutputStream fos = new FileOutputStream("/Users/dhs/Downloads/出库/"+fileName + ".xlsx");
            workbook.write(fos);
            fos.close();
            startTime = endTime;
        } while (startTime.isBefore(LocalDateTime.of(2021,3,1,0,0,0)));
    }
    private Long localDatetime2TimeStamp(LocalDateTime time){
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
