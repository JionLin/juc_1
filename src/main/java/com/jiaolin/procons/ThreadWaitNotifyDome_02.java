package com.jiaolin.procons;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author johnny
 * @Classname ThreadWaitNotifyDome_02
 * @Description 生产者 消费者 进行交互通信
 * 现在两个线程,可以操作初始值为零的一个变量
 * 实现一个线程对变量加1,一个线程对变量减1
 * 实现交替,来10轮,变量初始值为0
 * <p>
 * 线程操作资源类
 * 交互中,防止虚假幻想
 * 判断/干活/通知
 * 使用lock的方式进行 可重入锁
 * @Date 2022/3/24 3:10 下午
 */
public class ThreadWaitNotifyDome_02 {
    public static void main(String[] args) {
        Resource_02 resource02 = new Resource_02();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                resource02.decrement();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                resource02.increment();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                resource02.decrement();
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                resource02.increment();
            }
        }, "D").start();
    }
}


// 加1 和减1 两个方法
class Resource_02 {
    private int number = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}