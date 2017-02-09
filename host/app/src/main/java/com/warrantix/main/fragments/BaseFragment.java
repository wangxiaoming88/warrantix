package com.warrantix.main.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warrantix.main.common.bus.BusProvider;

public class BaseFragment extends Fragment {

    protected Activity mActivity = null;
    protected ImageView categoryImageView;
    protected TextView titleText;
    protected RelativeLayout titleBackground;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;

        BusProvider.get().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;

        BusProvider.get().unregister(this);
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
//        if (mActivity != null) {
//            InputMethodManager im = (InputMethodManager) mActivity.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//            im.hideSoftInputFromWindow(mActivity.getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        }

        super.setMenuVisibility(visible);
    }
}