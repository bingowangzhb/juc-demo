package com.bgw.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * desc：读写锁Case
 *
 * @author wangzhb 2019/8/6 15:56
 */
public class ReadWriteLockTestCase {

    public static void main(String[] args) {
        MyCache cache = new MyCache();

        for (int i = 1; i < 11; i++) {
            final int k = i;
            new Thread(() -> {
                cache.put("i" + k, k);
            }, "Thread-Write-" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < 11; i++) {
            final int k = i;
            new Thread(() -> {
                cache.get("" + k);
            }, "Thread-Read-" + i).start();
        }

    }
}

class MyCache {
    private volatile Map<String, Object> cache = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object val) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入...key:" + key);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cache.put(key, val);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取...key: " + key);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object val = cache.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成");
            return val;
        } finally {
            lock.readLock().unlock();
        }
    }


}
