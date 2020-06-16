package com.ale.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static final Lock lock = new ReentrantLock();

    public static void needLock() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " start working!");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void needLockBySync() {
        synchronized (ReentrantLockDemo.class) {
            try {
                System.out.println(Thread.currentThread().getName() + "开始工作");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void method1() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " invoked method1");
            method2();
        } finally {
            lock.unlock();
        }
    }

    public static void method2() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " invoked method2");
        } finally {
            lock.unlock();
        }
    }

    /**
     * lock方法是不可被打断的，即调用线程的interrupt方法不起作用：
     * ReentrantLock提供了可打断获取锁的方法lockInterruptibly：
     */
    public static void testLockInterruptibly() {
        try {
            //            lock.lock();  // 不可以被打断
            lock.lockInterruptibly(); // 可以被打断
            System.out.println(Thread.currentThread().getName() + "开始工作");
            while (true) {
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * ReentrantLock的tryLock方法用于尝试获取锁，返回boolean类型，表示获取锁成功与否：
     */
    public static void testTryLock() {
        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName() + "开始工作");
                while (true) {
                }
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "没有获取到锁");
        }
    }

}
