package com.warrantix.main.fragments.marketplace;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.activities.WalletBrandAMC0;
import com.warrantix.main.activities.WalletBrandAccessoriesEshop;
import com.warrantix.main.activities.WalletBrandInsurance0;
import com.warrantix.main.activities.WalletMarketplaceSubCategory;
import com.warrantix.main.activities.brandlist.WalletBrandListSettingsDefaultMarketplace;
import com.warrantix.main.common.command.WarrantixCommands;
import com.warrantix.main.common.permission.MarshMallowPermission;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.loader.plugin.PluginManager;

public class HeroFragment extends BaseFragment {

    private ImageButton hamburgerButton = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_plugin_hero, container, false);
        initializeCategoryBar(v);
        initializeBrandAppButton(v);
        initializeScrollView(v);
        return v;
    }

    private void initializeScrollView(View v) {

    }

    private void initializeBrandAppButton(View v) {
        ImageView brandeshopButton = (ImageView) v.findViewById(R.id.brandeshopButton);
        brandeshopButton.setOnClickListener(new View.OnClickListener() {
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
                else {
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
        });
    }

    private void initializeCategoryBar(View v) {
        LinearLayout category1 = (LinearLayout) v.findViewById(R.id.popularCategory1);
        LinearLayout category2 = (LinearLayout) v.findViewById(R.id.popularCategory2);
        LinearLayout category3 = (LinearLayout) v.findViewById(R.id.popularCategory3);
        LinearLayout category4 = (LinearLayout) v.findViewById(R.id.popularCategory4);

        category1.setOnClickListener(category1Listener);
        category2.setOnClickListener(category2Listener);
        category3.setOnClickListener(category3Listener);
        category4.setOnClickListener(categoryListener);

        LinearLayout bestSellingProduct1 = (LinearLayout) v.findViewById(R.id.bestSellingProduct1);
        LinearLayout bestSellingProduct2 = (LinearLayout) v.findViewById(R.id.bestSellingProduct2);
        LinearLayout bestSellingProduct3 = (LinearLayout) v.findViewById(R.id.bestSellingProduct3);
        LinearLayout topRatedProduct1 = (LinearLayout) v.findViewById(R.id.topRatedProduct1);
        LinearLayout topRatedProduct2 = (LinearLayout) v.findViewById(R.id.topRatedProduct2);
        LinearLayout topRatedProduct3 = (LinearLayout) v.findViewById(R.id.topRatedProduct3);

        bestSellingProduct1.setOnClickListener(categoryListener);
        bestSellingProduct2.setOnClickListener(categoryListener);
        bestSellingProduct3.setOnClickListener(categoryListener);
        topRatedProduct1.setOnClickListener(categoryListener);
        topRatedProduct2.setOnClickListener(categoryListener);
        topRatedProduct3.setOnClickListener(categoryListener);

        hamburgerButton = (ImageButton) v.findViewById(R.id.popupMenu);
        hamburgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMorePopupMenu();
            }
        });

    }

    private final View.OnClickListener category1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
            i.putExtra("title", "HERO SPORT BIKE");
            if (mActivity instanceof BaseActivity)
                ((BaseActivity)mActivity).startActivity(i, true);
            else
                mActivity.startActivity(i);
        }
    };

    private final View.OnClickListener category2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
            i.putExtra("title", "HERO MOTOCYCLE");
            if (mActivity instanceof BaseActivity)
                ((BaseActivity)mActivity).startActivity(i, true);
            else
                mActivity.startActivity(i);
        }
    };

    private final View.OnClickListener category3Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
            i.putExtra("title", "HERO SCOOTERS");

            if (mActivity instanceof BaseActivity)
                ((BaseActivity)mActivity).startActivity(i, true);
            else
                mActivity.startActivity(i);
        }
    };

    private final View.OnClickListener categoryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent i = new Intent(mActivity, WalletBrandAccessoriesEshop.class);
//            ((BaseActivity)mActivity).startActivity(i, true);

            Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
            if (mActivity instanceof BaseActivity)
                ((BaseActivity)mActivity).startActivity(i, true);
            else
                mActivity.startActivity(i);
        }
    };

    public void loadMorePopupMenu() {
        PopupMenu popupMenu = new PopupMenu(mActivity, hamburgerButton) {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {

                    case R.id.menu_plugin_accessories: {
                        Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
                        i.putExtra("title", "eSHOP ACCESSORIES");
                        if (mActivity instanceof BaseActivity)
                            ((BaseActivity) mActivity).startActivity(i, true);
                        else
                            mActivity.startActivity(i);
                    }
                        return true;

                    case R.id.menu_plugin_amc: {
                        Intent i = new Intent(mActivity, WalletBrandAMC0.class);
                        if (mActivity instanceof BaseActivity)
                            ((BaseActivity) mActivity).startActivity(i, true);
                        else
                            mActivity.startActivity(i);
                    }
                        return true;

                    case R.id.menu_plugin_usedproducts: {
                        Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
                        i.putExtra("title", "USED PRODUCTS");
                        if (mActivity instanceof BaseActivity)
                            ((BaseActivity) mActivity).startActivity(i, true);
                        else
                            mActivity.startActivity(i);
                    }
                    return true;

                    case R.id.menu_plugin_insurance: {
                        Intent i = new Intent(mActivity, WalletBrandInsurance0.class);
                        if (mActivity instanceof BaseActivity)
                            ((BaseActivity)mActivity).startActivity(i, true);
                        else
                            mActivity.startActivity(i);
                    }
                        return true;

                    default:
                        return super.onMenuItemSelected(menu, item);
                }
            }
        };

        popupMenu.inflate(R.menu.menu_popup_plugin);
        popupMenu.show();
    }

}