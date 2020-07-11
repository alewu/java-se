package com.ale.thread;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author alewu
 * @date 2020/7/11
 */
public class ExceptionTask implements Runnable {

    private int index;

    public ExceptionTask() {
    }

    public ExceptionTask(int index) {
        this.index = index;
    }

    private static final Logger LOGGER = Logger.getLogger(ExceptionTask.class.getName());

    @Override
    public void run() {
        LOGGER.log(Level.INFO,"{0} : Run", this.index);
        throw new RuntimeException("uncheckedException");
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
