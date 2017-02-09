package com.warrantix.main.activities.registration;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.zxing.Result;
import com.noveogroup.android.log.Log;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCodeActivity extends BaseActivity {

    private final int PERMISSION_REQUEST_CAMERA_SCAN_QR = 1001;

    private ZXingScannerView scannerView = null;

    private RelativeLayout navigationActionBar = null;
    private RelativeLayout scannerContainerView = null;

    private ImageButton btnBack = null;
    private ImageButton btnInfo = null;
    private Button btnSkip = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
            isInitialized = true;
        }

        scannerView.setResultHandler(barcodeScannerHandler);
        scannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    public void initialize() {
        navigationActionBar = (RelativeLayout) findViewById(R.id.registration_navigation_bar);
        scannerContainerView = (RelativeLayout) findViewById(R.id.containerInScanCode);

        btnBack = (ImageButton) findViewById(R.id.btnBackInNavigationBar);
        btnBack.setOnClickListener(btnBackClickListener);
        btnSkip = (Button) findViewById(R.id.btnSkipInScanCode);
        btnSkip.setOnClickListener(btnSkipClickListener);
        btnInfo = (ImageButton) findViewById(R.id.btnInfoInScanCode);
        btnInfo.setOnClickListener(btnInfoClickListener);

        createScannerView(scannerContainerView);
    }

    public void createScannerView(RelativeLayout containerView) {
        scannerView = (ZXingScannerView) findViewById(R.id.scannerViewInScanCode);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA_SCAN_QR);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CAMERA_SCAN_QR && hasPermission(grantResults)) {
            Log.v(" camera permission granted");
            scannerView.setResultHandler(barcodeScannerHandler);
            scannerView.startCamera();
        }
    }

    private boolean hasPermission(int[] grantResults) {
        return grantResults.length > 0 && grantResults[0] == PERMISSION_REQUEST_CAMERA_SCAN_QR;
    }

    private final View.OnClickListener btnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };

    private final View.OnClickListener btnInfoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ScanCodeActivity.this, RegistrationHelpActivity.class);
            startActivity(i, true);
        }
    };

    private final View.OnClickListener btnSkipClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ScanCodeActivity.this, ProductDetailActivity.class);
            startActivity(i, true);
        }
    };

    private final ZXingScannerView.ResultHandler barcodeScannerHandler = new ZXingScannerView.ResultHandler() {
        @Override
        public void handleResult(Result result) {
//            Log.i("Scancode Result = " + result.getText());                     // Prints scan results
//            Log.i("Scancode Type = ", result.getBarcodeFormat().toString());    // Prints the scan format (qrcode, pdf417 etc.)
//            Toast.makeText(ScanCodeActivity.this, "Result = " + result.getText() + " Type = " + result.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();
//            // If you would like to resume scanning, call this method below:
//            scannerView.resumeCameraPreview(this);

            Intent i = new Intent(ScanCodeActivity.this, ProductDetailActivity.class);
            i.putExtra("SCANCODE_RESULT", result.getText());
            i.putExtra("SCANCODE_TYPE", result.getBarcodeFormat().toString());
            startActivity(i, true);
        }
    };
}