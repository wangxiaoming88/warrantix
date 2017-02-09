package com.warrantix.main.adapter;

import android.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletBrandSocial;
import com.warrantix.main.activities.social.WalletBrandSocialChatMessage;

public class WalletBrandListOrderList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name1,name,days;
    private final String[] imageId;

    public WalletBrandListOrderList(Activity context, String[] name, String[] name1, String[] days, String[] imageId) {
        super(context, R.layout.activity_brand_list_order_list, name);
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
            rowView = inflater.inflate(R.layout.activity_brand_list_order_list, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);
        nameText.setText(name[position]);
        nameText1.setText(name1[position]);
        daysText.setText(days[position]);

        ImageButton brand_insurance_call = (ImageButton) rowView.findViewById(R.id.brand_insurance_call);
        brand_insurance_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarrantixApplication.getInstance().showDial(context);
            }
        });

        ImageButton brand_insurance_shop = (ImageButton) rowView.findViewById(R.id.brand_insurance_shop);
        brand_insurance_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, WalletBrandSocialChatMessage.class);
                i.putExtra("title", "START A NEW CHAT");

//                Intent i = new Intent(context, WalletBrandSocial.class);
//                i.putExtra("selected", 4);

                if (context instanceof BaseActivity)
                    ((BaseActivity) context).startActivity(i, true);
                else
                    context.startActivity(i);
            }
        });


            if (position==0) {
                Picasso.with(this.context.getApplicationContext())
                        .load(R.drawable.karizma3)
                        .error(R.drawable.image_holder)
                        .placeholder(R.drawable.image_holder)
                        .into(imageView);

            } else if (position==1) {
                Picasso.with(this.context.getApplicationContext())
                        .load(R.drawable.washing_machine3)
                        .error(R.drawable.image_holder)
                        .placeholder(R.drawable.image_holder)
                        .into(imageView);
            }else {
                Picasso.with(this.context.getApplicationContext())
                        .load(R.drawable.karizma3)
                        .error(R.drawable.image_holder)
                        .placeholder(R.drawable.image_holder)
                        .into(imageView);
            }

        return rowView;
    }
}