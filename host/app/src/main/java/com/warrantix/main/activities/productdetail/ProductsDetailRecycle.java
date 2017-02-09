package com.warrantix.main.activities.productdetail;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.media.Image;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class ProductsDetailRecycle extends BaseActivity {

    private EditText date_styleTXT;
    private EditText hour_styleTXT;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private SimpleDateFormat hourFormatter = new SimpleDateFormat("hh:mm", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_product_recycle);
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
                DatePickerDialog dialog = new DatePickerDialog(ProductsDetailRecycle.this, scheduleDatePickerListener,
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
                TimePickerDialog dialog = new TimePickerDialog(ProductsDetailRecycle.this, scheduleTimePickerListener,
                        newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), true);
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

        Button btnDone = (Button) findViewById(R.id.doneBTN);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
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
            Calendar newHour = Calendar.getInstance();
            newHour.set(1970, 1, 1, hourOfDay, minute);
            hour_styleTXT.setText(hourFormatter.format(newHour.getTime()));
        }
    };

}
