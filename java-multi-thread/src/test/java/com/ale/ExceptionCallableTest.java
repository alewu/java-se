package com.ale;

import com.ale.thread.ExceptionCallable;
import org.junit.jupiter.api.Test;

import java.util.concurrent.FutureTask;
import java.util.logging.Logger;

public class ExceptionCallableTest {
    private static final Logger LOGGER = Logger.getLogger(ExceptionHandlerTest.class.getName());
    @Test
    public void test(){
        FutureTask<String> task = new FutureTask<>(new ExceptionCallable());
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler((t, e) -> LOGGER.warning(String.format("线程[%s]发生异常%s", t.getName(), e)));
        thread.start();
    }
}
