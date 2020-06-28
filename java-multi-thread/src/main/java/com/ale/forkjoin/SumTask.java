package com.ale.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author alewu
 * @date 2020/6/28
 */
public class SumTask extends RecursiveTask<Long> {
    static final int THRESHOLD = 100;
    long[] array;
    int start;
    int end;

    SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // 如果任务足够小,直接计算:
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println(String.format("%s compute %d~%d = %d", Thread.currentThread().getName(), start, end,
                                             sum));
            return sum;
        }
        // 任务太大,一分为二:
        int middle = (end + start) / 2;
        System.out.println(String.format("%s split %d~%d ==> %d~%d, %d~%d", Thread.currentThread().getName(), start,
                                         end, start, middle, middle, end));
//                SumTask subtask1 = new SumTask(this.array, start, middle);
//                subtask1.fork();
//                SumTask subtask2 = new SumTask(this.array, middle, end);
//                subtask2.fork();
//
//                Long subresult1 = subtask1.join();
//                Long subresult2 = subtask2.join();


        SumTask subtask1 = new SumTask(this.array, start, middle);
        SumTask subtask2 = new SumTask(this.array, middle, end);
        invokeAll(subtask1, subtask2);
        Long subresult1 = subtask1.join();
        Long subresult2 = subtask2.join();

        Long result = subresult1 + subresult2;
        System.out.println(String.format("%s result = %d + %d = %d", Thread.currentThread().getName(), subresult1,
                                         subresult2, result));
        return result;
    }

}
