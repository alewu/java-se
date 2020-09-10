package com.ale.threadpool;

import com.ale.thread.SleepTask;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

class ThreadPoolTest {

    private static final CountDownLatch count = new CountDownLatch(5);

    ThreadPoolExecutor pool = new ThreadPoolExecutor(
            1, 2, 10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5), (ThreadFactory) Thread::new,
            new ThreadPoolExecutor.AbortPolicy());

    @Test
    void test() {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(new SleepTask(10));
        service.execute(new SleepTask(10));
    }

    @Test
    void testThread() throws InterruptedException {
        pool.execute(() -> sleep(100));
        pool.execute(() -> sleep(100));
        pool.execute(() -> sleep(200));
        pool.execute(() -> sleep(200));
        pool.execute(() -> sleep(200));
        pool.execute(() -> sleep(200));
        pool.execute(() -> sleep(200));
        count.await();

    }

    private void sleep(int i) {
        System.out.println(String.format("线程活跃数: %d, 任务数量： %d", pool.getActiveCount(), pool.getQueue().size()));
        System.out.println(String.format("线程%s 执行 sleep 方法", Thread.currentThread().getName()));
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.countDown();

    }
}
