package com.warrantix.main.common.rest;

import com.squareup.okhttp.OkHttpClient;

import javax.net.ssl.SSLContext;
import java.security.GeneralSecurityException;


public class OkHttpProvider {

	private static final OkHttpProvider INSTANCE = new OkHttpProvider();

	OkHttpClient mClient;


    public static OkHttpProvider get() {
        return INSTANCE;
    }

	private OkHttpProvider() {
        mClient = getNewClient();
	}

	public OkHttpClient getClient() {
		return mClient;
	}

    public OkHttpClient getNewClient() {
        OkHttpClient client = new OkHttpClient();

		// Fix for https://github.com/square/okhttp/issues/184
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, null);
            client.setSslSocketFactory(sslContext.getSocketFactory());
        } catch (GeneralSecurityException e) {
			// throw new AssertionError(); // The system has no TLS. Just give up.
        }

        return client;
    }

}
