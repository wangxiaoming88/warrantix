package com.warrantix.main.adapter;

import android.app.Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletBrandFinance;
import com.warrantix.main.activities.WalletBrandFinance1;
import com.warrantix.main.activities.WalletBrandInsurance;

public class WalletBrandFinanceList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name,place;
    private final String[] imageId;

    public WalletBrandFinanceList(Activity context, String[] name, String[] place, String[] imageId) {
        super(context, R.layout.listview_wallet_brand_finance, name);
        this.context = context;
        this.name = name;
        this.place=place;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_wallet_brand_finance, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView placeText = (TextView) rowView.findViewById(R.id.place);
        nameText.setText(name[position]);
        placeText.setText(place[position]);

        Button shopButton = (Button) rowView.findViewById(R.id.brand_insurance_shop);
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof BaseActivity) {
                    Intent i = new Intent(context, WalletBrandFinance1.class);
                    ((BaseActivity)context).startActivity(i, true);
                }
                else {
                    Intent i = new Intent(context, WalletBrandFinance1.class);
                    context.startActivity(i);
                }

//                Intent i = new Intent(context, WalletBrandFinance.class);
//                if (context instanceof BaseActivity)
//                    ((BaseActivity)context).startActivity(i, true);
//                else
//                    context.startActivity(i);
            }
        });

        ImageButton brand_insurance_call = (ImageButton) rowView.findViewById(R.id.brand_insurance_call);
        brand_insurance_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarrantixApplication.getInstance().showDial(context);
            }
        });

        return rowView;
    }




}