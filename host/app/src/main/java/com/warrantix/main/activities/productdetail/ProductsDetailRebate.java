package com.warrantix.main.activities.productdetail;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class ProductsDetailRebate extends BaseActivity {

    private TextView titleTXT;
    private TextView noteTXT;
    private TextView descriptionTXT;
    private TextView mallinrebateTXT;
    private TextView priceTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_product_rebate);
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
        titleTXT = (TextView) findViewById(R.id.title);
        noteTXT = (TextView) findViewById(R.id.note);
        descriptionTXT = (TextView) findViewById(R.id.featured_description);
        mallinrebateTXT = (TextView) findViewById(R.id.mallinrebateTXT);
        priceTXT = (TextView) findViewById(R.id.price);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        Button btnDone = (Button) findViewById(R.id.doneBTN);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

    }

}
