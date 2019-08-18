package com.bgw.juc.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * desc：
 *
 * @author wangzhb 2019/8/7 15:01
 */
public class ThreadPoolTestCase {

    public static void main(String[] args) {
        testThreadPool3();
    }

    private static void testThreadPool3() {
        ExecutorService pool = new ThreadPoolExecutor(3, 5, 1L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(3),
                new ThreadFactoryBuilder().setNameFormat("task-%d").build(),
                new ThreadPoolExecutor.DiscardPolicy());

        Future<String> future = pool.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "helloWorld";
        });

        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        pool.shutdown();
    }


    private static void testThreadPool2() {
        ExecutorService pool = new ThreadPoolExecutor(3, 5, 1L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(3),
                new ThreadFactoryBuilder().setNameFormat("task-%d").build(),
                new ThreadPoolExecutor.DiscardPolicy());

        try {
            for (int i = 0; i < 9; i++) {
                pool.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 处理请求");
                });
            }
        } finally {
            pool.shutdown();
        }

    }


    private static void testThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        //ExecutorService pool = Executors.newSingleThreadExecutor();
        //ExecutorService pool = Executors.newCachedThreadPool()


        try {
            for (int i = 0; i < 10; i++) {
                pool.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 处理请求");
                });
            }
        } finally {
            pool.shutdown();
        }
    }
}
