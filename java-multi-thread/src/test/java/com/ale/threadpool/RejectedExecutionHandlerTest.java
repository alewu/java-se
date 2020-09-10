package com.ale.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class RejectedExecutionHandlerTest {
    @Test
    void testAbortPolicy() {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0,
                                                                 TimeUnit.SECONDS, new SynchronousQueue<>());
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("%s sleep %d s", Thread.currentThread().getName(), 1));
        });
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("%s sleep %d s", Thread.currentThread().getName(), 1));
        });

        executorService.shutdown();

    }

    @Test
    void testCallerRunsPolicy() {

    }


}
