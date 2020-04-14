package com.fhdone.paper2019.util.disruptor;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 1)
@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class CacheLineEffectTest {

    @Benchmark
    public void test_1() throws InterruptedException {
        CacheLineEffect.test_1();
    }

    @Benchmark
    public void test_2() throws InterruptedException {
        CacheLineEffect.test_2();
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(CacheLineEffectTest.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
