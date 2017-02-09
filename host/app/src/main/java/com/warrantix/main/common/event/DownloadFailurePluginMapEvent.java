package com.warrantix.main.common.event;

/**
 *
 * Deprecated
 *
 */
public class DownloadFailurePluginMapEvent {

	String mErrorMessage = null;

	public DownloadFailurePluginMapEvent(String errorMessage){
		mErrorMessage = errorMessage;
	}

	public String getMessage() {
		return mErrorMessage;
	}
}
