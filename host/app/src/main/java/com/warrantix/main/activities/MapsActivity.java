package com.warrantix.main.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.warrantix.main.R;

public class MapsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        initialize();
    }

    private void initialize() {

    }

}