package com.jiaolin.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author johnny
 * @Classname CyclicBarrierDemo
 * @Description 到达指定的数 才开门  计数达到总和才会开门
 * @Date 2022/3/29 12:27
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws Exception {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,
                ()->{System.out.println("集齐7龙珠 召唤神龙");});
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 星龙珠被收集 ");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}
