package com.fhdone.paper2019.util.disruptor;


// https://tech.meituan.com/2016/11/18/disruptor.html
public class CacheLineEffect {

    //考虑一般缓存行大小是64字节，一个 long 类型占8字节
    static  long[][] arr;

    static{
        arr = new long[1024 * 1024][];
        for (int i = 0; i < 1024 * 1024; i++) {
            arr[i] = new long[8];
            for (int j = 0; j < 8; j++) {
                arr[i][j] = 0L;
            }
        }
    }

    public static void test_1(){
        long sum = 0L;
//        long marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i+=1) {
            for(int j =0; j< 8;j++){
                sum = arr[i][j];
            }
        }
//        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");
    }

    public static void test_2(){
        long sum = 0L;
//        long marked = System.currentTimeMillis();
        for (int i = 0; i < 8; i+=1) {
            for(int j =0; j< 1024 * 1024;j++){
                sum = arr[j][i];
            }
        }
//        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");
    }

    public static void main(String[] args) {
        CacheLineEffect.test_1();
        CacheLineEffect.test_2();
    }

}
