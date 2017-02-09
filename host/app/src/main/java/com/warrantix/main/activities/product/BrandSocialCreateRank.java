package com.warrantix.main.activities.product;


import android.media.Image;
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
public class BrandSocialCreateRank extends BaseActivity {

    private TextView titleTXT;
    private TextView salientFeaturedTEXT;
    private TextView ratingTXT;
    private TokenTextView editText;
    private TextView txtErrorMessage;

    private ImageButton star1;
    private ImageButton star2;
    private ImageButton star3;
    private ImageButton star4;
    private ImageButton star5;

    private ListView productListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_social_rank);
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
        ratingTXT = (TextView) findViewById(R.id.ratingTXT);
        editText = (TokenTextView) findViewById(R.id.selectproductTXT);
        txtErrorMessage = (TextView) findViewById(R.id.txtErrorMessage);

        star1 = (ImageButton) findViewById(R.id.rating_star1);
        star2 = (ImageButton) findViewById(R.id.rating_star2);
        star3 = (ImageButton) findViewById(R.id.rating_star3);
        star4 = (ImageButton) findViewById(R.id.rating_star4);
        star5 = (ImageButton) findViewById(R.id.rating_star5);

        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.star_black);
                star3.setBackgroundResource(R.drawable.star_black);
                star4.setBackgroundResource(R.drawable.star_black);
                star5.setBackgroundResource(R.drawable.star_black);
            }
        });

        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.star);
                star3.setBackgroundResource(R.drawable.star_black);
                star4.setBackgroundResource(R.drawable.star_black);
                star5.setBackgroundResource(R.drawable.star_black);
            }
        });

        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.star);
                star3.setBackgroundResource(R.drawable.star);
                star4.setBackgroundResource(R.drawable.star_black);
                star5.setBackgroundResource(R.drawable.star_black);
            }
        });

        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.star);
                star3.setBackgroundResource(R.drawable.star);
                star4.setBackgroundResource(R.drawable.star);
                star5.setBackgroundResource(R.drawable.star_black);
            }
        });

        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.star);
                star3.setBackgroundResource(R.drawable.star);
                star4.setBackgroundResource(R.drawable.star);
                star5.setBackgroundResource(R.drawable.star);
            }
        });

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
                String products = editText.getText().toString();

                if (products.equalsIgnoreCase("") == true) {
                    txtErrorMessage.setText("INFO : Please select the product.");
                    showErrorMessage();
                    return;
                }

                txtErrorMessage.setVisibility(View.GONE);
                finish(true);
            }
        });

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

        TokenListAdapter productAdapter = new TokenListAdapter(BrandSocialCreateRank.this, product);
        productListView = (ListView) findViewById(R.id.productListView);

        editText.setTokenListView(productListView, productAdapter);
    }

    private void showErrorMessage() {
        if (txtErrorMessage.getVisibility() != View.GONE)
            return;

        txtErrorMessage.setVisibility(View.VISIBLE);
    }
}
