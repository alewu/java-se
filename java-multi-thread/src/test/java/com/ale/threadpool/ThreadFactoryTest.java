package com.ale.threadpool;

import com.ale.thread.SleepTask;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The type Thread factory test.
 */
class ThreadFactoryTest {

    /**
     * Test scheduled fixed time.
     *
     * @throws InterruptedException the interrupted exception
     */
    @Test
    void testScheduledFixedTime() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(() -> System.out.println("hello"), 5, TimeUnit.SECONDS);
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    /**
     * 按照固定的时延处理任务，比如每延迟5秒执行一个任务，无论上一个任务处理了1秒，1分钟还是1小时，下一个任务总是在上一个任务执行完毕后5秒钟后开始执行。
     *
     * @throws InterruptedException the interrupted exception
     */
    @Test
    void testScheduleWithFixedDelay() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(() -> System.out.println(LocalTime.now() + " hello"), 1, 3, TimeUnit.SECONDS);

        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    /**
     * 按照固定速率执行任务，比如每5秒执行一个任务，即使上一个任务没有结束，5秒后也会开始处理新的任务；
     *
     * @throws InterruptedException the interrupted exception
     */
    @Test
    void testScheduleAtFixedRate() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> System.out.println(LocalTime.now() + " hello"), 1, 3, TimeUnit.SECONDS);

        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

}
