package com.warrantix.main.adapter;

import android.app.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;

import java.util.ArrayList;

public class WalletBrandSocialChatList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name,place,days, comments;
    private final String[] imageId;
    private final Integer[] messageCount;

    public WalletBrandSocialChatList(Activity context, String[] name, String[] place, String[] days, String[] imageId, String[] comments, Integer[] messageCount) {
        super(context, R.layout.listview_wallet_brand_social_chat, name);
        this.context = context;
        this.name = name;
        this.place=place;
        this.days = days;
        this.imageId = imageId;
        this.comments = comments;
        this.messageCount = messageCount;
    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.listview_wallet_brand_social_chat, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView typeText = (TextView) rowView.findViewById(R.id.type);
        TextView messageText = (TextView) rowView.findViewById(R.id.chatmessage);
        Button messageCountBTN = (Button) rowView.findViewById(R.id.messageCount);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);

        nameText.setText(name[position]);
        typeText.setText(place[position]);
        messageText.setText(comments[position]);
        daysText.setText(days[position]);
        if (messageCount[position] != 0)
            messageCountBTN.setText(Integer.toString(messageCount[position]));
        else
            messageCountBTN.setVisibility(View.GONE);

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