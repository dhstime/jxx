package com.jxx.service.impl;

import com.jxx.service.IndexService;
import com.jxx.common.BaseService.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public  class IndexServiceImpl extends BaseServiceImpl implements IndexService {
//    @Value("${node.host}")
    private String host;
//    @Value("${node.port}")
    private String port;
//    @Value("${server.port}")
    private String port2;


    @Override
    public String getMsg() {
        String msg = "ip地址是"+host+"端口是"+port+"启动"+port2;
        return msg;
    }

    @Override
    public String getorder() {
        return null;
    }

    @Override
    public void thread() {
        exec();
    }


}
