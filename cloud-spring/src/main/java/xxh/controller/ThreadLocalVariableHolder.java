package xxh.controller;

import java.util.concurrent.*;

/**
 * @Author: elyuan
 * @Date: 2020/10/29 4:37 下午
 */
public class ThreadLocalVariableHolder {


    private static ThreadLocal<Integer> variableHolder = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    private static int getValue() {
        return variableHolder.get();
    }

    private static void increment() {
        variableHolder.set(variableHolder.get() + 1);
    }


    private static void remove() {
        variableHolder.remove();
    }

    //为了验证
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(1, 5,
                10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                try {
                    long threadId = Thread.currentThread().getId();
                    int before = getValue();
                    increment();
                    int after = getValue();
                    System.out.println("threadId: " + threadId + ", before: " + before + ", after: " + after);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    remove();

                }
            });
        }
        threadPool.shutdown();
    }
}
