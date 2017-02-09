package com.warrantix.main.activities.productdetail;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.TokenListAdapter;
import com.warrantix.main.customview.TokenTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class ProductsDetailReturn extends BaseActivity {

    private EditText date_styleTXT;
    private EditText hour_styleTXT;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private SimpleDateFormat hourFormatter = new SimpleDateFormat("hh:mm", Locale.US);

    TokenTextView selectReasonTxt;
    TokenTextView dropOffTxt;

    ListView reasonListView;
    ListView dropOffListView;

    ImageButton dropButton1;
    ImageButton dropButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_product_return);
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

        // reason product list view
        reasonListView  = (ListView) findViewById(R.id.reasonListView);
        selectReasonTxt = (TokenTextView) findViewById(R.id.selectReasonTxt);

        String reasons[] = {"Reason 1", "Reason 2", "Reason 3"};
        TokenListAdapter reasonAdapter = new TokenListAdapter(ProductsDetailReturn.this, reasons);
        selectReasonTxt.setTokenListView(reasonListView, reasonAdapter);

        // drop button 1
        dropButton1 = (ImageButton) findViewById(R.id.dropButton1);
        dropButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reasonListView.getVisibility() == View.GONE) {
                    hideAllListView();

                    reasonListView.setVisibility(View.VISIBLE);
                    dropButton1.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    reasonListView.setVisibility(View.GONE);
                    dropButton1.setBackgroundResource(R.drawable.check);
                }
            }
        });

        // dropoff location list view
        dropOffListView = (ListView) findViewById(R.id.dropOffListView);
        dropOffTxt = (TokenTextView) findViewById(R.id.dropOffText);

        String locations[] = {"Location 1", "Location 2", "Location 3"};
        TokenListAdapter locationAdapter = new TokenListAdapter(ProductsDetailReturn.this, locations);
        dropOffTxt.setTokenListView(dropOffListView, locationAdapter);

        // drop button 2
        dropButton2 = (ImageButton) findViewById(R.id.dropButton2);
        dropButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dropOffListView.getVisibility() == View.GONE) {
                    hideAllListView();

                    dropOffListView.setVisibility(View.VISIBLE);
                    dropButton2.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    dropOffListView.setVisibility(View.GONE);
                    dropButton2.setBackgroundResource(R.drawable.check);
                }
            }
        });

        date_styleTXT = (EditText) findViewById(R.id.date_styleTXT);
        hour_styleTXT = (EditText) findViewById(R.id.hour_styleTXT);

        date_styleTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                try {
                    newCalendar.setTime(dateFormatter.parse(date_styleTXT.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                DatePickerDialog dialog = new DatePickerDialog(ProductsDetailReturn.this, scheduleDatePickerListener,
                        newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        hour_styleTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                try {
                    newCalendar.setTime(hourFormatter.parse(hour_styleTXT.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                TimePickerDialog dialog = new TimePickerDialog(ProductsDetailReturn.this, scheduleTimePickerListener,
                        newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), true);
                dialog.show();
            }
        });

        final RadioButton refundBTN = (RadioButton) findViewById(R.id.refundImageView);
        final RadioButton replaceBTN = (RadioButton) findViewById(R.id.replaceImageView);
        final RadioButton dropOffBTN = (RadioButton) findViewById(R.id.dropOffImageView);
        final RadioButton pickupBTN = (RadioButton) findViewById(R.id.pickupImageView);

        refundBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refundBTN.setChecked(true);
                replaceBTN.setChecked(false);
                dropOffBTN.setChecked(false);
                pickupBTN.setChecked(false);
            }
        });

        replaceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refundBTN.setChecked(false);
                replaceBTN.setChecked(true);
                dropOffBTN.setChecked(false);
                pickupBTN.setChecked(false);
            }
        });

        dropOffBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refundBTN.setChecked(false);
                replaceBTN.setChecked(false);
                dropOffBTN.setChecked(true);
                pickupBTN.setChecked(false);
            }
        });

        pickupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refundBTN.setChecked(false);
                replaceBTN.setChecked(false);
                dropOffBTN.setChecked(false);
                pickupBTN.setChecked(true);
            }
        });

    }

    private void hideAllListView() {
        reasonListView.setVisibility(View.GONE);
        dropOffListView.setVisibility(View.GONE);

        dropButton1.setBackgroundResource(R.drawable.check);
        dropButton2.setBackgroundResource(R.drawable.check);
    }


    private DatePickerDialog.OnDateSetListener scheduleDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            date_styleTXT.setText(dateFormatter.format(newDate.getTime()));
        }
    };

    private TimePickerDialog.OnTimeSetListener scheduleTimePickerListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_styleTXT.setText( "" + String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute) );
        }
    };
}
