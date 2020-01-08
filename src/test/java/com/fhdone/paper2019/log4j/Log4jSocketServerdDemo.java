package com.fhdone.paper2019.log4j;

import org.apache.logging.log4j.core.net.server.ObjectInputStreamLogEventBridge;
import org.apache.logging.log4j.core.net.server.TcpSocketServer;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Log4jSocketServerdDemo {

    public static void main(String[] args) {
        TcpSocketServer<ObjectInputStream> myServer = null;
        try {
            myServer = new TcpSocketServer<ObjectInputStream>(1337,new ObjectInputStreamLogEventBridge());
        } catch (IOException e) {
            e.printStackTrace();
        }
        myServer.run();
    }

}
