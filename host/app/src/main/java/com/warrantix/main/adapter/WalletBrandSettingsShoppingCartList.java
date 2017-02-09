package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.noveogroup.android.log.Log;
import com.squareup.picasso.Picasso;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletMarketplaceEshopProducts;

import retrofit.http.HEAD;

public class WalletBrandSettingsShoppingCartList extends RecyclerView.Adapter<WalletBrandSettingsShoppingCartList.ViewHolder> {

    // holder inner class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View contentView = null;

        public ViewHolder(View v, int viewType, final Activity context) {
            super(v);
            contentView = v;

            if (viewType == WalletBrandSettingsShoppingCartList.HEADER)
            {
                Button proceedForCheckoutBtn = (Button) v.findViewById(R.id.proceedForCheckoutBTN);
                proceedForCheckoutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WalletMarketplaceEshopProducts.eshop_productsSel=1;
                        Intent i = new Intent(context, WalletMarketplaceEshopProducts.class);
                        i.putExtra("title", "SHOPPING CART");

                        if (context instanceof BaseActivity)
                            ((BaseActivity)context).startActivity(i, true);
                        else
                            context.startActivity(i);
                    }
                });
            }
        }

        public void setActivity(Activity activity) {
        }
    }

    private final Activity context;
    private final String[] name1,name2,price1,price2;
    private final String[] imageId;
    private TextView numberView[];

    private int itemCount = 0;
    public static final int HEADER = 0, CONTENT = 1;

    public WalletBrandSettingsShoppingCartList(Activity activity, String[] name1, String[] name2, String[] price1, String[] price2, String[] imageId) {
        this.context = activity;
        this.name1=name1;
        this.name2 = name2;
        this.price1=price1;
        this.price2=price2;
        this.imageId = imageId;

        this.numberView = new TextView[name1.length];
    }

    @Override
    public int getItemCount() {
        if (name1 == null) {
            itemCount = 1;
        }
        else {
            itemCount = name1.length + 1;
        }

        return itemCount;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        if (i != 0) {
            int pos = i - 1;
            View rowView = holder.contentView;
            TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
            TextView nameText2 = (TextView) rowView.findViewById(R.id.name2);
            TextView priceText1 = (TextView) rowView.findViewById(R.id.price1);
            TextView priceText2 = (TextView) rowView.findViewById(R.id.price2);
            final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

            nameText1.setText(name1[pos]);
            nameText2.setText(name2[pos]);
            priceText1.setText(price1[pos]);
            priceText2.setText(price2[pos]);

            numberView[pos] = (TextView) rowView.findViewById(R.id.number);
            Log.d("ShoppingCart", "pos = " + pos + " numberView =" + numberView[pos]);

            ImageButton upButton = (ImageButton) rowView.findViewById(R.id.up_arrowBTN);
            ImageButton downButton = (ImageButton) rowView.findViewById(R.id.down_arrowBTN);
            upButton.setTag(pos);
            downButton.setTag(pos);
            upButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();

                    int number = Integer.parseInt(numberView[pos].getText().toString());
                    if (number < 1000)
                        number++;
                    numberView[pos].setText(Integer.toString(number));

                    Log.d("ShoppingCart", "Pos = " + pos + " number = " + number);
                }
            });

            downButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();

                    int number = Integer.parseInt(numberView[pos].getText().toString());
                    if (number > 1)
                        number--;
                    numberView[pos].setText(Integer.toString(number));

                    Log.d("ShoppingCart", "Pos = " + pos + " number = " + number);
                }
            });



            if (pos==0) {
                Picasso.with(this.context.getApplicationContext())
                        .load(R.drawable.hunk_deal)
                        .error(R.drawable.image_holder)
                        .placeholder(R.drawable.image_holder)
                        .into(imageView);

            } else if (pos==1) {
                Picasso.with(this.context.getApplicationContext())
                        .load(R.drawable.karizma_deal)
                        .error(R.drawable.image_holder)
                        .placeholder(R.drawable.image_holder)
                        .into(imageView);
            }else {
                Picasso.with(this.context.getApplicationContext())
                        .load(R.drawable.karizma_deal)
                        .error(R.drawable.image_holder)
                        .placeholder(R.drawable.image_holder)
                        .into(imageView);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER;
        else
            return CONTENT;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == CONTENT) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_brand_list_settings_shoppingcart_list, viewGroup, false);
            return new ViewHolder(itemView, viewType, context);
        }
        else {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_shoppingcart_header, viewGroup, false);
            return new ViewHolder(itemView, viewType, context);
        }
    }

}