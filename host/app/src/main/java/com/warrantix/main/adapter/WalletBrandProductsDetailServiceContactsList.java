package com.warrantix.main.adapter;

import android.app.Activity;

import android.content.Intent;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MapsActivity;
import com.warrantix.main.activities.WalletBrandAddTechnician;
import com.warrantix.main.activities.WalletBrandDealers;
import com.warrantix.main.activities.productdetail.ProductsDetailServiceContacts;
import com.warrantix.main.common.permission.MarshMallowPermission;

public class WalletBrandProductsDetailServiceContactsList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name1,name,name2, distance, price;
    private final String[] imageId;

    public WalletBrandProductsDetailServiceContactsList(Activity context, String[] name, String[] name1,String[] name2,String[] distance, String[] price, String[] imageId) {
        super(context, R.layout.listview_brand_products_detail_servicecontacts, name);
        this.context = context;
        this.name = name;
        this.name1=name1;
        this.name2=name2;
        this.distance=distance;
        this.price=price;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_brand_products_detail_servicecontacts, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
        TextView nameText2 = (TextView) rowView.findViewById(R.id.name2);
        TextView distanceText = (TextView) rowView.findViewById(R.id.distance);
        TextView priceText = (TextView) rowView.findViewById(R.id.price);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        nameText.setText(name[position]);
        nameText1.setText(name1[position]);
        nameText2.setText(name2[position]);
        distanceText.setText(distance[position]);
        priceText.setText(price[position]);

        if (position==0) {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.person1_1)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);

        }
        else if (position==1) {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.person2_2)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }
        else {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.person3_2)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }

        ImageButton brand_insurance_call = (ImageButton) rowView.findViewById(R.id.brand_insurance_call);
        brand_insurance_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarrantixApplication.getInstance().showDial(context);
            }
        });

        ImageButton brand_insurance_location = (ImageButton) rowView.findViewById(R.id.brand_insurance_location);
        brand_insurance_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (context instanceof ProductsDetailServiceContacts) {
//                    Location location = ((ProductsDetailServiceContacts)context).getMyLocation();
//                    WarrantixApplication.getInstance().showMyLocation(context, location);
//                }
                MarshMallowPermission marshMallowPermission = new MarshMallowPermission(context);

                // request camera
                if (!marshMallowPermission.checkPermissionForGPS()) {
                    marshMallowPermission.requestPermissionForGPS();
                    return;
                }

                Intent i = new Intent(context, MapsActivity.class);
                context.startActivity(i);

            }
        });

        return rowView;
    }



}