package com.fhdone.paper2019.util;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {

    @Test
    public void test() {
        //使用ArrayBlockingQueue创建一个阻塞队列
        BlockingQueue<Integer> numbers = new ArrayBlockingQueue<>(5);
        try {
            //插入元素到阻塞队列
            numbers.put(2);
            numbers.put(1);
            numbers.put(3);
            System.out.println("BLockingQueue: " + numbers);

            //从阻塞队列中移除元素
            int removedNumber = numbers.take();
            System.out.println("Removed Number: " + removedNumber);
        }
        catch(Exception e) {
            e.getStackTrace();
        }
    }
    
}
