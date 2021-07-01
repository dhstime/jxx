package com.jxx.test.email;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.mail.MailUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.jxx.JxxApplicationTests;
import com.jxx.excel.InStockDataDo;
import com.jxx.excel.easyexcel.BizMergeStrategy;
import com.jxx.excel.easyexcel.TitleSheetWriteHandler;
import com.jxx.mapper.LogDataDtoMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Strange
 * @ClassName EmailTest.java
 * @Description TODO
 * @createTime 2021年06月03日 09:42:00
 */
public class EmailTest extends JxxApplicationTests {

    @Resource
    private LogDataDtoMapper logDataDtoMapper;

    @Test
    public void sendTest(){
        String skus = "V254558";
        List<String> skuList = Arrays.asList(skus.split(","));
        String inSheetName = "入库记录";
        String path = System.getProperty("user.dir")+"/excel/入库记录.xlsx";
//        String inPath = "/Users/dhs/Downloads/审计数据/SKU/入库/" + "入库记录" + ".xlsx";
        List<InStockDataDo> inList = logDataDtoMapper.getInLogListBySku(skuList);
        exportInExcel(inList, inSheetName, path);


        MailUtil.send("it8185@vedeng.com", "测试", "邮件来自Hutool测试", true, FileUtil.file(path));

        FileUtil.del(path);

    }

    private void exportInExcel(List<InStockDataDo> inList, String inSheetName, String inPath) {
        EasyExcel.write(inPath, InStockDataDo.class)
                .excelType(ExcelTypeEnum.XLSX).head(InStockDataDo.class)
                .registerWriteHandler(new TitleSheetWriteHandler(inSheetName, 31)) // 标题及样式，lastCol为标题第0列到底lastCol列的宽度
                //设置默认样式及写入头信息开始的行数
                .relativeHeadRowIndex(1)
                .registerWriteHandler(BizMergeStrategy.CellStyleStrategy()) // 设置样式
                .sheet(inSheetName)
                .doWrite(inList);
    }
}
