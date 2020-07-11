package com.ale;

import com.ale.thread.ExceptionTask;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;


/**
 * @author alewu
 * @date 2020/7/11
 */
class ExceptionHandlerTest {
    private static final Logger LOGGER = Logger.getLogger(ExceptionHandlerTest.class.getName());

    @Test
    void test() {
        Thread thread = new Thread(new ExceptionTask());
        // 用日志记录下错误
        thread.setUncaughtExceptionHandler((t, e) -> LOGGER.warning(String.format("线程[%s]发生异常{%s}", t.getName(), e)));
        // Thread.setDefaultUncaughtExceptionHandler((t, e) -> LOGGER.warning("线程[%s]发生异常{%s}", t.getName(), e));
        thread.start();
    }
}
