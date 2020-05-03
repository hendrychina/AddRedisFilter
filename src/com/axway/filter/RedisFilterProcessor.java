package com.axway.filter;

import com.vordel.circuit.MessageProcessor;
import com.vordel.circuit.CircuitAbortException;
import com.vordel.circuit.Message;
import com.vordel.config.Circuit;
import com.vordel.config.ConfigContext;
import com.vordel.el.Selector;
import com.vordel.es.EntityStoreException;
import com.vordel.trace.Trace;
import com.vordel.es.ESPK;
import com.vordel.es.Entity;
import com.vordel.es.EntityType;

import redis.clients.jedis.Jedis;
import com.axway.redis.RedisPoolUtil;
import com.axway.util.StringUtil;

public class RedisFilterProcessor extends MessageProcessor{

	//private Selector<String> hostname;
	//private Selector<String> port;
	//private Selector<String> auth;
	private Selector<String> key;
	private Selector<String> value;
	
	public void filterAttached(ConfigContext ctx, com.vordel.es.Entity entity) throws EntityStoreException {
		
		super.filterAttached(ctx, entity);
		/*
		this.hostname = new Selector<String>(entity.getStringValue("hostname"), String.class);
		this.port = new Selector<String>(entity.getStringValue("port"), String.class);
		this.auth = new Selector<String>(entity.getStringValue("auth"), String.class);
		*/
		this.key = new Selector<String>(entity.getStringValue("key"), String.class);
		this.value = new Selector<String>(entity.getStringValue("value"), String.class);
		
	}
	
	/**
     * The invoke method. This method performs the filter processing.
     * @param c The circuit
     * @param message The message
     * @return true or false.
     */
	
	public boolean invoke(Circuit c, Message message) throws CircuitAbortException{
		
		//Jedis jedis = new Jedis(this.hostname.substitute(message),Integer.valueOf(this.port.substitute(message))); 
		//jedis.auth(this.auth.substitute(message));
		//key.getLiteral()  value.getLiteral()
		
		Jedis jedis = RedisPoolUtil.getConnectionJedisInstance();
		jedis.set(key.substitute(message), value.substitute(message));
		RedisPoolUtil.closeConnectionJedis(jedis);
		
		//String strKey = "redis.result";
		String strResult =  "add key: " + key.substitute(message) +" & value:" + value.substitute(message);
		message.put(StringUtil.strKey, strResult);
		
		return true;
	}
}
