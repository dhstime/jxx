package com.jxx.Controller;

import com.jxx.common.ResultInfo;
import com.jxx.crawler.mapper.ChnRegisterMapper;
import com.jxx.crawler.model.ChnRegister;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("index/")
public class ComputeController {

    @Resource
    private ChnRegisterMapper chnRegisterMapper;


    @RequestMapping("/get")
    public ResultInfo get(){
        ResultInfo resultInfo = new ResultInfo();
        ChnRegister chnRegister = chnRegisterMapper.getChnRegisterById(150019);
        resultInfo.setData(chnRegister);
        return resultInfo;
    }
}
