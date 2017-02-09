package com.warrantix.main.common.event;

/**
 *
 * Deprecated
 *
 */
public class PluginBackToScreenEvent {

	private String mScreenName = "";

	public PluginBackToScreenEvent(String brandName){
		mScreenName = brandName;
	}

	public String getScreenName() {
		return mScreenName;
	}
}
