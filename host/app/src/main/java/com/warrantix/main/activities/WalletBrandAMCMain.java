package com.warrantix.main.activities;


import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.warrantix.main.R;
import com.warrantix.main.adapter.WalletBrandAMCList;
import com.warrantix.main.adapter.WalletBrandAMCMainList;
import com.warrantix.main.adapter.WalletBrandInsuranceList;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandAMCMain extends BaseActivity {
    private ListView list = null;
    private LinearLayout mCategory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_amcmain);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            InitializeView();
            initializeBannerBar();
            isInitialized = true;
        }
    }

    public void InitializeView(){

        list = (ListView) findViewById(R.id.list);
        list.setAdapter(null);
        list.setOnScrollListener(scrollChangeListener);

        mCategory = (LinearLayout) findViewById(R.id.category);

        // sample data
        String[] name = new String[10],name1 = new String[10],name0 = new String[10],days = new String[10],imageID = new String[10];
        for (int i=0;i<10;i++)
        {
            if (Math.random()>=0.5){
                name[i] = "Karizma ZMR";
                name0[i] = "";
                name1[i] = "Hero Motocop";
                days[i] = "284";
                imageID[i] = "wallet_products_products1";
            }else {
                name[i] = "WT 650 CF Washing";
                name0[i] = "Machine";
                name1[i] = "Godrej";
                days[i] = "284";
                imageID[i] = "wallet_products_products2";
            }

        }

        WalletBrandAMCMainList adapter = new WalletBrandAMCMainList(this, name, name1, days,imageID);
        list.setAdapter(adapter);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }

    private void initializeBannerBar() {
        final Button allButton = (Button) findViewById(R.id.products_all);
        final Button appliancesButton = (Button) findViewById(R.id.products_appliances);
        final Button vehicleButton = (Button) findViewById(R.id.wallet_vehicles);
        final Button electronicButton = (Button) findViewById(R.id.products_electronics);
        final Button assortedButton = (Button) findViewById(R.id.products_assorted);

        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allButton.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                appliancesButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehicleButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                assortedButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allButton.setTextColor(getResources().getColor(R.color.wx_accent_color));
                appliancesButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehicleButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                assortedButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));

//                appliancesButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.appliances_b, 0);
//                vehicleButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.vehicles_b, 0);
//                electronicButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.electronics_b, 0);
//                assortedButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.assorted_b, 0);
            }
        });

        appliancesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesButton.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                vehicleButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                assortedButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesButton.setTextColor(getResources().getColor(R.color.wx_accent_color));
                vehicleButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                assortedButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));

//                appliancesButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.appliances, 0);
//                vehicleButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.vehicles_b, 0);
//                electronicButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.electronics_b, 0);
//                assortedButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.assorted_b, 0);
            }
        });

        vehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehicleButton.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                electronicButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                assortedButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehicleButton.setTextColor(getResources().getColor(R.color.wx_accent_color));
                electronicButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                assortedButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));

//                appliancesButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.appliances_b, 0);
//                vehicleButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.vehicles, 0);
//                electronicButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.electronics_b, 0);
//                assortedButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.assorted_b, 0);
            }
        });

        electronicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehicleButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicButton.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                assortedButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehicleButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicButton.setTextColor(getResources().getColor(R.color.wx_accent_color));
                assortedButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));

//                appliancesButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.appliances_b, 0);
//                vehicleButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.vehicles_b, 0);
//                electronicButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.electronics, 0);
//                assortedButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.assorted_b, 0);
            }
        });

        assortedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehicleButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicButton.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                assortedButton.setBackgroundResource(R.drawable.stroke_accent_button_selector);

                allButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehicleButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicButton.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                assortedButton.setTextColor(getResources().getColor(R.color.wx_accent_color));

//                appliancesButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.appliances_b, 0);
//                vehicleButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.vehicles_b, 0);
//                electronicButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.electronics_b, 0);
//                assortedButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.assorted, 0);
            }
        });
    }

    private final AbsListView.OnScrollListener scrollChangeListener = new AbsListView.OnScrollListener() {
        public int mLastFirstVisibleItem;
        public boolean mIsScrollingUp ;

        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            // TODO Auto-generated method stub

        }

        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // TODO Auto-generated method stub
            if(scrollState != 0)
                return;

            if(view.getFirstVisiblePosition() == 0) {
                if (mCategory.getVisibility() != View.VISIBLE) {
                    mCategory.setVisibility(View.VISIBLE);

                    Animation animation = AnimationUtils.loadAnimation(WalletBrandAMCMain.this, R.anim.animation_slide_in_up);
                    animation.setDuration(400);
                    mCategory.setAnimation(animation);
                    mCategory.animate();
                    animation.start();
                }
            }
            else {
                if (mCategory.getVisibility() != View.GONE) {
                    mCategory.setVisibility(View.GONE);

                    Animation animation = AnimationUtils.loadAnimation(WalletBrandAMCMain.this, R.anim.animation_slide_out_up);
                    animation.setDuration(400);
                    mCategory.setAnimation(animation);
                    mCategory.animate();
                    animation.start();
                }
            }
        }
    };
}
