package com.fhdone.paper2019.util;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//https://cloud.tencent.com/developer/article/1623343
//https://cloud.tencent.com/developer/article/1700640
//@BenchmarkMode(Mode.Throughput) // 测试类型：吞吐量
@BenchmarkMode(Mode.AverageTime) // 测试类型：吞吐量
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热 2 轮，每次 1s
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次 3s
@Fork(1) // fork 1 个线程
@State(Scope.Thread) // 每个测试线程一个实例
public class HashMapCycle {

    static int add(int key,int val) {
        return  key +val;
    }

    static Map<Integer, Integer> map = new HashMap() {{
        // 添加数据
        for (int i = 0; i < 100000; i++) {
            put(i, i);
        }
    }};

    public static void main(String[] args) throws Exception {
        //PrintStream ps=new PrintStream(new FileOutputStream("/Users/fhdone/Documents/jmh-map.log"));
        //System.setOut(null);

        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(HashMapCycle.class.getSimpleName()) // 要导入的测试类
                //.output("/Users/fhdone/Documents/jmh-map.log") // 输出测试结果的文件
                .build();
        new Runner(opt).run(); // 执行测试
    }

    @Benchmark
    public void entrySet(Blackhole blackhole) {
        // 遍历
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
            blackhole.consume(add(entry.getKey(), entry.getValue()));
        }
    }

    @Benchmark
    public void keySet(Blackhole blackhole) {
        // 遍历
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
//            System.out.println(key);
//            System.out.println(map.get(key));
            blackhole.consume(add(key, map.get(key)));
        }
    }

    @Benchmark
    public void forEachEntrySet(Blackhole blackhole) {
        // 遍历
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
            blackhole.consume(add(entry.getKey(), entry.getValue()));
        }
    }

    @Benchmark
    public void forEachKeySet(Blackhole blackhole) {
        // 遍历
        for (Integer key : map.keySet()) {
//            System.out.println(key);
//            System.out.println(map.get(key));
            blackhole.consume(add(key, map.get(key)));
        }
    }

    @Benchmark
    public void lambda(Blackhole blackhole) {
        // 遍历
        map.forEach((key, value) -> {
//            System.out.println(key);
//            System.out.println(value);
            blackhole.consume(add(key, value));
        });
    }

    @Benchmark
    public void streamApi(Blackhole blackhole) {
        // 单线程遍历
        map.entrySet().stream().forEach((entry) -> {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
            blackhole.consume(add(entry.getKey(), entry.getValue()));
        });
    }

    @Benchmark
    public void parallelStreamApi(Blackhole blackhole) {
        // 多线程遍历
        map.entrySet().parallelStream().forEach((entry) -> {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
            blackhole.consume(add(entry.getKey(), entry.getValue()));
        });
    }
}