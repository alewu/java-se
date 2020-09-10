package com.ale.threadpool;

import com.ale.thread.CustomThreadFactory;
import com.ale.thread.ExceptionTask;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class CustomThreadFactoryTest {

     @Test
      void test() {
         ThreadFactory threadFactory = new CustomThreadFactory();
         ExecutorService service = Executors.newFixedThreadPool(2, threadFactory);


         service.submit(new ExceptionTask(1));
         service.execute(new ExceptionTask(2));

         service.shutdown();
     }
}
