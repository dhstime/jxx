package com.jxx.Controller;

import com.jxx.crawler.mapper.ChnRecordMapper;
import com.jxx.crawler.model.ChnRecord;
import com.jxx.crawler.service.CrawlerService;
import com.jxx.queue.QueueGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

//    @Autowired
    private QueueGenerationService queueGenerationService;

    @Autowired
    private CrawlerService crawlerService;

    @Autowired
    private ChnRecordMapper chnRecordMapper;
    @RequestMapping("/init")
    public void init(){
        ChnRecord chnRecord = new ChnRecord();
        chnRecord.setChnRecordNumber("abc");
        ChnRecord chnRecordByNumber = chnRecordMapper.getChnRecordByNumber(chnRecord);
        System.out.println(chnRecordByNumber);
//        queueGenerationService.addData(chnRecord);
//        Integer integer = crawlerService.saveChnRecord(chnRecord);

    }
}
