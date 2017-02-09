package com.warrantix.main.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.noveogroup.android.log.Log;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.squareup.otto.Subscribe;
import com.warrantix.main.GlobalConfig;
import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.brandlist.WalletBrandListLocalDeals;
import com.warrantix.main.activities.brandlist.WalletBrandListManuals;
import com.warrantix.main.activities.brandlist.WalletBrandListOrder;
import com.warrantix.main.activities.brandlist.WalletBrandListRecalls;
import com.warrantix.main.activities.brandlist.WalletBrandListSettings;
import com.warrantix.main.activities.brandlist.WalletBrandListSettingsShoppingCart;
import com.warrantix.main.activities.brandlist.WalletBrandListTransferWarranty1;
import com.warrantix.main.activities.brandlist.WalletBrandListUserAccout;
import com.warrantix.main.activities.registration.ScanCodeActivity;
import com.warrantix.main.common.bus.BusProvider;
import com.warrantix.main.common.command.WarrantixCommands;
import com.warrantix.main.common.event.DownloadSuccessPluginMapEvent;
import com.warrantix.main.common.event.PluginBackToScreenEvent;
import com.warrantix.main.common.permission.MarshMallowPermission;
import com.warrantix.main.common.rest.WarrantixWebService;
import com.warrantix.main.common.rest.model.PluginMapInfo;
import com.warrantix.main.common.shortcut.ShortcutIcon;
import com.warrantix.main.customview.NonSwipeableViewPager;
import com.warrantix.main.customview.PinterestView;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.fragments.marketplace.MarketPlaceFragment;
import com.warrantix.main.fragments.marketplace.WarrantixCategoryFragment;
import com.warrantix.main.fragments.marketplace.WarrantixUsedProductFragment;
import com.warrantix.main.fragments.wallet.MainFragment;
import com.warrantix.main.loader.plugin.PluginManager;
import com.warrantix.main.loader.plugin.model.SiteSpec;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

    public static final int PLUGIN_APP_LAUNCHED = 10;
    public static final int PLUGIN_APP_INSTALLED = 20;

    private final int BRAND_APP_SELECTED = 0;
    private final int RETAIL_APP_SELECTED = 1;
    private final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 10;

    private int sectionSelected = BRAND_APP_SELECTED;

    protected LinearLayout customActionBar = null;
    protected RelativeLayout actionbtnMap[];
    protected ImageView actionImageView[];
    private PinterestView social2Bar = null;

//    private int selectedMarketIndex = -1;

    protected MainFragment mainFragment = new MainFragment();
    protected MarketPlaceFragment marketFragment = new MarketPlaceFragment();
    protected WarrantixUsedProductFragment usedProductFragment = new WarrantixUsedProductFragment();
    protected WarrantixCategoryFragment categoryFragment = new WarrantixCategoryFragment();

    private NonSwipeableViewPager contentPager = null;
    private final FragmentStatePagerAdapter contentPageAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                if (mainFragment == null)
                    mainFragment = new MainFragment();
                return mainFragment;
            }
            else if (position == 1) {
                if (marketFragment == null)
                    marketFragment = new MarketPlaceFragment();
                return marketFragment;
            }
            else if (position == 2) {
                if (categoryFragment == null)
                    categoryFragment = new WarrantixCategoryFragment();
                return categoryFragment;
            }
            else if (position == 3) {
                if (usedProductFragment == null)
                    usedProductFragment = new WarrantixUsedProductFragment();
                return usedProductFragment;
            }

            return new Fragment();
        }

        @Override
        public int getCount() {
            return 9;
        }
    };

    protected ImageView actionButton = null;
    protected FloatingActionMenu actionMenu = null;

    protected SubActionButton reviewButton = null;
    protected SubActionButton referButton = null;
    protected SubActionButton rankButton = null;
    protected SubActionButton revealButton = null;
    protected SubActionButton chatButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
            initSocial2Bar();
            requestMarshMallowPermission();

            isInitialized = true;
        }

        updateSectionIcons();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }


    public void requestMarshMallowPermission() {
        MarshMallowPermission marshMallowPermission = new MarshMallowPermission(this);

        // request camera
        if (!marshMallowPermission.checkPermissionForCamera()) {
            marshMallowPermission.requestPermissionForCamera();
        }

        // request external storage
        if (!marshMallowPermission.checkPermissionForExternalStorage()) {
            marshMallowPermission.requestPermissionForExternalStorage();
        }

        // request gps permission
        if (!marshMallowPermission.checkPermissionForGPS()) {
            marshMallowPermission.requestPermissionForGPS();
        }
    }

    public View createChildView(int imageId,String tip){
        CircleImageView imageView = new CircleImageView(this);
        imageView.setBorderWidth(0);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setFillColor(getResources().getColor(R.color.colorAccent));
        imageView.setImageResource(imageId);
        imageView.setTag(tip);
        return imageView;
    }

    private void initSocial2Bar() {
        social2Bar = (PinterestView) findViewById(R.id.social2Bar);
        social2Bar.addShowView(40, createChildView(R.drawable.long_press, "")
                , createChildView(R.drawable.popup_review, "Review")
                , createChildView(R.drawable.popup_refer, "Refer")
                , createChildView(R.drawable.popup_rank, "Rank")
                , createChildView(R.drawable.popup_reveal, "Reveal")
                , createChildView(R.drawable.popup_chat, "Chat"));
        /**
         * add pinterestview menu and Pre click view click
         */
        social2Bar.setPinClickListener(new PinterestView.PinMenuClickListener() {

            @Override
            public void onMenuItemClick(int childAt) {
                String tips = (String) social2Bar.getChildAt(childAt).getTag();
                int selected = 0;
                switch (childAt) {
                    case 1:
                        selected = 2;
                        break;
                    case 2:
                        selected = 1;
                        break;
                    case 3:
                        selected = 3;
                        break;
                    case 4:
                        selected = 0;
                        break;
                    case 5:
                        selected = 4;
                        break;
                    default:
                        return;
                }

                Intent i = new Intent(MainActivity.this, WalletBrandSocial.class);
                i.putExtra("selected", selected);
                MainActivity.this.startActivity(i, true);
            }

            @Override
            public void onPreViewClick() {
            }
        });
    }

    public boolean social2BarDispatchEvent(View v, MotionEvent event) {
        return social2Bar.dispatchTouchEvent(event);
    }

    private void updateSectionIcons() {
        if (sectionSelected == BRAND_APP_SELECTED) {
            setSelectedTab(0);
        }
        else if (sectionSelected == RETAIL_APP_SELECTED) {
            setSelectedTab(1);
        }
    }

    public void initialize() {
        customActionBar = (LinearLayout) findViewById(R.id.custom_tab_actionbar);

        initializeFragments();
        initActionbarButtons();
        initSocialListeners();
        // loadPluginMapInfo();
    }

    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private void initializeFragments() {
        contentPager = (NonSwipeableViewPager) findViewById(R.id.contentPager);
        contentPager.setAdapter(contentPageAdapter);
        contentPager.setOffscreenPageLimit(contentPageAdapter.getCount());
    }

    private void initSocialListeners() {
        RelativeLayout socialLayout = (RelativeLayout) findViewById(R.id.product_service_layout);
        ImageView reviewView = (ImageView) socialLayout.findViewById(R.id.product_review);
        reviewView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Review button is clicked", Toast.LENGTH_SHORT).show();
                hideSocial1Bar();
            }
        });

        ImageView revealView = (ImageView) socialLayout.findViewById(R.id.product_reveal);
        revealView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Reveal button is clicked", Toast.LENGTH_SHORT).show();
                hideSocial1Bar();
            }
        });

        ImageView referView = (ImageView) socialLayout.findViewById(R.id.product_refer);
        referView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Refer button is clicked", Toast.LENGTH_SHORT).show();
                hideSocial1Bar();
            }
        });

        ImageView rankView = (ImageView) socialLayout.findViewById(R.id.product_rank);
        rankView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Rank button is clicked", Toast.LENGTH_SHORT).show();
                hideSocial1Bar();
            }
        });

        ImageView chatView = (ImageView) socialLayout.findViewById(R.id.product_chat);
        chatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Chat button is clicked", Toast.LENGTH_SHORT).show();
                hideSocial1Bar();
            }
        });

        LinearLayout social1Layout = (LinearLayout) findViewById(R.id.product_service1_layout);
        LinearLayout revealButton = (LinearLayout) social1Layout.findViewById(R.id.revealBTN);
        revealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(MainActivity.this, WalletBrandSocial.class);
                intent.putExtra("selected", 0);
                startActivity(intent, true);
            }
        });

        LinearLayout referButton = (LinearLayout) social1Layout.findViewById(R.id.referBTN);
        referButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(MainActivity.this, WalletBrandSocial.class);
                intent.putExtra("selected", 1);
                startActivity(intent, true);
            }
        });

        LinearLayout reviewButton = (LinearLayout) social1Layout.findViewById(R.id.reviewBTN);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(MainActivity.this, WalletBrandSocial.class);
                intent.putExtra("selected", 2);
                startActivity(intent, true);
            }
        });

        LinearLayout rankButton = (LinearLayout) social1Layout.findViewById(R.id.rankBTN);
        rankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(MainActivity.this, WalletBrandSocial.class);
                intent.putExtra("selected", 3);
                startActivity(intent, true);
            }
        });

        LinearLayout chatButton = (LinearLayout) social1Layout.findViewById(R.id.chatBTN);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(MainActivity.this, WalletBrandSocial.class);
                intent.putExtra("selected", 4);
                startActivity(intent, true);
            }
        });

    }

//    public void showSocialBar() {
//        View maskView = (View) findViewById(R.id.maskView);
//        maskView.setVisibility(View.VISIBLE);
//        maskView.setOnClickListener(maskViewClickListener);
//
//        RelativeLayout socialLayout = (RelativeLayout) findViewById(R.id.product_service_layout);
//        socialLayout.setVisibility(View.VISIBLE);
//    }

//    public void hideSocialBar() {
//        View maskView = (View) findViewById(R.id.maskView);
//        maskView.setVisibility(View.GONE);
//        maskView.setOnClickListener(null);
//
//        RelativeLayout socialLayout = (RelativeLayout) findViewById(R.id.product_service_layout);
//        socialLayout.setVisibility(View.GONE);
//    }

    public void showSocial1Bar(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        View maskView = (View) findViewById(R.id.maskView);
        maskView.setVisibility(View.VISIBLE);
        maskView.setOnClickListener(maskViewClickListener);

        LinearLayout socialLayout = (LinearLayout) findViewById(R.id.product_service1_layout);
        socialLayout.setVisibility(View.VISIBLE);

        int topHeight = (int) getResources().getDimension(R.dimen._60sdp);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) socialLayout.getLayoutParams();
        int topMargin = buttonY - layoutParams.height - buttonWidth - 30;
        if (topMargin < topHeight)
            topMargin += buttonHeight + layoutParams.height + (int) getResources().getDimension(R.dimen._15sdp);
        layoutParams.setMargins(layoutParams.leftMargin, topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
        socialLayout.setLayoutParams(layoutParams);
    }

    public void hideSocial1Bar() {
        View maskView = (View) findViewById(R.id.maskView);
        maskView.setVisibility(View.GONE);
        maskView.setOnClickListener(null);

        LinearLayout socialLayout = (LinearLayout) findViewById(R.id.product_service1_layout);
        socialLayout.setVisibility(View.GONE);
    }


    private void loadPluginMapInfo() {
        WarrantixWebService.getInstance().getPluginMap();
    }

    private void initActionbarButtons()
    {
        actionbtnMap = new RelativeLayout[5];
        actionbtnMap[0] = (RelativeLayout) findViewById(R.id.actionbar_wallet_item);
        actionbtnMap[1] = (RelativeLayout) findViewById(R.id.actionbar_marketplace_item);
        actionbtnMap[2] = (RelativeLayout) findViewById(R.id.actionbar_registration_item);
        actionbtnMap[3] = (RelativeLayout) findViewById(R.id.actionbar_social_item);
        actionbtnMap[4] = (RelativeLayout) findViewById(R.id.actionbar_more_item);

        actionbtnMap[0].setOnClickListener(tabButtonListener);
        actionbtnMap[1].setOnClickListener(tabButtonListener);
        actionbtnMap[2].setOnClickListener(tabButtonListener);
        actionbtnMap[3].setOnClickListener(tabButtonListener);
        actionbtnMap[4].setOnClickListener(tabButtonListener);

        actionImageView = new ImageView[5];
        actionImageView[0] = (ImageView) findViewById(R.id.actionbar_wallet_imageview);
        actionImageView[1] = (ImageView) findViewById(R.id.actionbar_marketplace_imageview);
        actionImageView[2] = (ImageView) findViewById(R.id.actionbar_registration_imageview);
        actionImageView[3] = (ImageView) findViewById(R.id.actionbar_social_imageview);
        actionImageView[4] = (ImageView) findViewById(R.id.actionbar_more_imageview);
    }

    public void changeTab(int position) {
        switch (position) {
            case 0:
                loadWalletMode();
                break;
            case 1:
                loadMarketplaceMode();
                break;
            case 2:
                loadRegistrationPage();
                break;
            case 3:
                loadSocialPage();
                break;
            case 4:
                loadMorePopupMenu();
                break;
            default:
        }

        setSelectedTab(position);
    }

    public void setSelectedTab(int position) {
        if (position == 0) {
            actionImageView[0].setBackgroundResource(R.drawable.maintab_wallet_selected);
            actionImageView[1].setBackgroundResource(R.drawable.maintab_marketplace);
            actionImageView[2].setBackgroundResource(R.drawable.maintab_registration);
            actionImageView[3].setBackgroundResource(R.drawable.maintab_social);
            actionImageView[4].setBackgroundResource(R.drawable.maintab_more);

            sectionSelected = BRAND_APP_SELECTED;
        }
        else if (position == 1) {
            actionImageView[0].setBackgroundResource(R.drawable.maintab_wallet);
            actionImageView[1].setBackgroundResource(R.drawable.maintab_marketplace_selected);
            actionImageView[2].setBackgroundResource(R.drawable.maintab_registration);
            actionImageView[3].setBackgroundResource(R.drawable.maintab_social);
            actionImageView[4].setBackgroundResource(R.drawable.maintab_more);

            sectionSelected = RETAIL_APP_SELECTED;
        }
        else if (position == 2) {
            actionImageView[0].setBackgroundResource(R.drawable.maintab_wallet);
            actionImageView[1].setBackgroundResource(R.drawable.maintab_marketplace);
            actionImageView[2].setBackgroundResource(R.drawable.maintab_registration_selected);
            actionImageView[3].setBackgroundResource(R.drawable.maintab_social);
            actionImageView[4].setBackgroundResource(R.drawable.maintab_more);
        }
        else if (position == 3) {
            actionImageView[0].setBackgroundResource(R.drawable.maintab_wallet);
            actionImageView[1].setBackgroundResource(R.drawable.maintab_marketplace);
            actionImageView[2].setBackgroundResource(R.drawable.maintab_registration);
            actionImageView[3].setBackgroundResource(R.drawable.maintab_social_selected);
            actionImageView[4].setBackgroundResource(R.drawable.maintab_more);
        }
        else if (position == 4) {
            actionImageView[0].setBackgroundResource(R.drawable.maintab_wallet);
            actionImageView[1].setBackgroundResource(R.drawable.maintab_marketplace);
            actionImageView[2].setBackgroundResource(R.drawable.maintab_registration);
            actionImageView[3].setBackgroundResource(R.drawable.maintab_social);
            actionImageView[4].setBackgroundResource(R.drawable.maintab_more_selected);
        } else {

        }
    }

    public void loadWalletMode() {
        contentPager.setCurrentItem(0);
        hideKeyboard(this);
    }

    public void loadMarketplaceMode() {
//        if  (selectedMarketIndex == -1) {
            contentPager.setCurrentItem(1);

            String defaultPluginName = GlobalConfig.getInstance().getPluginName(GlobalConfig.getInstance().getDefaultPluginIndex());
            marketFragment.manualSelectBrandIcon(defaultPluginName);
//        }
//        else {
//            contentPager.setCurrentItem(3);
//        }
        hideKeyboard(this);
    }

    public void loadRegistrationPage() {
        Intent i = new Intent(MainActivity.this, ScanCodeActivity.class);
        startActivity(i, true);
    }

    public void loadSocialPage() {
        Intent i = new Intent(MainActivity.this, WalletBrandSocial.class);
        startActivity(i, true);
    }

    public void loadMorePopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, actionImageView[4]) {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.menu_shopping_cart:
                        intent = new Intent(MainActivity.this, WalletBrandListSettingsShoppingCart.class);
                        startActivity(intent, true);
                        return true;

                    case R.id.menu_your_orders:
                        intent = new Intent(MainActivity.this, WalletBrandListOrder.class);
                        startActivity(intent, true);
                        return true;

                    case R.id.menu_manuals:
                        intent = new Intent(MainActivity.this, WalletBrandListManuals.class);
                        startActivity(intent, true);
                        return true;

                    case R.id.menu_recalls:
                        intent = new Intent(MainActivity.this, WalletBrandListRecalls.class);
                        startActivity(intent, true);
                        return true;

                    case R.id.menu_deal:
                        intent = new Intent(MainActivity.this, WalletBrandListLocalDeals.class);
                        startActivity(intent, true);
                        return true;

                    case R.id.menu_sync_vault:
//                        intent = new Intent(MainActivity.this, WalletBrandListLocalDeals.class);
//                        startActivity(intent, true);

                        MessageDialog dialog = new MessageDialog(MainActivity.this);
                        dialog.setTitle("App Updated");
                        dialog.setMessage("");
                        dialog.show();

                        return true;

                    case R.id.menu_warranty:
                        intent = new Intent(MainActivity.this, WalletBrandListTransferWarranty1.class);
                        startActivity(intent, true);
                        return true;

                    case R.id.menu_user_account:
                        intent = new Intent(MainActivity.this, WalletBrandListUserAccout.class);
                        startActivity(intent, true);
                        return true;

                    case R.id.menu_settings:
                        intent = new Intent(MainActivity.this, WalletBrandListSettings.class);
                        startActivity(intent, true);
                        return true;

                    default:
                        return super.onMenuItemSelected(menu, item);
                }
            }
        };

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                updateSectionIcons();
            }
        });
        popupMenu.inflate(R.menu.menu_popup_main);
        popupMenu.show();
    }

    private void doExit()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialog.setNegativeButton("No", null);
        alertDialog.setMessage("Do you want to exit?");
        alertDialog.setTitle("Info");
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        doExit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onDownloadSuccessPluginMap(DownloadSuccessPluginMapEvent successEvent) {
        final ArrayList<PluginMapInfo> acPluginMaps = successEvent.getPluginMapInfo();
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PluginManager pluginManager = WarrantixApplication.getInstance().pluginManager();
                pluginManager.setPluginMapInfos(acPluginMaps);
            }
        });

    }

    private final View.OnClickListener tabButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof RelativeLayout) {
                RelativeLayout selectedTabButton = (RelativeLayout) v;
                if (selectedTabButton == actionbtnMap[0]) changeTab(0);
                else if (selectedTabButton == actionbtnMap[1]) changeTab(1);
                else if (selectedTabButton == actionbtnMap[2]) changeTab(2);
                else if (selectedTabButton == actionbtnMap[3]) changeTab(3);
                else if (selectedTabButton == actionbtnMap[4]) changeTab(4);
            }
        }
    };

    //
    // deprecated: runPlugin and Worker
    //
    public void runPlugin(String siteUrl) {
        Worker worker = new Worker(siteUrl);
        worker.start();
    }

    private class Worker extends Thread {
        private String url;

        public Worker(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            final String siteUrl = url;
            try {
                URL url = new URL(siteUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(15000);

                InputStream ins = conn.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream(16 * 1024);
                byte[] buf = new byte[1024 * 4]; // 4k buffer
                int l;
                while ((l = ins.read(buf, 0, buf.length)) != -1) {
                    bos.write(buf, 0, l);
                }
                bos.close();
                ins.close();
                conn.disconnect();

                byte[] bytes = bos.toByteArray();
                String str = new String(bytes, "UTF-8");

                // TODO:
                // verify signature

                JSONObject json = new JSONObject(str);
                final SiteSpec fSite = new SiteSpec(json);

                File dir = new File(getFilesDir(), "repo");
                new File(dir, "lastUrl.txt").delete();
                dir.mkdir();

                File local = new File(dir, "site.txt");
                File tmp = new File(dir, "site_tmp");
                try {
                    FileOutputStream fos = new FileOutputStream(tmp);
                    fos.write(bytes);
                    fos.close();
                    tmp.renameTo(local);
                }
                catch (Exception e) {
                    tmp.delete();
                }

                Log.i("site.xml updated to " + fSite.id());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String url = WarrantixApplication.PRIMARY_SCHEME + "://" + fSite.fragments()[0].host();
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        i.putExtra("_site", fSite);
                        startActivity(i);
                    }
                });
            }
            catch (final Exception e) {
                Log.w("fail to download site from " + siteUrl, e);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    };

    @Subscribe
    public void OnPluginBackToScreenEvent(PluginBackToScreenEvent event) {
        final String toScreen = event.getScreenName();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(WarrantixApplication.getAppContext(), "ToMarket = " + brandName, Toast.LENGTH_SHORT).show();

                if ((toScreen.equalsIgnoreCase("brand") == true) || (toScreen.equalsIgnoreCase("retailer") == true)) {
                    changeTab(0);
                }
                else {
                    changeTab(1);
                    marketFragment.manualSelectBrandIcon(toScreen);
                }
            }
        });
    }

    private final View.OnClickListener maskViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideSocial1Bar();
        }
    };


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("Request Code = " + requestCode + " result Code = " + resultCode);

        if (requestCode == PLUGIN_APP_LAUNCHED) {
            if (resultCode == RESULT_OK && data != null) {
                try {
                    String launched = data.getStringExtra(WarrantixCommands.PLUGIN_LAUNCHED);
                    if (launched != null) {
                        getPackageManager().setComponentEnabledSetting(
                                new ComponentName("com.warrantix.main", "com.warrantix.main.MainAlias"),
                                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                    }

                    String toScreen = data.getStringExtra(WarrantixCommands.PLUGIN_BACKTO_SCREEN);
                    if (toScreen != null) {
                        PluginBackToScreenEvent event = new PluginBackToScreenEvent(toScreen);
                        BusProvider.get().post(event);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else if (requestCode == PLUGIN_APP_INSTALLED) {
            if (resultCode == RESULT_OK) {
                Log.d("MainActivity", "Plugin app installation is succeeded");
            }
            else {
                Log.d("MainActivity", "Plugin app installation is failed");
            }
        }
    }

    public void setMarketplaceFragment() {
//        selectedMarketIndex = -1;
        contentPager.setCurrentItem(1);

        hideKeyboard(this);
    }

    public void setWarrantixUsedProductFragment() {
//        selectedMarketIndex = 0;
        contentPager.setCurrentItem(3);
    }

    public void setWarrantixCategoryFragment(int index) {
        if (index == 0) {
            categoryFragment.setType(WarrantixCategoryFragment.TYPE_APPLIANCE);
            contentPager.setCurrentItem(2);
        }
        else if (index == 1) {
            categoryFragment.setType(WarrantixCategoryFragment.TYPE_ELECTRONICS);
            contentPager.setCurrentItem(2);
        }
        else if (index == 2) {
            categoryFragment.setType(WarrantixCategoryFragment.TYPE_VEHICLES);
            contentPager.setCurrentItem(2);
        }
        else if (index == 3) {
            categoryFragment.setType(WarrantixCategoryFragment.TYPE_FITNESS);
            contentPager.setCurrentItem(2);
        }
        else if (index == 4) {
            categoryFragment.setType(WarrantixCategoryFragment.TYPE_PERSONAL);
            contentPager.setCurrentItem(2);
        }
        else if (index == 5) {
            categoryFragment.setType(WarrantixCategoryFragment.TYPE_ASSORTED);
            contentPager.setCurrentItem(2);
        }
        else
            ;
    }
}
