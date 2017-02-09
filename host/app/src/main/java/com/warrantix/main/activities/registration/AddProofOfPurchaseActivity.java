package com.warrantix.main.activities.registration;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.noveogroup.android.log.Log;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import eu.livotov.labs.android.camview.CameraLiveView;
import eu.livotov.labs.android.camview.camera.PictureProcessingCallback;

public class AddProofOfPurchaseActivity extends BaseActivity {

    private RelativeLayout navigationActionBar = null;
    private ImageButton btnBack = null;
    private Button btnSkip = null;
    private CameraLiveView camView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_proof_of_purchase);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if (isInitialized == false ) {
            initialize();
            isInitialized = true;
        }

        camView.startCamera();
    }

    @Override
    protected void onPause()
    {
        camView.stopCamera();
        super.onPause();
    }

    public void initialize() {
        navigationActionBar = (RelativeLayout) findViewById(R.id.registration_navigation_bar);
        btnBack = (ImageButton) findViewById(R.id.btnBackInNavigationBar);
        btnBack.setOnClickListener(btnBackClickListener);

        btnSkip = (Button) findViewById(R.id.btnSkipInAddProofPurchase);
        btnSkip.setOnClickListener(btnSkipClickListener);

        camView = (CameraLiveView) findViewById(R.id.camviewInAddProofPurchase);
        camView.setOnClickListener(btnCamViewClickListener);
    }

    private final View.OnClickListener btnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };
    private final View.OnClickListener btnSkipClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    private final View.OnClickListener btnCamViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            camView.getController().takePicture(pictureProcessingCallback);
        }
    };

    private final PictureProcessingCallback pictureProcessingCallback = new PictureProcessingCallback() {
        @Override
        public void onShutterTriggered() {
        }

        @Override
        public void onRawPictureTaken(byte[] bytes) {
        }

        @Override
        public void onPictureTaken(byte[] bytes) {
            // Display on screen..send to LoSt server
            Bitmap pictureTaken = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.Images.Media.TITLE, "image");

            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            OutputStream outputStream;
            try {
                outputStream = getContentResolver().openOutputStream(uri);
                boolean compressed = pictureTaken.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                Log.e("picture successfully compressed at:" + uri + compressed);
                outputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.e("jpeg data length " + bytes.length);

            Intent i = new Intent(AddProofOfPurchaseActivity.this, TapOnInvoiceDateActivity.class);
            i.putExtra("INVOICE_IMAGE", uri.toString());
            startActivity(i, true);
        }
    };
}