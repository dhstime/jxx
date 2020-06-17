package com.jxx.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Strange
 * @ClassName ThreadLocalTest.java
 * @Description TODO
 * @createTime 2020年06月08日 22:50:00
 */
public class ThreadLocalTest {

    static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<>();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static class ParseDate implements Runnable{
        int i  = 0;
        public ParseDate(int i){
            this.i=i;
        }
        @Override
        public void run() {
             try {
                 if(tl.get() == null){
                     tl.set(new SimpleDateFormat("yyyy-MM-dd"));
                 }
                 Date t = tl.get().parse("2015-03-29");
//                 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                 Date t = simpleDateFormat.parse("2015-03-29");
                 System.out.println(i+":"+t);
                } catch (Exception e) {
                e.printStackTrace();
             }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000 ; i++) {
            es.execute(new ParseDate(i));
        }
    }
}
