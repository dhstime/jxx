package com.jxx.dataTest;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.jxx.JxxApplicationTests;
import com.jxx.common.model.WarehouseGoodsOperateLogDto;
import com.jxx.common.model.vo.WarehouseGoodsOperateLogVo;
import com.jxx.excel.InStockDataDo;
import com.jxx.excel.NoNumberVo;
import com.jxx.mapper.WarehouseGoodsOperateLogMapper;
import org.junit.Test;
import sun.text.resources.FormatData;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author Strange
 * @ClassName NoNumberLog.java
 * @Description TODO
 * @createTime 2021年04月23日 17:15:00
 */
public class NoNumberLogTest extends JxxApplicationTests {

    @Resource
    private WarehouseGoodsOperateLogMapper warehouseGoodsOperateLogMapper;

    @Test
    public void test(){
        File file = new File("/Users/dhs/Downloads/noNumber.xlsx");
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setSheetNum(1);
        List<NoNumberVo> noNumberVoList = ExcelImportUtil.importExcel(file, NoNumberVo.class, params);

        SimpleDateFormat simpleMonthFormat = new SimpleDateFormat("yyyy-MM");

        HashMap<String,Integer> noNumberMap = new HashMap<>();
        HashMap<String, Set<String>> daTeskuMap = new HashMap<>();

        for (NoNumberVo noNumberVo : noNumberVoList) {
            String key = noNumberVo.getOrderNo() + ";" + noNumberVo.getSku();
            Integer allNum = noNumberMap.get(key);
            if(allNum == null) {
                noNumberMap.put(key, noNumberVo.getNum());
            }else{
                noNumberMap.put(key,allNum +  noNumberVo.getNum());
            }

            String month = simpleMonthFormat.format(noNumberVo.getAddTime());
            String[] split = month.split("-");
            Integer year = Integer.valueOf(split[0]);

        }
//        WarehouseGoodsOperateLogVo search = new WarehouseGoodsOperateLogVo();
//        for (String key : noNumberMap.keySet()) {
//            Integer allNum = noNumberMap.get(key);
//
//            String[] split = key.split(";");
//            search.setOrderNo(split[0]);
//            search.setSku(split[1]);
//            List<WarehouseGoodsOperateLogDto> list = warehouseGoodsOperateLogMapper.getNoNumberLogByOrderNo(search);
//
//        }
    }
}
