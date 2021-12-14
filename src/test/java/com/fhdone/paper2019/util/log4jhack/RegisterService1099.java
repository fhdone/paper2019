package com.fhdone.paper2019.util.log4jhack;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.CountDownLatch;

/**
 * 注册Service
 */
public class RegisterService1099 {
    public static void main(String[] args) throws InterruptedException {
        try {
            LocateRegistry.createRegistry(1099); //Registry使用8000端口
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        CountDownLatch latch=new CountDownLatch(1);
        latch.await();  //挂起主线程，否则应用会退出
    }
}