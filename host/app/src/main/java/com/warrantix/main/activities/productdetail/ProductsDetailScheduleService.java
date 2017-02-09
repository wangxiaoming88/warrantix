package com.warrantix.main.activities.productdetail;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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
public class ProductsDetailScheduleService extends BaseActivity {

    private TextView titleTXT;
    private TextView FIXES_NEEDEDTXT;

    private TokenTextView serviceTokenView;
    private EditText date_styleTXT;
    private EditText hour_styleTXT;
    private TokenTextView Fixes_1TXT;
    private TokenTextView Fixes_2TXT;
    private TokenTextView Fixes_3TXT;
    private EditText messageTXT;
    private View bottomSpaceView;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private SimpleDateFormat hourFormatter = new SimpleDateFormat("hh:mm", Locale.US);

    private ListView tosListView = null;
    private ListView fix1ListView = null;
    private ListView fix2ListView = null;
    private ListView fix3ListView = null;

    ImageButton btnDrop1;
    ImageButton btnDrop2;
    ImageButton btnDrop3;
    ImageButton btnDrop4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_products_detail_service);
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
        titleTXT = (TextView) findViewById(R.id.title);
        FIXES_NEEDEDTXT = (TextView) findViewById(R.id.FIXES_NEEDEDTXT);

        serviceTokenView = (TokenTextView) findViewById(R.id.First_Free_ServiceTXT);
        date_styleTXT = (EditText) findViewById(R.id.date_styleTXT);
        hour_styleTXT = (EditText) findViewById(R.id.hour_styleTXT);
        Fixes_1TXT = (TokenTextView) findViewById(R.id.Fixes_1TXT);
        Fixes_2TXT = (TokenTextView) findViewById(R.id.Fixes_2TXT);
        Fixes_3TXT = (TokenTextView) findViewById(R.id.Fixes_3TXT);
        messageTXT = (EditText) findViewById(R.id.messageTXT);
        bottomSpaceView = (View) findViewById(R.id.bottomSpaceView);

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

        date_styleTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                try {
                    newCalendar.setTime(dateFormatter.parse(date_styleTXT.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                DatePickerDialog dialog = new DatePickerDialog(ProductsDetailScheduleService.this, scheduleDatePickerListener,
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
                TimePickerDialog dialog = new TimePickerDialog(ProductsDetailScheduleService.this, scheduleTimePickerListener,
                        newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), true);
                dialog.show();
            }
        });
        btnDrop1 = (ImageButton) findViewById(R.id.dropbutton1);
        btnDrop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tosListView.getVisibility() == View.GONE) {
                    hideAllListView();

                    tosListView.setVisibility(View.VISIBLE);
                    btnDrop1.setBackgroundResource(R.drawable.check_up);
                } else {
                    tosListView.setVisibility(View.GONE);
                    btnDrop1.setBackgroundResource(R.drawable.check);
                }
            }
        });

        String tos[] = {"Service 1", "Service 2", "Service 3"};

        TokenListAdapter tosAdapter = new TokenListAdapter(ProductsDetailScheduleService.this, tos);
        tosListView = (ListView) findViewById(R.id.tosListView);
        serviceTokenView.setTokenListView(tosListView, tosAdapter);

        btnDrop2 = (ImageButton) findViewById(R.id.dropbutton2);
        btnDrop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fix1ListView.getVisibility() == View.GONE) {
                    hideAllListView();

                    fix1ListView.setVisibility(View.VISIBLE);
                    btnDrop2.setBackgroundResource(R.drawable.check_up);
                } else {
                    fix1ListView.setVisibility(View.GONE);
                    btnDrop2.setBackgroundResource(R.drawable.check);
                }
            }
        });

        String fixes[] = {"Fixes 1", "Fixes 2", "Fixes 3", "Fixes 4"};

        TokenListAdapter fix1Adapter = new TokenListAdapter(ProductsDetailScheduleService.this, fixes);
        fix1ListView = (ListView) findViewById(R.id.fix1ListView);
        this.Fixes_1TXT.setTokenListView(fix1ListView, fix1Adapter);

        btnDrop3 = (ImageButton) findViewById(R.id.dropbutton3);
        btnDrop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fix2ListView.getVisibility() == View.GONE) {
                    hideAllListView();

                    fix2ListView.setVisibility(View.VISIBLE);
                    btnDrop3.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    fix2ListView.setVisibility(View.GONE);
                    btnDrop3.setBackgroundResource(R.drawable.check);
                }
            }
        });

        TokenListAdapter fix2Adapter = new TokenListAdapter(ProductsDetailScheduleService.this, fixes);
        fix2ListView = (ListView) findViewById(R.id.fix2ListView);
        this.Fixes_2TXT.setTokenListView(fix2ListView, fix2Adapter);

        btnDrop4 = (ImageButton) findViewById(R.id.dropbutton4);
        btnDrop4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fix3ListView.getVisibility() == View.GONE) {
                    hideAllListView();

                    fix3ListView.setVisibility(View.VISIBLE);
                    btnDrop4.setBackgroundResource(R.drawable.check_up);
                } else {
                    fix3ListView.setVisibility(View.GONE);
                    btnDrop4.setBackgroundResource(R.drawable.check);
                }
            }
        });

        TokenListAdapter fix3Adapter = new TokenListAdapter(ProductsDetailScheduleService.this, fixes);
        fix3ListView = (ListView) findViewById(R.id.fix3ListView);
        this.Fixes_3TXT.setTokenListView(fix3ListView, fix3Adapter);
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

    private void hideAllListView() {
        fix3ListView.setVisibility(View.GONE);
        fix2ListView.setVisibility(View.GONE);
        fix1ListView.setVisibility(View.GONE);
        tosListView.setVisibility(View.GONE);

        btnDrop1.setBackgroundResource(R.drawable.check);
        btnDrop2.setBackgroundResource(R.drawable.check);
        btnDrop3.setBackgroundResource(R.drawable.check);
        btnDrop4.setBackgroundResource(R.drawable.check);
    }

}
