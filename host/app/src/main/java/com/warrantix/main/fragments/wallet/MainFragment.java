package com.warrantix.main.fragments.wallet;

import android.app.Activity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentStatePagerItemAdapter;
import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.fragments.BaseFragment;

public class MainFragment extends BaseFragment
{
    private final String LOG_TAG = WarrantixApplication.LOG_APP_NAME + MainFragment.class.getSimpleName();
    protected MainActivity mActivity = null;

//    protected FragmentPagerItemAdapter currentPagerAdapter = null;
    protected FragmentStatePagerItemAdapter currentPagerAdapter = null;
    protected ViewPager viewPager = null;
    protected SmartTabLayout viewPagerTab = null;
    protected View mainView = null;

    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        mActivity = (MainActivity) activity;
    }

    @Override
    public void onDetach () {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        mainView = inflater.inflate(R.layout.fragment_base, container, false);
        viewPager = (ViewPager) mainView.findViewById(R.id.viewpager);

        currentPagerAdapter = new FragmentStatePagerItemAdapter(
                mActivity.getSupportFragmentManager(), FragmentPagerItems.with(mActivity.getBaseContext())
                .add("Brands", BrandsFragment.class)
                .add("Retailers", RetailersFragment.class)
                .add("Products", ProductsFragment.class)
                .create());
        viewPager.setAdapter(currentPagerAdapter);
        viewPager.setOffscreenPageLimit(currentPagerAdapter.getCount());

        viewPagerTab = (SmartTabLayout) mainView.findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
        viewPagerTab.setOnPageChangeListener(viewPagerTabOnChangeListener);

        // set custom font & accent color
        int count = currentPagerAdapter.getCount();
        for (int i = 0; i < count; i++) {
            TextView view = (TextView) viewPagerTab.getTabAt(i);
            Typeface face= Typeface.createFromAsset(mActivity.getAssets(), "fonts/Montserrat-Regular.ttf");
            view.setTypeface(face);
            view.setTextColor(getResources().getColor(R.color.wx_text_color));
        }

        TextView view = (TextView) viewPagerTab.getTabAt(0);
        view.setTextColor(getResources().getColor(R.color.wx_accent_color));
        return mainView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private final ViewPager.OnPageChangeListener viewPagerTabOnChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (currentPagerAdapter == null)
                return;

            int count = currentPagerAdapter.getCount();
            for (int i = 0; i < count; i++) {
                TextView view = (TextView) viewPagerTab.getTabAt(i);
                view.setTextColor(getResources().getColor(R.color.wx_text_color));
            }

            TextView view = (TextView) viewPagerTab.getTabAt(position);
            view.setTextColor(getResources().getColor(R.color.wx_accent_color));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
