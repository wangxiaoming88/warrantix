package com.warrantix.main.activities.brandlist;

import android.content.Context;
import android.content.Intent;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.adapter.SearchMarketPlaceGridAdapter;
import com.warrantix.main.common.bus.BusProvider;
import com.warrantix.main.common.event.PluginBackToScreenEvent;


/**
 * deprecated : Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListSettingsSearchMarketplace extends BaseActivity implements View.OnClickListener{

    private TextView titleTEXT;

    private Button doneBTN;
    private GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings_searchmarketplace);
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

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        final int[] resouceIds = new int[]
                {
                        R.drawable.warrantix0, R.drawable.croma0, R.drawable.big_bazaar0,
                        R.drawable.videocon0, R.drawable.samsung0, R.drawable.lg0,
                        R.drawable.suzuki0, R.drawable.tata0, R.drawable.brand_cromax,
                        R.drawable.reliance0, R.drawable.flipkart0, R.drawable.zopper0
                };
        gridView = (GridView) findViewById(R.id.gridView1);
        SearchMarketPlaceGridAdapter adapter = new SearchMarketPlaceGridAdapter(this, resouceIds);
        gridView.setAdapter(adapter);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) gridView.getLayoutParams();
        layoutParams.height = 12 / 3 * (int) getResources().getDimension(R.dimen._50sdp) + (int) getResources().getDimension(R.dimen._50sdp);
        gridView.setLayoutParams(layoutParams);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.doneBTN){
            String toScreen = "Warrantix";
            PluginBackToScreenEvent event = new PluginBackToScreenEvent(toScreen);
            BusProvider.get().post(event);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent, true, true);
        }
    }

    public static int convertDpToPixels(float dp, Context context){
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, resources.getDisplayMetrics()
        );
    }
}
