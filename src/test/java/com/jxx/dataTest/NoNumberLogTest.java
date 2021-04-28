package com.jxx.dataTest;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.jxx.JxxApplicationTests;
import com.jxx.common.model.OrderGoods;
import com.jxx.common.model.WarehouseGoodsOperateLogDto;
import com.jxx.common.model.vo.WarehouseGoodsOperateLogVo;
import com.jxx.excel.InExport;
import com.jxx.excel.NoNumberVo;
import com.jxx.excel.OutExport;
import com.jxx.mapper.ExportMapper;
import com.jxx.mapper.WarehouseGoodsOperateLogMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @author Strange
 * @ClassName NoNumberLog.java
 * @Description TODO 处理2020年12月盘盈亏
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
    //109的盘盈
    @Test
    public void panYingtest(){
        String[] split = skus.split("\n");
        for (String skuNum : split) {
            String[] split1 = skuNum.split("\t");
            String sku = split1[0];
            String num = split1[1];
//            String sql = "INSERT INTO `erp_ivedeng_com`.`T_WMS_INPUT_ORDER_GOODS_backup1`( `WMS_INPUT_ORDER_ID`, `SKU_NO`, `GOODS_ID`, `INPUT_NUM`, `ARRIVAL_NUM`, `ARRIVAL_STATUS`, `ADD_TIME`, `MODE_TIME`, `CREATOR`, `UPDATER`, `IS_DELTET`) VALUES (224, \'"+sku+"\', "+sku.substring(1)+", "+num+", "+num+", 2, '2020-12-31 22:00:00', '2020-12-31 22:00:00', 0, 0, 0);";
//            System.out.println(sql);

        }
        String[] split1 = idsSKU.split("\n");
        for (String idSku : split1) {
            String[] split2 = idSku.split("\t");
            Integer id = Integer.valueOf(split2[0].trim());
            String sku = split2[1].trim();
            OrderGoods orderGoods = warehouseGoodsOperateLogMapper.getPanyingDetail("PY2179258792020",sku);
            String sql = "UPDATE T_WAREHOUSE_GOODS_OPERATE_LOG SET RELATED_ID = "+orderGoods.getOrderGoodsId()+",NUM = "+orderGoods.getNum() +" WHERE WAREHOUSE_GOODS_OPERATE_LOG_ID="+id+";";
            System.out.println(sql);
        }

    }

    @Test
    public void bfstr109(){
        String[] split = bfstr109.split("\n");
        HashMap<Integer, HashMap<String, Integer>> daTeskuMap = new HashMap<>();
        HashMap<String,Integer> map = new HashMap<>();
        for (String s : split) {
            String[] split1 = s.split("\t");
            map.put(split1[0],Integer.valueOf(split1[1].trim()));
        }
        daTeskuMap.put(2020,map);
//        getPKSQl(daTeskuMap);
        String[] split1 = bfidSku109.split("\n");
        for (String idSku : split1) {
            String[] split2 = idSku.split("\t");
            Integer id = Integer.valueOf(split2[0].trim());
            String sku = split2[1].trim();
            OrderGoods  orderGoods = warehouseGoodsOperateLogMapper.getBFDetailByOrderIdSku(143,sku);
            String sql = "UPDATE T_WAREHOUSE_GOODS_OPERATE_LOG SET RELATED_ID = "+orderGoods.getOrderGoodsId()+",NUM = -NUM WHERE WAREHOUSE_GOODS_OPERATE_LOG_ID="+id+";";
            System.out.println(sql);
        }

    }

    @Resource
    private ExportMapper exportMapper;

    @Test
    public void export() throws Exception{
        exportin();
        exportout();
    }

    private void exportout() throws Exception{
        LocalDateTime  startTime =  LocalDateTime.of(2020,9,1,0,0,0);
        LocalDateTime  endTime = LocalDateTime.of(2021,9,1,0,0,0);
        List<OutExport> outlist = exportMapper.selectOutAll(localDatetime2TimeStamp(startTime),localDatetime2TimeStamp(endTime));
        //直发销售
//        List<OutExport> outExports = exportMapper.selectDirectOutAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
//        outlist.addAll(outExports);
        String titleName = "出库记录" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String sheetName = "出库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String path = "/Users/dhs/Downloads/" + sheetName + ".xlsx";
        Workbook  workbook = ExcelExportUtil.exportExcel(new ExportParams(titleName,sheetName),OutExport.class,outlist);
        FileOutputStream outfos = new FileOutputStream(path);
        workbook.write(outfos);
        outfos.close();
    }

    private void exportin()  throws Exception{
        LocalDateTime  startTime =  LocalDateTime.of(2020,9,1,0,0,0);
        LocalDateTime  endTime = LocalDateTime.of(2021,9,1,0,0,0);
        List<InExport> inList = exportMapper.selectInAll(localDatetime2TimeStamp(startTime),localDatetime2TimeStamp(endTime));
        //直发采购
//        List<InExport> inExports = exportMapper.selectDirectInAll(localDatetime2TimeStamp(startTime), localDatetime2TimeStamp(endTime));
//        inLIst.addAll(inExports);
        String titleName = "入库记录" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String sheetName = "入库" + startTime.getYear() + "-" + startTime.getMonth().getValue();
        String path = "/Users/dhs/Downloads/" + sheetName + ".xlsx";
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(titleName,sheetName),InExport.class,inList);
        FileOutputStream infos = new FileOutputStream(path);
        workbook.write(infos);
        infos.close();
    }

    private Long localDatetime2TimeStamp(LocalDateTime time){
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
    //V255216
    private String skus =
            "V277448\t40\n" +
                    "V503632\t4\n" +
                    "V251648\t1\n" +
                    "V273180\t8\n" +
                    "V277022\t8\n" +
                    "V116830\t5\n" +
                    "V255261\t1\n" +
                    "V255216\t1\n" +
                    "V149757\t2\n" +
                    "V254404\t1\n" +
                    "V272277\t40\n" +
                    "V274470\t4\n" +
                    "V274526\t30\n" +
                    "V275431\t5\n" +
                    "V275689\t1\n" +
                    "V276222\t2\n" +
                    "V277890\t8\n" +
                    "V277967\t1\n" +
                    "V278161\t1\n" +
                    "V500116\t1\n" +
                    "V500123\t18\n" +
                    "V500213\t1\n" +
                    "V500652\t1\n" +
                    "V501902\t15\n" +
                    "V502524\t20\n" +
                    "V503761\t5";
    //新增盘盈id
    private String idsSKU = "7096590\tV116830\n" +
            "7096591\tV149757\n" +
            "7096592\tV251648\n" +
            "7096593\tV254404\n" +
            "7096594\tV255216\n" +
            "7096595\tV255261\n" +
            "7096596\tV272277\n" +
            "7096597\tV273180\n" +
            "7096598\tV275431\n" +
            "7096599\tV275689\n" +
            "7096600\tV274470\n" +
            "7096601\tV276222\n" +
            "7096602\tV277022\n" +
            "7096603\tV277448\n" +
            "7096604\tV274526\n" +
            "7096605\tV278161\n" +
            "7096606\tV277890\n" +
            "7096607\tV277967\n" +
            "7096608\tV500116\n" +
            "7096609\tV500123\n" +
            "7096610\tV500213\n" +
            "7096611\tV501902\n" +
            "7096612\tV502524\n" +
            "7096613\tV503761\n" +
            "7096614\tV503632\n" +
            "7096615\tV500652";


    private String bfstr109 = "V254149\t1\n" +
            "V503307\t5\n" +
            "V148005\t1\n" +
            "V274405\t2\n" +
            "V274453\t1\n" +
            "V274457\t17\n" +
            "V274523\t1\n" +
            "V274731\t1\n" +
            "V275991\t1\n" +
            "V276660\t14\n" +
            "V500358\t6\n" +
            "V502180\t3\n" +
            "V502339\t17\n" +
            "V504900\t2\n" +
            "V505621\t30\n" +
            "V506100\t1\n" +
            "V506327\t12";

    private String bfidSku109 = "7096694\tV254149\n" +
            "7096695\tV275991\n" +
            "7096696\tV274731\n" +
            "7096697\tV274523\n" +
            "7096698\tV502180\n" +
            "7096699\tV502180\n" +
            "7096700\tV502180\n" +
            "7096701\tV274457\n" +
            "7096702\tV505621\n" +
            "7096703\tV505621\n" +
            "7096704\tV505621\n" +
            "7096705\tV505621\n" +
            "7096706\tV505621\n" +
            "7096707\tV505621\n" +
            "7096708\tV505621\n" +
            "7096709\tV505621\n" +
            "7096710\tV505621\n" +
            "7096711\tV505621\n" +
            "7096712\tV505621\n" +
            "7096713\tV505621\n" +
            "7096714\tV505621\n" +
            "7096715\tV505621\n" +
            "7096716\tV505621\n" +
            "7096717\tV505621\n" +
            "7096718\tV505621\n" +
            "7096719\tV505621\n" +
            "7096720\tV505621\n" +
            "7096721\tV505621\n" +
            "7096722\tV505621\n" +
            "7096723\tV505621\n" +
            "7096724\tV505621\n" +
            "7096725\tV505621\n" +
            "7096726\tV505621\n" +
            "7096727\tV505621\n" +
            "7096728\tV505621\n" +
            "7096729\tV505621\n" +
            "7096730\tV505621\n" +
            "7096731\tV505621\n" +
            "7096732\tV274457\n" +
            "7096733\tV274457\n" +
            "7096734\tV274457\n" +
            "7096735\tV274457\n" +
            "7096736\tV274457\n" +
            "7096737\tV274457\n" +
            "7096738\tV274457\n" +
            "7096739\tV274457\n" +
            "7096740\tV274457\n" +
            "7096741\tV274457\n" +
            "7096742\tV274405\n" +
            "7096743\tV274405\n" +
            "7096744\tV503307\n" +
            "7096745\tV503307\n" +
            "7096746\tV503307\n" +
            "7096747\tV506327\n" +
            "7096748\tV504900\n" +
            "7096749\tV504900\n" +
            "7096750\tV276660\n" +
            "7096751\tV276660\n" +
            "7096752\tV276660\n" +
            "7096753\tV506100\n" +
            "7096754\tV500358\n" +
            "7096755\tV276660\n" +
            "7096756\tV276660\n" +
            "7096757\tV276660\n" +
            "7096758\tV276660\n" +
            "7096759\tV276660\n" +
            "7096760\tV276660\n" +
            "7096761\tV276660\n" +
            "7096762\tV276660\n" +
            "7096763\tV276660\n" +
            "7096764\tV276660\n" +
            "7096765\tV276660\n" +
            "7096766\tV148005\n" +
            "7096767\tV274453\n" +
            "7096826\tV502339\n" +
            "7096827\tV502339";
}
