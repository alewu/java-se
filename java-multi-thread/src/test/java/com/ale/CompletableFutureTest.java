package com.ale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * 创建CompletableFuture
 * - runAsync
 * - supplyAsync
 * - completedFuture
 * <p>
 * 异步计算启用的线程池是守护线程
 */
public class CompletableFutureTest {

    @DisplayName("创建一个完成的CompletableFuture")
    @Test
    public void completedFutureExample() {
        // 初始化一个有结果无计算的CompletableFuture
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        assertEquals("message", cf.getNow(null));
    }

    @DisplayName("运行一个简单的异步阶段")
    @Test
    public void runAsyncExample() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
        });
        assertFalse(cf.isDone());
        sleepEnough();
        assertTrue(cf.isDone());
    }

    private void sleepEnough() {
        try {
            TimeUnit.MILLISECONDS.sleep(120L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void randomSleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(120L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
