package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.activities.WalletBrandAccessoriesEshop;
import com.warrantix.main.activities.WalletMarketplaceSubCategory;
import com.warrantix.main.activities.registration.ScanCodeActivity;
import com.warrantix.main.dialog.MessageDialog;

public class WarrantixMarketplaceSubCategoryAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] names, places, prices;
    private final String[] imageId;
    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private int progressStatus = 0;

    private Button serviceBTN;
    private Button shopBTN;

    public WarrantixMarketplaceSubCategoryAdapter(Activity context, String[] names, String[] places, String[] prices, String[] imageId) {
        super(context, R.layout.listview_marketplace_subcategory, names);
        this.context = context;
        this.names = names;
        this.places = places;
        this.prices = prices;
        this.imageId = imageId;

    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_marketplace_subcategory, null, true);
        else
            rowView = view;

        serviceBTN = (Button) rowView.findViewById(R.id.products_service);
        serviceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ScanCodeActivity.class);
                if (context instanceof BaseActivity)
                    ((BaseActivity)context).startActivity(i, true);
                else
                    context.startActivity(i);
            }
        });
        shopBTN = (Button) rowView.findViewById(R.id.shopBTN);
        shopBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, WalletBrandAccessoriesEshop.class);
                if (context instanceof WalletMarketplaceSubCategory)
                    i.putExtra("title", ((WalletMarketplaceSubCategory)context).getSubCategoryTitle());

                i.putExtra("productName", names[position]);
                i.putExtra("productImage", position % 3);
                if (context instanceof BaseActivity)
                    ((BaseActivity)context).startActivity(i, true);
                else
                    context.startActivity(i);
            }
        });

        final LinearLayout socialButton = (LinearLayout) rowView.findViewById(R.id.socialButton);
        socialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] locationInWindow = new int[2];
                socialButton.getLocationInWindow(locationInWindow);

                if (context instanceof WalletMarketplaceSubCategory)
                    ((WalletMarketplaceSubCategory) context).showSocial1Bar(locationInWindow[0], locationInWindow[1], socialButton.getWidth(), socialButton.getHeight());
            }
        });

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView placeText = (TextView) rowView.findViewById(R.id.place);
        TextView priceText = (TextView) rowView.findViewById(R.id.price);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        nameText.setText(names[position]);
        placeText.setText(places[position]);
        priceText.setText(prices[position]);

        if (position % 3 == 0) {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.karizma_bike)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }
        else if (position % 3 == 1) {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.impulse_bike)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }
        else if (position % 3 == 2) {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.super_splendor)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }

        return rowView;
    }



}