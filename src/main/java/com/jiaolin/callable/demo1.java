package com.jiaolin.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author johnny
 * @Classname demo1
 * @Description 实现callable接口的方式, 也是线程的一种方式
 * 比较Runnable 接口区别
 * 1. 是否有返回值
 * 2. 是否抛出异常
 * 3. 落地方法不同 run() 一个call()
 * <p>
 * call方法只执行一次,使用异步的方式,需要获取返回值 放在最后一行
 * @Date 2022/3/27 22:06
 */
public class demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyThread2());
        new Thread(futureTask, "A").start();
//        new Thread(futureTask, "B").start();
        System.out.println("main 线程");
        System.out.println(futureTask.get());
    }
}



class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("come in ");
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {

    }
}