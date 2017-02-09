package com.warrantix.main.common.event;

/**
 *
 * Deprecated
 *
 */
public class ErrorEvent {

	String mErrorMessage = null;

	public ErrorEvent(String errorMessage){
		mErrorMessage = errorMessage;
	}

	public String getMessage() {
		return mErrorMessage;
	}
}
