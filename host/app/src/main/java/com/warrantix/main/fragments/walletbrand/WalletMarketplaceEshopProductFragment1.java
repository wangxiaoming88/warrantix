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
import com.warrantix.main.activities.WalletMarketplaceEshopProducts;
import com.warrantix.main.adapter.TokenListAdapter;
import com.warrantix.main.customview.TokenTextView;
import com.warrantix.main.fragments.BaseFragment;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletMarketplaceEshopProductFragment1 extends BaseFragment implements View.OnClickListener  {
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

    private ListView insuranceTypeView = null;
    private ListView vendorView = null;
    private ListView dealView = null;
    private ListView durationView = null;

    ImageButton insuranceDropButton;
    ImageButton vendorDropButton;
    ImageButton dealDropbutton;
    ImageButton durationDropButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_brand_product1, container, false);
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
        purchasetxt.setText("Purchase Protection - this coverage foes beyond the limitation of manufacturer's warranty defects and protects purchases from loss due to the end accidental damage");

        insurance_type = (TokenTextView) mView.findViewById(R.id.Insurance_Type);
        vendor = (TokenTextView) mView.findViewById(R.id.Vendor);
        deal = (TokenTextView) mView.findViewById(R.id.Deal);
        duration = (TokenTextView) mView.findViewById(R.id.Duration);

        nextBTN = (Button) mView.findViewById(R.id.nextBTN);
        nextBTN.setOnClickListener(this);

        insuranceTypeView = (ListView) mView.findViewById(R.id.insuranceTypeView);
        vendorView = (ListView) mView.findViewById(R.id.vendorView);
        dealView = (ListView) mView.findViewById(R.id.dealView);
        durationView = (ListView) mView.findViewById(R.id.durationView);

        insuranceDropButton = (ImageButton) mView.findViewById(R.id.insuranceDropBTN);
        insuranceDropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insuranceTypeView.getVisibility() == View.GONE) {
                    hideAllListView();

                    insuranceTypeView.setVisibility(View.VISIBLE);
                    insuranceDropButton.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    insuranceTypeView.setVisibility(View.GONE);
                    insuranceDropButton.setBackgroundResource(R.drawable.check);
                }
            }
        });

        vendorDropButton = (ImageButton) mView.findViewById(R.id.vendorDropBTN);
        vendorDropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vendorView.getVisibility() == View.GONE) {
                    hideAllListView();

                    vendorView.setVisibility(View.VISIBLE);
                    vendorDropButton.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    vendorView.setVisibility(View.GONE);
                    vendorDropButton.setBackgroundResource(R.drawable.check);
                }
            }
        });

        dealDropbutton = (ImageButton) mView.findViewById(R.id.dealDropBTN);
        dealDropbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dealView.getVisibility() == View.GONE) {
                    hideAllListView();

                    dealView.setVisibility(View.VISIBLE);
                    dealDropbutton.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    dealView.setVisibility(View.GONE);
                    dealDropbutton.setBackgroundResource(R.drawable.check);
                }
            }
        });

        durationDropButton = (ImageButton) mView.findViewById(R.id.durationDropBTN);
        durationDropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (durationView.getVisibility() == View.GONE) {
                    hideAllListView();

                    durationView.setVisibility(View.VISIBLE);
                    durationDropButton.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    durationView.setVisibility(View.GONE);
                    durationDropButton.setBackgroundResource(R.drawable.check);
                }
            }
        });

        String insurance[] = {"Insurance 1", "Insurance 2", "Insurance 3"};
        TokenListAdapter insuranceAdapter = new TokenListAdapter((Activity)mContext, insurance);
        this.insurance_type.setTokenListView(insuranceTypeView, insuranceAdapter);

        String vendor[] = {"Vendor 1", "Vendor 2", "Vendor 3"};
        TokenListAdapter vendorAdapter = new TokenListAdapter((Activity)mContext, vendor);
        this.vendor.setTokenListView(vendorView, vendorAdapter);

        String deals[] = {"Deal 1", "Deal 2", "Deal 3"};
        TokenListAdapter dealAdapter = new TokenListAdapter((Activity)mContext, deals);
        this.deal.setTokenListView(dealView, dealAdapter);

        String durations[] = {"Duration 1", "Duration 2", "Duration 3"};
        TokenListAdapter durationAdapter = new TokenListAdapter((Activity)mContext, durations);
        this.duration.setTokenListView(durationView, durationAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nextBTN){
            if ((mContext != null) && (mContext instanceof WalletMarketplaceEshopProducts)) {
                ((WalletMarketplaceEshopProducts)mContext).loadProduct2Fragment();
            }
        }
    }

    private void hideAllListView() {
        insuranceTypeView.setVisibility(View.GONE);
        vendorView.setVisibility(View.GONE);
        dealView.setVisibility(View.GONE);
        durationView.setVisibility(View.GONE);

        insuranceDropButton.setBackgroundResource(R.drawable.check);
        vendorDropButton.setBackgroundResource(R.drawable.check);
        dealDropbutton.setBackgroundResource(R.drawable.check);
        durationDropButton.setBackgroundResource(R.drawable.check);
    }
}
