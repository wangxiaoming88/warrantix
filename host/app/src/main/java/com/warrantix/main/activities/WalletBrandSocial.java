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
import com.warrantix.main.fragments.walletbrand.WalletBrandSocialChatFragment;
import com.warrantix.main.fragments.walletbrand.WalletBrandSocialRankFragment;
import com.warrantix.main.fragments.walletbrand.WalletBrandSocialReferFragment;
import com.warrantix.main.fragments.walletbrand.WalletBrandSocialRevealFragment;
import com.warrantix.main.fragments.walletbrand.WalletBrandSocialReviewFragment;

public class WalletBrandSocial extends BaseActivity {
    private ImageView social1tab;
    private ImageView social2tab;
    private ImageView social3tab;
    private ImageView social4tab;
    private ImageView social5tab;

    private ImageView socialUnder1tab;
    private ImageView socialUnder2tab;
    private ImageView socialUnder3tab;
    private ImageView socialUnder4tab;
    private ImageView socialUnder5tab;

    private LinearLayout revealLyout;
    private LinearLayout referLyout;
    private LinearLayout reviewLyout;
    private LinearLayout rankLyout;
    private LinearLayout chatLyout;

    private TextView titleTXT;
    private Intent mIntent;

    private NonSwipeableViewPager contentPager = null;
    private final FragmentStatePagerAdapter contentPageAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new WalletBrandSocialRevealFragment();
            else if (position == 1)
                return new WalletBrandSocialReferFragment();
            else if (position == 2)
                return new WalletBrandSocialReviewFragment();
            else if (position == 3)
                return new WalletBrandSocialRankFragment();
            else if (position == 4)
                return new WalletBrandSocialChatFragment();
            else
                return new Fragment();
        }

        @Override
        public int getCount() {
            return 5;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_social);
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
        int selected = 0;
        selected = getIntent().getIntExtra("selected", 0);
        titleTXT = (TextView) findViewById(R.id.title);

        social1tab = (ImageView) findViewById(R.id.social1TAB);
        social2tab = (ImageView) findViewById(R.id.social2TAB);
        social3tab = (ImageView) findViewById(R.id.social3TAB);
        social4tab = (ImageView) findViewById(R.id.social4TAB);
        social5tab = (ImageView) findViewById(R.id.social5TAB);

        socialUnder1tab = (ImageView) findViewById(R.id.socialUnder1TAB);
        socialUnder2tab = (ImageView) findViewById(R.id.socialUnder2TAB);
        socialUnder3tab = (ImageView) findViewById(R.id.socialUnder3TAB);
        socialUnder4tab = (ImageView) findViewById(R.id.socialUnder4TAB);
        socialUnder5tab = (ImageView) findViewById(R.id.socialUnder5TAB);

        revealLyout = (LinearLayout) findViewById(R.id.revealLyout);
        referLyout = (LinearLayout) findViewById(R.id.referLyout);
        reviewLyout = (LinearLayout) findViewById(R.id.reviewLyout);
        rankLyout = (LinearLayout) findViewById(R.id.rankLyout);
        chatLyout = (LinearLayout) findViewById(R.id.chatLyout);


        socialUnder1tab.setVisibility(View.VISIBLE);
        socialUnder2tab.setVisibility(View.GONE);
        socialUnder3tab.setVisibility(View.GONE);
        socialUnder4tab.setVisibility(View.GONE);
        socialUnder5tab.setVisibility(View.GONE);

        social1tab.setImageDrawable(getResources().getDrawable(R.drawable.reveal));
        social2tab.setImageDrawable(getResources().getDrawable(R.drawable.refer_black));
        social3tab.setImageDrawable(getResources().getDrawable(R.drawable.review_black));
        social4tab.setImageDrawable(getResources().getDrawable(R.drawable.rank_black));
        social5tab.setImageDrawable(getResources().getDrawable(R.drawable.chat_black_notification));

        contentPager = (NonSwipeableViewPager) findViewById(R.id.fragment_container);
        contentPager.setAdapter(contentPageAdapter);
        contentPager.setOffscreenPageLimit(contentPageAdapter.getCount());

        revealLyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRevealFragment();
            }
        });
        referLyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReferFragment();
            }
        });
        reviewLyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReviewFragment();
            }
        });
        rankLyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRankFragment();
            }
        });
        chatLyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChatFragment();
            }
        });

        switch (selected) {
            case 0:
                showRevealFragment();
                break;
            case 1:
                showReferFragment();
                break;
            case 2:
                showReviewFragment();
                break;
            case 3:
                showRankFragment();
                break;
            case 4:
                showChatFragment();
                break;
            default:
                showRevealFragment();
        }

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }

    private void showRevealFragment() {
        contentPager.setCurrentItem(0);

        socialUnder1tab.setVisibility(View.VISIBLE);
        socialUnder2tab.setVisibility(View.GONE);
        socialUnder3tab.setVisibility(View.GONE);
        socialUnder4tab.setVisibility(View.GONE);
        socialUnder5tab.setVisibility(View.GONE);

        social1tab.setImageDrawable(getResources().getDrawable(R.drawable.reveal));
        social2tab.setImageDrawable(getResources().getDrawable(R.drawable.refer_black));
        social3tab.setImageDrawable(getResources().getDrawable(R.drawable.review_black));
        social4tab.setImageDrawable(getResources().getDrawable(R.drawable.rank_black));
        social5tab.setImageDrawable(getResources().getDrawable(R.drawable.chat_black_notification));
    }

    private void showReferFragment() {
        contentPager.setCurrentItem(1);

        socialUnder1tab.setVisibility(View.GONE);
        socialUnder2tab.setVisibility(View.VISIBLE);
        socialUnder3tab.setVisibility(View.GONE);
        socialUnder4tab.setVisibility(View.GONE);
        socialUnder5tab.setVisibility(View.GONE);

        social1tab.setImageDrawable(getResources().getDrawable(R.drawable.reveal_black));
        social2tab.setImageDrawable(getResources().getDrawable(R.drawable.refer));
        social3tab.setImageDrawable(getResources().getDrawable(R.drawable.review_black));
        social4tab.setImageDrawable(getResources().getDrawable(R.drawable.rank_black));
        social5tab.setImageDrawable(getResources().getDrawable(R.drawable.chat_black_notification));
    }

    private void showReviewFragment() {
        contentPager.setCurrentItem(2);

        socialUnder1tab.setVisibility(View.GONE);
        socialUnder2tab.setVisibility(View.GONE);
        socialUnder3tab.setVisibility(View.VISIBLE);
        socialUnder4tab.setVisibility(View.GONE);
        socialUnder5tab.setVisibility(View.GONE);

        social1tab.setImageDrawable(getResources().getDrawable(R.drawable.reveal_black));
        social2tab.setImageDrawable(getResources().getDrawable(R.drawable.refer_black));
        social3tab.setImageDrawable(getResources().getDrawable(R.drawable.review));
        social4tab.setImageDrawable(getResources().getDrawable(R.drawable.rank_black));
        social5tab.setImageDrawable(getResources().getDrawable(R.drawable.chat_black_notification));
    }

    private void showRankFragment(){
        contentPager.setCurrentItem(3);

        socialUnder1tab.setVisibility(View.GONE);
        socialUnder2tab.setVisibility(View.GONE);
        socialUnder3tab.setVisibility(View.GONE);
        socialUnder4tab.setVisibility(View.VISIBLE);
        socialUnder5tab.setVisibility(View.GONE);

        social1tab.setImageDrawable(getResources().getDrawable(R.drawable.reveal_black));
        social2tab.setImageDrawable(getResources().getDrawable(R.drawable.refer_black));
        social3tab.setImageDrawable(getResources().getDrawable(R.drawable.review_black));
        social4tab.setImageDrawable(getResources().getDrawable(R.drawable.rank));
        social5tab.setImageDrawable(getResources().getDrawable(R.drawable.chat_black_notification));
    }

    private void showChatFragment() {
        contentPager.setCurrentItem(4);

        socialUnder1tab.setVisibility(View.GONE);
        socialUnder2tab.setVisibility(View.GONE);
        socialUnder3tab.setVisibility(View.GONE);
        socialUnder4tab.setVisibility(View.GONE);
        socialUnder5tab.setVisibility(View.VISIBLE);

        social1tab.setImageDrawable(getResources().getDrawable(R.drawable.reveal_black));
        social2tab.setImageDrawable(getResources().getDrawable(R.drawable.refer_black));
        social3tab.setImageDrawable(getResources().getDrawable(R.drawable.review_black));
        social4tab.setImageDrawable(getResources().getDrawable(R.drawable.rank_black));
        social5tab.setImageDrawable(getResources().getDrawable(R.drawable.chat_notification));
    }
}
