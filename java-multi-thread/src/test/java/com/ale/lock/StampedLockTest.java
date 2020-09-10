package com.ale.lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class StampedLockTest {

    @Test
    void test(){
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        Runnable read = StampedLockDemo::read;
        Runnable write = StampedLockDemo::write;

        IntStream.range(0, 19).forEach(i -> executorService.submit(read));
        executorService.submit(write);

        executorService.shutdown();
    }


}
