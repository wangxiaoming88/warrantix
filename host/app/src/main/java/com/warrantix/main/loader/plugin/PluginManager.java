package com.warrantix.main.loader.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import com.noveogroup.android.log.Log;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.common.rest.model.PluginMapInfo;
import com.warrantix.main.loader.plugin.model.FileSpec;
import com.warrantix.main.loader.plugin.model.FragmentSpec;
import com.warrantix.main.loader.plugin.model.SiteSpec;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;

/**
 *
 * Deprecated : Unused Source code
 *
 */
public class PluginManager {
    public static final String PRIMARY_SCHEME = "app";

    private static PluginManager instance;
    public static PluginManager instance() {
        if (instance == null) {
            throw new IllegalStateException("PlgunManager has not been created");
        }

        return instance;
    }

    private ArrayList<PluginMapInfo> pluginMapInfos = null;

    public PluginManager() {
        instance = this;
    }

    public SiteSpec readSite() {
        File dir = new File(WarrantixApplication.getInstance().getFilesDir(), "repo");
        File local = new File(dir, "site.txt");
        if (local.length() > 0) {
            try {
                FileInputStream fis = new FileInputStream(local);
                byte[] bytes = new byte[fis.available()];
                int l = fis.read(bytes);
                fis.close();
                String str = new String(bytes, 0, l, "UTF-8");
                JSONObject json = new JSONObject(str);
                return new SiteSpec(json);
            } catch (Exception e) {
                Log.w("loader", "fail to load site.txt from " + local, e);
            }
        }
        return new SiteSpec("empty.0", "0", new FileSpec[0],
                new FragmentSpec[0]);
    }

    public void setPluginMapInfos(ArrayList<PluginMapInfo> pluginMaps) {
        pluginMapInfos = pluginMaps;
    }

    public String getPluginUrl(String id) {
        if (pluginMapInfos == null)
            return "";

        for (int i = 0; i < pluginMapInfos.size(); i++) {
            PluginMapInfo mapInfo = pluginMapInfos.get(i);
            if (mapInfo.id.equalsIgnoreCase(id))
                return mapInfo.url;
        }

        return "";
    }

    public String getPluginUri(String id) {
        if (pluginMapInfos == null)
            return "";

        for (int i = 0; i < pluginMapInfos.size(); i++) {
            PluginMapInfo mapInfo = pluginMapInfos.get(i);
            if (mapInfo.id.equalsIgnoreCase(id))
                return mapInfo.uri;
        }

        return "";
    }

    public Intent urlMap(Intent intent) {
        do {
            // already specify a class, no need to map url
            if (intent.getComponent() != null)
                break;

            // only process my scheme uri
            Uri uri = intent.getData();
            if (uri == null)
                break;
            if (uri.getScheme() == null)
                break;
            if (!(PRIMARY_SCHEME.equalsIgnoreCase(uri.getScheme())))
                break;

            SiteSpec site = null;
            if (intent.hasExtra("_site")) {
                site = intent.getParcelableExtra("_site");
            }
            if (site == null) {
                site = readSite();
                intent.putExtra("_site", site);
            }

            // i'm responsible
            intent.setClass(WarrantixApplication.getInstance(), PluginDownloaderActivity.class);

            //
            // disable running PluginContainerActivity
            //

//            String host = uri.getHost();
//            if (TextUtils.isEmpty(host))
//                break;
//            host = host.toLowerCase(Locale.US);
//            FragmentSpec fragment = site.getFragment(host);
//            if (fragment == null)
//                break;
//            intent.putExtra("_fragment", fragment.name());
//
//            // class loader
//            ClassLoader classLoader;
//            if (TextUtils.isEmpty(fragment.code())) {
//                classLoader = WarrantixApplication.getInstance().getClassLoader();
//            } else {
//                intent.putExtra("_code", fragment.code());
//                FileSpec fs = site.getFile(fragment.code());
//                if (fs == null)
//                    break;
//                classLoader = WXClassLoader.getClassLoader(site, fs);
//                if (classLoader == null)
//                    break;
//            }
//
//            intent.setClass(WarrantixApplication.getInstance(), PluginContainerActivity.class);

        } while (false);

        return intent;
    }

    public boolean checkInstallation(String uri) {
        PackageManager pm = WarrantixApplication.getAppContext().getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}
