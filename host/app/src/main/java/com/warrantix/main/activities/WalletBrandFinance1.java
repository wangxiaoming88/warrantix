package com.warrantix.main.activities;

import android.content.Intent;
import android.graphics.Paint;

import android.media.session.MediaSession;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.adapter.RecipientListAdapter;
import com.warrantix.main.adapter.TokenListAdapter;
import com.warrantix.main.customview.TokenTextView;


/**
 *
 */
public class WalletBrandFinance1 extends BaseActivity {
    private Intent mIntent;
    private Button nextBtn;

    private TokenTextView selectBrandTXT;
    private TokenTextView selectConfigurationTXT;
    private TokenTextView selectLocationTXT;

    private ListView brandListView;
    private ListView configurationListView;
    private ListView locationListView;

    ImageButton btnDrop1;
    ImageButton btnDrop2;
    ImageButton btnDrop3;

    private EditText otherLocationEdit;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_finance1);
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

        title = getIntent().getStringExtra("title");
        TextView titleView = (TextView) findViewById(R.id.title);
        if (title != null)
            titleView.setText(title);

        nextBtn = (Button) findViewById(R.id.nextBTN);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(WalletBrandFinance1.this, WalletBrandFinance2.class);
                mIntent.putExtra("title", title);
                startActivity(mIntent, true);
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        selectBrandTXT = (TokenTextView) findViewById(R.id.selectBrandTXT);
        selectConfigurationTXT = (TokenTextView) findViewById(R.id.selectConfigurationTXT);
        selectLocationTXT = (TokenTextView) findViewById(R.id.selectLocationTXT);

        brandListView = (ListView) findViewById(R.id.brandListView);
        configurationListView = (ListView) findViewById(R.id.configurationListView);
        locationListView = (ListView) findViewById(R.id.locationListView);

        btnDrop1 = (ImageButton) findViewById(R.id.dropbutton1);
        btnDrop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (brandListView.getVisibility() == View.GONE) {
                    hideAllListView();

                    brandListView.setVisibility(View.VISIBLE);
                    btnDrop1.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    brandListView.setVisibility(View.GONE);
                    btnDrop1.setBackgroundResource(R.drawable.check);
                }
            }
        });

        btnDrop2 = (ImageButton) findViewById(R.id.dropbutton2);
        btnDrop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (configurationListView.getVisibility() == View.GONE) {
                    hideAllListView();

                    configurationListView.setVisibility(View.VISIBLE);
                    btnDrop2.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    configurationListView.setVisibility(View.GONE);
                    btnDrop2.setBackgroundResource(R.drawable.check);
                }
            }
        });

        btnDrop3 = (ImageButton) findViewById(R.id.dropbutton3);
        btnDrop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationListView.getVisibility() == View.GONE) {
                    hideAllListView();

                    locationListView.setVisibility(View.VISIBLE);
                    btnDrop2.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    locationListView.setVisibility(View.GONE);
                    btnDrop2.setBackgroundResource(R.drawable.check);
                }
            }
        });

        String brands[] = {"brand 1", "brand 2", "brand 3"};
        TokenListAdapter productAdapter = new TokenListAdapter(WalletBrandFinance1.this, brands);
        selectBrandTXT.setTokenListView(brandListView, productAdapter);

        String configurations[] = {"configuration 1", "configuration 2", "configuration 3"};
        TokenListAdapter configurationAdapter = new TokenListAdapter(WalletBrandFinance1.this, configurations);
        selectConfigurationTXT.setTokenListView(configurationListView, configurationAdapter);

        String locations[] = {"location 1", "location 2", "location 3"};
        TokenListAdapter locationAdapter = new TokenListAdapter(WalletBrandFinance1.this, locations);
        selectLocationTXT.setTokenListView(locationListView, locationAdapter);
    }

    private void hideAllListView() {
        locationListView.setVisibility(View.GONE);
        configurationListView.setVisibility(View.GONE);
        brandListView.setVisibility(View.GONE);

        btnDrop1.setBackgroundResource(R.drawable.check);
        btnDrop2.setBackgroundResource(R.drawable.check);
        btnDrop3.setBackgroundResource(R.drawable.check);
    }
}
