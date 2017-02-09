package com.warrantix.main.activities.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.noveogroup.android.log.Log;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;

public class ProductDetailActivity extends BaseActivity {

    private RelativeLayout navigationActionBar = null;
    private ImageButton btnBack = null;

    private Button btnNext = null;
    private RelativeLayout btnScanProductName = null;
    private RelativeLayout btnScanSerialNumber = null;

    private EditText txtProductName = null;
    private EditText txtBrand = null;
    private EditText txtModelNumber = null;
    private EditText txtSerialNumber = null;

    private String scanCodeResult = "";
    private String scanCodeType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
            updateWithProductDetail();
            isInitialized = true;
        }
    }

    public void initialize() {
        navigationActionBar = (RelativeLayout) findViewById(R.id.registration_navigation_bar);

        btnBack = (ImageButton) findViewById(R.id.btnBackInNavigationBar);
        btnNext = (Button) findViewById(R.id.btnNextInProductDetail);
        btnScanProductName = (RelativeLayout) findViewById(R.id.btnScanProductInProductDetail);
        btnScanSerialNumber = (RelativeLayout) findViewById(R.id.btnScanSerialInProductDetail);

        btnBack.setOnClickListener(btnBackClickListener);
        btnNext.setOnClickListener(btnNextClickListener);
        btnScanProductName.setOnClickListener(btnScanProductNameClickListener);
        btnScanSerialNumber.setOnClickListener(btnScanSerialNumberClickListener);

        txtProductName = (EditText) findViewById(R.id.txtProductNameInProductDetail);
        txtBrand = (EditText) findViewById(R.id.txtBrandInProductDetail);
        txtModelNumber = (EditText) findViewById(R.id.txtModelNumberInProductDetail);
        txtSerialNumber = (EditText) findViewById(R.id.txtSerialNumberInProductDetail);
    }

    private void updateWithProductDetail()
    {
        scanCodeResult = getIntent().getStringExtra("SCANCODE_RESULT");
        scanCodeType = getIntent().getStringExtra("SCANCODE_TYPE");

        // debug message
        Log.i("Result:" + scanCodeResult + " Type:" + scanCodeType, Toast.LENGTH_SHORT);
    }

    private final View.OnClickListener btnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };

    private final View.OnClickListener btnNextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ProductDetailActivity.this, ProofPurchaseActivity.class);
            startActivity(i, true);
        }
    };

    private final View.OnClickListener btnScanProductNameClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };

    private final View.OnClickListener btnScanSerialNumberClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };
}