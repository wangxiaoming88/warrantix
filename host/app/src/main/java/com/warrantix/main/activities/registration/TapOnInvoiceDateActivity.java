package com.warrantix.main.activities.registration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.manager.RegistrationManager;

public class TapOnInvoiceDateActivity extends BaseActivity {

    private RelativeLayout navigationActionBar = null;
    private ImageButton btnBack = null;
    private ImageButton btnFunc = null;
    private ImageView photoView = null;

    private TextView titleView = null;
    private Bitmap mInvoiceBitmap = null;
    private View dragView = null;

    private int startX, startY, endX, endY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_invoicedate);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isInitialized == false) {
            initialize();
            preprocessTakenPhoto();
            isInitialized = true;
        }
    }

    public void initialize() {
        navigationActionBar = (RelativeLayout) findViewById(R.id.registration_navigation_bar);
        btnBack = (ImageButton) findViewById(R.id.btnBackInNavigationBar);
        btnBack.setOnClickListener(btnBackClickListener);
        btnFunc = (ImageButton) findViewById(R.id.btnFuncInNavigationBar);
        btnFunc.setImageResource(R.drawable.registration_rotate_photo);
        btnFunc.setOnClickListener(btnFuncClickListener);

        titleView = (TextView) findViewById(R.id.titleViewInNavigationBar);
        titleView.setText("TAP ON INVOICE DATE");

        photoView = (ImageView) findViewById(R.id.takenPhotoViewInTapInvoiceDate);
        photoView.setOnTouchListener(btnImageDragListener);

        dragView = (View) findViewById(R.id.dragViewInInvoiceDate);
    }

    public void preprocessTakenPhoto()
    {
        try {
            Uri uri = Uri.parse(getIntent().getStringExtra("INVOICE_IMAGE"));
            mInvoiceBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            if (mInvoiceBitmap.getWidth() > mInvoiceBitmap.getHeight())
                mInvoiceBitmap = rotateBitmap(mInvoiceBitmap, 90);
            photoView.setImageBitmap(mInvoiceBitmap);
        }
        catch (Exception e) {
            e.printStackTrace();
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

    private void nextScreen() {
        int tempX, tempY;

        if (endX < startX)
            tempX = endX;
        else
            tempX = startX;

        if (endY < startY)
            tempY = endY;
        else
            tempY = startY;

        RegistrationManager.getInstance().setInvoiceBitmap(mInvoiceBitmap);
        RegistrationManager.getInstance().setInvoiceRect(tempX, tempY, Math.abs(endX - startX), Math.abs(endY - startY));

        Intent i = new Intent(TapOnInvoiceDateActivity.this, TapOnDealerNameActivity.class);
        startActivity(i, true);
    }

    private final View.OnClickListener btnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };

    private final View.OnClickListener btnFuncClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mInvoiceBitmap = rotateBitmap(mInvoiceBitmap, 180);
            photoView.setImageBitmap(mInvoiceBitmap);
        }
    };

    private final View.OnTouchListener btnImageDragListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getX();
            final int Y = (int) event.getY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    startX = X;
                    startY = Y;
                    break;
                case MotionEvent.ACTION_UP:
                    endX = X;
                    endY = Y;
                    nextScreen();
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    endX = X;
                    endY = Y;
                    int tempX, tempY;

                    if (endX < startX)
                        tempX = endX;
                    else
                        tempX = startX;

                    if (endY < startY)
                        tempY = endY;
                    else
                        tempY = startY;

                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) dragView.getLayoutParams();
                    layoutParams.leftMargin = tempX;
                    layoutParams.topMargin = tempY;
                    layoutParams.width = Math.abs(endX - startX);
                    layoutParams.height = Math.abs(endY - startY);
                    dragView.setLayoutParams(layoutParams);
                    break;
            }
            return true;
        }
    };
}
