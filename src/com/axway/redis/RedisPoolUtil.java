package com.axway.redis;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import com.vordel.trace.Trace;

public class RedisPoolUtil {

	private static JedisPool jedisPool = null;
	private static String redisConfigFile = "com/axway/redis/redisPool.properties";
	
	
	static {
		
		Trace.info(" ***************   start to initial JedisConnectionPool   ******************");
		try {
				
				Properties props = new Properties();
				
				props.load(RedisPoolUtil.class.getClassLoader().getResourceAsStream(redisConfigFile));
				
				JedisPoolConfig config = new JedisPoolConfig();		
				config.setMaxTotal(Integer.valueOf(props.getProperty("jedis.pool.maxActive")));
				config.setMaxIdle(Integer.valueOf(props.getProperty("jedis.pool.maxIdle")));
				config.setMaxWaitMillis(Long.valueOf(props.getProperty("jedis.pool.maxWait")));
				config.setTestOnBorrow(Boolean.valueOf(props.getProperty("jedis.pool.testOnBorrow")));
				config.setTestOnReturn(Boolean.valueOf(props.getProperty("jedis.pool.testOnReturn")));
				
				Trace.info(props.getProperty("IP info ***************: " + "redis.ip"));
				jedisPool = new JedisPool(config, props.getProperty("redis.ip"),
											Integer.valueOf(props.getProperty("redis.port")),
											Integer.valueOf(props.getProperty("redis.timeout")), 
											props.getProperty("redis.passWord"));
			} catch (Exception e) {
				Trace.info(new RedisPoolUtil().exceptionToString(e));
			}
			
		}
	

	public static void initialJedisPool() {
		
		Trace.info(" ***************     start to configure         ******************");
		try {
			
			Properties props = new Properties();
			
			props.load(RedisPoolUtil.class.getClassLoader().getResourceAsStream(redisConfigFile));
			
			JedisPoolConfig config = new JedisPoolConfig();		
			config.setMaxTotal(Integer.valueOf(props.getProperty("jedis.pool.maxActive")));
			config.setMaxIdle(Integer.valueOf(props.getProperty("jedis.pool.maxIdle")));
			config.setMaxWaitMillis(Long.valueOf(props.getProperty("jedis.pool.maxWait")));
			config.setTestOnBorrow(Boolean.valueOf(props.getProperty("jedis.pool.testOnBorrow")));
			config.setTestOnReturn(Boolean.valueOf(props.getProperty("jedis.pool.testOnReturn")));
			
			Trace.info(props.getProperty("IP info ***************: " + "redis.ip"));
			jedisPool = new JedisPool(config, props.getProperty("redis.ip"),
										Integer.valueOf(props.getProperty("redis.port")),
										Integer.valueOf(props.getProperty("redis.timeout")), 
										props.getProperty("redis.passWord"));
			
			Trace.info(" ***************     ent to configure         ******************");
		} catch (Exception e) {
			Trace.info(new RedisPoolUtil().exceptionToString(e));
		}
	}
	
	/*static {
		initialJedisPool();
    }*/
	
	public static Jedis getConnectionJedisInstance() {
		
		Jedis jedis = null;
		
		/*
		if( jedis == null || jedis.isConnected() == false) {
			
			if (jedisPool == null || jedisPool.isClosed() == true) {    
        		initialJedisPool();  
            }
			
			jedis = jedisPool.getResource();
		}
		*/
		/*
		if (jedisPool == null || jedisPool.isClosed() == true) {    
    		//initialJedisPool();  
			Trace.info(" ***************     start to configure         ******************");
			try {
				
				Properties props = new Properties();
				
				props.load(RedisPoolUtil.class.getClassLoader().getResourceAsStream(redisConfigFile));
				
				JedisPoolConfig config = new JedisPoolConfig();		
				config.setMaxTotal(Integer.valueOf(props.getProperty("jedis.pool.maxActive")));
				config.setMaxIdle(Integer.valueOf(props.getProperty("jedis.pool.maxIdle")));
				config.setMaxWaitMillis(Long.valueOf(props.getProperty("jedis.pool.maxWait")));
				config.setTestOnBorrow(Boolean.valueOf(props.getProperty("jedis.pool.testOnBorrow")));
				config.setTestOnReturn(Boolean.valueOf(props.getProperty("jedis.pool.testOnReturn")));
				
				Trace.info(props.getProperty("IP info ***************: " + "redis.ip"));
				jedisPool = new JedisPool(config, props.getProperty("redis.ip"),
											Integer.valueOf(props.getProperty("redis.port")),
											Integer.valueOf(props.getProperty("redis.timeout")), 
											props.getProperty("redis.passWord"));
				
				Trace.info(" ***************     ent to configure         ******************");
			} catch (Exception e) {
				Trace.info(new RedisPoolUtil().exceptionToString(e));
			}
        }
        */
		
		jedis = jedisPool.getResource();
		return jedis;
	}
	
	public static void closeConnectionJedis(Jedis jedis) {
        // pool.returnResource(jedis);
        if ( null != jedis && jedis.isConnected()) {
        	//jedis.disconnect();
            jedis.close();
        }
    }

	private String exceptionToString(Exception e) {
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}
