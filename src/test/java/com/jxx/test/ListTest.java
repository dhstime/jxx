package com.jxx.test;

import com.jxx.pojo.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
//        List<User> list = new ArrayList<>();
//        User user = new User();
//        user.setName("as");
////        list.add(user);
//        if(CollectionUtils.isNotEmpty(list)) {
//            System.out.println("list.size() = " + list.size());
//        }
//        String t = "0";
//        if (t != null && t != "") {
//            System.out.println("t = " + t);
//        }
        BigDecimal a = new BigDecimal(0.00);
        BigDecimal b = new BigDecimal(100);
        System.out.println("a.multiply(b) = " + a.multiply(b));
        if(null != a && a.equals(b)){
            System.out.println(a);
        }
    }
}
