package com.warrantix.main.activities.brandlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.WalletBrandListManualsList;
import com.warrantix.main.adapter.WalletBrandListOrderList;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListManuals extends BaseActivity {
    ListView list;

    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_manuals);
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
        String[] name2 = { "", "Godrej", "", "LG" };
        String[] imageID = { "brand_product_image1","brand_product_image2", "brand_product_image3","brand_product_image1"};

        WalletBrandListManualsList adapter = new WalletBrandListManualsList(this, name, name1, name2,imageID);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WarrantixApplication.getInstance().openPDFFiles(WalletBrandListManuals.this);
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }
}
