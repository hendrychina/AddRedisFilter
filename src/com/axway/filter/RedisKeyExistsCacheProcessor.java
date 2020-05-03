package com.axway.filter;

import com.axway.redis.RedisPoolUtil;
import com.axway.util.StringUtil;
import com.vordel.circuit.CircuitAbortException;
import com.vordel.circuit.Message;
import com.vordel.circuit.MessageProcessor;
import com.vordel.config.Circuit;
import com.vordel.config.ConfigContext;
import com.vordel.el.Selector;
import com.vordel.es.EntityStoreException;

import redis.clients.jedis.Jedis;

public class RedisKeyExistsCacheProcessor extends MessageProcessor{
	
	private Selector<String> key;
	
	public void filterAttached(ConfigContext ctx, com.vordel.es.Entity entity) throws EntityStoreException {
		super.filterAttached(ctx, entity);
		this.key = new Selector<String>(entity.getStringValue("exists_key"), String.class);
	}

	/**
     * The invoke method. This method performs the filter processing.
     * @param c The circuit
     * @param message The message
     * @return true or false.
     */
	
	public boolean invoke(Circuit c, Message message) throws CircuitAbortException{
		
		Jedis jedis = RedisPoolUtil.getConnectionJedisInstance();
		boolean exists = jedis.exists(key.substitute(message));
		RedisPoolUtil.closeConnectionJedis(jedis);
		
		//String strKey = "redis.result";
		String strResult = "";
		if(exists) {
			strResult = "Attribute " + key.substitute(message) + " exists";
		}else {
			strResult = "Attribute " + key.substitute(message) + " not exist";
		}	
		message.put(StringUtil.strKey, strResult);
		
		return true;
	}
}
