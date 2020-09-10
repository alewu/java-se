package com.ale.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2020/9/10
 */
public class SleepTask implements Runnable {
    private int taskId;
    private int sleepTime;

    public SleepTask(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public SleepTask(int taskId, int sleepTime) {
        this.taskId = taskId;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s execute task-%d sleep %d s", Thread.currentThread().getName(), taskId, sleepTime));
    }
}
