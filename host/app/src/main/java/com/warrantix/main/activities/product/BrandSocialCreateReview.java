package com.warrantix.main.activities.product;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.TokenListAdapter;
import com.warrantix.main.customview.TokenTextView;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class BrandSocialCreateReview extends BaseActivity {

    private TextView titleTXT;
    private TextView salientFeaturedTEXT;
    private TextView txtErrorMessage;

    private TokenTextView selectproductTXT;
    private EditText messageTXT;

    private ListView productListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_social_review);
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
        salientFeaturedTEXT = (TextView) findViewById(R.id.salientfeatured);
        txtErrorMessage = (TextView) findViewById(R.id.txtErrorMessage);

        selectproductTXT = (TokenTextView) findViewById(R.id.selectproductTXT);
        messageTXT = (EditText) findViewById(R.id.messageTXT);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        Button doneBTN = (Button) findViewById(R.id.doneBTN);
        doneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String products = selectproductTXT.getText().toString();

                if (products.equalsIgnoreCase("") == true) {
                    txtErrorMessage.setText("INFO : Please select the product.");
                    showErrorMessage();
                    return;
                }

                txtErrorMessage.setVisibility(View.GONE);
                finish(true);
            }
        });

        productListView = (ListView) findViewById(R.id.productListView);

        final ImageButton btnDrop1 = (ImageButton) findViewById(R.id.dropbutton1);
        btnDrop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productListView.getVisibility() == View.GONE) {
                    productListView.setVisibility(View.VISIBLE);
                    btnDrop1.setBackgroundResource(R.drawable.check_up);
                }
                else {
                    productListView.setVisibility(View.GONE);
                    btnDrop1.setBackgroundResource(R.drawable.check);
                }
            }
        });

        String product[] = {"Product 1", "Product 2", "Product 3"};

        TokenListAdapter productAdapter = new TokenListAdapter(BrandSocialCreateReview.this, product);
        selectproductTXT.setTokenListView(productListView, productAdapter);
    }

    private void showErrorMessage() {
        if (txtErrorMessage.getVisibility() != View.GONE)
            return;

        txtErrorMessage.setVisibility(View.VISIBLE);
    }
}
