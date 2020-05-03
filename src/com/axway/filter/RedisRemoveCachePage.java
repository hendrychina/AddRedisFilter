package com.axway.filter;

import org.eclipse.swt.widgets.Composite;
import com.vordel.client.manager.wizard.VordelPage;

public class RedisRemoveCachePage extends VordelPage{

	public RedisRemoveCachePage() {
		super("Redis Filter Page");
		setTitle( ("Redis-Remove Cache Attribute"));
		setDescription( ("Remove Attribute in Redis Cache"));
		setPageComplete(false);
	}
	
	public String getHelpID() {
		return "redis.help";
	}
	
	public boolean performFinish() {
		return true;
	}
	
	public void createControl(Composite parent) {
		Composite panel = render(parent, getClass().getResourceAsStream("redisRemoveCache.xml"));
		setControl(panel);
		setPageComplete(true);
	}
}
