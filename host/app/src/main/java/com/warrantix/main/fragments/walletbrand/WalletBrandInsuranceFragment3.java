package com.warrantix.main.fragments.walletbrand;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.fragments.BaseFragment;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandInsuranceFragment3 extends BaseFragment implements View.OnClickListener {
    private Context mContext;
    private Intent mIntent;
    private View mView;

    private TextView conformplaytxt;
    private TextView accepttxt;
    private ImageView wallet_hero;

    private boolean isAccepted = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_brand_insurance3, container, false);
        InitializeView();
        return mView;
    }

    public void InitializeView(){
        conformplaytxt = (TextView) mView.findViewById(R.id.comformplay);
        accepttxt = (TextView) mView.findViewById(R.id.acceptTXT);
        accepttxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarrantixApplication.getInstance().openPDFFiles(getActivity());
            }
        });

        Button doneBTN = (Button) mView.findViewById(R.id.doneBTN);
        doneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof BaseActivity)
                    ((BaseActivity)mContext).finish(true);
                else
                    ((Activity)mContext).finish();
            }
        });

        final ImageView selectView = (ImageView) mView.findViewById(R.id.accept_select);
        selectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAccepted == false)
                    isAccepted = true;
                else
                    isAccepted = false;

                if (isAccepted == false)
                    selectView.setBackgroundResource(R.drawable.square);
                else
                    selectView.setBackgroundResource(R.drawable.square_tick);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
