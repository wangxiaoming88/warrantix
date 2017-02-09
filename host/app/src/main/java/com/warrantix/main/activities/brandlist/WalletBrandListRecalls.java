package com.warrantix.main.activities.brandlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.WalletBrandListManualsList;
import com.warrantix.main.adapter.WalletBrandListRecallsList;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListRecalls extends BaseActivity {
    ListView list;

    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_recalls);
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

        String[] name = { "Hero Motorcorp", "Godrej", "LG", "Hero Motorcorp" };
        String[] name1 = { "SUB:Lorem lpsum is simply dummy text of the printing", "SUB:Lorem lpsum is simply dummy text of the printing", "SUB:Lorem lpsum is simply dummy text of the printing","SUB:Lorem lpsum is simply dummy text of the printing"};
        String[] name2 = { "Hero", "Godrej", "LG", "Hero" };
        String[] days = { "13-Feb-2016   9:30 AM", "03-Feb-2016   11:20 AM", "23-Jan-2016   11:20 AM", "13-Feb-2016   9:30 AM" };
        String[] imageID = { "brand_product_image1","brand_product_image2", "brand_product_image3","brand_product_image1"};
        WalletBrandListRecallsList adapter = new WalletBrandListRecallsList(this, name, name1, name2,days,imageID);
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
