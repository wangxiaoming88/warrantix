package com.warrantix.main.activities.brandlist;


import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.registration.TapOnInvoiceDateActivity;
import com.warrantix.main.common.permission.MarshMallowPermission;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListUserAccout extends BaseActivity {

    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;

    private TextView titleTEXT;
    private TextView walletIDTEXT;
    private TextView customerIDTEXT;
    private TextView nameTEXT;
    private TextView addressTEXT;
    private TextView cityTEXT;
    private TextView mobilenoTEXT;
    private TextView emailaddressTEXT;
    private TextView passwordTEXT;
    private TextView debitcardTEXT;

    private EditText walletIDEdit;
    private EditText customerIDEdit;
    private EditText nameEdit;
    private EditText addressEdit;
    private EditText cityEdit;
    private EditText mobilenoEdit;
    private EditText emailaddressEdit;
    private EditText passwordEdit;
    private EditText debitcardEdit;

    private LinearLayout btnTakePhoto;
    private LinearLayout btnFromGallery;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_useraccout);
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
        walletIDTEXT = (TextView) findViewById(R.id.walletIDTXT);
        customerIDTEXT = (TextView) findViewById(R.id.customerIDTXT);

        nameTEXT = (TextView) findViewById(R.id.nameTXT);
        addressTEXT = (TextView) findViewById(R.id.addressTXT);
        cityTEXT = (TextView) findViewById(R.id.cityTXT);
        mobilenoTEXT = (TextView) findViewById(R.id.mobilenoTXT);
        emailaddressTEXT = (TextView) findViewById(R.id.emailaddressTXT);
        passwordTEXT = (TextView) findViewById(R.id.passwordTXT);
        debitcardTEXT = (TextView) findViewById(R.id.debitcardTXT);

        walletIDEdit = (EditText) findViewById(R.id.walletID_edit);;
        customerIDEdit = (EditText) findViewById(R.id.customerID_edit);
        nameEdit = (EditText) findViewById(R.id.name_edit);
        addressEdit = (EditText) findViewById(R.id.address_edit);
        cityEdit = (EditText) findViewById(R.id.city_edit);
        mobilenoEdit = (EditText) findViewById(R.id.mobileNo_edit);
        emailaddressEdit = (EditText) findViewById(R.id.emailaddress_edit);
        passwordEdit = (EditText) findViewById(R.id.password_edit);
        debitcardEdit = (EditText) findViewById(R.id.debitcard_edit);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        Button btnDone = (Button) findViewById(R.id.editBTN);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        btnTakePhoto = (LinearLayout) findViewById(R.id.btnTakePhoto);
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarshMallowPermission marshMallowPermission = new MarshMallowPermission(WalletBrandListUserAccout.this);

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

            }
        });

        btnFromGallery = (LinearLayout) findViewById(R.id.btnFromGallery);
        btnFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            if (requestCode == REQUEST_CAMERA) {
                Log.d("UserAccount", "Image URL = " + imageUri.toString());
            }
            else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                Log.d("UserAccount", "Selected Image URL = " + selectedImageUri.toString());
            }
        }
    }
}
