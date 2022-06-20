package com.jiaolin.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author johnny
 * @Classname SemaphoreDemo
 * @Description 信号灯
 * @Date 2022/3/29 12:36
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到了车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + "\t------- 离开");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
