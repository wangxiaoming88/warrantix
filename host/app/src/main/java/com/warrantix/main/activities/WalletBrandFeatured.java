package com.warrantix.main.activities;

import android.graphics.Paint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.warrantix.main.R;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandFeatured extends BaseActivity {

    private TextView readmoreTEXT;
    private TextView titleTEXT;
    private TextView priceTEXT;
    private TextView descriptionTEXT;
    private TextView featuredpriceTEXT;
    private TextView salientFeaturedTEXT;

    private TextView revealTEXT;
    private TextView reviewTEXT;
    private TextView referTEXT;
    private TextView rankTEXT;
    private TextView chatTEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_featured);
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
        readmoreTEXT = (TextView) findViewById(R.id.readmore);
        readmoreTEXT.setPaintFlags(readmoreTEXT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG );

        titleTEXT = (TextView) findViewById(R.id.title);
        salientFeaturedTEXT = (TextView) findViewById(R.id.salientfeatured);
        priceTEXT = (TextView) findViewById(R.id.price);
        featuredpriceTEXT = (TextView) findViewById(R.id.featured_price);
        descriptionTEXT = (TextView) findViewById(R.id.featured_description);

        revealTEXT = (TextView) findViewById(R.id.revealTXT);
        reviewTEXT = (TextView) findViewById(R.id.reviewTXT);
        referTEXT = (TextView) findViewById(R.id.referTXT);
        rankTEXT = (TextView) findViewById(R.id.rankTXT);
        chatTEXT = (TextView) findViewById(R.id.chatTXT);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }


}
