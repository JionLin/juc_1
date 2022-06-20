package com.jiaolin.nosafedemo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author johnny
 * @Classname demo_1
 * @Description CopyOnWriteArrayList 使用了写时复制,里面用了copy数组,进行重新赋值 使用lock 的方式
 * final ReentrantLock lock = this.lock;
 * lock.lock();
 * try {
 * Object[] elements = getArray();
 * int len = elements.length;
 * Object[] newElements = Arrays.copyOf(elements, len + 1);
 * newElements[len] = e;
 * setArray(newElements);
 * return true;
 * } finally {
 * lock.unlock();
 * }
 * @Date 2022/3/25 9:31 上午
 */
public class demo_1 {
    public static void main(String[] args) {

        // 使用copyonwriteArraylist 读写分离
//        lockList();
//        setlock();
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(UUID.randomUUID().toString().substring(0, 8), Thread.currentThread().getName());
                System.out.println(map);
            }, "A").start();

        }


    }

    private static void setlock() {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, "A").start();

        }
    }

    private static void lockList() {
        List<String> list = new CopyOnWriteArrayList();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
