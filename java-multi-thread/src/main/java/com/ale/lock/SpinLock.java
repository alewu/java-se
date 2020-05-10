package com.ale.lock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    AtomicReference<Thread> reference = new AtomicReference<>();

    public void lock () {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " try to get lock");
        while (!reference.compareAndSet(null, thread)) {
            // 自旋锁就是利用CAS思想制造循环，block住代码
        }
        System.out.println(thread.getName() + " got the lock");
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        reference.compareAndSet(thread, null);
        System.out.println(thread.getName() + " release the lock");
    }

}
