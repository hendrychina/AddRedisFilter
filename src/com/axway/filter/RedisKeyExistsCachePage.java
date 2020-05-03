package com.axway.filter;

import org.eclipse.swt.widgets.Composite;
import com.vordel.client.manager.wizard.VordelPage;

public class RedisKeyExistsCachePage extends VordelPage{
	
	public RedisKeyExistsCachePage() {
		super("Redis Filter Page");
		setTitle( ("Redis-Is Cached"));
		setDescription( ("Check Attribute exist in Redis"));
		setPageComplete(false);
	}
	
	public String getHelpID() {
		return "redis.help";
	}
	
	public boolean performFinish() {
		return true;
	}
	
	public void createControl(Composite parent) {
		Composite panel = render(parent, getClass().getResourceAsStream("redisKeyExistsCache.xml"));
		setControl(panel);
		setPageComplete(true);
	}
}
