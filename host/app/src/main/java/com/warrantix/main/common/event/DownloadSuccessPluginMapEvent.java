package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.model.PluginMapInfo;

import java.util.ArrayList;

/**
 *
 * Deprecated
 *
 */
public class DownloadSuccessPluginMapEvent {

	ArrayList<PluginMapInfo> pluginMapInfo;
	public DownloadSuccessPluginMapEvent(ArrayList<PluginMapInfo> info){
		pluginMapInfo = info;
	}

	public ArrayList<PluginMapInfo> getPluginMapInfo() {
		return pluginMapInfo;
	}
}
