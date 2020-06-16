package com.ale.future;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
/**
  *
  * @author alewu
  * @date 2020/6/16
  */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            System.out.println("isDaemon: " + Thread.currentThread().isDaemon());
            randomSleep();
        });
        System.out.println(Thread.currentThread().getName() + ": " + cf.isDone());
        sleepEnough();
        System.out.println("sleepEnough: " + cf.isDone());
    }

    private static void sleepEnough() {
        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void randomSleep() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextLong(5L));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
