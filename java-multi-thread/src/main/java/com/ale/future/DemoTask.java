package com.ale.future;

import com.ale.util.ThreadPoolUtil;

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
        Executor executor = ThreadPoolUtil.getCustomExecutor(5,6);
        long start = Instant.now().getEpochSecond();
        List<CompletableFuture<String>> collect =
                names.stream().map(s -> CompletableFuture.supplyAsync(() -> new ImgPressTask(s).press(), executor)).collect(Collectors.toList());
        List<String> collect1 = collect.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(Instant.now().getEpochSecond() - start);
        System.out.println(collect1);
    }







}
