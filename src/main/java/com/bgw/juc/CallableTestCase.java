package com.bgw.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * desc：
 *
 * @author wangzhb 2019/8/7 10:52
 */
public class CallableTestCase {

    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1024);
//        new Thread(() -> {}, "Thread").start();
//
        new Thread(futureTask, "FutureTask1").start();

        System.out.println(futureTask.get());



    }
}


class CallThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}
