package com.warrantix.main.activities;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.warrantix.main.R;
import com.warrantix.main.adapter.WalletBrandFinanceCompanyList;
import com.warrantix.main.adapter.WalletBrandFinanceList;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandFinanceCompany extends BaseActivity {
    private ListView list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_financecompany);
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

        String[] name = { "Tata AIG Finance", "IFFCO TOKIO", "Relience General" ,"Tata AIG Finance"};
        String[] place = { "Comprehensive, starts from RS.4,000 p.a", "Comprehensive, starts from RS.3,800 p.a", "Comprehensive, starts from RS.4,000 p.a","Comprehensive, starts from RS.4,000 p.a" };
        String[] imageID = { "brand_insurance_image1","brand_insurance_image2", "brand_insurance_image3", "brand_insurance_image1"};
        WalletBrandFinanceCompanyList adapter = new WalletBrandFinanceCompanyList(this, name, place, imageID);
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
