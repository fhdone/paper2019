package com.fhdone.paper2019.util.log4jhack;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient2 {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);  //获取注册中心引用
        CalcTest remote = (CalcTest) registry.lookup("calc"); //获取RemoteHello服务
        System.out.println("Client:调用远程方法:"+remote.show());  //调用远程方法
    }
}