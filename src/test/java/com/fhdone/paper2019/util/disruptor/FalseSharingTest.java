package com.fhdone.paper2019.util.disruptor;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 1)
@Measurement(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
@Threads(4)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class FalseSharingTest {

    @Benchmark
    public void falseSharingPadding() throws InterruptedException {
        FalseSharingPadding.Test();
    }

    @Benchmark
    public void falseSharingNoPadding() throws InterruptedException {
        FalseSharingNoPadding.Test();
    }

    @Benchmark
    public void falseSharingPaddingContented() throws InterruptedException {
        FalseSharingPaddingContented.Test();
    }


    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(FalseSharingTest.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
