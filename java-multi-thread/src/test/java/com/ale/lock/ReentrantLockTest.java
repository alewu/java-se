package com.ale.lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class ReentrantLockTest {

    @Test
    void testReentrantLock() throws InterruptedException {
        IntStream.range(0, 2).forEach(i -> new Thread(ReentrantLockDemo::needLock).start());
        TimeUnit.SECONDS.sleep(20);
    }

    @Test
    void testReentrantLockRecursive() {
        IntStream.rangeClosed(0, 1).forEach(i -> new Thread(ReentrantLockDemo::method1, String.valueOf(i)).start());
    }

    @Test
    void testReentrantLockUnInterrupted() throws InterruptedException {
        Thread t1 = new Thread(ReentrantLockDemo::testLockInterruptibly);
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(ReentrantLockDemo::testLockInterruptibly);
        t2.start();
        TimeUnit.SECONDS.sleep(1);

        t2.interrupt();

        TimeUnit.SECONDS.sleep(10);

    }

    @Test
    void testTryLock() throws InterruptedException {
        Thread thread1 = new Thread(ReentrantLockDemo::testTryLock, "thread1");
        thread1.start();
        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread(ReentrantLockDemo::testTryLock, "thread2");
        thread2.start();
    }


}
