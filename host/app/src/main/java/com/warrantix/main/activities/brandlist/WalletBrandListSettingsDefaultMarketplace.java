package com.warrantix.main.activities.brandlist;

import android.content.Context;
import android.content.Intent;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warrantix.main.GlobalConfig;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.adapter.MarketPlaceGridAdapter;
import com.warrantix.main.common.bus.BusProvider;
import com.warrantix.main.common.event.PluginBackToScreenEvent;


/**
 * deprecated : Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListSettingsDefaultMarketplace extends BaseActivity implements View.OnClickListener{

    private TextView titleTEXT;
    private Button doneBTN;
    private GridView gridView;

    private int selectedIndex = 0;
    private View selectedView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings_defaultmarketplace);
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

        selectedIndex = GlobalConfig.getInstance().getDefaultPluginIndex();

        doneBTN = (Button) findViewById(R.id.doneBTN);
        doneBTN.setOnClickListener(this);
        titleTEXT = (TextView) findViewById(R.id.title);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

//        final int[] resourceIds = new int[]
//                {
//                        R.drawable.warrantix0, R.drawable.croma0, R.drawable.big_bazaar0,
//                        R.drawable.videocon0, R.drawable.samsung0, R.drawable.lg0,
//                        R.drawable.suzuki0, R.drawable.tata0, R.drawable.brand_cromax,
//                        R.drawable.reliance0, R.drawable.flipkart0, R.drawable.zopper0
//                };

        final int[] resourceIds = new int []
                {
                        R.drawable.tab_warrantix, R.drawable.tab_hero, R.drawable.tab_godrej,
                        R.drawable.tab_samsung, R.drawable.tab_forbles, R.drawable.tab_lg,
                        R.drawable.tab_mahindra, R.drawable.tab_micromax, R.drawable.tab_voltas,
                };
        MarketPlaceGridAdapter adapter = new MarketPlaceGridAdapter(this, resourceIds, selectedIndex);

        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int row = position / 3;
                int index = ((row-1)/ 2) * 3 + position % 3;
                Log.d("DefaultMarketplace", "OnItemClick is called. index = " + index);

                if (selectedView != null) {
                    selectedView.setBackgroundResource(0);
                }

                view.setBackgroundResource(R.drawable.default_selected_background);

                selectedIndex = index;
                selectedView = view;
            }
        });

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) gridView.getLayoutParams();
        layoutParams.height = 9 / 3 * (int) getResources().getDimension(R.dimen._90sdp) + (int) getResources().getDimension(R.dimen._50sdp);
        gridView.setLayoutParams(layoutParams);
    }

    public void setSelectedView(View selectedView) {
        this.selectedView = selectedView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.doneBTN){
            GlobalConfig.getInstance().setDefaultPluginIndex(selectedIndex);
            String toScreen = GlobalConfig.getInstance().getPluginName(selectedIndex);
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
