package com.colak.concurrent.forkjoinpool;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

@Slf4j
@UtilityClass
public class ForkJoinPoolTest {

    public static void main() {
        try (ForkJoinPool pool = new ForkJoinPool()) {
            FibonacciTask task = new FibonacciTask(30);
            Integer fibonacci = pool.invoke(task);
            log.info("Result : {}", fibonacci);
        }
    }

    static class FibonacciTask extends RecursiveTask<Integer> {
        private final int n;

        FibonacciTask(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            FibonacciTask f1 = new FibonacciTask(n - 1);
            FibonacciTask f2 = new FibonacciTask(n - 2);
            f1.fork();
            return f2.compute() + f1.join();
        }
    }
}
