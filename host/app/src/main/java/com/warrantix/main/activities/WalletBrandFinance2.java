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
public class WalletBrandFinance2 extends BaseActivity {
    private Intent mIntent;
    private Button nextBtn;

    private CurrencyTextView monthlyView;
    private TokenTextView bankView;
    private ListView bankListView;
    private EditText joinDateTxt;
    private String title;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_finance2);
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
        TextView titleView = (TextView) findViewById(R.id.title);
        if (title != null)
            titleView.setText(title);

        nextBtn = (Button) findViewById(R.id.nextBTN);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(WalletBrandFinance2.this, WalletBrandFinance3.class);
                mIntent.putExtra("title", title);
                startActivity(mIntent, true);
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
        joinDateTxt = (EditText) findViewById(R.id.joiningDateTxt);
        joinDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();

                try {
                    newCalendar.setTime(dateFormatter.parse(joinDateTxt.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                DatePickerDialog dialog = new DatePickerDialog(WalletBrandFinance2.this, purchaseDatePickerListener,
                        newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        // token list view
        String bank[] = {"Bank 1", "Bank 2", "Bank 3"};

        TokenListAdapter productAdapter = new TokenListAdapter(WalletBrandFinance2.this, bank);
        bankListView = (ListView) findViewById(R.id.bankListview);
        bankView = (TokenTextView) findViewById(R.id.bankProductTXT);
        bankView.setTokenListView(bankListView, productAdapter);

        final ImageButton btnDrop1 = (ImageButton) findViewById(R.id.dropbutton1);
        btnDrop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bankListView.getVisibility() == View.GONE) {
                    bankListView.setVisibility(View.VISIBLE);
                    btnDrop1.setBackgroundResource(R.drawable.check_up);
                } else {
                    bankListView.setVisibility(View.GONE);
                    btnDrop1.setBackgroundResource(R.drawable.check);
                }
            }
        });

        // Currency Text View
        monthlyView = (CurrencyTextView) findViewById(R.id.monthlySalaryTxt);
        monthlyView.createLeftDrawable("Rs.");

    }

    private DatePickerDialog.OnDateSetListener purchaseDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
            Calendar today = Calendar.getInstance();
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            joinDateTxt.setText(dateFormatter.format(newDate.getTime()));
        }
    };

}
