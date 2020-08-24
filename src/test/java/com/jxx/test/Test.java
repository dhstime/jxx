package com.jxx.test;


import cn.hutool.core.date.DateUtil;
import com.jxx.common.model.LogicalShow;
import com.jxx.common.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String [] args) throws Exception {
        Order order1 = new Order();
        order1.setId(100);
        order1.setNum(0);
        Order order2 = new Order();
        order2.setId(90);
        order2.setNum(12);
        Order order3 = new Order();
        order3.setId(1);
        order3.setNum(23);
        Order order4 = new Order();
        order4.setId(50);
        order4.setNum(0);
        List<Order> list = new ArrayList<>();
        list.add(order1);
        list.add(order2);
        list.add(order3);
        list.add(order4);

        List<Order> collect = list.stream().sorted(Comparator.comparing(Order::getId)).filter(item -> !item.getNum().equals(0)).collect(Collectors.toList());
        System.out.println(collect.toString());
        String[] s = "黑龙江 哈尔滨 南岗区".split(" ");
        System.out.println(s[0]);
        System.out.println(s[1]);
        System.out.println(s[2]);
        String s1 = "soReference1_a1".split("_")[0];
        System.out.println(s1);
    }


}
