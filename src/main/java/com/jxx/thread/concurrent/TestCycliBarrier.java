package com.jxx.thread.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author Strange
 * @ClassName TestCycliBarrier.java
 * @Description TODO
 * @createTime 2020年11月16日 09:55:00
 */
public class TestCycliBarrier {

    // 类似于一个线程总管 保证所有的任务都在队列之中
    private static ExecutorService service = new ThreadPoolExecutor(2, 4,
            0L, TimeUnit.MILLISECONDS,  new LinkedBlockingQueue<Runnable>(1024),
            new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());

    //用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务；
    public static void main(String[] args) {
        int N = 2;
        CyclicBarrier barrier  = new CyclicBarrier(N);
        for(int i=0;i<N;i++){
            service.submit(new Writer(barrier));
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("CyclicBarrier重用");

        for(int i=0;i<N;i++) {
            service.submit(new Writer(barrier));
        }
        service.shutdown();
    }
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await(2,TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}
