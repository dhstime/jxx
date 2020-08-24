package com.jxx.study;


import com.google.inject.internal.cglib.reflect.$FastMethod;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;


/**
 * @author Strange
 * @ClassName add.java
 * @Description TODO
 * @createTime 2020年08月15日 22:19:00
 */
public class add {
    public static void main(String[] args) {
        method2();

    }

    public static void method2() {
        double a = new Scanner(System.in).nextDouble();
        double b = new Scanner(System.in).nextDouble();
          // BigDecimal bd1= new BigDecimal(a);
          // BigDecimal bd2 = new BigDecimal(b);
            BigDecimal bd1   = new BigDecimal(String.valueOf(a));
            BigDecimal bd2 = new BigDecimal(String.valueOf(b+""));

            BigDecimal bd3 = bd1.add(bd2);
            System.out.println(bd3);
            bd3 = bd1.subtract(bd2);
            System.out.println(bd3);
            bd3 = bd1.multiply(bd2);
            System.out.println(bd3);

    }

}
