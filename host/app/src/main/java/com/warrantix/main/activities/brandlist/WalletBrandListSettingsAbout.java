package com.warrantix.main.activities.brandlist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.customview.NonSwipeableViewPager;
import com.warrantix.main.fragments.about.WarrantixAboutFragment1;
import com.warrantix.main.fragments.about.WarrantixAboutFragment2;
import com.warrantix.main.fragments.about.WarrantixAboutFragment3;
import com.warrantix.main.fragments.about.WarrantixAboutFragment4;
import com.warrantix.main.fragments.about.WarrantixAboutFragment5;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListSettingsAbout extends BaseActivity {

    private ViewPager contentPager = null;
    private final FragmentStatePagerAdapter contentPageAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new WarrantixAboutFragment1();
            else if (position == 1)
                return new WarrantixAboutFragment2();
            else if (position == 2)
                return new WarrantixAboutFragment3();
            else if (position == 3)
                return new WarrantixAboutFragment4();
            else if (position == 4)
                return new WarrantixAboutFragment5();

            return new Fragment();
        }

        @Override
        public int getCount() {
            return 5;
        }
    };

    int pageStep = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_settings_about);
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

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        contentPager = (ViewPager) findViewById(R.id.viewPager);
        contentPager.setAdapter(contentPageAdapter);
        contentPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageStep = position;
                updateImageBar();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        contentPager.setCurrentItem(0);

        Button prevButton = (Button) findViewById(R.id.prev);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageStep--;
                if (pageStep < 0)
                    pageStep = 0;

                contentPager.setCurrentItem(pageStep);
                updateImageBar();
            }
        });

        Button nextButton = (Button) findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageStep++;
                if (pageStep > 4)
                    pageStep = 4;

                contentPager.setCurrentItem(pageStep);
                updateImageBar();
            }
        });
    }

    private void updateImageBar() {
        ImageButton circle1 = (ImageButton) findViewById(R.id.circle1);
        ImageButton circle2 = (ImageButton) findViewById(R.id.circle2);
        ImageButton circle3 = (ImageButton) findViewById(R.id.circle3);
        ImageButton circle4 = (ImageButton) findViewById(R.id.circle4);
        ImageButton circle5 = (ImageButton) findViewById(R.id.circle5);

        ImageButton bar1 = (ImageButton) findViewById(R.id.bar1);
        ImageButton bar2 = (ImageButton) findViewById(R.id.bar2);
        ImageButton bar3 = (ImageButton) findViewById(R.id.bar3);
        ImageButton bar4 = (ImageButton) findViewById(R.id.bar4);

        switch (pageStep) {
            case 0:
                circle1.setBackgroundResource(R.drawable.transfer_circle_color);
                circle2.setBackgroundResource(R.drawable.transfer_circle_white);
                circle3.setBackgroundResource(R.drawable.transfer_circle_white);
                circle4.setBackgroundResource(R.drawable.transfer_circle_white);
                circle5.setBackgroundResource(R.drawable.transfer_circle_white);

                bar1.setBackgroundResource(R.drawable.transfer_retangle);
                bar2.setBackgroundResource(R.drawable.transfer_retangle);
                bar3.setBackgroundResource(R.drawable.transfer_retangle);
                bar4.setBackgroundResource(R.drawable.transfer_retangle);
                break;

            case 1:
                circle1.setBackgroundResource(R.drawable.transfer_circle_color);
                circle2.setBackgroundResource(R.drawable.transfer_circle_color);
                circle3.setBackgroundResource(R.drawable.transfer_circle_white);
                circle4.setBackgroundResource(R.drawable.transfer_circle_white);
                circle5.setBackgroundResource(R.drawable.transfer_circle_white);

                bar1.setBackgroundResource(R.drawable.transfer_retangle_color);
                bar2.setBackgroundResource(R.drawable.transfer_retangle);
                bar3.setBackgroundResource(R.drawable.transfer_retangle);
                bar4.setBackgroundResource(R.drawable.transfer_retangle);
                break;

            case 2:
                circle1.setBackgroundResource(R.drawable.transfer_circle_color);
                circle2.setBackgroundResource(R.drawable.transfer_circle_color);
                circle3.setBackgroundResource(R.drawable.transfer_circle_color);
                circle4.setBackgroundResource(R.drawable.transfer_circle_white);
                circle5.setBackgroundResource(R.drawable.transfer_circle_white);

                bar1.setBackgroundResource(R.drawable.transfer_retangle_color);
                bar2.setBackgroundResource(R.drawable.transfer_retangle_color);
                bar3.setBackgroundResource(R.drawable.transfer_retangle);
                bar4.setBackgroundResource(R.drawable.transfer_retangle);
                break;

            case 3:
                circle1.setBackgroundResource(R.drawable.transfer_circle_color);
                circle2.setBackgroundResource(R.drawable.transfer_circle_color);
                circle3.setBackgroundResource(R.drawable.transfer_circle_color);
                circle4.setBackgroundResource(R.drawable.transfer_circle_color);
                circle5.setBackgroundResource(R.drawable.transfer_circle_white);

                bar1.setBackgroundResource(R.drawable.transfer_retangle_color);
                bar2.setBackgroundResource(R.drawable.transfer_retangle_color);
                bar3.setBackgroundResource(R.drawable.transfer_retangle_color);
                bar4.setBackgroundResource(R.drawable.transfer_retangle);
                break;

            case 4:
                circle1.setBackgroundResource(R.drawable.transfer_circle_color);
                circle2.setBackgroundResource(R.drawable.transfer_circle_color);
                circle3.setBackgroundResource(R.drawable.transfer_circle_color);
                circle4.setBackgroundResource(R.drawable.transfer_circle_color);
                circle5.setBackgroundResource(R.drawable.transfer_circle_color);

                bar1.setBackgroundResource(R.drawable.transfer_retangle_color);
                bar2.setBackgroundResource(R.drawable.transfer_retangle_color);
                bar3.setBackgroundResource(R.drawable.transfer_retangle_color);
                bar4.setBackgroundResource(R.drawable.transfer_retangle_color);
                break;
        }
    }

}
