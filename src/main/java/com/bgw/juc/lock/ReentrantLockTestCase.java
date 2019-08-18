package com.bgw.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * desc：
 *
 * @author wangzhb 2019/8/6 14:57
 */
public class ReentrantLockTestCase {

    public static void main(String[] args) {

        MessageSender messageSender = new MessageSender();

        new Thread(messageSender::sendEmail, "ThreadA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(messageSender::sendSms, "ThreadB").start();
    }
}


class MessageSender {
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t sendSms----------------");
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t sendEmail--------------------");
    }
}
