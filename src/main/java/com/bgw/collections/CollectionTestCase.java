package com.bgw.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * desc：
 *
 * @author wangzhb 2019/8/13 10:48
 */
public class CollectionTestCase {

    public static void main(String[] args) {
        Map<Integer, String> hmap = new HashMap<>(16, 1);
        hmap.put(null, "1");
        hmap.put(null, "23");
        hmap.put(32, "1");
        hmap.put(48, "1");
        hmap.put(64, "1");
        hmap.put(80, "1");
        hmap.put(96, "1");
        System.out.println(hmap.containsKey(null));
        System.out.println(hmap);

        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("1", "");
        ConcurrentHashMap<String, String> cmap = new ConcurrentHashMap<>(16, 0.75f);
        cmap.put("12", "23");
        cmap.get("23");
    }
}