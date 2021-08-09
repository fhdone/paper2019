package com.fhdone.paper2019.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

//https://mp.weixin.qq.com/s/Zqfk7ZymQ02eHoaRg1YIEw
public class ConcurrencyTest {

    static final List a = Collections.synchronizedList(new ArrayList<>());


    public static  void main(String[] args) throws InterruptedException{
        Thread t = new Thread(() -> addIfAbsent(17));
        t.start();
        addIfAbsent(17);
        t.join();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(a);
    }


    private static void addIfAbsent(int x){
        if(!a.contains(x)){
            a.add(x);
        }else{
            System.out.println("exists value");
        }
    }

}


