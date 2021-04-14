package com.jxx.designfuction.builder;

import java.util.ArrayList;

public class Product {
    //产品类,想要获取的对象

    ArrayList<String> parts = new ArrayList<>();

    public void add(String part){
        parts.add(part);
    }

    public void show(){
        for (String part : parts) {
            System.out.println(part);
        }
    }
}
