package com.jxx.excel.model;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 直接用map接收数据
 *
 * @author Jiaju Zhuang
 */
public class NoModelDataListener<T> extends AnalysisEventListener<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoModelDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    public  List<T> list = new ArrayList<>();

    @Override
    public void invoke(T data, AnalysisContext context) {
       // LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
       // LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
       // LOGGER.info("{}条数据，开始存储数据库！", list.size());
       // LOGGER.info("存储数据库成功！");
    }
}