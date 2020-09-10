package com.ale.threadpool;

import com.ale.thread.CustomThreadFactory;
import com.ale.thread.ExceptionTask;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

class CustomThreadFactoryTest {

     @Test
      void test() throws InterruptedException {
         ThreadFactory threadFactory = new CustomThreadFactory();
         ExecutorService service = Executors.newFixedThreadPool(2, threadFactory);


         service.submit(new ExceptionTask(1));
         service.execute(new ExceptionTask(2));

         TimeUnit.SECONDS.sleep(11);

//         service.shutdown();
     }
}
