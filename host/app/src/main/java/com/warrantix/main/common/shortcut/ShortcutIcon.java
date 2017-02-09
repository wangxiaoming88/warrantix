package com.warrantix.main.common.shortcut;

import android.content.Intent;
import android.graphics.Bitmap;

import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.SplashActivity;
import com.warrantix.main.common.rest.WarrantixApi;

public class ShortcutIcon {

    public static void shortcutAdd(String name, Bitmap icon) {
        // Intent to be send, when shortcut is pressed by user ("launched")
        Intent shortcutIntent = new Intent(WarrantixApplication.getAppContext(), SplashActivity.class);
        shortcutIntent.setAction(Intent.ACTION_MAIN);

        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, icon);

        // Inform launcher to create shortcut
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        WarrantixApplication.getAppContext().sendBroadcast(addIntent);
    }

    public static void shortcutDel(String name) {
        // Intent to be send, when shortcut is pressed by user ("launched")
        Intent shortcutIntent = new Intent(WarrantixApplication.getAppContext(), SplashActivity.class);
        shortcutIntent.setAction(Intent.ACTION_MAIN);

        // Decorate the shortcut
        Intent delIntent = new Intent();
        delIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        delIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // Inform launcher to remove shortcut
        delIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
        WarrantixApplication.getAppContext().sendBroadcast(delIntent);
    }
}