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
import com.warrantix.main.adapter.RecipientListAdapter;
import com.warrantix.main.customview.RecipientTextView;
import com.warrantix.main.customview.TokenTextView;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class BrandSocialCreateRefer extends BaseActivity {

    private TextView titleTXT;
    private TextView salientFeaturedTEXT;
    private TokenTextView referviaTXT;
    private RecipientTextView addrecipientsTXT;
    private EditText messageTXT;
    private TextView txtErrorMessage;

    private ListView productListView;
    private ListView recipientListView;

    ImageButton btnDrop1;
    ImageButton btnDrop2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_social_refer);
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

        referviaTXT = (TokenTextView) findViewById(R.id.referviaTXT);
        addrecipientsTXT = (RecipientTextView) findViewById(R.id.addrecipientsTXT);
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
                String tokens = referviaTXT.getText().toString();
                String recipients = addrecipientsTXT.getText().toString();

                if (tokens.equalsIgnoreCase("") == true) {
                    txtErrorMessage.setText("INFO : Please select the product.");
                    showErrorMessage();
                    return;
                }

                if (recipients.equalsIgnoreCase("") == true) {
                    txtErrorMessage.setText("INFO : Please select the recipient");
                    showErrorMessage();
                    return;
                }

                txtErrorMessage.setVisibility(View.GONE);
                finish(true);
            }
        });

        productListView = (ListView) findViewById(R.id.productListView);
        recipientListView = (ListView) findViewById(R.id.profileListView);

        btnDrop1 = (ImageButton) findViewById(R.id.dropbutton1);
        btnDrop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productListView.getVisibility() == View.GONE) {
                    hideAllListview();

                    productListView.setVisibility(View.VISIBLE);
                    btnDrop1.setBackgroundResource(R.drawable.check_up);

                    if (recipientListView.getVisibility() == View.VISIBLE)
                        recipientListView.setVisibility(View.GONE);
                }
                else {
                    productListView.setVisibility(View.GONE);
                    btnDrop1.setBackgroundResource(R.drawable.check);
                }
            }
        });

        btnDrop2 = (ImageButton) findViewById(R.id.dropbutton2);
        btnDrop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recipientListView.getVisibility() == View.GONE) {
                    hideAllListview();

                    recipientListView.setVisibility(View.VISIBLE);
                    btnDrop2.setBackgroundResource(R.drawable.plus);

                    if (productListView.getVisibility() == View.VISIBLE)
                        productListView.setVisibility(View.GONE);
                }
                else {
                    recipientListView.setVisibility(View.GONE);
                    btnDrop2.setBackgroundResource(R.drawable.plus);
                }
            }
        });

        String product[] = {"Product 1", "Product 2", "Product 3"};
        RecipientListAdapter.Recipient recipients[] = new RecipientListAdapter.Recipient[3];
        recipients[0] = new RecipientListAdapter.Recipient();
        recipients[0].name = "Suresh Yadav";
        recipients[0].imageResource = R.drawable.person1;

        recipients[1] = new RecipientListAdapter.Recipient();
        recipients[1].name = "Kailash Kumar";
        recipients[1].imageResource = R.drawable.person2;

        recipients[2] = new RecipientListAdapter.Recipient();
        recipients[2].name = "Vinod Kabra";
        recipients[2].imageResource = R.drawable.person3;


        TokenListAdapter productAdapter = new TokenListAdapter(BrandSocialCreateRefer.this, product);
        referviaTXT.setTokenListView(productListView, productAdapter);

        RecipientListAdapter recipientAdapter = new RecipientListAdapter(BrandSocialCreateRefer.this, recipients);
        recipientListView.setAdapter(recipientAdapter);
        addrecipientsTXT.setRecipientListView(recipientListView, recipientAdapter);

    }

    private void hideAllListview() {
        productListView.setVisibility(View.GONE);
        recipientListView.setVisibility(View.GONE);
        btnDrop1.setBackgroundResource(R.drawable.check);
        btnDrop2.setBackgroundResource(R.drawable.plus);
    }

    private void showErrorMessage() {
        if (txtErrorMessage.getVisibility() != View.GONE)
            return;

        txtErrorMessage.setVisibility(View.VISIBLE);
    }

}
