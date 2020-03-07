package com.jxx.crawler.service.impl;

import com.jxx.crawler.mapper.ChnRecordMapper;
import com.jxx.crawler.mapper.ChnRegisterMapper;
import com.jxx.crawler.mapper.ForeRecordMapper;
import com.jxx.crawler.mapper.ForeRegisterMapper;
import com.jxx.crawler.model.ChnRecord;
import com.jxx.crawler.model.ChnRegister;
import com.jxx.crawler.model.ForeRecord;
import com.jxx.crawler.model.ForeRegister;
import com.jxx.crawler.service.CrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CrawlerServiceImpl implements CrawlerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChnRegisterMapper chnRegisterMapper;

    @Autowired
    private ChnRecordMapper chnRecordMapper;

    @Autowired
    private ForeRecordMapper foreRecordMapper;

    @Autowired
    private ForeRegisterMapper foreRegisterMapper;

    @Override
    public Integer saveChnRecord(ChnRecord chnRecord) {
        int i = 0;
        try {
            ChnRecord old = chnRecordMapper.getChnRecordByNumber(chnRecord);
            if(old == null){
                i = chnRecordMapper.insert(chnRecord);
            }else{
                chnRecord.setChnRecordId(old.getChnRecordId());
                i = chnRecordMapper.updateByPrimaryKey(chnRecord);
            }
            return i;
        }catch (Exception e){
            logger.error("saveChnRecord Number:{},error:{}",chnRecord.getChnRecordNumber(),e);
        }
        return i;
    }

    @Override
    public Integer saveChnRegister(ChnRegister chnRegister) {
        int i = 0;
        try {
            ChnRegister old =  chnRegisterMapper.getChnRegisterByNumber(chnRegister);
        if(old == null){
            i = chnRegisterMapper.insert(chnRegister);
        }else{
            chnRegister.setChnRegisterId(old.getChnRegisterId());
            i = chnRegisterMapper.updateByPrimaryKey(chnRegister);
        }
        }catch (Exception e){
            logger.error("saveChnRegister Number:{},error:{]",chnRegister.getChnRegisterNumber(),e);

        }
        return i;
    }

    @Override
    public Integer saveForeRecordd(ForeRecord foreRecord) {
        int i = 0;
        try {
            ForeRecord old =  foreRecordMapper.getForeRecordByNumber(foreRecord);
        if(old == null){
            i = foreRecordMapper.insert(foreRecord);
        }else{
            foreRecord.setForeRecordId(old.getForeRecordId());
            i = foreRecordMapper.updateByPrimaryKey(foreRecord);
        }
        }catch (Exception e){
            logger.error("saveForeRecordd number:{},error:{}",foreRecord.getForeRecordNumber(),e);
        }
        return i;
    }

    @Override
    public Integer saveForeRegister(ForeRegister foreRegister) {
        int i = 0;
        try {
            ForeRegister old =  foreRegisterMapper.getForeRegisterByNumber(foreRegister);
        if(old == null){
            i = foreRegisterMapper.insert(foreRegister);
        }else{
            foreRegister.setForeRegisterId(old.getForeRegisterId());
            i = foreRegisterMapper.updateByPrimaryKey(foreRegister);
        }
        }catch (Exception e){
            logger.error("saveForeRegister number:{},error:{}",foreRegister.getForeRegisterNumber(),e);
        }
        return i;
    }
}
