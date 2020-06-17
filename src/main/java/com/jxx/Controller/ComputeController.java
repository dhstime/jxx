package com.jxx.Controller;

import cn.hutool.core.util.RandomUtil;
import com.jxx.Service.DataService;
import com.jxx.Service.IndexService;
import com.jxx.common.ResultInfo;
import com.jxx.crawler.mapper.ChnRegisterMapper;
import com.jxx.crawler.model.ChnRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("index/")
public class ComputeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ChnRegisterMapper chnRegisterMapper;

    @Autowired
    private DataService dataService;

    @Autowired
    private IndexService indexService;


    @RequestMapping("/get")
    public ResultInfo get(){
        ResultInfo resultInfo = new ResultInfo();
        ChnRegister chnRegister = chnRegisterMapper.getChnRegisterById(150019);
        resultInfo.setData(chnRegister);
        return resultInfo;
    }

    @RequestMapping("/solr")
    public ResultInfo solr() throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        return resultInfo;
    }

    @RequestMapping("/insert")
    public void  insert() throws Exception {
        dataService.insert();
    }

    @RequestMapping("/thread")
    public void thread(){
        double random = Math.random();
        dataService.thread(random);
    }
    @RequestMapping("/thread2")
    public void thread2(){
       indexService.thread();
    }
}
