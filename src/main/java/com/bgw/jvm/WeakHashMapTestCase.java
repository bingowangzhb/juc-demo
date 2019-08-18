package com.bgw.jvm;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * desc：
 *
 * @author wangzhb 2019/8/8 14:01
 */
public class WeakHashMapTestCase {

    public static void main(String[] args) {
        // testHashMap();
        // testWeakHashMap2();

        System.out.println(16 << 1);
        ConcurrentHashMap map;
    }

    private static void testWeakHashMap() {
        Map<Gc, String> map = new WeakHashMap<>();
        Gc key = new Gc();
        String value = "WeakHashMap";
        map.put(key, value);
        System.out.println(map);
        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + ", " + map.size());
    }

    private static void testWeakHashMap2() {
        Map<Long, String> map = new WeakHashMap<>();
        Long key = new Long(1);
        String value = "WeakHashMap";
        map.put(key, value);
        System.out.println(map);
        key = null;
        System.gc();
        System.out.println(map + ", " + map.size());
    }

    private static void testHashMap() {
        Map<String, String> map = new HashMap<>();

        String key = "a";
        String value = "HashMap";
        map.put(key, value);
        key = null;

        System.out.println(map);

        System.gc();
        System.out.println(map);
    }
}

class Gc {

}
