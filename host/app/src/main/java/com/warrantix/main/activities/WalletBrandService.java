package com.warrantix.main.activities;

import android.graphics.Paint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.adapter.WalletBrandServiceList;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandService extends BaseActivity {
    private ListView list;
    private TextView dealersplaceTEXT;
    private TextView dealerschangeTEXT;
    private TextView title,explain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_service);
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
        dealersplaceTEXT = (TextView) findViewById(R.id.dealersplace);
        dealerschangeTEXT = (TextView) findViewById(R.id.dealerschange);
        title = (TextView) findViewById(R.id.title);
        explain = (TextView) findViewById(R.id.explain);

        dealersplaceTEXT.setPaintFlags(dealersplaceTEXT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG );
        dealerschangeTEXT.setPaintFlags(dealerschangeTEXT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(null);

        //String[] name = new String[3],name1 = new String[3],days = new String[3],imageID = new String[3];
        String[] name = { "Zero Motocycles", "Indian Motocycles", "CF Motor" };
        String[] distance = { "0.6", "0.8", "1.2" };
        String[] place = { "stadium corssroads", "shyamal corssroads", "stadium corssroads" };
        String[] place1 = { "navrangpura", "navrangpura", "navrangpura" };
        String[] imageID = { "wallet_products_products1","wallet_products_products2", "wallet_products_products2"};
        WalletBrandServiceList adapter = new WalletBrandServiceList(this, name,distance,place,place1,imageID);
        list.setAdapter(adapter);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_product_return);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }
}
