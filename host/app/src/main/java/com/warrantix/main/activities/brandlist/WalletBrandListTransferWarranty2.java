package com.warrantix.main.activities.brandlist;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.dialog.MessageDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListTransferWarranty2 extends BaseActivity {

    private TextView titleTEXT;
    private TextView transfereeTEXT;
    private TextView walletIDTEXT;
    private TextView transferdateTEXT;
    private TextView messageTEXT;
    private TextView authorizedTEXT;
    private TextView releaseTEXT;

    private EditText transfereeEdit;
    private EditText walletIDEdit;
    private EditText transferdateEdit;
    private EditText messageEdit;

    private boolean isWarrantyAuthorized = false;
    private boolean isWarrantyPaymentReceipt = false;

    private ImageView chkWarrantyAuthrized = null;
    private ImageView chkPaymentReceipt = null;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_transferwarranty2);
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
        transfereeTEXT = (TextView) findViewById(R.id.transfereeTXT);
        walletIDTEXT = (TextView) findViewById(R.id.walletIDTXT);
        transferdateTEXT = (TextView) findViewById(R.id.transferDateTXT);
        messageTEXT = (TextView) findViewById(R.id.messageTXT);
        authorizedTEXT = (TextView) findViewById(R.id.authorizedTXT);
        releaseTEXT = (TextView) findViewById(R.id.releaseTXT);

        transfereeEdit = (EditText) findViewById(R.id.transferee_edit);
        walletIDEdit = (EditText) findViewById(R.id.walletID_edit);
        transferdateEdit = (EditText) findViewById(R.id.transferDate_edit);
        transferdateEdit.setOnClickListener(txtTransferDateListener);

        messageEdit = (EditText) findViewById(R.id.message_edit);

        chkWarrantyAuthrized = (ImageView) findViewById(R.id.isWarrantyAuthorized);
        chkPaymentReceipt = (ImageView) findViewById(R.id.isWarrantyPaymentReceipt);
        chkWarrantyAuthrized.setOnClickListener(isAuthorizedListener);
        chkPaymentReceipt.setOnClickListener(isPaymentReceiptListener);

        Button transferBTN = (Button) findViewById(R.id.transferBTN);
        transferBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDialog dialog = new MessageDialog(WalletBrandListTransferWarranty2.this);
                dialog.setTitle("Warranty Transferred");
                dialog.setMessage("");
                dialog.setOkButtonListener(btnOkClickListener);
                dialog.show();
            }
        });


        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }

    private final MessageDialog.OkButtonListener btnOkClickListener = new MessageDialog.OkButtonListener() {
        @Override
        public void callback() {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent, true, true);
        }
    };


    private final View.OnClickListener isAuthorizedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isWarrantyAuthorized == false)
                isWarrantyAuthorized = true;
            else
                isWarrantyAuthorized = false;

            if (isWarrantyAuthorized == true)
                chkWarrantyAuthrized.setBackgroundResource(R.drawable.square_tick);
            else
                chkWarrantyAuthrized.setBackgroundResource(R.drawable.square);
        }
    };

    private final View.OnClickListener isPaymentReceiptListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isWarrantyPaymentReceipt == false)
                isWarrantyPaymentReceipt = true;
            else
                isWarrantyPaymentReceipt = false;

            if (isWarrantyPaymentReceipt == true)
                chkPaymentReceipt.setBackgroundResource(R.drawable.square_tick);
            else
                chkPaymentReceipt.setBackgroundResource(R.drawable.square);
        }
    };

    private final View.OnClickListener txtTransferDateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar newCalendar = Calendar.getInstance();
            try {
                newCalendar.setTime(dateFormatter.parse(transferdateEdit.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            DatePickerDialog dialog = new DatePickerDialog(WalletBrandListTransferWarranty2.this, purchaseDatePickerListener,
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
            transferdateEdit.setText(dateFormatter.format(newDate.getTime()));
        }
    };

}
