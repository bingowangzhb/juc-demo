package com.bgw.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * desc：
 *
 * @author wangzhb 2019/8/6 10:37
 */
public class AtomicStampedReferenceTestCase {

    public static void main(String[] args) {

        // 复现ABA问题
        AtomicReference<Integer> reference = new AtomicReference<>(100);
        new Thread(() -> {
            reference.compareAndSet(100, 101);
            reference.compareAndSet(101, 100);
        }, "Thread1").start();


        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(reference.compareAndSet(100, 2019) + "\t " + reference.get());
        }, "Thread2").start();

        // ABA问题解决
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t stamp=" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stampedReference.compareAndSet(100, 101, stamp, stamp + 1);
            stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t stamp=" + stamp);


            stampedReference.compareAndSet(101, 100, stamp, stamp + 1);
            stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t stamp=" + stamp);
        }, "ThreadC").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t stamp=" + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t" + stampedReference.getReference() + "\t " + stampedReference.getStamp());
        }, "ThreadD").start();


    }
}
