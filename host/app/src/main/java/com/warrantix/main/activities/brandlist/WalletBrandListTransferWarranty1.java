package com.warrantix.main.activities.brandlist;

import android.app.DatePickerDialog;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListTransferWarranty1 extends BaseActivity {

    private TextView titleTEXT;
    private TextView brandTEXT;
    private TextView productnameTEXT;
    private TextView modelnoTEXT;
    private TextView purchasedateTEXT;
    private TextView warrantyvalidityTEXT;

    private TextView brandEdit;
    private TextView productnameEdit;
    private TextView modelnoEdit;
    private TextView purchasedateEdit;
    private TextView warrantyvalidityEdit;

    private Button nextBTN;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_transferwarranty1);
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

        nextBTN = (Button) findViewById(R.id.nextBTN);
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(WalletBrandListTransferWarranty1.this, WalletBrandListTransferWarranty2.class);
                startActivity(mIntent, true);
            }
        });
        titleTEXT = (TextView) findViewById(R.id.title);
        brandTEXT = (TextView) findViewById(R.id.brandTXT);
        productnameTEXT = (TextView) findViewById(R.id.productnameTXT);
        modelnoTEXT = (TextView) findViewById(R.id.modelnoTXT);
        purchasedateTEXT = (TextView) findViewById(R.id.purchasedateTXT);
        warrantyvalidityTEXT = (TextView) findViewById(R.id.warrantyvalidityTXT);

        brandEdit = (EditText) findViewById(R.id.brand_edit);
        productnameEdit = (EditText) findViewById(R.id.productname_edit);
        modelnoEdit = (EditText) findViewById(R.id.model_edit);
        purchasedateEdit = (EditText) findViewById(R.id.purchasedate_edit);
//        purchasedateEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar newCalendar = Calendar.getInstance();
//                DatePickerDialog dialog = new DatePickerDialog(WalletBrandListTransferWarranty1.this, purchaseDatePickerListener,
//                        newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//                dialog.show();
//            }
//        });

        warrantyvalidityEdit = (EditText) findViewById(R.id.warrantyvalidity_edit);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }

    private DatePickerDialog.OnDateSetListener purchaseDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
            Calendar today = Calendar.getInstance();
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            purchasedateEdit.setText(dateFormatter.format(newDate.getTime()));
        }
    };
}
