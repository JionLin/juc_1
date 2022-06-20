package com.jiaolin.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: juc_zhouyang
 * @description: 阻塞队列
 * @author: Join
 * @create: 2022-03-30 20:27
 **/
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);
       /* blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        System.out.println(blockingQueue);
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.element());*/
       /* blockingQueue.offer("a");
        blockingQueue.offer("b");
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.peek());*/
/*
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();*/

        blockingQueue.offer("a",1, TimeUnit.SECONDS);
        blockingQueue.offer("b",1, TimeUnit.SECONDS);
        blockingQueue.offer("c",1, TimeUnit.SECONDS);
        blockingQueue.offer("d",1, TimeUnit.SECONDS);
    }
}