package com.ale.threadpool;

import com.ale.thread.SleepTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

class RejectedExecutionHandlerTest {
    @Test
    void testAbortPolicy() {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0,
                                                                 TimeUnit.SECONDS, new SynchronousQueue<>());
        executorService.execute(new SleepTask(10));
        try {
            executorService.execute(new SleepTask(10));
        } catch (RejectedExecutionException e) {
            Assertions.fail(e.getMessage());
        }

        executorService.shutdown();

    }

    @Test
    void testCallerRunsPolicy() {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0,
                                                                 TimeUnit.SECONDS, new SynchronousQueue<>(),
                                                                 new ThreadPoolExecutor.CallerRunsPolicy());
        executorService.execute(new SleepTask(5));
        executorService.execute(new SleepTask(5));

        executorService.shutdown();

    }

    @Test
    void testDiscardPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0,
                                                                 TimeUnit.SECONDS, new SynchronousQueue<>(),
                                                                 new ThreadPoolExecutor.DiscardPolicy());
        executorService.execute(new SleepTask(3));
        executorService.execute(new SleepTask(2));
        executorService.execute(new SleepTask(1));

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    @Test
    void testDiscardOldestPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(2, 3, 10,
                                                                 TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                                                                 new ThreadPoolExecutor.DiscardOldestPolicy());
        executorService.execute(new SleepTask(1,5));
        executorService.execute(new SleepTask(2,5));
        // discard
        executorService.execute(new SleepTask(3,5));
        executorService.execute(new SleepTask(4,5));
        executorService.execute(new SleepTask(5,5));



        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

    }


}
