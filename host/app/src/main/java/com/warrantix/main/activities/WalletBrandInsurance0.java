package com.warrantix.main.activities;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.warrantix.main.R;
import com.warrantix.main.adapter.WalletBrandInsuranceList;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandInsurance0 extends BaseActivity {
    private ListView list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_insurance0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            InitializeView();
            isInitialized = true;
        }
    }

    public void InitializeView(){

        list = (ListView) findViewById(R.id.list);
        list.setAdapter(null);

        String[] name = { "Tata AIG Insurance", "IFFCO TOKIO", "Relience General" ,"Tata AIG Insurance"};
        String[] place = { "Comprehensive, starts from RS.4,000 p.a", "Comprehensive, starts from RS.3,800 p.a", "Comprehensive, starts from RS.4,000 p.a","Comprehensive, starts from RS.4,000 p.a" };
        String[] imageID = { "brand_insurance_image1","brand_insurance_image2", "brand_insurance_image3", "brand_insurance_image1"};
        WalletBrandInsuranceList adapter = new WalletBrandInsuranceList(this, name, place, imageID);
        list.setAdapter(adapter);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }
}
