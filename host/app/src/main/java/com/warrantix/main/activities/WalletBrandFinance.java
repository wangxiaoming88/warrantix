package com.warrantix.main.activities;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.customview.NonSwipeableViewPager;
import com.warrantix.main.fragments.walletbrand.WalletBrandFinanceFragment1;
import com.warrantix.main.fragments.walletbrand.WalletBrandFinanceFragment2;
import com.warrantix.main.fragments.walletbrand.WalletBrandFinanceFragment3;

public class WalletBrandFinance extends BaseActivity implements View.OnClickListener {
    private ImageView finance1;
    private ImageView finance2;
    private ImageView finance3;

    private LinearLayout insurance1Lyout;
    private LinearLayout insurance2Lyout;
    private LinearLayout insurance3Lyout;
    private Intent mIntent;

    private TextView titleTXT;
    public static int insuranceSel=0;

    private NonSwipeableViewPager contentPager = null;
    private final FragmentStatePagerAdapter contentPageAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new WalletBrandFinanceFragment1();
            else if (position == 1)
                return new WalletBrandFinanceFragment2();
            else if (position == 2)
                return new WalletBrandFinanceFragment3();
            else
                return new Fragment();
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_finance);
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

        contentPager = (NonSwipeableViewPager) findViewById(R.id.fragment_container);
        contentPager.setAdapter(contentPageAdapter);

        titleTXT = (TextView) findViewById(R.id.title);
        finance1 = (ImageView) findViewById(R.id.insurance1BTN);
        finance2 = (ImageView) findViewById(R.id.insurance2BTN);
        finance3 = (ImageView) findViewById(R.id.insurance3BTN);

        insurance1Lyout = (LinearLayout) findViewById(R.id.insurance1Lyout);
        insurance2Lyout = (LinearLayout) findViewById(R.id.insurance2Lyout);
        insurance3Lyout = (LinearLayout) findViewById(R.id.insurance3Lyout);

        finance1.setVisibility(View.VISIBLE);
        finance2.setVisibility(View.GONE);
        finance3.setVisibility(View.GONE);

        insurance1Lyout.setOnClickListener(this);
        insurance2Lyout.setOnClickListener(this);
        insurance3Lyout.setOnClickListener(this);

        if (insuranceSel==0){
            contentPager.setCurrentItem(0);
        }
        else if(insuranceSel==1){
            contentPager.setCurrentItem(1);
        }
        else {
            contentPager.setCurrentItem(2);
        }

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }

    public void loadFinanceFragment1() {
        contentPager.setCurrentItem(0);

        finance1.setVisibility(View.VISIBLE);
        finance2.setVisibility(View.GONE);
        finance3.setVisibility(View.GONE);
    }

    public void loadFinanceFragment3() {
        contentPager.setCurrentItem(2);

        finance1.setVisibility(View.GONE);
        finance2.setVisibility(View.GONE);
        finance3.setVisibility(View.VISIBLE);
    }

    public void loadFinanceFragment2() {
        contentPager.setCurrentItem(1);

        finance1.setVisibility(View.GONE);
        finance2.setVisibility(View.VISIBLE);
        finance3.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.insurance1Lyout) {
            loadFinanceFragment1();
        }
        if (v.getId() == R.id.insurance2Lyout) {
            loadFinanceFragment2();
        }
        if (v.getId() == R.id.insurance3Lyout){
            loadFinanceFragment3();
        }
    }
}
