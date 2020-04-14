package com.fhdone.paper2019.util.disruptor;

// https://tech.meituan.com/2016/11/18/disruptor.html
public class FalseSharingPadding implements Runnable{
    public final static long ITERATIONS = 500L * 1000L * 100L;
    private int arrayIndex = 0;

    private static ValuePadding[] longs;

    public FalseSharingPadding(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

//    public static void main(final String[] args) throws Exception {
//        System.out.println(FalseSharingPadding.class.getSimpleName());
//        for(int i=1;i<10;i++){
//            System.gc();
//            final long start = System.currentTimeMillis();
//            runTest(i);
//            System.out.println("Thread num "+i+" duration = " + (System.currentTimeMillis() - start));
//        }
//    }

    public static void test() throws InterruptedException {
        runTest(4);
    }

    private static void runTest(int NUM_THREADS) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        longs = new ValuePadding[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new ValuePadding();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharingPadding(i));
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

    public final static class ValuePadding {
        protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        protected long p9, p10, p11, p12, p13, p14;
        protected long p15;
    }

}