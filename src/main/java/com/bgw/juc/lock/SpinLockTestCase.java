package com.bgw.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * desc：自旋锁Case
 *
 * @author wangzhb 2019/8/6 15:18
 */
public class SpinLockTestCase {
    public static void main(String[] args) {
        SpinLock lock = new SpinLock();

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.unlock();
        }, "ThreadA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "ThreadB").start();

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "ThreadC").start();
    }
}

class SpinLock {
    private AtomicReference<Thread> threadRef = new AtomicReference<>();

    public void lock() {
        Thread currentThread = Thread.currentThread();
        while (!threadRef.compareAndSet(null, currentThread));
        System.out.println(Thread.currentThread().getName() + "\t get lock");
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();
        threadRef.compareAndSet(currentThread, null);
        System.out.println(Thread.currentThread().getName() + "\t release lock");
    }
}
