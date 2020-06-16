package com.ale.future;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author alewu
 * @date 2020/6/16
 */
public class ImgPressTask implements Supplier<String> {
    private String name;

    public ImgPressTask(String aa) {
        super();
        this.name = aa;
    }

    public String press() {
        try {
            int timeout = ThreadLocalRandom.current().nextInt(3);
            System.out.println("sleep %d s".format(String.valueOf(timeout)));
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextInt(1000) + name;
    }

    @Override
    public String get() {
        return press();
    }
}
