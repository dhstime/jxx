package com.jxx.Service.impl;

import com.jxx.Service.IndexService;
import com.jxx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {
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
        User user = new User();
        user.setId(2);
        return user.toString();
    }
}
