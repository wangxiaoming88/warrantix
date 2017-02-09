package com.warrantix.main.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

public class OnSetupReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String packageName = intent.getDataString();
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
            Log.d("OnSetupReceiver", packageName + " Package installed");

            if (packageName.equalsIgnoreCase("package:com.warrantix.hero") == true) {
                Log.d("OnSetupReceiver", "Hero Package installed");
                context.getPackageManager().setComponentEnabledSetting(
                        new ComponentName("com.warrantix.main", "com.warrantix.main.MainAlias"),
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED , PackageManager.DONT_KILL_APP);
            }

        }
        else if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
            Log.d("OnSetupReceiver", packageName + " Package removed");

            if (packageName.equalsIgnoreCase("package:com.warrantix.hero") == true) {
                Log.d("OnSetupReceiver", "Hero Package removed");
                context.getPackageManager().setComponentEnabledSetting(
                        new ComponentName("com.warrantix.main", "com.warrantix.main.MainAlias"),
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED , PackageManager.DONT_KILL_APP);
            }
        }
    }
}

