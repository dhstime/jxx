package com.jxx.service;

import java.math.BigDecimal;
import java.util.Scanner;

public interface DataService {

   public  String getData();

    void insert() throws Exception;

    void thread(double random);

    public static void main(String[] args) {
        method();
    }
    public  static  void method(){
        double a=  new Scanner(System.in).nextDouble();
        double b=  new Scanner(System.in).nextDouble();

        BigDecimal bd1 = new BigDecimal(a);
        BigDecimal bd2 = new BigDecimal(b);

        BigDecimal bd3 = bd1.add(bd2);
          System.out.println(bd3);
           bd3 = bd1.subtract(bd2);
          System.out.println(bd3);
           bd3 = bd1.multiply(bd2);
          System.out.println(bd3);
          //bd3 = bd1.divide(bd2);
          bd3 = bd1.divide(bd2,5,BigDecimal.ROUND_HALF_UP);
          System.out.println(bd3);




    }
}
