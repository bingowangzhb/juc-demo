package com.bgw.juc.lock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc：lock vs lockInterruptibly
 *
 * @author wangzhb 2019/8/6 22:26
 */
public class LockTestCase {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                doLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
                doPrint("thread 1 is interrupted.");
            }
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            try {
                doLock();
            } catch (InterruptedException e) {
                // e.printStackTrace();
                doPrint("thread 2 is interrupted！！！");
            }
        }, "Thread2");


        thread1.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 1秒后把线程2中断
        thread2.interrupt();
    }

    private static void doLock() throws InterruptedException {
        doPrint(Thread.currentThread().getName() + " try get lock");
        lock.lock(); //等待锁的过程中会立即响应中断
        doPrint(Thread.currentThread().getName() + " got lock");
        try {
            TimeUnit.SECONDS.sleep(10); // 等待几秒方便查看线程的先后顺序
        } finally {
            doPrint(Thread.currentThread().getName() + " release lock");
            lock.unlock();
        }
    }

    private static void doPrint(String text) {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " : " + text);
    }
}
