package com.warrantix.main.adapter;

import android.app.Activity;
import android.os.Handler;
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

public class WalletBrandProductList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name1,name,days;
    private final String[] imageId;
    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private int progressStatus = 0;
    private Button serviceBTN;

    public WalletBrandProductList(Activity context, String[] name, String[] name1, String[] days, String[] imageId) {
        super(context, R.layout.listview_wallet_product, name);
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
            rowView = inflater.inflate(R.layout.listview_wallet_product, null, true);
        else
            rowView = view;

        serviceBTN = (Button)rowView.findViewById(R.id.products_service_button);
        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        nameText.setText(name[position]);
        nameText1.setText(name1[position]);
        daysText.setText(days[position] + " days to go");



        if (position==0) {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.karizma_bike)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);

        } else if (position==1) {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.impulse_bike)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }else {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.super_splendor)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }
//
//          imageView.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
////                //...
////             Intent intent = new Intent(getContext(),BrandProductSelectActivity.class);
////             getContext().startActivity(intent);
//                Log.e("service_sel--------", String.valueOf("true"));
//
//
//            }
//          });
//
//          serviceBTN.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    //...
//
//              }
//            });
        return rowView;
    }



}