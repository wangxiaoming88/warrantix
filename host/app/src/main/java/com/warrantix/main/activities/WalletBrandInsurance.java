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
import com.warrantix.main.fragments.walletbrand.WalletBrandInsuranceFragment1;
import com.warrantix.main.fragments.walletbrand.WalletBrandInsuranceFragment2;
import com.warrantix.main.fragments.walletbrand.WalletBrandInsuranceFragment3;

public class WalletBrandInsurance extends BaseActivity implements View.OnClickListener {
    private ImageView insurance1;
    private ImageView insurance2;
    private ImageView insurance3;

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
                return new WalletBrandInsuranceFragment1();
            else if (position == 1)
                return new WalletBrandInsuranceFragment2();
            else if (position == 2)
                return new WalletBrandInsuranceFragment3();
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
        setContentView(R.layout.activity_brand_insurance);
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
        insurance1 = (ImageView) findViewById(R.id.insurance1BTN);
        insurance2 = (ImageView) findViewById(R.id.insurance2BTN);
        insurance3 = (ImageView) findViewById(R.id.insurance3BTN);

        insurance1Lyout = (LinearLayout) findViewById(R.id.insurance1Lyout);
        insurance2Lyout = (LinearLayout) findViewById(R.id.insurance2Lyout);
        insurance3Lyout = (LinearLayout) findViewById(R.id.insurance3Lyout);

        insurance1.setVisibility(View.VISIBLE);
        insurance2.setVisibility(View.GONE);
        insurance3.setVisibility(View.GONE);

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

    public void loadInsuranceFragment1() {
        contentPager.setCurrentItem(0);

        insurance1.setVisibility(View.VISIBLE);
        insurance2.setVisibility(View.GONE);
        insurance3.setVisibility(View.GONE);
    }

    public void loadInsuranceFragment3() {
        contentPager.setCurrentItem(2);

        insurance1.setVisibility(View.GONE);
        insurance2.setVisibility(View.GONE);
        insurance3.setVisibility(View.VISIBLE);
    }

    public void loadInsuranceFragment2() {
        contentPager.setCurrentItem(1);

        insurance1.setVisibility(View.GONE);
        insurance2.setVisibility(View.VISIBLE);
        insurance3.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.insurance1Lyout) {
            loadInsuranceFragment1();
        }
        if (v.getId() == R.id.insurance2Lyout) {
            loadInsuranceFragment2();
        }
        if (v.getId() == R.id.insurance3Lyout){
            loadInsuranceFragment3();
        }
    }
}
