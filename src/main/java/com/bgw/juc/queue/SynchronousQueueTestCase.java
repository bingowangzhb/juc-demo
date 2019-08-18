package com.bgw.juc.queue;

import java.util.concurrent.*;

/**
 * desc：
 *
 * @author wangzhb 2019/8/6 20:42
 */
public class SynchronousQueueTestCase {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {

            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName() + "\t put 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName() + "\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "ThreadA").start();

        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(blockingQueue.take());

                TimeUnit.SECONDS.sleep(3);
                System.out.println(blockingQueue.take());

                TimeUnit.SECONDS.sleep(3);
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "ThreadB").start();
    }
}
