package com.axway.filter;

import com.vordel.circuit.DefaultFilter;
import com.vordel.common.util.PropDef;
import com.vordel.config.ConfigContext;
import com.vordel.es.EntityStoreException;
import com.vordel.mime.Body;
import com.vordel.mime.HeaderSet;

import com.axway.filter.RedisGetCacheUI;

public class RedisGetCache extends DefaultFilter{
	
	protected final void setDefaultPropertyDefs() {
		this.reqProps.add(new PropDef("content.body", Body.class));
		this.reqProps.add(new PropDef("http.headers", HeaderSet.class));
	}
	
	@Override
	public void configure(ConfigContext ctx, com.vordel.es.Entity entity) throws EntityStoreException {
		super.configure(ctx, entity);
	}
	
	public Class getMessageProcessorClass() {
		return com.axway.filter.RedisGetCacheProcessor.class;
	}
	
	public Class getConfigPanelClass() throws ClassNotFoundException {
		return Class.forName("com.axway.filter.RedisGetCacheUI");
							  //com.axway.filter.RedisGetCacheUI
	}

}
