package com.jxx.Controller;

import com.jxx.Service.DataService;
import com.jxx.common.ResultInfo;
import com.jxx.crawler.mapper.ChnRegisterMapper;
import com.jxx.crawler.model.ChnRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("index/")
public class ComputeController {

    @Resource
    private ChnRegisterMapper chnRegisterMapper;

    @Autowired
    private DataService dataService;

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
}
