package com.fhdone.paper2019.util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

	public static Jedis jedis;
	public static Jedis jedis2;
	private static JedisPool jedisPool;

	synchronized public static Jedis getJedisServer(){
		if ( jedis == null ){
			jedis = new Jedis("127.0.0.1",6379);
			jedis.select(7);
		}
		return jedis;
	}

	synchronized public static Jedis getJedisByPool(){
		if ( jedis2 == null ){
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxTotal(8);
			poolConfig.setMaxIdle(8);
			poolConfig.setMinIdle(1);
			//			poolConfig.setMaxWaitMillis(30000);
			jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
		}
		jedis2 = jedisPool.getResource();
		jedis2.select(7);
		return jedis2;
	}

	public static void main(String args[]) throws Exception{
//		ExecutorService executorService = Executors.newFixedThreadPool(100);
        ExecutorService executorService = new ThreadPoolExecutor(100, 100,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

		Jedis jedis = RedisUtils.getJedisByPool();
		Set<Long> tmp = new HashSet<Long>();
		jedis.set("a","0");
		jedis.close();
		for(int i=0;i<1000;i++){
			executorService.execute(  new Runnable(){
				@Override
				public void run() {
					try {
						Jedis jedis = RedisUtils.getJedisByPool();
						tmp.add(jedis.incr("a"));
						jedis.close();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		executorService.shutdown();  
		executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		System.out.println(jedis.get("a"));
		System.out.println(tmp.size());
	}

}
