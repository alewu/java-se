package com.ale;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * 计算完成时需要对异常进行处理或者对结果进行处理
 * - whenComplete：同步处理包括异常
 * - thenApply：同步处理正常结果（前提是没有异常）
 * - whenCompleteAsync：异步处理包括异常
 * - thenApplyAsync：异步处理正常结果(前提是没有异常)
 * - exceptionally : 处理异常
 */
public class DealWithResultTest {
    /**
     * 处理异步计算的结果: thenApply
     *
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    @Test
    public void test() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> future = completableFuture.thenApply(s -> s + " World");

        assertEquals("Hello World", future.get());

    }

    /**
     * 不需要在Future链中返回值: thenAccept
     *
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture.thenAccept(s -> System.out.println("Computation returned: "
                                                                                                      + s));

        Void actual = future.get();
        assertNull(actual);
    }

    /**
     * 既不需要计算的值也不想在链的末尾返回一些值：thenRun
     *
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture.thenRun(() -> System.out.println("Computation finished."));

        future.get();

    }
}
