package com.warrantix.main.activities.productdetail;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.noveogroup.android.log.Log;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.common.permission.MarshMallowPermission;
import com.warrantix.main.customview.CurrencyTextView;

public class ProductDetailResell extends BaseActivity {

    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;

    private ImageButton star1;
    private ImageButton star2;
    private ImageButton star3;
    private ImageButton star4;
    private ImageButton star5;

    private boolean isAccepted = false;

    private CurrencyTextView txtOfferPrice = null;

    private LinearLayout btnTakePhoto;
    private LinearLayout btnFromGallery;
    private Uri imageUri;

    private ImageView imageViews[] = new ImageView[5];
    private TextView indicatorViews[] = new TextView[5];
    private int step = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_product_resell);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
            initializePhotoViews();
            isInitialized = true;
        }
    }

    private void initializePhotoViews() {
        imageViews[0] = (ImageView) findViewById(R.id.firstImageView);
        imageViews[1] = (ImageView) findViewById(R.id.secondImageView);
        imageViews[2] = (ImageView) findViewById(R.id.thirdImageView);
        imageViews[3] = (ImageView) findViewById(R.id.fourthImageView);
        imageViews[4] = (ImageView) findViewById(R.id.fifthImageView);

        indicatorViews[0] = (TextView) findViewById(R.id.firstIndicatorView);
        indicatorViews[1] = (TextView) findViewById(R.id.secondIndicatorView);
        indicatorViews[2] = (TextView) findViewById(R.id.thirdIndicatorView);
        indicatorViews[3] = (TextView) findViewById(R.id.fourthIndicatorView);
        indicatorViews[4] = (TextView) findViewById(R.id.fifthIndicatorView);
    }

    public void initialize() {

        star1 = (ImageButton) findViewById(R.id.rating_star1);
        star2 = (ImageButton) findViewById(R.id.rating_star2);
        star3 = (ImageButton) findViewById(R.id.rating_star3);
        star4 = (ImageButton) findViewById(R.id.rating_star4);
        star5 = (ImageButton) findViewById(R.id.rating_star5);

        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.star_black);
                star3.setBackgroundResource(R.drawable.star_black);
                star4.setBackgroundResource(R.drawable.star_black);
                star5.setBackgroundResource(R.drawable.star_black);
            }
        });

        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.star);
                star3.setBackgroundResource(R.drawable.star_black);
                star4.setBackgroundResource(R.drawable.star_black);
                star5.setBackgroundResource(R.drawable.star_black);
            }
        });

        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.star);
                star3.setBackgroundResource(R.drawable.star);
                star4.setBackgroundResource(R.drawable.star_black);
                star5.setBackgroundResource(R.drawable.star_black);
            }
        });

        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.star);
                star3.setBackgroundResource(R.drawable.star);
                star4.setBackgroundResource(R.drawable.star);
                star5.setBackgroundResource(R.drawable.star_black);
            }
        });

        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.star);
                star3.setBackgroundResource(R.drawable.star);
                star4.setBackgroundResource(R.drawable.star);
                star5.setBackgroundResource(R.drawable.star);
            }
        });

        final ImageView selectView = (ImageView) findViewById(R.id.accept_select);
        selectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAccepted == false)
                    isAccepted = true;
                else
                    isAccepted = false;

                if (isAccepted == false)
                    selectView.setBackgroundResource(R.drawable.square);
                else
                    selectView.setBackgroundResource(R.drawable.square_tick);
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        Button btnDone = (Button) findViewById(R.id.doneBTN);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        txtOfferPrice = (CurrencyTextView) findViewById(R.id.txtOfferPrice);
        txtOfferPrice.createLeftDrawable("Rs.");

        btnTakePhoto = (LinearLayout) findViewById(R.id.btnTakePhoto);
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarshMallowPermission marshMallowPermission = new MarshMallowPermission(ProductDetailResell.this);

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
                imageUri = data.getData();
            }

            if (step > 4)
                return;

            try {
                Bitmap mInvoiceBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                if (mInvoiceBitmap.getWidth() > mInvoiceBitmap.getHeight())
                    mInvoiceBitmap = rotateBitmap(mInvoiceBitmap, 90);

                indicatorViews[step].setVisibility(View.GONE);
                imageViews[step].setImageBitmap(mInvoiceBitmap);

                step++;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap rotateBitmap(Bitmap source, int angle) {
        try {
            Bitmap bitmap = null;

            Matrix matrix = new Matrix();
            matrix.postRotate(angle);
            bitmap = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
            source.recycle();
            source = null;
            return bitmap;
        }
        catch (OutOfMemoryError e) {
            return source;
        }
    }
}