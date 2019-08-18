package com.bgw.juc;

import org.omg.CORBA.TIMEOUT;

import java.security.SecureRandom;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * desc：Lock & await signal 实现生产者消费者
 *
 * @author wangzhb 2019/8/5 12:37
 */
public class ProducerConsumerTestCase3 {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        BlockingQueueShareObj shareObj = new BlockingQueueShareObj(blockingQueue);
        for (int i = 1; i < 12; i++) {
            final int k = i;
            new Thread(() -> shareObj.produce(k), "P-" + i).start();
        }


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < 12; i++) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(shareObj::consume, "C-" + i).start();
        }

    }
}

/**
 * 线程资源类
 */
class BlockingQueueShareObj {

    private BlockingQueue<Integer> queue;

    public BlockingQueueShareObj(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void produce(int i) {
        try {
            this.queue.put(i);
            System.out.println(Thread.currentThread().getName() + "\t 放入 蛋糕" + i + " t=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int consume() {
        try {
            int i = this.queue.take();
            System.out.println(Thread.currentThread().getName() + "\t 取出 蛋糕" + i + " t=" + System.currentTimeMillis());
            return i;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getQueueCount() {
        return this.queue.size();
    }
}