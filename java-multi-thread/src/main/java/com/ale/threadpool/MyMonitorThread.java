package com.ale.threadpool;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2020/9/10
 */
public class MyMonitorThread implements Runnable {
    private ThreadPoolExecutor executor;
    private int seconds;
    private boolean run = true;

    public MyMonitorThread(ThreadPoolExecutor executor, int delay) {
        this.executor = executor;
        this.seconds = delay;
    }

    public void shutdown() {
        this.run = false;
    }

    @Override
    public void run() {
        while(run){
            System.out.println(
                    String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                                  this.executor.getPoolSize(),
                                  this.executor.getCorePoolSize(),
                                  this.executor.getActiveCount(),
                                  this.executor.getCompletedTaskCount(),
                                  this.executor.getTaskCount(),
                                  this.executor.isShutdown(),
                                  this.executor.isTerminated()));
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
