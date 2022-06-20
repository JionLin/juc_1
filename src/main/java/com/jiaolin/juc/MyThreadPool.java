package com.jiaolin.juc;

import java.util.concurrent.*;

/**
 * @program: juc_zhouyang
 * @description: 线程池
 * @author: Join  线程池的作用 线程池的复用 控制并发数量 管理线程
 * 3种方式创建线程 1.Executors.newFixedThreadPool(int param)
 * Executors.newSingleThreadPool()
 * Executors.newCacheThreadPool()
 * 自定义线程池 核心数会进行区分,分为io密集型和cpu密集型 cpu密集型 用来进行逻辑运算 以及 高并发的情况 核心数+1
 * io密集型 核心线程数 为 核数*2
 * 七大参数:
 * int corePoolSize, 核心数
 * int maximumPoolSize, 最大线程数
 * long keepAliveTime, 存活时间
 * TimeUnit unit, 时间单位
 * BlockingQueue<Runnable> workQueue, 阻塞队列
 * ThreadFactory threadFactory, 线程工厂 使用默认的
 * RejectedExecutionHandler handler) 拒绝策略 四中
 * 底层原理:
 * 1. execute 进行方法执行,如果线程数小于corepoolsize 等待处理
 * 2. 会进行如下判断
 * 2.1  如果线程数大于核心数<最大数,那么就进入队列中,
 * 2.2  如果这个队列满了,且小于最大线程数,那么就创建非核心线程数进行
 * 2.3  如果这个队列满了,且大于或者等于最大线程数,那么就实施拒绝策略来进行。
 * 2.4  如果线程数大于最大数+队列里面大数,就实施阻塞拒绝方式。有4种 默认中断报错 抛出异常。
 * 3 当线程运行完成后,那么就执行下一个请求
 * 4 当线程无事可做后,且到达了设置的存活时间,那么就消亡,当所有任务完成后,会保持到核心线程数
 * 拒绝策略:当最大线程数量满了,且队列也满了,那么就执行拒绝策略 有4种
 * 1.   默认的,超过之后,直接中断异常
 * 2。   调用者退回机制,谁调用的,退回去给谁
 * 3.   放弃等待时间最久的任务
 * 4.   默默的丢弃无法处理的任务,不处理也不抛出异常。
 * 为什么不用jdk自带的,因为里面的最大线程池为Integer.max 太大。会导致oom 无限制的浪费资源
 * @create: 2022-03-30 21:11
 **/
public class MyThreadPool {
    public static void main(String[] args) {
//         ExecutorService service = Executors.newFixedThreadPool(5);
//         ExecutorService service = Executors.newSingleThreadExecutor();
//        ExecutorService service = Executors.newCachedThreadPool();
        int value = Runtime.getRuntime().availableProcessors();
        System.out.println(value);
        ThreadPoolExecutor service = new ThreadPoolExecutor(
                value + 1,
                16,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(7),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());


        try {
            for (int i = 1; i <= 24; i++) {
                service.submit(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 执行了");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }

    }
}
