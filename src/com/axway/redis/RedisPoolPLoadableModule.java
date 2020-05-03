package com.axway.redis;

import com.vordel.config.ConfigContext;
import com.vordel.config.LoadableModule;
import com.vordel.es.Entity;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import com.axway.redis.RedisPoolUtil;
import com.vordel.trace.Trace;

public class RedisPoolPLoadableModule implements LoadableModule {

	//private static JedisPool jedisPool = null;
	
	private static JedisPoolConfig config = null;
	private static String redisConfigFile = "redisPool.properties";
	private static Properties props = new Properties();
	
	
	@Override
	public void configure(ConfigContext solutionPack, Entity entity) {

		
	}

	@Override
	public void load(LoadableModule loadableModule, String arg) {
		
		
	}

	@Override
	public void unload() {

	}
	
	private String exceptionToString(Exception e) {
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

}
