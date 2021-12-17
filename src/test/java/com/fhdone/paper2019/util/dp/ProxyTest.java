package com.fhdone.paper2019.util.dp;

import org.junit.Test;

import java.lang.reflect.Proxy;

//https://cloud.tencent.com/developer/article/1784882
public class ProxyTest {

    @Test
    public void test1() {
        Subject proxy = (Subject) Proxy
            .newProxyInstance(
                Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyInvocationHandler2());

        proxy.sayHello();
        /**
         * 打印输出如下
         * 调用处理器：   进入代理调用处理器
         */
    }


    @Test
    public void test2() {
        Subject subject = new SubjectImpl();
        Subject proxy = (Subject) Proxy
            .newProxyInstance(
                subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),
                new ProxyInvocationHandler(subject));

        proxy.sayHello();
        /**
         * 打印输出如下
         * 调用处理器：   进入代理调用处理器
         * 被代理实现类：Hello World
         */
    }
}
