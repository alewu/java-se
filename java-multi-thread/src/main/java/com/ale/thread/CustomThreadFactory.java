package com.ale.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
  *
  * @author alewu
  * @date 2020/7/11
  */
public class CustomThreadFactory implements ThreadFactory {
    private static final Logger LOGGER = Logger.getLogger(CustomThreadFactory.class.getName());

    private final AtomicInteger count = new AtomicInteger(1);

    private static final String THREAD_NAME_TEMPLATE = "custom-thread-%d";

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, String.format(THREAD_NAME_TEMPLATE, count.getAndIncrement()));
        thread.setUncaughtExceptionHandler((t, e) -> LOGGER.warning(String.format("线程[%s]发生异常%s", t.getName(), e)));
        return thread;
    }
}
