package com.jxx.Service.impl;


import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
//@PropertySource("classpath:/properties/data.properties")
public class DataService implements com.jxx.Service.DataService {
//    @Value("${dog.id}")
    private Integer id;
//    @Value("${dog.name}")
    private  String name;
    @Override
    public String getData() {

        return "id是"+1+"名字是"+2;
    }
}
