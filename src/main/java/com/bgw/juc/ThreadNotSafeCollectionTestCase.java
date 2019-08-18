package com.bgw.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * desc：
 *
 * @author wangzhb 2019/8/6 11:01
 */
public class ThreadNotSafeCollectionTestCase {

    public static void main(String[] args) {
        List<Integer> list2 = Arrays.asList(2, 4, 0, 3, 9);
        list2.forEach(System.out::println);

        //testNotSafeList();
        //testSafeList();

        //testNotSafeMap();
        testSafeMap();
    }

    private static void testNotSafeMap() {
        Map<String, Integer> unsafeMap = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            final int k = i;
            new Thread(() -> {
                unsafeMap.put(k + "", k);
                System.out.println(unsafeMap);
            }, "t" + k).start();
        }
    }
    private static void testSafeMap() {
        Map<String, Integer> unsafeMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            final int k = i;
            new Thread(() -> {
                unsafeMap.put(k + "", k);
                System.out.println(unsafeMap);
            }, "T" + k).start();
        }
    }

    private static void testNotSafeList() {
        List<Integer> unsafeList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            final int k = i;
            new Thread(() -> {
                unsafeList.add(k);
                System.out.println(unsafeList);
            }, "T" + k).start();
        }
    }

    private static void testSafeList() {
        List<Integer> safeList = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            final int k = i;
            new Thread(() -> {
                safeList.add(k);
                System.out.println(safeList);
            }, "Thread" + k).start();
        }
    }


}
