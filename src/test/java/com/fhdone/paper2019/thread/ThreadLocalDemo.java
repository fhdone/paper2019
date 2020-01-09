package com.fhdone.paper2019.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class ThreadLocalDemo {

    static class Span {
        public String name;
        public int age;
        public Span(String name) {
            this.name = name;
        }
    }

    @Test
    public  void test_1() throws Exception {

        final InheritableThreadLocal<Span> inheritableThreadLocal = new InheritableThreadLocal<Span>();
        inheritableThreadLocal.set(new Span("xiexiexie"));
        //输出 xiexiexie
        Object o = inheritableThreadLocal.get();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("========");
                System.out.println(inheritableThreadLocal.get().name);
                inheritableThreadLocal.set(new Span("zhangzhangzhang"));
                System.out.println(inheritableThreadLocal.get().name);
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);

        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);

        System.out.println("========");
        System.out.println(inheritableThreadLocal.get().name);

        executorService.shutdown();
    }


    @Test
    public  void test_2() throws Exception {
        // 0.创建线程池
        final ThreadPoolExecutor bizPoolExecutor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(1));


        // 1 创建线程变量
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<String>();
        inheritableThreadLocal.set("before-value-set-in-parent");

        // 2 投递三个任务
        for (int i = 0; i < 3; ++i) {
            bizPoolExecutor.execute(() -> {
                try {
                    System.out.println("child:" + inheritableThreadLocal.get());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        // 3休眠4s
        Thread.sleep(4000);

        System.out.println("#######################");
        // 4.设置线程变量
        inheritableThreadLocal.set("after-value-set-in-parent");


        // 5. 提交任务到线程池
        bizPoolExecutor.execute(() -> {
            try {
                // 5.1访问线程变量
                System.out.println("child:" + inheritableThreadLocal.get());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        bizPoolExecutor.shutdown();
    }


    @Test
    public  void test_3() throws Exception {
        // 0.创建线程池
        final ThreadPoolExecutor bizPoolExecutor = new ThreadPoolExecutor(2, 3, 1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>());

        // 1 创建线程变量
        InheritableThreadLocal<Map<String,String>> inheritableThreadLocal = new InheritableThreadLocal<Map<String,String>>();
        Map<String,String> map = new HashMap<>();
        map.put("key","value");
        inheritableThreadLocal.set(map);

        // 2.1 投递三个任务
        for (int i = 0; i < 3; ++i) {
            bizPoolExecutor.execute(() -> {
                try {
                    System.out.println("child_1:" + inheritableThreadLocal.get().get("key"));
                    Map<String,String> map2 = new HashMap<>();
                    map2.put("key","child_1+value1");
                    inheritableThreadLocal.set(map2);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 2.2 投递三个任务
        for (int i = 0; i < 3; ++i) {
            bizPoolExecutor.execute(() -> {
                try {
                    System.out.println("child_2:" + inheritableThreadLocal.get().get("key"));
                    Map<String,String> map2 = new HashMap<>();
                    map2.put("key","child_2_value2");
                    inheritableThreadLocal.set(map2);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 3休眠4s
        Thread.sleep(10000);

        System.out.println("#######################");
        // 4.设置线程变量
        //pass

        // 5. 提交任务到线程池
        bizPoolExecutor.execute(() -> {
            try {
                // 5.1访问线程变量
                System.out.println("child:" + inheritableThreadLocal.get().get("key"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        System.out.println("parent:" + inheritableThreadLocal.get().get("key"));

        bizPoolExecutor.shutdown();
    }


    @Test
    public  void test_transmittableThreadLocal() throws Exception {

        final TransmittableThreadLocal<Span> transmittableThreadLocal = new TransmittableThreadLocal<Span>();
        transmittableThreadLocal.set(new Span("xiexiexie"));
        //输出 xiexiexie
        Object o = transmittableThreadLocal.get();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(transmittableThreadLocal.get().name);
                transmittableThreadLocal.set(new Span("zhangzhangzhang"));
                System.out.println(transmittableThreadLocal.get().name);
            }
        };

        System.out.println("##### child thread #####");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);

        System.out.println("##### parent thread #####");
        System.out.println(transmittableThreadLocal.get().name);
        transmittableThreadLocal.set(new Span("xiexiexie2"));

        System.out.println("##### child thread #####");
        executorService.submit(runnable);    //自线程数据没变
        TimeUnit.SECONDS.sleep(1);


        System.out.println("##### ttl thread #####");
        Runnable ttlRunnable = TtlRunnable.get(runnable);  //TTL数据变了
        executorService.execute(ttlRunnable);

        executorService.shutdown();
    }




}
