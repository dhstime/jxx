package com.jxx.test;



import java.util.concurrent.LinkedBlockingQueue;

public class Test {
    public static void main(String [] args) throws Exception {
        LinkedBlockingQueue<Integer> tasks = new LinkedBlockingQueue<Integer>(1000);
        for (int i = 0; i < 10 ; i++) {
            tasks.put(i);
            System.out.println("size:"+tasks.size());
            Integer take = tasks.take();
//            System.out.println("take:"+take);
            System.out.println("size:"+tasks.size());
        }
    }
}
