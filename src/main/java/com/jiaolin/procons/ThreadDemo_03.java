package com.jiaolin.procons;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author johnny
 * @Classname ThreadDemo_03
 * @Description 顺序调用 a->b->c
 * 1. aa 打印5次  bb打印10次 cc 打印15次
 * 来10轮
 * 1. 线程控制资源类
 * 2. 判断/干活/通知
 * 3. 防止虚假唤醒
 * 4。 标志位
 * @Date 2022/3/24 5:17 下午
 */
public class ThreadDemo_03 {
    public static void main(String[] args) {
        Resource resource = new Resource();


        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.print5();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.print10();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.print15();
            }
        }, "C").start();
    }
}


class Resource {

    private int number = 1;
    Lock lock = new ReentrantLock();

    Condition conditon1 = lock.newCondition();
    Condition conditon2 = lock.newCondition();
    Condition conditon3 = lock.newCondition();

    // aa 打印5次
    public void print5() {
        lock.lock();
        try {
            while (number != 1) {
                conditon1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t");
            }
            number = 2;
            conditon2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    // bb 打印10次
    public void print10() {
        lock.lock();
        try {
            while (number != 2) {
                conditon2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t");
            }
            number = 3;
            conditon3.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    // cc 打印15次
    public void print15() {
        lock.lock();
        try {
            while (number != 3) {
                conditon3.await();
            }
            for (int i = 1; i <= 15; i++) {

                System.out.println(Thread.currentThread().getName() + "\t");
            }
            number = 1;
            conditon1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}