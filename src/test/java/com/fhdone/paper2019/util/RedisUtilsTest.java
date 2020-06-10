package com.fhdone.paper2019.util;

import com.fhdone.paper2019.controller.StudentController;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.UUID;

public class RedisUtilsTest {

    private Logger logger = LogManager.getLogger(this.getClass());

    public static final String CERT_SET = "CERT_MAP";

    @Test
    @Ignore
    public void putKeysToRedis(){
        Jedis jedis = RedisUtils.getJedisByPool();

        int i=0;
        while (i<900_000){
            String certNo = RandomStringUtils.random(18, false, true);
            String name = RandomStringUtils.random(20, true, false);
            jedis.hset(CERT_SET,certNo,name);
            i++;
            if(i%5000==0){
                logger.info("{},finished" , i );
            }
        }
        jedis.close();
    }

    @Test
    public void testQueryCert(){
        int i=0;
        while (i<100_000) {
            String certNo = RandomStringUtils.random(18, false, true);
            Jedis jedis = RedisUtils.getJedisByPool();
            //logger.info("result:{}
            i++;
            if(i%5000==0){
                logger.info("{},finished" , i );
            }
            jedis.close();
        }

    }

}
