package com.fhdone.paper2019.util.log4jhack;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class RmiClient {

    public static void main(String[] args) throws Exception {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
        Context ctx = new InitialContext(env);
        ctx.lookup("BugFinder");
    }

}
