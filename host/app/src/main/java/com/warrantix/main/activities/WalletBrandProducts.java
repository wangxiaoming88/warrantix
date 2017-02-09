package com.warrantix.main.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.adapter.WalletProductList;

public class WalletBrandProducts extends BaseActivity {
    private LinearLayout mCategory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_products);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    private void Initialize() {
        initializeView();
        initializeBannerBar();

        ImageButton back = (ImageButton) findViewById(R.id.brand_arrow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }

    private void initializeBannerBar() {
        final LinearLayout allButton = (LinearLayout) findViewById(R.id.products_all);
        final LinearLayout appliancesButton = (LinearLayout) findViewById(R.id.products_appliances);
        final LinearLayout vehicleButton = (LinearLayout) findViewById(R.id.wallet_vehicles);
        final LinearLayout electronicButton = (LinearLayout) findViewById(R.id.products_electronics);
        final LinearLayout assortedButton = (LinearLayout) findViewById(R.id.products_assorted);

        final TextView allTextView = (TextView) findViewById(R.id.allTextView);
        final TextView appliancesTextView = (TextView) findViewById(R.id.appliancesTextView);
        final TextView vehiclesTextView = (TextView) findViewById(R.id.vehiclesTextView);
        final TextView electronicTextView = (TextView) findViewById(R.id.electronicsTextView);
        final TextView personalTextView = (TextView) findViewById(R.id.personalTextView);

        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
            }
        });

        appliancesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
            }
        });

        vehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
            }
        });

        electronicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
            }
        });

        assortedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                appliancesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                vehiclesTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                electronicTextView.setBackgroundResource(R.drawable.stroke_grey_button_selector);
                personalTextView.setBackgroundResource(R.drawable.stroke_accent_button_selector);

                allTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                appliancesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                vehiclesTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                electronicTextView.setTextColor(getResources().getColor(R.color.wx_fourth_color));
                personalTextView.setTextColor(getResources().getColor(R.color.wx_accent_color));
            }
        });
    }

    public void initializeView() {
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

        WalletProductList adapter = new WalletProductList(this, name,name0, name1, days,imageID);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(scrollChangeListener);
//        listView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (mActivity instanceof MainActivity)
//                    return ((MainActivity) mActivity).social2BarDispatchEvent(v, event);
//
//                return false;
//            }
//        });

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                if (mActivity instanceof MainActivity)
//                    ((MainActivity) mActivity).showSocial3Bar();
//
//                return true;
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(WalletBrandProducts.this, WalletBrandProductsDetail.class);
                startActivity(mIntent, true);
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

                    Animation animation = AnimationUtils.loadAnimation(WalletBrandProducts.this, R.anim.animation_slide_in_up);
                    animation.setDuration(400);
                    mCategory.setAnimation(animation);
                    mCategory.animate();
                    animation.start();
                }
            }
            else {
                if (mCategory.getVisibility() != View.GONE) {
                    mCategory.setVisibility(View.GONE);

                    Animation animation = AnimationUtils.loadAnimation(WalletBrandProducts.this, R.anim.animation_slide_out_up);
                    animation.setDuration(400);
                    mCategory.setAnimation(animation);
                    mCategory.animate();
                    animation.start();
                }
            }
        }
    };
}