package com.jxx.common.BaseService;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
@Service
public class BaseServiceImpl implements BaseService{
    private static ExecutorService service = new ThreadPoolExecutor(2, 2,
            0L, TimeUnit.MILLISECONDS,  new LinkedBlockingQueue<Runnable>(1024),
            new ThreadFactoryBuilder().setNameFormat("baba-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());
    @Override
    public void exec() {
        service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Math.random());
            }
        });
    }
}
