package com.warrantix.main.activities;

import android.app.DatePickerDialog;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.adapter.TokenListAdapter;
import com.warrantix.main.customview.CurrencyTextView;
import com.warrantix.main.customview.TokenTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * obsoluted : The oldest screen
 */
public class WalletBrandFinance3 extends BaseActivity {

    private Button DoneBtn;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private EditText birthDateTxt;
    private String title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_finance3);
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
        title = getIntent().getStringExtra("title");
        final TextView titleView = (TextView) findViewById(R.id.title);
        if (title != null)
            titleView.setText(title);

        DoneBtn = (Button) findViewById(R.id.doneBTN);
        DoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((title != null) && (title.equalsIgnoreCase("GET FINANCE") == true)) {
                    Intent intent = new Intent(getApplicationContext(), WalletBrandAccessoriesEshop.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent, true, true);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), WalletBrandFinance0.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent, true, true);
                }
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        // calendar text
        birthDateTxt = (EditText) findViewById(R.id.birthDateTxt);
        birthDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();

                try {
                    newCalendar.setTime(dateFormatter.parse(birthDateTxt.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                DatePickerDialog dialog = new DatePickerDialog(WalletBrandFinance3.this, birthDatePickerListener,
                        newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        // token list view
        String residences[] = {"Residence 1", "Residence 2", "Residence 3"};
        TokenListAdapter residenceAdapter = new TokenListAdapter(WalletBrandFinance3.this, residences);
        final ListView residenceTypeListView = (ListView) findViewById(R.id.residenceTypeListView);
        TokenTextView residenceTypeTxt = (TokenTextView) findViewById(R.id.residenceTypeTxt);
        residenceTypeTxt.setTokenListView(residenceTypeListView, residenceAdapter);

        final ImageButton btnDrop1 = (ImageButton) findViewById(R.id.dropbutton1);
        btnDrop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (residenceTypeListView.getVisibility() == View.GONE) {
                    residenceTypeListView.setVisibility(View.VISIBLE);
                    btnDrop1.setBackgroundResource(R.drawable.check_up);
                } else {
                    residenceTypeListView.setVisibility(View.GONE);
                    btnDrop1.setBackgroundResource(R.drawable.check);
                }
            }
        });

        // Currency Text View
        CurrencyTextView spouseTxt = (CurrencyTextView) findViewById(R.id.spouseTxt);
        spouseTxt.createLeftDrawable("Rs.");
    }

    private DatePickerDialog.OnDateSetListener birthDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
            Calendar today = Calendar.getInstance();
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            birthDateTxt.setText(dateFormatter.format(newDate.getTime()));
        }
    };

}
