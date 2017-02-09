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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.WalletBrandAMC;
import com.warrantix.main.activities.WalletBrandInsurance;
import com.warrantix.main.adapter.TokenListAdapter;
import com.warrantix.main.customview.TokenTextView;
import com.warrantix.main.fragments.BaseFragment;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandAMCFragment1 extends BaseFragment implements View.OnClickListener  {
    private Context mContext;
    private View mView;
    private TextView nametxt;
    private TextView name_explaintxt;
    private TextView tata_explaintxt;
    private TextView premiumtxt;
    private TextView rstxt;
    private TextView purchasetxt;

    private TokenTextView insurance_type;
    private TokenTextView vendor;
    private TokenTextView deal;
    private TokenTextView duration;

    private Button nextBTN;

    private ListView list = null;
    private Intent mIntent;

    private ListView amcTypeView = null;
    private ListView serviceProviderView = null;
    private ListView dealView = null;
    private ListView durationView = null;

    ImageButton amcTypeBTN;
    ImageButton serviceProviderBTN;
    ImageButton dealBTN;
    ImageButton durationBTN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_brand_amc1, container, false);
        InitializeView();
        return mView;
    }

    public void InitializeView(){
        nametxt = (TextView) mView.findViewById(R.id.nameTXT);
        name_explaintxt = (TextView) mView.findViewById(R.id.name_explainTXT);
        tata_explaintxt = (TextView) mView.findViewById(R.id.tata_explainTXT);
        premiumtxt = (TextView) mView.findViewById(R.id.premiumTXT);
        rstxt = (TextView) mView.findViewById(R.id.RS_VALUETXT);
        purchasetxt = (TextView) mView.findViewById(R.id.Purchase_ProtectionTXT);

        insurance_type = (TokenTextView) mView.findViewById(R.id.Insurance_Type);
        vendor = (TokenTextView) mView.findViewById(R.id.Vendor);
        deal = (TokenTextView) mView.findViewById(R.id.Deal);
        duration = (TokenTextView) mView.findViewById(R.id.Duration);

        nextBTN = (Button) mView.findViewById(R.id.nextBTN);
        nextBTN.setOnClickListener(this);

        amcTypeView = (ListView) mView.findViewById(R.id.amcTypeView);
        serviceProviderView = (ListView) mView.findViewById(R.id.serviceProviderView);
        dealView = (ListView) mView.findViewById(R.id.dealTypeView);
        durationView = (ListView) mView.findViewById(R.id.durationTypeView);

        String amcs[] = {"AMC 1", "AMC 2", "AMC 3"};
        TokenListAdapter insuranceAdapter = new TokenListAdapter((Activity)mContext, amcs);
        insurance_type.setTokenListView(amcTypeView, insuranceAdapter);

        String serviceProviders[] = {"Provider 1", "Provider 2", "Provider 3"};
        TokenListAdapter providerAdapter = new TokenListAdapter((Activity)mContext, serviceProviders);
        vendor.setTokenListView(serviceProviderView, providerAdapter);

        String deals[] = {"Deal 1", "Deal 2", "Deal 3"};
        TokenListAdapter dealAdapter = new TokenListAdapter((Activity)mContext, deals);
        deal.setTokenListView(dealView, dealAdapter);

        String durations[] = {"Duration 1", "Duration 2", "Duration 3"};
        final TokenListAdapter durationAdapter = new TokenListAdapter((Activity)mContext, durations);
        duration.setTokenListView(durationView, durationAdapter);

        amcTypeBTN = (ImageButton) mView.findViewById(R.id.amcTypeBTN);
        amcTypeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amcTypeView.getVisibility() == View.GONE) {
                    hideAllListView();

                    amcTypeView.setVisibility(View.VISIBLE);
                    amcTypeBTN.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    amcTypeView.setVisibility(View.GONE);
                    amcTypeBTN.setBackgroundResource(R.drawable.check);
                }
            }
        });

        serviceProviderBTN = (ImageButton) mView.findViewById(R.id.serviceProviderBTN);
        serviceProviderBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serviceProviderView.getVisibility() == View.GONE) {
                    hideAllListView();

                    serviceProviderView.setVisibility(View.VISIBLE);
                    serviceProviderBTN.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    serviceProviderView.setVisibility(View.GONE);
                    serviceProviderBTN.setBackgroundResource(R.drawable.check);
                }
            }
        });

        dealBTN = (ImageButton) mView.findViewById(R.id.dealTypeBTN);
        dealBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dealView.getVisibility() == View.GONE) {
                    hideAllListView();

                    dealView.setVisibility(View.VISIBLE);
                    dealBTN.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    dealView.setVisibility(View.GONE);
                    dealBTN.setBackgroundResource(R.drawable.check);
                }
            }
        });

        durationBTN = (ImageButton) mView.findViewById(R.id.durationTypeBTN);
        durationBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (durationView.getVisibility() == View.GONE) {
                    hideAllListView();

                    durationView.setVisibility(View.VISIBLE);
                    durationBTN.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    durationView.setVisibility(View.GONE);
                    durationBTN.setBackgroundResource(R.drawable.check);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nextBTN){
            if ((mContext != null) && (mContext instanceof WalletBrandAMC)) {
                ((WalletBrandAMC)mContext).loadAMCFragment2();
            }
        }
    }

    private void hideAllListView() {
        amcTypeView.setVisibility(View.GONE);
        serviceProviderView.setVisibility(View.GONE);
        dealView.setVisibility(View.GONE);
        durationView.setVisibility(View.GONE);

        amcTypeBTN.setBackgroundResource(R.drawable.check);
        serviceProviderBTN.setBackgroundResource(R.drawable.check);
        dealBTN.setBackgroundResource(R.drawable.check);
        durationBTN.setBackgroundResource(R.drawable.check);
    }
}
