package com.jxx.crawler.factory;

import com.jxx.crawler.model.ChnRecord;
import com.jxx.crawler.model.ChnRegister;
import com.jxx.crawler.model.ForeRecord;
import com.jxx.crawler.model.ForeRegister;
import com.jxx.crawler.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  工厂类实现
 * @author strange
 * @date $
 */
@Component
public class CrawlerFactory implements IfCrawlerFactory{
    @Autowired
    private CrawlerService crawlerService;
    @Override
    public Integer saveCrawlerInfo(Object object) {
        Integer i = 0;
        if(object instanceof ChnRecord){

             i = crawlerService.saveChnRecord((ChnRecord)object);

        }else if(object instanceof ChnRegister){

             i = crawlerService.saveChnRegister((ChnRegister)object);

        }else if(object instanceof ForeRegister){

            i = crawlerService.saveForeRegister((ForeRegister)object);

        }else if(object instanceof ForeRecord){

            i = crawlerService.saveForeRecordd((ForeRecord)object);

        }
        return i;
    }
}
