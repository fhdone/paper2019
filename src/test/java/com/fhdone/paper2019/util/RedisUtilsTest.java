package com.fhdone.paper2019.util;

import com.fhdone.paper2019.controller.StudentController;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RedisUtilsTest {

    private Logger logger = LogManager.getLogger(this.getClass());

    public static final String CERT_MAP = "CERT_MAP";
    public static final String CERT_SET = "CERT_SET";

    @Test
//    @Ignore
    public void putKeysToRedisMap(){
        Jedis jedis = RedisUtils.getJedisByPool();

        int i=0;
        while (i<1000_000){
            String certNo = RandomStringUtils.random(18, false, true);
            String name = RandomStringUtils.random(20, true, false);
            jedis.hset(CERT_MAP,certNo,name);
            i++;
            if(i%5000==0){
                logger.info("{},finished" , i );
            }
        }
        jedis.close();
    }

    @Test
    public void putKeysToRedisSet() throws InterruptedException {

        for(int i=0; i<2; i++){
            new Thread(new Runnable(){
                @Override
                public void run() {
                    Jedis jedis = RedisUtils.getJedisByPool();
                    Set<String> sets= new HashSet<>();
                    int i=0;
                    while (i<100_000_0){
                        String certNo = RandomStringUtils.random(18, false, true);
                        String name = RandomStringUtils.random(20, true, false);
                        sets.add(certNo+name);
                        i++;
                        if(i%5000==0){
                            jedis.sadd(CERT_SET,sets.toArray(new String[0]));
                            sets.clear();
                            logger.info("{},finished" , i );
                        }
                    }
                    jedis.close();
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(300);
    }

    @Test
    public void testQueryCertSet(){
        Jedis jedis = RedisUtils.getJedisByPool();
        String cursor = "0";
        int count=0;

        ScanParams sp = new ScanParams();
        sp.count(5000);

        ScanResult<String> sr = jedis.sscan(CERT_SET,"0", sp);
        count += sr.getResult().size();
        logger.info("size:"+sr.getResult().size());

        while( !"0".equals(sr.getCursor())){
            sr = jedis.sscan(CERT_SET,sr.getCursor(), sp);
            count += sr.getResult().size();
            logger.info("size:"+sr.getResult().size());
        }
        jedis.close();
        logger.info("count:"+count);
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
