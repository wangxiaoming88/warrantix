package com.warrantix.main.adapter;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.warrantix.main.R;
import com.squareup.picasso.Picasso;

public class WalletBrandSocialSelectContactList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name;
    private final String[] type;

    public WalletBrandSocialSelectContactList(Activity context, String[] name, String[] type) {
        super(context, R.layout.listview_social_select_contact, name);

        this.context = context;
        this.name = name;
        this.type = type;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_social_select_contact, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView typeText = (TextView) rowView.findViewById(R.id.type);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        nameText.setText(name[position]);
        typeText.setText(type[position]);

        if (position==0) {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.person1)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);

        } else if (position==1) {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.person2)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }else if (position==2){
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.person3)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }else {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.person1)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }

        return rowView;
    }

}