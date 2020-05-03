package com.axway.filter;

import org.eclipse.swt.widgets.Composite;
import com.vordel.client.manager.wizard.VordelPage;

public class RedisFilterPage extends VordelPage{

	public RedisFilterPage() {
		super("Redis Filter Page");
		setTitle( ("Redis-Cache Attribute"));
		setDescription( ("Cache Attribute in Redis Cache"));
		setPageComplete(false);
	}
	
	public String getHelpID() {
		return "redis.help";
	}
	
	public boolean performFinish() {
		return true;
	}
	
	public void createControl(Composite parent) {
		Composite panel = render(parent, getClass().getResourceAsStream("redis_filter.xml"));
		setControl(panel);
		setPageComplete(true);
	}
}
