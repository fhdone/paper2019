package com.fhdone.paper2019.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class HashMapDemo {

    public static final int SIZE = 2;

    public static void main(String[] args) throws InterruptedException {

//        Map<String, String> map = new HashMap<>();

        Map<String, String> map = new ConcurrentHashMap<>();

//        Map<String, String> map = Collections.synchronizedMap(maps);

        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
//        ExecutorService es = Executors.newFixedThreadPool(SIZE);

        ExecutorService es = new ThreadPoolExecutor(SIZE, SIZE,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), new ThreadPoolExecutor.AbortPolicy());


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
