package com.warrantix.main.fragments.wallet;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.common.command.WarrantixCommands;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.loader.plugin.PluginManager;

public class RetailersFragment extends BaseFragment {

    private ImageView retailImageView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_retailers, container, false);

        retailImageView = (ImageView) v.findViewById(R.id.retailLogoInBrandFragment);
        retailImageView.setOnClickListener(retailClickListener);
        return v;
    }

    private final View.OnClickListener retailClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PluginManager manager = WarrantixApplication.getInstance().pluginManager();
            String pluginURL = "com.warrantix.hero";
            String pluginUri = "com.warrantix.hero";

            boolean installed = manager.checkInstallation(pluginUri);
            if(installed) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(pluginUri, "com.warrantix.hero.activity.WalletRetailerApp"));
                intent.putExtra(WarrantixCommands.PLUGIN_LAUNCHED, "yes");
                ((BaseActivity)mActivity).startActivityForResult(intent, MainActivity.PLUGIN_APP_LAUNCHED, true);
            }
            else {
                Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + pluginUri));
                marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET|Intent.FLAG_ACTIVITY_MULTIPLE_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(marketIntent);
            }
        }
    };
}