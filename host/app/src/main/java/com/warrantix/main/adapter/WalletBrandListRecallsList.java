package com.warrantix.main.adapter;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.squareup.picasso.Picasso;

public class WalletBrandListRecallsList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name1,name,name2,days;
    private final String[] imageId;

    public WalletBrandListRecallsList(Activity context, String[] name, String[] name1, String[] name2, String[] days,String[] imageId) {
        super(context, R.layout.activity_brand_list_recalls_list, name);
        this.context = context;
        this.name = name;
        this.name1=name1;
        this.name2=name2;
        this.days=days;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.activity_brand_list_recalls_list, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
        TextView nameText2 = (TextView) rowView.findViewById(R.id.name2);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);


        nameText.setText(name[position]);
        nameText1.setText(name1[position]);
        //nameText2.setText(name2[position]);
        daysText.setText(days[position]);



        if (position==0) {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.hero3)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);

        } else if (position==1) {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.godrej3)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }else if (position==2) {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.lg3)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        } else {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.hero3)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }

        return rowView;
    }



}