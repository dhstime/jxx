package com.jxx.crawler.service;


import com.jxx.crawler.model.ChnRecord;
import com.jxx.crawler.model.ChnRegister;
import com.jxx.crawler.model.ForeRecord;
import com.jxx.crawler.model.ForeRegister;

public interface CrawlerService {
    /**
    *保存国产备案信息
    * @Author:strange
    * @Date:15:54 2020-03-07
    */
   public Integer  saveChnRecord(ChnRecord chnRecord);
    /**
     *保存国产注册信息
     * @Author:strange
     * @Date:15:54 2020-03-07
     */
   public Integer  saveChnRegister(ChnRegister chnRegister);
    /**
     *保存进口备案信息
     * @Author:strange
     * @Date:15:54 2020-03-07
     */
   public Integer  saveForeRecordd(ForeRecord foreRecord);
    /**
     *保存进口注册信息
     * @Author:strange
     * @Date:15:54 2020-03-07
     */
   public Integer  saveForeRegister(ForeRegister foreRegister);


}
