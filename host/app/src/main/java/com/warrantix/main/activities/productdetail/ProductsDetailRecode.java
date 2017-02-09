package com.warrantix.main.activities.productdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.WalletProductsDetailRecodeList;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class ProductsDetailRecode extends BaseActivity {
    ListView list;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_product_recode);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    public void Initialize(){

        list=(ListView)findViewById(R.id.list);
        list.setAdapter(null);

        String[] name = { "Karizma ZMR", "Washing Machine", "gnitor","Karizma ZMR" };
        String[] name1 = { "Hero Motocop", "WT 650 CF,Godrej", "Hero Motocop","Hero Motocop"};
        String[] days = { "On the way", "Delivered Wednesday", "Delivered 13-Feb-2016", "On the way" };
        String[] imageID = { "brand_product_image1","brand_product_image2", "brand_product_image3","brand_product_image1"};

        WalletProductsDetailRecodeList adapter = new WalletProductsDetailRecodeList(this, name, name1, days,imageID);
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
