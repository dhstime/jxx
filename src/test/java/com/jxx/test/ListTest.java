package com.jxx.test;

import java.math.BigDecimal;

public class ListTest {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(0.00);
        BigDecimal b = new BigDecimal(100);
        System.out.println("a.multiply(b) = " + a.multiply(b));
        if(null != a && a.equals(b)){
            System.out.println(a);
        }
    }
}
