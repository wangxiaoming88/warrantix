package com.warrantix.main.activities.registration;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.dialog.MessageDialog;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrationHelpActivity extends BaseActivity {

    private RelativeLayout navigationActionBar = null;
    private ImageButton btnBack = null;
    private ImageButton btnClose = null;
    private Button btnScanner = null;
    private Button btnManual = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_help);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
            isInitialized = true;
        }
    }

    public void initialize() {
        navigationActionBar = (RelativeLayout) findViewById(R.id.registration_navigation_bar);
        btnBack = (ImageButton) findViewById(R.id.btnBackInNavigationBar);
        btnClose = (ImageButton) findViewById(R.id.btnCloseInRegistrationHelp);
        btnScanner = (Button) findViewById(R.id.btnProceedToScanInHelp);
        btnManual = (Button) findViewById(R.id.btnManualModeInHelp);

        btnBack.setOnClickListener(btnBackClickListener);
        btnClose.setOnClickListener(btnCloseClickListener);
        btnScanner.setOnClickListener(btnScannerClickListener);
        btnManual.setOnClickListener(btnManualClickListener);
    }

    private final View.OnClickListener btnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };

    private final View.OnClickListener btnCloseClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };

    private final View.OnClickListener btnScannerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };

    private final View.OnClickListener btnManualClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(RegistrationHelpActivity.this, ProductDetailActivity.class);
            startActivity(i, true);
            finish(true);
        }
    };

}