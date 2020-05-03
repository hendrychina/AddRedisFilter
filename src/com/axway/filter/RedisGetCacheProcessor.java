package com.axway.filter;

import com.vordel.circuit.MessageProcessor;
import com.vordel.circuit.CircuitAbortException;
import com.vordel.circuit.Message;
import com.vordel.config.Circuit;
import com.vordel.config.ConfigContext;
import com.vordel.el.Selector;
import com.vordel.es.EntityStoreException;

import redis.clients.jedis.Jedis;
import com.axway.redis.RedisPoolUtil;
import com.axway.util.StringUtil;

public class RedisGetCacheProcessor extends MessageProcessor{

private Selector<String> key;
	
	public void filterAttached(ConfigContext ctx, com.vordel.es.Entity entity) throws EntityStoreException {
		super.filterAttached(ctx, entity);
		this.key = new Selector<String>(entity.getStringValue("get_key"), String.class);
	}
	
	/**
     * The invoke method. This method performs the filter processing.
     * @param c The circuit
     * @param message The message
     * @return true or false.
     */
	
	public boolean invoke(Circuit c, Message message) throws CircuitAbortException{
		
		Jedis jedis = RedisPoolUtil.getConnectionJedisInstance();
		String valueResult = jedis.get(key.substitute(message));
		RedisPoolUtil.closeConnectionJedis(jedis);
		
		//String strKey = "redis.result";
		String strResult = "";
		if(valueResult != null || "null".equalsIgnoreCase(valueResult) == false) {
			strResult = "Value is: " + valueResult;
		}else {
			strResult = "Value of Key: " + key.substitute(message) + " not exist";
		}
		
		message.put(StringUtil.strKey, strResult);
		
		return true;
	}
}
