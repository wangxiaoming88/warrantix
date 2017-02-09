package com.warrantix.main.activities.brandlist;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.WalletBrandSettingsShoppingCartList;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListSettingsShoppingCart extends BaseActivity {

    private TextView titleTEXT;
    private TextView zeromotorText;
    private TextView youroderText;
    private TextView importantmessageText;
    private TextView lookupText;

    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings_shoppingcart);
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

        titleTEXT = (TextView) findViewById(R.id.title);
        zeromotorText = (TextView) findViewById(R.id.zeromotor_TEXT);
        youroderText = (TextView) findViewById(R.id.yourorder_TEXT);
        importantmessageText = (TextView) findViewById(R.id.important_message_TEXT);
        lookupText = (TextView) findViewById(R.id.lookup_TEXT);

        list = (RecyclerView) findViewById(R.id.list);
        list.setHasFixedSize(false);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(llm);

        String[] name1 = {  "Hunk","Karizma ZMR","Hunk" , "Karizma ZMR","Karizma ZMR","Hunk" , "Karizma ZMR","Karizma ZMR","Hunk" , "Karizma ZMR" };
        String[] name2 = { "Hero Motocop", "Hero Motocop", "Hero Motocop","Hero Motocop", "Hero Motocop", "Hero Motocop","Hero Motocop", "Hero Motocop", "Hero Motocop","Hero Motocop"};
        String[] price1 = { "Rs. 53,000", "Rs. 60,000", "Rs. 53,000", "Rs. 60,000", "Rs. 60,000", "Rs. 53,000", "Rs. 60,000", "Rs. 60,000", "Rs. 53,000", "Rs. 60,000" };
        String[] price2 = { "You saved Rs. 1,250", "You saved Rs. 3,250", "You saved Rs. 1,250", "You saved Rs. 3,250", "You saved Rs. 3,250", "You saved Rs. 1,250", "You saved Rs. 3,250"
                , "You saved Rs. 3,250", "You saved Rs. 1,250", "You saved Rs. 3,250"};
        String[] imageID = { "brand_product_image1","brand_product_image2", "brand_product_image3","brand_product_image1","brand_product_image2", "brand_product_image3","brand_product_image1"
                ,"brand_product_image2", "brand_product_image3","brand_product_image1"};

        WalletBrandSettingsShoppingCartList adapter = new WalletBrandSettingsShoppingCartList(this, name1, name2,price1,price2,imageID);
        list.setAdapter(adapter);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }


//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.insuranceBTN) {
//            WalletBrandInsurance.insuranceSel=1;
//            mIntent = new Intent(WalletBrandListSettingsShoppingCart.this, WalletBrandInsurance.class);
//            startActivity(mIntent);
//        }
//    }
}
