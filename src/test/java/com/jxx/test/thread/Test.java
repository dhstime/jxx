package com.jxx.test.thread;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class Test extends Thread{
    int i = 0;
    @Override
    public void run() {
        for (; i < 5; i++) {
            System.out.println(getName() + ":" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            if (i == 1) {
                new Test().start();
                new Test().start();
            }
        }
    }
}
