package com.jxx;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.jxx.excel.InExport;
import com.jxx.excel.OutExport;
import com.jxx.excel.StockExport;
import com.jxx.mapper.ExportMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
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

            List<OutExport> outlist = exportMapper.selectOutAll(localDatetime2TimeStamp(startTime),localDatetime2TimeStamp(endTime));
            //直发销售
            List<OutExport> outExports = exportMapper.selectDirectOutAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
            outlist.addAll(outExports);

            Workbook outworkbook = ExcelExportUtil.exportExcel(new ExportParams("出库记录",""),OutExport.class,outlist);
            String outfileName = "出库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
            FileOutputStream outfos = new FileOutputStream("/Users/dhs/Downloads/数据/出库明细/"+outfileName + ".xlsx");
            outworkbook.write(outfos);
            outfos.close();

            List<InExport> inLIst = exportMapper.selectInAll(localDatetime2TimeStamp(startTime),localDatetime2TimeStamp(endTime));
            //直发采购
            List<InExport> inExports = exportMapper.selectDirectInAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
            inLIst.addAll(inExports);

            Workbook inworkbook = ExcelExportUtil.exportExcel(new ExportParams("入库记录",""),InExport.class,inLIst);
            String infileName = "入库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
            FileOutputStream infos = new FileOutputStream("/Users/dhs/Downloads/数据/入库明细/"+infileName + ".xlsx");
            inworkbook.write(infos);
            infos.close();

            List<StockExport> stockExportList = exportMapper.selectStockSnapshot(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));

            Workbook stockworkbook = ExcelExportUtil.exportExcel(new ExportParams("库存明细",""),StockExport.class,stockExportList);
            String stockfileName = "库存明细" + startTime.getYear() + "-" + startTime.getMonth().getValue();
            FileOutputStream stockfos = new FileOutputStream("/Users/dhs/Downloads/数据/库存明细/"+stockfileName + ".xlsx");
            stockworkbook.write(stockfos);
            stockfos.close();

            System.out.println(startTime.toString());
            startTime = endTime;

        } while (startTime.isBefore(LocalDateTime.of(2021,4,1,0,0,0)));
    }

    private Long localDatetime2TimeStamp(LocalDateTime time){
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
