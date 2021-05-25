package com.jxx.dataTest;

import com.jxx.JxxApplicationTests;
import com.jxx.excel.LogDataDo;
import com.jxx.excel.StockDataDo;
import com.jxx.mapper.LogDataDtoMapper;
import com.jxx.mapper.StockDataDtoMapper;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Strange
 * @ClassName StockDataWeightPrice.java
 * @Description TODO
 * @createTime 2021年05月24日 13:23:00
 */
public class StockDataWeightPrice extends JxxApplicationTests {
    @Resource
    private LogDataDtoMapper logDataDtoMapper;
    @Resource
    private StockDataDtoMapper stockDataDtoMapper;

    @Test
    public void execute(){
        List<StockDataDo> stockDataDoList = stockDataDtoMapper.getNotHaveWeightPrice();
        for (StockDataDo stockDataDo : stockDataDoList) {
            StockDataDo update = new StockDataDo();
            update.setStockDataId(stockDataDo.getStockDataId());
            List<LogDataDo> logDataByOrderNoSku = logDataDtoMapper.getLogDataByOrderNoSku(stockDataDo.getOrderNo(),stockDataDo.getSku(),0);
            if(CollectionUtils.isNotEmpty(logDataByOrderNoSku)){
                LogDataDo logDataDo = logDataByOrderNoSku.get(0);
                update.setRealPrice(logDataDo.getRealPrice());
                update.setRealTotalAmount(logDataDo.getRealTotalAmount());
                stockDataDtoMapper.updateByPrimaryKeySelective(update);
            }
        }

    }
}
