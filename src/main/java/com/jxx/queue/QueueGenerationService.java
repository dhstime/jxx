package com.jxx.queue;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;

/**
 * 队列执行
 * @author strange
 * @date $
 */
@Component
public class QueueGenerationService {
    // 日志监控
    private static final Logger log = LoggerFactory.getLogger(QueueGenerationService.class);
    // 根据业务与服务器性能自行配置 这里我配置的是最多50000个任务
    // LinkedBlockingQueue构造的时候若没有指定大小，则默认大小为Integer.MAX_VALUE
    private final LinkedBlockingQueue<QueueTaskHandler> tasks = new LinkedBlockingQueue<QueueTaskHandler>(5000);

    // 类似于一个线程总管 保证所有的任务都在队列之中
    private ExecutorService service = new ThreadPoolExecutor(2, 2,
            0L, TimeUnit.MILLISECONDS,  new LinkedBlockingQueue<Runnable>(1024),
            new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());

    // 检查服务是否运行
    private volatile boolean running = true;
    //线程状态
    private Future<?> serviceThreadStatus = null;

    @PostConstruct
    public void init() {
        serviceThreadStatus = service.submit(new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    try {
                        //开始一个任务
                        QueueTaskHandler QueueTaskHandler = tasks.take();
                        try {
                            QueueTaskHandler.processData();
                        } catch (Exception e) {
                            log.error("任务处理发生错误", e);
                        }
                    } catch (InterruptedException e) {
                        log.error("服务停止，退出", e);
                        running = false;
                    }
                }
            }
        }, "save data thread"));
    }

    public boolean addData(QueueTaskHandler dataHandler) throws InterruptedException {
        if (!running) {
            log.warn("service is stop");
            return false;
        }
        //put 队列已经满了，阻塞
        tasks.put(dataHandler);
        return true;
    }

    //判断队列是否有任务
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public boolean checkServiceRun() {
        return running && !service.isShutdown() && !serviceThreadStatus.isDone();
    }

    public void activeService() {
        running = true;
        if (service.isShutdown()) {
            service = new ThreadPoolExecutor(2, 2,
                    0L, TimeUnit.MILLISECONDS,  new LinkedBlockingQueue<Runnable>(1024),
                    new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());
            init();
            log.info("线程池关闭，重新初始化线程池及任务");
        }
        if (serviceThreadStatus.isDone()) {
            init();
            log.info("线程池任务结束，重新初始化任务");
        }
    }

    @PreDestroy
    public void destory() {
        running = false;
        service.shutdownNow();
    }
}
