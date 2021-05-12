package com.ale;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class ThreadPoolDemoTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        // 创建了一个核心线程数量为1，允许最大线程数量为2，最大活跃时间为10秒，线程队列长度为1的线程池。
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 10, TimeUnit.SECONDS,
                                                                       new ArrayBlockingQueue<>(1));
        // 通过execute方法向线程池提交1个任务
        threadPoolExecutor.execute(() -> sleep(100));
        threadPoolExecutor.execute(() -> sleep(5));
        threadPoolExecutor.execute(() -> sleep(5));
//        threadPoolExecutor.execute(() -> sleep(100));

        int activeCount = -1;
        int queueSize = -1;
        while (true) {
            if (activeCount != threadPoolExecutor.getActiveCount()
                    || queueSize != threadPoolExecutor.getQueue().size()) {
                System.out.println("活跃线程个数 " + threadPoolExecutor.getActiveCount());
                System.out.println("核心线程个数 " + threadPoolExecutor.getCorePoolSize());
                System.out.println("队列线程个数 " + threadPoolExecutor.getQueue().size());
                System.out.println("最大线程数 " + threadPoolExecutor.getMaximumPoolSize());
                System.out.println("------------------------------------");
                activeCount = threadPoolExecutor.getActiveCount();
                queueSize = threadPoolExecutor.getQueue().size();
            }
        }
    }

    private static void sleep(long value) {
        try {
            System.out.println(Thread.currentThread().getName() + "线程执行sleep方法");
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
