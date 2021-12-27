package com.fhdone.paper2019.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HashMapDemo {

    public static final int SIZE = 2;

    public static void main(String[] args) throws InterruptedException {

        Map<String, String> maps = new HashMap<>();

//        Map<String, String> map = new ConcurrentHashMap<>();

        Map<String, String> map = Collections.synchronizedMap(maps);

        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        ExecutorService es = Executors.newFixedThreadPool(SIZE);

        for(int j=0; j<SIZE; j++) {
            es.execute(() -> {
                for (int i = 0; i < 100000; i++) {
                    map.put(Thread.currentThread().getName()+ "key" + i, "value" + i);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        es.shutdown();
        System.out.println("finish:"+map.size());
    }

}
