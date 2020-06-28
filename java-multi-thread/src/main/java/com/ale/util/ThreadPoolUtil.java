package com.ale.util;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author alewu
 * @date 2020/6/27
 */
public final class ThreadPoolUtil {

    public static Executor getCustomExecutor(int corePoolSize, int maximumPoolSize) {

        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                                      0L, TimeUnit.MILLISECONDS,
                                      new ArrayBlockingQueue<>(10), customThreadFactory());
    }

    public static ThreadFactory customThreadFactory() {
        return new CustomThreadFactory();
    }

    /**
     * The default thread factory
     */
    static class CustomThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        CustomThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "custom-pool-" +
                    POOL_NUMBER.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                                  namePrefix + threadNumber.getAndIncrement(),
                                  0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

}
