package com.jxx.dataTest;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.jxx.JxxApplicationTests;
import com.jxx.common.model.OrderGoods;
import com.jxx.common.model.WarehouseGoodsOperateLogDto;
import com.jxx.common.model.vo.WarehouseGoodsOperateLogVo;
import com.jxx.excel.NoNumberVo;
import com.jxx.mapper.WarehouseGoodsOperateLogMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

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
        HashMap<Integer,HashMap<String,Integer>> daTeskuMap = new HashMap<>();

        for (NoNumberVo noNumberVo : noNumberVoList) {
            String key = noNumberVo.getOrderNo() + ";" + noNumberVo.getSku() ;
            Integer allNum = noNumberMap.get(key);
            if(allNum == null) {
                allNum = 0;
            }
            noNumberMap.put(key,allNum +  noNumberVo.getNum());

            String month = simpleMonthFormat.format(noNumberVo.getAddTime());
            String[] split = month.split("-");
            Integer year = Integer.valueOf(split[0]);
            if(year <= 2018){
                year = 2018;
            }
            //每年每个sku的个数
            HashMap<String, Integer> stringIntegerHashMap = daTeskuMap.get(year);
            if(stringIntegerHashMap == null){
                stringIntegerHashMap = new HashMap<>();
            }
            Integer dateNum = stringIntegerHashMap.get(noNumberVo.getSku());
            if(dateNum == null){
                dateNum = 0;
            }
            stringIntegerHashMap.put(noNumberVo.getSku(), dateNum +noNumberVo.getNum());
            daTeskuMap.put(year,stringIntegerHashMap);
        }

//        getPKSQl(daTeskuMap);

        WarehouseGoodsOperateLogVo search = new WarehouseGoodsOperateLogVo();
        for (String key : noNumberMap.keySet()) {
            String[] orderSkuSplit = key.split(";");
            search.setOrderNo(orderSkuSplit[0]);
            search.setSku(orderSkuSplit[1]);
            List<WarehouseGoodsOperateLogDto> list = warehouseGoodsOperateLogMapper.getNoNumberLogByOrderNo(search);

            for (WarehouseGoodsOperateLogDto inLogDto : list) {
                Date date = new Date(inLogDto.getAddTime());
                String month = simpleMonthFormat.format(date);
                String[] split = month.split("-");
                Integer year = Integer.valueOf(split[0]);
                if(year == 2020){
//                    year = 2018;
                    System.out.println(inLogDto.getWarehouseGoodsOperateLogId());
                }

            }

        }
    }

    private void getPKSQl(HashMap<Integer, HashMap<String, Integer>> daTeskuMap) {
        for (Integer year : daTeskuMap.keySet()) {
            Integer id = 0;
            if(year == 2018){
                id = 141;
            }else if(year == 2019){
                id = 142;
            }else{
                id = 143;
            }
            HashMap<String, Integer> yearSkuMap = daTeskuMap.get(year);
            for (String sku : yearSkuMap.keySet()) {
                Integer num = yearSkuMap.get(sku);
                String insert = "INSERT INTO `T_WMS_OUTPUT_ORDER_GOODS` (`wms_output_order_id`, `sku_no`, `output_num`, `already_output_num`, `last_output_time`, `out_status`, `is_delete` ) " +
                        "VALUES ("+id+",\'"+sku+"\',"+num+","+num+",\'"+year+"-12-31 22:00:00', 2, 0 );";
                System.out.println(insert);
            }
        }
    }


    @Test
    public void read() throws Exception{
        String fileName = "2020年盘亏";
        Integer orderId = 143;

        FileInputStream fileInputStream  = new FileInputStream("/Users/dhs/Downloads/"+fileName+".txt");
        InputStreamReader isr = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(isr);

        String str ;
        while((str = br.readLine() )!= null){
            String[] split = str.split(",");
            List<Integer> tagSoureList = new ArrayList<>();
            for (String s : split) {
                tagSoureList.add(Integer.valueOf(s));
            }

            List<WarehouseGoodsOperateLogDto>  inList = warehouseGoodsOperateLogMapper.getLogOtherBytagSource(tagSoureList);
            for (WarehouseGoodsOperateLogDto warehouseGoodsOperateLogDto : inList) {
                OrderGoods  orderGoods = warehouseGoodsOperateLogMapper.getBFDetailByOrderIdSku(orderId,"V"+warehouseGoodsOperateLogDto.getGoodsId());
                String sql = "UPDATE T_WAREHOUSE_GOODS_OPERATE_LOG SET RELATED_ID = "+orderGoods.getOrderGoodsId()+" WHERE WAREHOUSE_GOODS_OPERATE_LOG_ID="+warehouseGoodsOperateLogDto.getWarehouseGoodsOperateLogId()+";";
                System.out.println(sql);
            }


        }

    }

}
