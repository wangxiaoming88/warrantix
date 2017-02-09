package com.warrantix.main.activities.social;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.WalletBrandSocialSelectContactList;

public class WalletBrandSocialSelectContact extends BaseActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_select_contact);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    public void Initialize() {
        list = (ListView) findViewById(R.id.list);

        String[] name = { "Srinivasan M.K.", "Pankaj Vadhel", "Seetaraman Narayanan","Hardik Sindha", "Vishal Chauhan"};
        String[] type = { "Hero Splendor", "Karizma ZMR", "Hero CBZ","Hero Splendor", "Hero Passion"};

        WalletBrandSocialSelectContactList listAdapter = new WalletBrandSocialSelectContactList(this, name, type);
        list.setAdapter(listAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(WalletBrandSocialSelectContact.this, WalletBrandSocialChatMessage.class);
                mIntent.putExtra("title", "START A NEW CHAT");
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
    }
}