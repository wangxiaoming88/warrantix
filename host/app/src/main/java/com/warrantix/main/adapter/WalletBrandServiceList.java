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

public class WalletBrandServiceList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name,distance,place,place1;
    private final String[] imageId;

    public WalletBrandServiceList(Activity context, String[] name, String[] distance, String[] place, String[] place1, String[] imageId) {
        super(context, R.layout.listview_wallet_brand_dealers, name);
        this.context = context;
        this.name = name;
        this.distance=distance;
        this.place=place;
        this.place1=place1;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_wallet_brand_dealers, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView placeText = (TextView) rowView.findViewById(R.id.place);
        TextView place1Text = (TextView) rowView.findViewById(R.id.place1);
        TextView distanceText = (TextView) rowView.findViewById(R.id.distance);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);
        ImageView Dealerstar = (ImageView) rowView.findViewById(R.id.dealer_star);
        Dealerstar.setVisibility(View.GONE);

        nameText.setText(name[position]);
        placeText.setText(place[position]);
        place1Text.setText(place1[position]);
        distanceText.setText(distance[position] + " km");

      //  Resources resources = this.context.getApplicationContext().getResources();
//            imageView.setImageDrawable(resources.getDrawable(Integer.parseInt(imageId[position])));
       // imageView.setImageResource(imageId[position]);
        if (position==0) {
            Dealerstar.setVisibility(View.VISIBLE);
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.zero_motorcycles)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);

        } else if (position==1) {
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.indian_motorcycle)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }else{
            Picasso.with(this.context.getApplicationContext())
                    .load(R.drawable.cfmoto)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView);
        }

      imageView.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            //...
//             Intent intent = new Intent(getContext(),WalletHeroActivity.class);
//             getContext().startActivity(intent);
         //overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
        }
      });

        return rowView;
    }




}