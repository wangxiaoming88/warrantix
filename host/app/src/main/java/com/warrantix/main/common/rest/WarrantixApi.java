package com.warrantix.main.common.rest;


import com.warrantix.main.common.rest.model.PluginMapInfo;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.GET;

public interface WarrantixApi
{
    @GET("/pluginmap/")
    Call<ArrayList<PluginMapInfo>> getSiteMap();
}
