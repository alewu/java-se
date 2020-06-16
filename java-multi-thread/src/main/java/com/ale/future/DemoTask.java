package com.ale.future;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class DemoTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        names.add("e");
        Map<String, Object> map = new HashMap<>(5);
        Executor executor = getExecutor();
        long start = Instant.now().getEpochSecond();
        List<CompletableFuture<String>> collect =
                names.stream().map(s -> CompletableFuture.supplyAsync(() -> new ImgPressTask(s).press(), executor)).collect(Collectors.toList());
        List<String> collect1 = collect.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(Instant.now().getEpochSecond() - start);
        System.out.println(collect1);
    }

    private static Executor getExecutor() {
        return new ThreadPoolExecutor(5, 5,
                                      0L, TimeUnit.MILLISECONDS,
                                      new ArrayBlockingQueue<>(10));
    }






}
