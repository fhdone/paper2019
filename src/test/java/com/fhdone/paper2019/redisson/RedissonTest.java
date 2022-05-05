package com.fhdone.paper2019.redisson;

import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.TimeUnit;

public class RedissonTest {

    private RedissonClient redissonClient;
    
    @Before
    public void before(){
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        redissonClient = Redisson.create(config);
        
    }

    @Test
    public void reentrantLock(){

        RLock lock = redissonClient.getLock("anyLock");
        try{
            // 1. 最常见的使用方法
            lock.lock();

            // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
//            lock.lock(10, TimeUnit.SECONDS);

            // 3. 尝试加锁，最多等待3秒，上锁以后10秒自动解锁
            //boolean res = lock.tryLock(3, 3, TimeUnit.SECONDS);
//            if(res){    //成功
//                // do your business
//                System.out.println("success lock");
//            }
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    
    

}
