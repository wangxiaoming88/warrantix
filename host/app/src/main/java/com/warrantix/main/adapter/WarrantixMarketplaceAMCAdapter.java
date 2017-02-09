package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Intent;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.activities.WalletBrandAMC0;
import com.warrantix.main.activities.WalletBrandProductsDetail;
import com.warrantix.main.activities.productdetail.ProductsDetailScheduleService;

public class WarrantixMarketplaceAMCAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name1,name0,name,days;
    private final String[] imageId;

    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private int progressStatus = 0;

    public WarrantixMarketplaceAMCAdapter(Activity context, String[] name, String[] name0, String[] name1, String[] days, String[] imageId) {
        super(context, R.layout.listview_marketplace_amc, name);
        this.context = context;
        this.name = name;
        this.name0 = name0;
        this.name1=name1;
        this.days=days;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_marketplace_amc, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
        TextView nameText0 = (TextView) rowView.findViewById(R.id.name0);
        TextView nameTextGone = (TextView) rowView.findViewById(R.id.name1GONE);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        nameText.setText(name[position]);
        nameText0.setText(name0[position]);
        nameText1.setText(name1[position]);
        daysText.setText(days[position] + " days to go");

        if (nameText0.getText().toString()!=""){
            nameText0.setVisibility(View.VISIBLE);
            nameTextGone.setVisibility(View.GONE);
        }else {
            nameText0.setVisibility(View.GONE);
            nameTextGone.setVisibility(View.VISIBLE);
        }

        if (Math.random()>=0.5) {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.wallet_product_1)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);

        }
        else {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.wallet_product_2)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }

        Button serviceBtn =  (Button) rowView.findViewById(R.id.products_service_button);
        serviceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //...
                Intent mIntent = new Intent(getContext(),WalletBrandAMC0.class);
                if (context instanceof BaseActivity)
                    ((BaseActivity) context).startActivity(mIntent, true);
                else
                    getContext().startActivity(mIntent);
            }
        });

        return rowView;
    }
}