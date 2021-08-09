package com.fhdone.paper2019.demo;

import org.junit.Test;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

//https://cloud.tencent.com/developer/article/1488128
public class Jdk8Demo {

    @Test
    public void test_Consumer() {
        //① 使用consumer接口实现方法
        Consumer<String> consumer = new Consumer<String>() {

            @Override
            public void accept(String s) {
                s = s + "...";
                System.out.println(s);
            }
        };
        Stream<String> stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        stream.forEach(consumer);

        System.out.println("********************");

        //② 使用lambda表达式，forEach方法需要的就是一个Consumer接口
        stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        Consumer<String> consumer1 = (s) -> System.out.println(s);//lambda表达式返回的就是一个Consumer接口
        stream.forEach(consumer1);
        //更直接的方式
        //stream.forEach((s) -> System.out.println(s));
        System.out.println("********************");

        //③ 使用方法引用，方法引用也是一个consumer
        stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        Consumer consumer2 = System.out::println;
//        consumer.accept("xxx");
//        stream.forEach(consumer);

        stream.skip(1).forEach(consumer);

        //更直接的方式
        //stream.forEach(System.out::println);
    }

    /**
     * Supplier接口测试，supplier相当一个容器或者变量，可以存储值
     */
    @Test
    public void test_Supplier() {
        //① 使用Supplier接口实现方法,只有一个get方法，无参数，返回一个值
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                //返回一个随机值
                return new Random().nextInt();
            }
        };

        System.out.println(supplier.get());

        System.out.println("********************");

        //② 使用lambda表达式，
        supplier = () -> new Random().nextInt();
        System.out.println(supplier.get());
        System.out.println("********************");

        //③ 使用方法引用
        Supplier<Double> supplier2 = Math::random;
        System.out.println(supplier2.get());
    }

}
