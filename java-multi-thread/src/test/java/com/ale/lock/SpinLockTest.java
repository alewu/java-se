package com.ale.lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class SpinLockTest {
    @Test
    void testSpinLock() throws InterruptedException {
        SpinLock lock = new SpinLock();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " do something ...");
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "线程1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " do something ...");
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "线程2").start();

        TimeUnit.SECONDS.sleep(20);
    }

}