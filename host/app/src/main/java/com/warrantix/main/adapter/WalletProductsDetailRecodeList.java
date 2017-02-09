package com.warrantix.main.adapter;

import android.app.Activity;
import android.graphics.LinearGradient;
import android.graphics.Paint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.WarrantixApplication;

public class WalletProductsDetailRecodeList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name1,name,days;
    private final String[] imageId;

    public WalletProductsDetailRecodeList(Activity context, String[] name, String[] name1, String[] days, String[] imageId) {
        super(context, R.layout.listview_wallet_brand_product_recode, name);
        this.context = context;
        this.name = name;
        this.name1=name1;
        this.days=days;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_wallet_brand_product_recode, null, true);
        else
            rowView = view;

        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        TextView timesText = (TextView) rowView.findViewById(R.id.times);
        TextView service_typeText = (TextView) rowView.findViewById(R.id.service_type);
        TextView parts_replaceText = (TextView) rowView.findViewById(R.id.parts_replace);
        TextView product_canditionText = (TextView) rowView.findViewById(R.id.product_candition);
        TextView rating_markText = (TextView) rowView.findViewById(R.id.rating_mark);
        TextView charge_rsText = (TextView) rowView.findViewById(R.id.charge_RS);
        TextView priceText = (TextView) rowView.findViewById(R.id.price);
        TextView invoiceText = (TextView) rowView.findViewById(R.id.invoice);

        invoiceText.setPaintFlags(invoiceText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //nameText.setText(name[position]);

        LinearLayout invoiceBTN = (LinearLayout) rowView.findViewById(R.id.invoiceBTN);
        invoiceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarrantixApplication.getInstance().openPDFFiles(context);
            }
        });
        return rowView;
    }



}