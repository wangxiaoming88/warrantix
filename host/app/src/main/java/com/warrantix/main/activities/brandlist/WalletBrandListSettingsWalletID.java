package com.warrantix.main.activities.brandlist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListSettingsWalletID extends BaseActivity {

    private TextView titleTEXT;
    private TextView walletIDTEXT;
    private TextView FamilyMember1;
    private TextView FamilyMember1TEXT;
    private TextView FamilyMember2;
    private TextView FamilyMember2TEXT;
    private TextView CustomerID1;
    private TextView CustomerID1TEXT;
    private TextView CustomerID2;
    private TextView CustomerID2TEXT;
    private TextView MobilePhone1_1;
    private TextView MobilePhone1_2;
    private TextView MobilePhone2_1;
    private TextView MobilePhone2_2;
    private TextView Tablet;
    private TextView MobilePhone1_1TEXT;
    private TextView MobilePhone1_2TEXT;
    private TextView MobilePhone2_1TEXT;
    private TextView MobilePhone2_2TEXT;
    private TextView TabletTEXT;

    private EditText txtCustomerID;
    private int keyDel;
    private String strWalletID;

    private boolean isCheck1 = false;
    private boolean isCheck2 = false;
    private boolean isTablet = false;
    private boolean isCheck12 = false;
    private boolean isCheck22 = false;

    private RelativeLayout btnSetting1;
    private RelativeLayout btnSetting2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings_walletid);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            initializeCheckViews();
            isInitialized = true;
        }
    }

    private void initializeCheckViews() {
        final ImageView mobilephone1_check = (ImageView) findViewById(R.id.mobilephone1_check);
        final ImageView mobilephone2_check = (ImageView) findViewById(R.id.mobilephone2_check);
        final ImageView tablet_check = (ImageView) findViewById(R.id.tablet_check);
        final ImageView mobilephone1_check2 = (ImageView) findViewById(R.id.mobilephone1_check2);
        final ImageView mobilephone2_check2 = (ImageView) findViewById(R.id.mobilephone2_check2);

        mobilephone1_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheck1 == false)
                    isCheck1 = true;
                else
                    isCheck1 = false;

                if (isCheck1 == false)
                    mobilephone1_check.setBackgroundResource(R.drawable.square);
                else
                    mobilephone1_check.setBackgroundResource(R.drawable.square_tick);
            }
        });

        mobilephone2_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheck2 == false)
                    isCheck2 = true;
                else
                    isCheck2 = false;

                if (isCheck2 == false)
                    mobilephone2_check.setBackgroundResource(R.drawable.square);
                else
                    mobilephone2_check.setBackgroundResource(R.drawable.square_tick);
            }
        });

        tablet_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTablet == false)
                    isTablet = true;
                else
                    isTablet = false;

                if (isTablet == false)
                    tablet_check.setBackgroundResource(R.drawable.square);
                else
                    tablet_check.setBackgroundResource(R.drawable.square_tick);
            }
        });

        mobilephone1_check2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheck12 == false)
                    isCheck12 = true;
                else
                    isCheck12 = false;

                if (isCheck12 == false)
                    mobilephone1_check2.setBackgroundResource(R.drawable.square);
                else
                    mobilephone1_check2.setBackgroundResource(R.drawable.square_tick);
            }
        });

        mobilephone2_check2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheck22 == false)
                    isCheck22 = true;
                else
                    isCheck22 = false;

                if (isCheck22 == false)
                    mobilephone2_check2.setBackgroundResource(R.drawable.square);
                else
                    mobilephone2_check2.setBackgroundResource(R.drawable.square_tick);
            }
        });

    }

    public void Initialize(){

        titleTEXT = (TextView) findViewById(R.id.title);
        walletIDTEXT = (TextView) findViewById(R.id.walletID);
        FamilyMember1 = (TextView) findViewById(R.id.familymember1);
        FamilyMember1TEXT = (TextView) findViewById(R.id.familymember1_txt);
        FamilyMember2 = (TextView) findViewById(R.id.familymember2);
        FamilyMember2TEXT = (TextView) findViewById(R.id.familymember2_txt);
        CustomerID1 = (TextView) findViewById(R.id.customerid1);
        CustomerID1TEXT = (TextView) findViewById(R.id.customerid1_TXT);
        CustomerID2 = (TextView) findViewById(R.id.customerid2);
        CustomerID2TEXT = (TextView) findViewById(R.id.customerid2_TXT);
        MobilePhone1_1 = (TextView) findViewById(R.id.mobilephone1);
        MobilePhone1_2 = (TextView) findViewById(R.id.mobilephone1_2);
        MobilePhone2_1 = (TextView) findViewById(R.id.mobilephone2);
        MobilePhone2_2 = (TextView) findViewById(R.id.mobilephone2_2);
        Tablet = (TextView) findViewById(R.id.tablet);
        MobilePhone1_1TEXT = (TextView) findViewById(R.id.mobilephone1_number);
        MobilePhone1_2TEXT = (TextView) findViewById(R.id.mobilephone1_number2);
        MobilePhone2_1TEXT = (TextView) findViewById(R.id.mobilephone2_number);
        MobilePhone2_2TEXT = (TextView) findViewById(R.id.mobilephone2_number2);
        TabletTEXT = (TextView) findViewById(R.id.tablet_number);

        txtCustomerID = (EditText) findViewById(R.id.customerID_edit);
        txtCustomerID.addTextChangedListener(walletWatcher);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        Button addCustomerIDBTN = (Button) findViewById(R.id.addCustomerIDBTN);
        addCustomerIDBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletBrandListSettingsWalletID.this, WalletBrandListSettingsAddCustomerID.class);
                startActivity(i, true);
            }
        });

        btnSetting1 = (RelativeLayout) findViewById(R.id.btnSetting1);
        btnSetting1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletBrandListSettingsWalletID.this, WalletBrandListSettingsAddDeviceID.class);
                startActivity(i, true);
            }
        });

        btnSetting2 = (RelativeLayout) findViewById(R.id.btnSetting2);
        btnSetting2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletBrandListSettingsWalletID.this, WalletBrandListSettingsAddDeviceID.class);
                startActivity(i, true);
            }
        });

    }

    private final TextWatcher walletWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            boolean flag = true;
            String eachBlock[] = txtCustomerID.getText().toString().split("-");
            for (int i = 0; i < eachBlock.length; i++) {
                if (eachBlock[i].length() > 4) {
                    flag = false;
                }
            }

            if (flag) {
                txtCustomerID.setOnKeyListener(new View.OnKeyListener() {

                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                        if (keyCode == KeyEvent.KEYCODE_DEL)
                            keyDel = 1;
                        return false;
                    }
                });

                if (keyDel == 0) {
                    if (((txtCustomerID.getText().length() + 1) % 5) == 0) {

                        if (txtCustomerID.getText().toString().split("-").length <= 3) {
                            txtCustomerID.setText(txtCustomerID.getText() + "-");
                            txtCustomerID.setSelection(txtCustomerID.getText().length());
                        }
                    }
                    strWalletID = txtCustomerID.getText().toString();
                } else {
                    strWalletID = txtCustomerID.getText().toString();
                    keyDel = 0;
                }

            } else {
                txtCustomerID.setText(strWalletID);
            }

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

}
