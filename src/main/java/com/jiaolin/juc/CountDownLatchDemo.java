package com.jiaolin.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author johnny
 * @Classname CountDownLatchDemo
 * @Description 倒计时计数器 按顺序才能执行 计数为0才会打开
 * @Date 2022/3/29 12:20
 */
public class CountDownLatchDemo {
    public static void main(String[] args)throws Exception {
        // 获取电脑的cpu核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 号同学离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("班长离开教室+\t");
    }
}
