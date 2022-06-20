package com.jiaolin.juc;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author johnny
 * @Classname ReadWriteLockDemo
 * @Description 读写锁
 * 读读 不加锁
 * 读写枷锁
 * 写写加锁
 * @Date 2022/3/29 12:47
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            int num = i;
            new Thread(() -> {
                myCache.put(num + "", Thread.currentThread().getName());
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, String.valueOf(i)).start();
        }

    }
}


class MyCache {

    private volatile Map map = new HashMap();

    ReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, String value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写完了" + key);

        } finally {
            lock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        lock.readLock().lock();

        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读完了" + result);
        } finally {
            lock.readLock().unlock();
        }
        return result;
    }

}
