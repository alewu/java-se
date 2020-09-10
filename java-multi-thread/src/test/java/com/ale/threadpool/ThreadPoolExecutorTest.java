package com.ale.threadpool;


import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

class ThreadPoolExecutorTest {
    @Test
    void testAllowCoreThreadTimeOut() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1, 2, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("任务执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPoolExecutor.awaitTermination(20, TimeUnit.SECONDS);
    }

    /**
     * 默认情况下，只有当往线程池里提交了任务后，线程池才会启动核心线程处理任务。
     * 我们可以通过调用prestartCoreThread方法，让核心线程即使没有任务提交，也处于等待执行任务的活跃状态：
     */
    @Test
    void testPrestartCoreThread(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, 2, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        System.out.println("活跃线程数: " + threadPoolExecutor.getActiveCount());
        threadPoolExecutor.prestartCoreThread();
        System.out.println("活跃线程数: " + threadPoolExecutor.getActiveCount());
        threadPoolExecutor.prestartCoreThread();
        System.out.println("活跃线程数: " + threadPoolExecutor.getActiveCount());
        threadPoolExecutor.prestartCoreThread();
        System.out.println("活跃线程数: " + threadPoolExecutor.getActiveCount());
    }
}
