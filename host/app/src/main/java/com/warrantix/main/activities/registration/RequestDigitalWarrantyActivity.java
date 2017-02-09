package com.warrantix.main.activities.registration;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.customview.CurrencyTextView;
import com.warrantix.main.dialog.MessageDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class RequestDigitalWarrantyActivity extends BaseActivity {

    private RelativeLayout navigationActionBar = null;
    private ImageButton btnBack = null;
    private Button btnRegister = null;

    private CircleImageView productImageView = null;
    private TextView lblProductBrand = null;
    private EditText txtModelNumber = null;
    private EditText txtSerialNumber = null;
    private EditText txtPurchaseDate = null;
    private EditText txtDealerName = null;
    private CurrencyTextView txtPrice = null;
    private TextView txtAttachment = null;

    private Button lblAttachment = null;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_digital_warranty);
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
        btnBack.setOnClickListener(btnBackClickListener);
        btnRegister = (Button) findViewById(R.id.btnRegisterInDigitalWarranty);
        btnRegister.setOnClickListener(btnRegisterClickListener);

//        productImageView = (CircleImageView) findViewById(R.id.productPhotoInDigitalWarranty);
//        lblProductBrand = (TextView) findViewById(R.id.lblProductBrandInDigitalWarranty);
        txtModelNumber = (EditText) findViewById(R.id.txtModelNumberInDigitalWarranty);
        txtSerialNumber = (EditText) findViewById(R.id.txtSerialNumberInDigitalWarranty);
        txtPurchaseDate = (EditText) findViewById(R.id.txtPurchaseDateInDigitalWarranty);
        txtDealerName = (EditText) findViewById(R.id.txtDealerNameInDigitalWarranty);
        txtPrice = (CurrencyTextView) findViewById(R.id.txtPriceInDigitalWarranty);
        txtAttachment = (TextView) findViewById(R.id.txtAttachmentInDigitalWarranty);

        lblAttachment = (Button) findViewById(R.id.lblAttachmentInDigitalWarranty);
        lblAttachment.setOnClickListener(btnAttachmentClickListener);

        txtPrice.createLeftDrawable("Rs.");

        txtPurchaseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();

                try {
                    newCalendar.setTime(dateFormatter.parse(txtPurchaseDate.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                DatePickerDialog dialog = new DatePickerDialog(RequestDigitalWarrantyActivity.this, purchaseDatePickerListener,
                        newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
    }

    private final View.OnClickListener btnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(true);
        }
    };

    private final View.OnClickListener btnRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MessageDialog dialog = new MessageDialog(RequestDigitalWarrantyActivity.this);
            dialog.setTitle("CONGRATULATIONS!");
            dialog.setMessage("Digital Warranty is being Processed. Will be delivered to you shortly");
            dialog.setOkButtonListener(btnOkClickListener);
            dialog.show();
        }
    };

    private final View.OnClickListener btnAttachmentClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            WarrantixApplication.getInstance().openPDFFiles(RequestDigitalWarrantyActivity.this);
        }
    };

    private final MessageDialog.OkButtonListener btnOkClickListener = new MessageDialog.OkButtonListener() {
        @Override
        public void callback() {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent, true, true);
        }
    };

    private final View.OnClickListener txtPurchaseDateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar newCalendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(RequestDigitalWarrantyActivity.this, purchaseDatePickerListener,
                    newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }
    };

    private DatePickerDialog.OnDateSetListener purchaseDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
            Calendar today = Calendar.getInstance();
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            txtPurchaseDate.setText(dateFormatter.format(newDate.getTime()));
        }
    };

}