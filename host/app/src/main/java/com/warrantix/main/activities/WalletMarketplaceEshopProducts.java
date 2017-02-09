package com.warrantix.main.activities;

import android.content.Intent;
import android.graphics.Typeface;
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
import com.warrantix.main.fragments.walletbrand.WalletMarketPlaceEshopProductFragment3;
import com.warrantix.main.fragments.walletbrand.WalletMarketplaceEshopProductFragment1;
import com.warrantix.main.fragments.walletbrand.WalletMarketplaceEshopProductFragment2;

public class WalletMarketplaceEshopProducts extends BaseActivity implements View.OnClickListener {
    private ImageView insurance1;
    private ImageView insurance2;
    private ImageView insurance3;

    private LinearLayout insurance1Lyout;
    private LinearLayout insurance2Lyout;
    private LinearLayout insurance3Lyout;

    private TextView titleTXT;
    public static int eshop_productsSel=0;

    private NonSwipeableViewPager contentPager = null;
    private final FragmentStatePagerAdapter contentPageAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new WalletMarketplaceEshopProductFragment1();
            else if (position == 1)
                return new WalletMarketplaceEshopProductFragment2();
            else if (position == 2)
                return new WalletMarketPlaceEshopProductFragment3();
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
        setContentView(R.layout.activity_brand_product);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
            isInitialized = true;
        }
    }

    private void initialize() {
        contentPager = (NonSwipeableViewPager) findViewById(R.id.fragment_container);
        contentPager.setAdapter(contentPageAdapter);

//        String title = getIntent().getStringExtra("title");
        titleTXT = (TextView) findViewById(R.id.title);
//        if (title != null)
//            titleTXT.setText(title);
//        else
//            titleTXT.setText("eSHOP PRODUCTS");

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


        if (eshop_productsSel==0){
            loadProduct1Fragment();
        }
        else if(eshop_productsSel==1){
            loadProduct2Fragment();
        }
        else {
            loadProduct3Fragment();
        }

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }

    public void loadProduct1Fragment() {
        contentPager.setCurrentItem(0);

        insurance1.setVisibility(View.VISIBLE);
        insurance2.setVisibility(View.GONE);
        insurance3.setVisibility(View.GONE);
    }

    public void loadProduct2Fragment() {
        contentPager.setCurrentItem(1);

        insurance1.setVisibility(View.GONE);
        insurance2.setVisibility(View.VISIBLE);
        insurance3.setVisibility(View.GONE);
    }

    public void loadProduct3Fragment() {
        contentPager.setCurrentItem(2);

        insurance1.setVisibility(View.GONE);
        insurance2.setVisibility(View.GONE);
        insurance3.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.insurance1Lyout) {
            loadProduct1Fragment();
        }
        if (v.getId() == R.id.insurance2Lyout) {
            loadProduct2Fragment();
        }
        if (v.getId() == R.id.insurance3Lyout){
            loadProduct3Fragment();
        }
    }
}
