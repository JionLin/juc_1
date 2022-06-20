package com.jiaolin.procons;

/**
 * @program: juc_zhouyang
 * @description: 线程等待唤醒
 * @author: Join 需求为
 * 现在两个线程,可以操作初始值为零的一个变量
 * 实现一个线程对变量加1,一个线程对变量减1
 * 实现交替,来10轮,变量初始值为0
 * 高内聚,低耦合进行操作
 * 线程操作资源类
 * 口诀 判断/干活/通知
 * 在多线程交互的时候,防止虚假唤醒。
 * 明天继续操作
 * @create: 2022-03-23 21:30
 * 先提交
 **/
public class ThreadWaitNotifyDemo_01 {

    public static void main(String[] args) {
        Resource_01 resource01 = new Resource_01();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    resource01.decrea();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    resource01.increm();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    resource01.decrea();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "C").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    resource01.increm();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "D").start();

    }
}

// 初始值为0 一个加1 一个减1
class Resource_01 {
    private int number = 0;

    // 减1
    public  synchronized void decrea() throws Exception {
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }

    // 加1
    public synchronized void increm() throws Exception {
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }
}

