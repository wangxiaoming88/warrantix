package com.warrantix.main.activities.registration;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.noveogroup.android.log.Log;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.common.permission.MarshMallowPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ProofPurchaseActivity extends BaseActivity {

    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;

    private RelativeLayout navigationActionBar = null;
    private ImageButton btnBack = null;
    private Button btnTakePhoto = null;
    private Button btnChooseGallery = null;
    private Button btnSkip = null;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proof_purchase);
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
        btnTakePhoto = (Button) findViewById(R.id.btnTakePhotoInProofPurchase);
        btnChooseGallery = (Button) findViewById(R.id.btnChooseGalleryInProofPurchase);
        btnSkip = (Button) findViewById(R.id.btnSkipInProofPurchase);

        btnBack.setOnClickListener(btnBackClickListener);
        btnTakePhoto.setOnClickListener(btnTakePhotoClickListener);
        btnChooseGallery.setOnClickListener(btnChooseGalleryClickListener);
        btnSkip.setOnClickListener(btnSkipClickListener);
    }

//    private static boolean canWriteToExternalStorage(Context context) {
//        return ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
//    }

    private final View.OnClickListener btnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };

    private final View.OnClickListener btnTakePhotoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MarshMallowPermission marshMallowPermission = new MarshMallowPermission(ProofPurchaseActivity.this);

            // request camera
            if (!marshMallowPermission.checkPermissionForCamera()) {
                marshMallowPermission.requestPermissionForCamera();
                return;
            }

            // request external storage
            if (!marshMallowPermission.checkPermissionForExternalStorage()) {
                marshMallowPermission.requestPermissionForExternalStorage();
                return;
            }

            // Take photo from camera
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "New Picture");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
            imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, REQUEST_CAMERA);

//            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(cameraIntent, REQUEST_CAMERA);

//            Intent i = new Intent(ProofPurchaseActivity.this, AddProofOfPurchaseActivity.class);
//            startActivity(i, true);
        }
    };

    private final View.OnClickListener btnChooseGalleryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            // Show only images, no videos or anything else
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            // Always show the chooser (if there are multiple options available)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);

//            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            intent.setType("image/*");
//            startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
        }
    };

    private final View.OnClickListener btnSkipClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ProofPurchaseActivity.this, RequestDigitalWarrantyActivity.class);
            startActivity(i, true);
        }
    };

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == REQUEST_CODE_PERMISSION) {
//            if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // permission was granted
//            } else {
//                // permission wasn't granted
//            }
//        }
//    }
//
//    private void checkWritingPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                // permission wasn't granted
//            } else {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
//            }
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("OnActivityResult: ProofPurchaseActivity, requestCode = " + requestCode + " resultCode = " + resultCode);

        if (resultCode == RESULT_OK)
        {
            if (requestCode == REQUEST_CAMERA) {
                Intent i = new Intent(ProofPurchaseActivity.this, TapOnInvoiceDateActivity.class);
                i.putExtra("INVOICE_IMAGE", imageUri.toString());
                startActivity(i, true);
            }
            else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();

                Intent i = new Intent(ProofPurchaseActivity.this, TapOnInvoiceDateActivity.class);
                i.putExtra("INVOICE_IMAGE", selectedImageUri.toString());
                startActivity(i, true);

//                String[] projection = {MediaStore.MediaColumns.DATA};
//                CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null, null);
//                Cursor cursor = cursorLoader.loadInBackground();
//                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//                cursor.moveToFirst();
//
//                String selectedImagePath = cursor.getString(column_index);
//                Bitmap bm;
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inJustDecodeBounds = true;
//
//                BitmapFactory.decodeFile(selectedImagePath, options);
//                final int REQUIRED_SIZE = 200;
//                int scale = 1;
//                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
//                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
//                    scale *= 2;
//                options.inSampleSize = scale;
//                options.inJustDecodeBounds = false;
//                bm = BitmapFactory.decodeFile(selectedImagePath, options);
//
//                Intent i = new Intent(ProofPurchaseActivity.this, TapOnInvoiceDateActivity.class);
//                i.putExtra("BITMAP", bm);
//                startActivity(i, true);
            }
        }
    }
}