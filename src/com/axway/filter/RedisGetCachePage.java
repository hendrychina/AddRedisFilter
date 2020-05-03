package com.axway.filter;

import org.eclipse.swt.widgets.Composite;
import com.vordel.client.manager.wizard.VordelPage;

public class RedisGetCachePage extends VordelPage{

	public RedisGetCachePage() {
		super("Redis Filter Page");
		setTitle( ("Redis-Get Cache Attribute"));
		setDescription( ("Get Attribute in Redis Cache"));
		setPageComplete(false);
	}
	
	public String getHelpID() {
		return "redis.help";
	}
	
	public boolean performFinish() {
		return true;
	}
	
	public void createControl(Composite parent) {
		Composite panel = render(parent, getClass().getResourceAsStream("redisGetCache.xml"));
		setControl(panel);
		setPageComplete(true);
	}
}
