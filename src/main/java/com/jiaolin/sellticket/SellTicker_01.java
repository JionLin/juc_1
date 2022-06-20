package com.jiaolin.sellticket;

/**
 * @program: juc_zhouyang  3个人共卖票 30张
 * @description: 多线程卖票功能 2
 * @author: Join 线程 操作 资源类 2 使用lock 可重入锁
 * @create: 2022-03-23 16:37
 **/
public class SellTicker_01 {

    public static void main(String[] args) {
        Ticket_01 ticket01 = new Ticket_01();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) ticket01.sell();
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) ticket01.sell();
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) ticket01.sell();
        }, "C").start();
    }
}


// 票
class Ticket_01 {

    // 高内聚 低耦合  一个资源类 包含它自身携带的方法

    private int number = 30;

    public synchronized void sell() {

        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "\t 卖出第" + number-- + "张票,还剩" + number + "票");
        }
    }

}
