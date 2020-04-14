package com.fhdone.paper2019.util.disruptor;

// https://tech.meituan.com/2016/11/18/disruptor.html
public class FalseSharingPaddingContented implements Runnable{
    public final static long ITERATIONS = 500L * 1000L * 100L;
    private int arrayIndex = 0;

    private static ValueContended[] longs;

    public FalseSharingPaddingContented(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void test() throws InterruptedException {
        runTest(4);
    }

    private static void runTest(int NUM_THREADS) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        longs = new ValueContended[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new ValueContended();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharingPaddingContented(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = 0L;
        }
    }

    @jdk.internal.vm.annotation.Contended
    public final static class ValueContended {
        protected volatile long value = 0L;
    }

}