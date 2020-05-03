package com.axway.filter;

import java.util.Vector;
import com.axway.filter.RedisFilterPage;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.vordel.client.manager.Images;
import com.vordel.client.manager.filter.DefaultGUIFilter;
import com.vordel.client.manager.wizard.VordelPage;

public class RedisFilterUI extends DefaultGUIFilter{
	
	public Vector<VordelPage> getPropertyPages() {
		Vector<VordelPage> pages = new Vector<VordelPage>();
		pages.add(new RedisFilterPage());
		pages.add(createLogPage());
		return pages;
	}
	
	public String[] getCategories() {
		//return new String[] { ("FILTER_GROUP_REDISFILTER")};
		return new String[] { ("Redis Filters")};
	}
	
	private static final String IMAGE_KEY = "redis";
	
	public String getSmallIconId() {
		return IMAGE_KEY;
	}
	
	public Image getSmallImage() {
		 return Images.getImageRegistry().get(getSmallIconId());
	}
	
	public ImageDescriptor getSmallIcon() {
		return Images.getImageDescriptor(getSmallIconId());
	}
	
	public String getTypeName() {
		return "Redis-Cache Attribute";
	}

}
