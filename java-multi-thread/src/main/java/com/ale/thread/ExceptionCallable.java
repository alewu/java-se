package com.ale.thread;

import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
  *
  * @author alewu
  * @date 2020/7/11
  */
public class ExceptionCallable implements Callable<String> {
    private static final Logger LOGGER = Logger.getLogger(ExceptionTask.class.getName());
    @Override
    public String call() throws Exception {
        LOGGER.info("Call");
        throw new RuntimeException("uncheckedException");
    }
}
