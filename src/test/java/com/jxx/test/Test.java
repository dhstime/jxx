package com.jxx.test;


import cn.hutool.core.date.DateUtil;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
import com.jxx.common.model.LogicalShow;
import com.jxx.common.model.Order;
import com.jxx.common.model.QuoteorderGoods;
import sun.security.provider.MD5;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String [] args) throws Exception {
//        Order order1 = new Order();
//        order1.setId(100);
//        order1.setNum(0);
//        Order order2 = new Order();
//        order2.setId(90);
//        order2.setNum(12);
//        Order order3 = new Order();
//        order3.setId(1);
//        order3.setNum(23);
//        Order order4 = new Order();
//        order4.setId(50);
//        order4.setNum(0);
//        List<Order> list = new ArrayList<>();
//        list.add(order1);
//        list.add(order2);
//        list.add(order3);
//        list.add(order4);
//
//        List<Order> collect = list.stream().sorted(Comparator.comparing(Order::getId)).filter(item -> !item.getNum().equals(0)).collect(Collectors.toList());
//        System.out.println(collect.toString());
//        String[] s = "黑龙江 哈尔滨 南岗区".split(" ");
//        System.out.println(s[0]);
//        System.out.println(s[1]);
//        System.out.println(s[2]);
//        String s1 = "soReference1_a1".split("_")[0];
//        System.out.println(s1);


//        FileInputStream fileInputStream = new FileInputStream("/Users/dhs/Downloads/1.txt");
//        InputStreamReader isr = new InputStreamReader(fileInputStream);
//        BufferedReader br = new BufferedReader(isr);
//        String str = null;
//        boolean flag = false;
//        String preStr = null;
//        List<QuoteorderGoods> list = new ArrayList<>();
//        while((str = br.readLine() )!= null){
//            if(flag){
//                String[] split = preStr.split("]");
//                String[] split1 = split[0].split("\\[");
//                Integer id = Integer.valueOf(split1[1]);
//
//                System.out.println(id);
//                flag = false;
//                preStr = null;
//            }
//            if(str.contains("quoteorderGoods")){
//                flag = true;
////                System.out.println("title:"+str);
//                preStr = str;
//            }
//        }

//        String bar = "";
//        String[] split = bar.split(",");
//        for (String a : split) {
//            String sql = "";
//            System.out.println(sql);
//        }



    }
}
