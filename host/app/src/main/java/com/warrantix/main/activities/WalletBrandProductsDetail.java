package com.warrantix.main.activities;

import android.content.Intent;
import android.graphics.Paint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.productdetail.ProductDetailResell;
import com.warrantix.main.activities.productdetail.ProductsDetailRebate;
import com.warrantix.main.activities.productdetail.ProductsDetailRecode;
import com.warrantix.main.activities.productdetail.ProductsDetailRecycle;
import com.warrantix.main.activities.productdetail.ProductsDetailReturn;
import com.warrantix.main.activities.productdetail.ProductsDetailScheduleService;
import com.warrantix.main.activities.productdetail.ProductsDetailServiceContacts;

public class WalletBrandProductsDetail extends BaseActivity {
    private TextView viewmanualTXT;
    private TextView viewinvoiceTXT;
    private TextView extendTXT;
    private TextView entitlementTXT;
    private TextView entend1TXT;
    private TextView priceTXT;

    private TextView nametxt;
    private TextView purchasetxt;
    private TextView warrantytxt;
    private TextView producttxt;

    private TextView modeltxt;
    private TextView modelvaluetxt;
    private TextView purchasedatetxt;
    private TextView expirydatetxt;
    private TextView emitxt;
    private TextView remaindaytxt;
    private TextView insurancetxt;
    private TextView insurancetilltxt;
    private TextView paymenttxt;
    private TextView providertxt;

    private ImageButton ScheduleServiceBTN;
    private ImageButton service_contanctsBTN;
    private LinearLayout recodeBTN;
    private LinearLayout recycleBTN;
    private LinearLayout rebateBTN;
    private LinearLayout returnBTN;
    private LinearLayout resellBTN;
    private ImageButton dialBTN;

    private LinearLayout product_service1_layout;
    private View maskView;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_products_detail);
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
        maskView = (View) findViewById(R.id.maskView);
        product_service1_layout = (LinearLayout) findViewById(R.id.product_service1_layout);

        ImageView productImageView = (ImageView) findViewById(R.id.productImageView);
        productImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product_service1_layout.getVisibility() == View.GONE)
                    product_service1_layout.setVisibility(View.VISIBLE);

                maskView.setVisibility(View.VISIBLE);
                maskView.setOnClickListener(maskViewClickListener);
            }
        });

        viewmanualTXT = (TextView) findViewById(R.id.viewmanual);
        viewmanualTXT.setPaintFlags(viewmanualTXT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        viewmanualTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarrantixApplication.getInstance().openPDFFiles(WalletBrandProductsDetail.this);
            }
        });

        viewinvoiceTXT = (TextView) findViewById(R.id.viewinvoice);
        viewinvoiceTXT.setPaintFlags(viewinvoiceTXT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        viewinvoiceTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarrantixApplication.getInstance().openPDFFiles(WalletBrandProductsDetail.this);
            }
        });

        extendTXT = (TextView) findViewById(R.id.extend);
        extendTXT.setPaintFlags(extendTXT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        extendTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletBrandProductsDetail.this, WalletBrandAMC0.class);
                startActivity(i, true);
            }
        });

        entitlementTXT = (TextView) findViewById(R.id.entitlements);
        entitlementTXT.setPaintFlags(entitlementTXT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        entitlementTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarrantixApplication.getInstance().openPDFFiles(WalletBrandProductsDetail.this);
            }
        });

        entend1TXT = (TextView) findViewById(R.id.extenddown);
        entend1TXT.setPaintFlags(entend1TXT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        entend1TXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletBrandProductsDetail.this, WalletBrandInsurance0.class);
                startActivity(i, true);
            }
        });

        priceTXT = (TextView) findViewById(R.id.product_price);
        priceTXT.setText(priceTXT.getText() + " 12,000");

        nametxt = (TextView) findViewById(R.id.nameTXT);
        purchasetxt = (TextView) findViewById(R.id.purchseTXT);
        warrantytxt = (TextView) findViewById(R.id.warrantTXT);
        producttxt = (TextView) findViewById(R.id.product_insuranceTXT);

        modeltxt = (TextView) findViewById(R.id.modelTXT);
        modelvaluetxt = (TextView) findViewById(R.id.modelvalueTXT);
        purchasedatetxt = (TextView) findViewById(R.id.purchasedateTXT);
        expirydatetxt = (TextView) findViewById(R.id.expirydateTXT);
        emitxt = (TextView) findViewById(R.id.emiTXT);
        remaindaytxt = (TextView) findViewById(R.id.remaindayTXT);
        insurancetxt = (TextView) findViewById(R.id.insuranceTXT);
        insurancetilltxt = (TextView) findViewById(R.id.insurancetillTXT);
        paymenttxt = (TextView) findViewById(R.id.paymentTXT);
        providertxt = (TextView) findViewById(R.id.providerTXT);


        ScheduleServiceBTN = (ImageButton) findViewById(R.id.schedule_seviceBTN);
        ScheduleServiceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(WalletBrandProductsDetail.this, ProductsDetailScheduleService.class);
                startActivity(mIntent, true);
            }
        });

        service_contanctsBTN = (ImageButton) findViewById(R.id.service_ContactsBTN);
        service_contanctsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(WalletBrandProductsDetail.this, ProductsDetailServiceContacts.class);
                startActivity(mIntent, true);
            }
        });

        recodeBTN = (LinearLayout) findViewById(R.id.recordBTN);
        recodeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(WalletBrandProductsDetail.this, ProductsDetailRecode.class);
                startActivity(mIntent, true);
            }
        });

        recycleBTN = (LinearLayout) findViewById(R.id.recycleBTN);
        recycleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(WalletBrandProductsDetail.this, ProductsDetailRecycle.class);
                startActivity(mIntent, true);
            }
        });

        rebateBTN = (LinearLayout) findViewById(R.id.rebateBTN);
        rebateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(WalletBrandProductsDetail.this, ProductsDetailRebate.class);
                startActivity(mIntent, true);
            }
        });

        returnBTN = (LinearLayout) findViewById(R.id.returnBTN);
        returnBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(WalletBrandProductsDetail.this, ProductsDetailReturn.class);
                startActivity(mIntent, true);
            }
        });

        resellBTN = (LinearLayout) findViewById(R.id.resellBTN);
        resellBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(WalletBrandProductsDetail.this, ProductDetailResell.class);
                startActivity(mIntent, true);
            }
        });

        ImageButton dialBTN = (ImageButton) findViewById(R.id.dialBTN);
        dialBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarrantixApplication.getInstance().showDial(WalletBrandProductsDetail.this);
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_product_return);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        initSocialListeners();
    }

    private final View.OnClickListener maskViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideSocial1Bar();
        }
    };

    private void hideSocial1Bar() {
        product_service1_layout.setVisibility(View.GONE);
        maskView.setVisibility(View.GONE);
    }

    private void initSocialListeners() {
        LinearLayout social1Layout = (LinearLayout) findViewById(R.id.product_service1_layout);
        LinearLayout revealButton = (LinearLayout) social1Layout.findViewById(R.id.revealBTN);
        revealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletBrandProductsDetail.this, WalletBrandSocial.class);
                intent.putExtra("selected", 0);
                startActivity(intent, true);
            }
        });

        LinearLayout referButton = (LinearLayout) social1Layout.findViewById(R.id.referBTN);
        referButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletBrandProductsDetail.this, WalletBrandSocial.class);
                intent.putExtra("selected", 1);
                startActivity(intent, true);
            }
        });

        LinearLayout reviewButton = (LinearLayout) social1Layout.findViewById(R.id.reviewBTN);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletBrandProductsDetail.this, WalletBrandSocial.class);
                intent.putExtra("selected", 2);
                startActivity(intent, true);
            }
        });

        LinearLayout rankButton = (LinearLayout) social1Layout.findViewById(R.id.rankBTN);
        rankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletBrandProductsDetail.this, WalletBrandSocial.class);
                intent.putExtra("selected", 3);
                startActivity(intent, true);
            }
        });

        LinearLayout chatButton = (LinearLayout) social1Layout.findViewById(R.id.chatBTN);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSocial1Bar();

                Intent intent = new Intent(WalletBrandProductsDetail.this, WalletBrandSocial.class);
                intent.putExtra("selected", 4);
                startActivity(intent, true);
            }
        });

    }

}
