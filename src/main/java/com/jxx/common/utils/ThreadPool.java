package com.jxx.common.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Strange
 * @ClassName ThreadPool.java
 * @Description TODO
 * @createTime 2021年04月26日 19:01:00
 */
public class ThreadPool {

    private static ExecutorService service = new ThreadPoolExecutor(3, 3,
            0L, TimeUnit.MILLISECONDS,  new LinkedBlockingQueue<Runnable>(1024),
            new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());

    public static void submit(Runnable runnable){
        service.submit(runnable);
    }

}
