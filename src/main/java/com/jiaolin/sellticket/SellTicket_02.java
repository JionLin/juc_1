package com.jiaolin.sellticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: juc_zhouyang
 * @description: 使用lock进行
 * @author: Join 线程操作资源类
 * @create: 2022-03-23 21:11
 **/
public class SellTicket_02 {
    public static void main(String[] args) {
        Ticket_02 ticket02 = new Ticket_02();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket02.sell();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket02.sell();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket02.sell();
            }
        }, "C").start();
    }
}


class Ticket_02 {

    private int number = 30;

    Lock lock = new ReentrantLock();

    public void sell() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第" + number-- + "张票,还剩" + number + "票");
            }
        } finally {
            lock.unlock();
        }
    }

}