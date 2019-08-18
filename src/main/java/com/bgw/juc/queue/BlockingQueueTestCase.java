package com.bgw.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * desc：
 *
 * @author wangzhb 2019/8/6 19:21
 */
public class BlockingQueueTestCase {

    public static void main(String[] args) {
        // testBlockingQueueOperation1();
        // testBlockingQueueOperation2();
        // testBlockingQueueOperation3();
        testBlockingQueueOperation4();
    }

    private static void testBlockingQueueOperation4() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        // offer元素
        try {
            System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("d", 2L, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // blockingQueue.clear();

        // 检查队首元素
        // System.out.println(blockingQueue.peek());

        // remove元素 队列空抛异常
        try {
            System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void testBlockingQueueOperation1() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        // add元素 队列满抛异常
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // System.out.println(blockingQueue.add("d"));

        // blockingQueue.clear();

        // 检查队首元素
        System.out.println(blockingQueue.element());

        // remove元素 队列空抛异常
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

    }

    private static void testBlockingQueueOperation2() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        // offer元素
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));

        // blockingQueue.clear();

        // 检查队首元素
        System.out.println(blockingQueue.peek());

        // remove元素 队列空抛异常
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    private static void testBlockingQueueOperation3() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        Thread currentThread = Thread.currentThread();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentThread.interrupt();
        }, "Thread").start();


        // offer元素
        try {
            blockingQueue.put("a");
            blockingQueue.put("b");
            blockingQueue.put("c");
            blockingQueue.put("d");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-------quit---------");

        try {
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
