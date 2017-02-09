package com.warrantix.main.fragments.walletbrand;

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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.WalletBrandInsurance;
import com.warrantix.main.fragments.BaseFragment;

import it.sephiroth.android.library.tooltip.Tooltip;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandInsuranceFragment2 extends BaseFragment implements View.OnClickListener {
    private Context mContext;
    private Intent mIntent;
    private View mView;

    private TextView amounttxt;
    private TextView amountValuetxt;
    private TextView salientfeaturedtxt;

    private RadioButton creditcardRB;
    private RadioButton debitcardRB;
    private RadioButton netbankingRB;
    private RadioButton americanexpresscardRB;
    private RadioButton mobiquikwalletRB;
    private RadioButton payTMRB;

    private TextView entercarddetails;

    private EditText cardnumberTXT;
    private EditText numberoncardTXT;
    private EditText empiryTXT;
    private EditText CVVTXT;

    private Button nextBtn;
    private ImageView wallet_hero;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_brand_insurance2, container, false);

        InitializeView();
        return mView;
    }

    public void InitializeView(){
        amounttxt = (TextView) mView.findViewById(R.id.amountTXT);
        amountValuetxt = (TextView) mView.findViewById(R.id.amountValueTXT);
        salientfeaturedtxt = (TextView) mView.findViewById(R.id.salientfeatured);
        entercarddetails = (TextView) mView.findViewById(R.id.entercarddetails);

        creditcardRB = (RadioButton) mView.findViewById(R.id.mode_creditcard);
        debitcardRB = (RadioButton) mView.findViewById(R.id.mode_debitcard);
        netbankingRB = (RadioButton) mView.findViewById(R.id.mode_netbanking);
        americanexpresscardRB = (RadioButton) mView.findViewById(R.id.mode_americancard);
        mobiquikwalletRB = (RadioButton) mView.findViewById(R.id.mode_mobiquikwallet);
        payTMRB = (RadioButton) mView.findViewById(R.id.mode_paytmwallet);


        cardnumberTXT = (EditText) mView.findViewById(R.id.cardnumberTXT);
        numberoncardTXT = (EditText) mView.findViewById(R.id.numberoncardTXT);
        empiryTXT = (EditText) mView.findViewById(R.id.empiryTXT);
        CVVTXT = (EditText) mView.findViewById(R.id.CVVTXT);

        cardnumberTXT.setTextColor(getResources().getColor(R.color.wx_tag_color));
        numberoncardTXT.setTextColor(getResources().getColor(R.color.wx_tag_color));
        empiryTXT.setTextColor(getResources().getColor(R.color.wx_tag_color));
        CVVTXT.setTextColor(getResources().getColor(R.color.wx_tag_color));

        creditcardRB.setOnClickListener(this);
        debitcardRB.setOnClickListener(this);
        netbankingRB.setOnClickListener(this);
        americanexpresscardRB.setOnClickListener(this);
        mobiquikwalletRB.setOnClickListener(this);
        payTMRB.setOnClickListener(this);

        nextBtn = (Button) mView.findViewById(R.id.nextBTN);
        nextBtn.setOnClickListener(this);

        final ImageButton cvvInfoBTN = (ImageButton) mView.findViewById(R.id.cvvInfoBTN);
        cvvInfoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface font = Typeface.createFromAsset(mActivity.getAssets(), "fonts/Montserrat-Regular.ttf");
                Tooltip.make(mActivity,
                        new Tooltip.Builder(101)
                                .anchor(CVVTXT, Tooltip.Gravity.TOP)
                                .closePolicy(new Tooltip.ClosePolicy()
                                        .insidePolicy(true, false)
                                        .outsidePolicy(true, false), 2000)
                                .activateDelay(400)
                                .showDelay(300)
                                .text("This is dummy information")
                                .maxWidth(500)
                                .withArrow(true)
                                .withOverlay(true)
                                .typeface(font)
                                .withStyleId(R.style.ToolTipLayoutCustomStyle)
                                .floatingAnimation(Tooltip.AnimationBuilder.DEFAULT)
                                .build()
                ).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nextBTN){
            if ((mContext != null) && (mContext instanceof WalletBrandInsurance)) {
                ((WalletBrandInsurance)mContext).loadInsuranceFragment3();
            }
        }

    }


}
