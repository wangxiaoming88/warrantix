package com.warrantix.main.common.rest;

import com.noveogroup.android.log.Log;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.warrantix.main.common.bus.BusProvider;
import com.warrantix.main.common.event.DownloadFailurePluginMapEvent;
import com.warrantix.main.common.event.DownloadSuccessPluginMapEvent;
import com.warrantix.main.common.rest.model.PluginMapInfo;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class WarrantixWebService {
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static boolean isDisabled = true;

    private OkHttpClient okHttpClient;
    private Retrofit client;
    private WarrantixApi mWarrentixApi;

    private static WarrantixWebService instance = null;
    public static WarrantixWebService getInstance() {
        if (instance == null)
            instance = new WarrantixWebService();

        return instance;
    }

    public WarrantixWebService()
    {
        if (isDisabled == true)
            return;

        okHttpClient = new OkHttpClient();
//        okHttpClient.interceptors().add(new Interceptor() {
//            @Override
//            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
//
//                Request request = chain.request();
//                Request newRequest;
//                newRequest = request.newBuilder()
//                        .addHeader("AUTH-TOKEN", MyAccount.getInstance().getAuthToken())
//                        .build();
//                com.squareup.okhttp.Response response = chain.proceed(newRequest);
//                return response;
//            }
//        });

        client = new Retrofit.Builder()
                .baseUrl(Api.SERVER)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mWarrentixApi = client.create(WarrantixApi.class);

        BusProvider.get().register(this);
    }

    // getpluginmap
    public synchronized void getPluginMap() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<ArrayList<PluginMapInfo>> call = mWarrentixApi.getSiteMap();
                call.enqueue(new Callback<ArrayList<PluginMapInfo>>() {
                    @Override
                    public void onResponse(Response<ArrayList<PluginMapInfo>> response, Retrofit retrofit) {
                        ArrayList<PluginMapInfo> mapInfos = response.body();
                        Log.v("mapinfo count = ", mapInfos.size());

                        if (response.isSuccess())
                        {
                            Log.v("Success to download plugin map.");

                            // send plugin map to UI
                            DownloadSuccessPluginMapEvent successEvent = new DownloadSuccessPluginMapEvent(mapInfos);
                            BusProvider.get().post(successEvent);
                        }
                        else {
                            String errorMessage = "Failure to download plugin map.";
                            Log.v("Failure1 to download plugin map.");

                            DownloadFailurePluginMapEvent failureEvent = new DownloadFailurePluginMapEvent(errorMessage);
                            BusProvider.get().post(failureEvent);
                        }
                    }
                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();

                        DownloadFailurePluginMapEvent failureEvent = new DownloadFailurePluginMapEvent(t.getLocalizedMessage());
                        BusProvider.get().post(failureEvent);
                    }
                });
            }
        }).start();
    }

}
