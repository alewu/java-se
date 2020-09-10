package com.ale.threadpool;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
  *  
  * @author alewu
  * @date 2020/9/10
  */
public class WorkerThread implements Runnable {
    private String command;

    public WorkerThread(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName() + " Start. Command = " + command);
        processCommand();
        System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName() + " End.");
    }

    private void processCommand() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}
