package com.warrantix.main.fragments.wallet;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.common.command.WarrantixCommands;
import com.warrantix.main.common.permission.MarshMallowPermission;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.loader.plugin.PluginManager;

public class BrandsFragment extends BaseFragment {

    private ImageView brandLogoImageView = null;
    private ImageView advertiseVideoView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_brands, container, false);

        brandLogoImageView = (ImageView) v.findViewById(R.id.brandLogoInBrandFragment);
        advertiseVideoView = (ImageView) v.findViewById(R.id.advertiseVideoInBrandFragment);
        brandLogoImageView.setOnClickListener(brandLogoClickListener);
        advertiseVideoView.setOnClickListener(advertiseVideoClickListener);

        return v;
    }

    private final View.OnClickListener brandLogoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PluginManager manager = WarrantixApplication.getInstance().pluginManager();
            String pluginUri = "com.warrantix.hero";
            boolean installed = manager.checkInstallation(pluginUri);
            if (installed) {
                try {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(pluginUri, "com.warrantix.hero.activity.WalletBrandApp"));
                    intent.putExtra(WarrantixCommands.PLUGIN_LAUNCHED, "yes");
                    ((BaseActivity) mActivity).startActivityForResult(intent, MainActivity.PLUGIN_APP_LAUNCHED, true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mActivity, "Please check the brand plugin installation", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                // download apk from play store
                try {
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + pluginUri));
                    marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET | Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivityForResult(marketIntent, MainActivity.PLUGIN_APP_INSTALLED);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mActivity, "Please install google play store", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private final View.OnClickListener advertiseVideoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}