package com.warrantix.main.fragments.walletbrand;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletBrandProductsDetail;
import com.warrantix.main.activities.product.BrandSocialCreateRank;
import com.warrantix.main.activities.social.WalletBrandSocialChatMessage;
import com.warrantix.main.activities.social.WalletBrandSocialSelectContact;
import com.warrantix.main.adapter.WalletBrandSocialChatList;
import com.warrantix.main.adapter.WalletBrandSocialRankList;
import com.warrantix.main.common.rest.model.ChatMessage;
import com.warrantix.main.fragments.BaseFragment;

import java.util.ArrayList;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandSocialChatFragment extends BaseFragment {
    private Context mContext;
    private View mView;
    private TextView revealtxt;
    private TextView reveal1txt;

    private ImageButton mFabButton;

    private ListView list = null;
    private Intent mIntent;
    private int status=0;
    private boolean touched = true;
    private LinearLayout frameLayout;

    private ArrayList<String> name = new ArrayList<String>();
    private ArrayList<String> place = new ArrayList<String>();
    private ArrayList<String> comments = new ArrayList<String>();
    private ArrayList<String> days = new ArrayList<String>();
    private ArrayList<String> imageID = new ArrayList<String>();
    private ArrayList<Integer> messageCount = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_brand_social_chat, container, false);
        InitializeView();
        return mView;
    }

    public void InitializeView() {
        revealtxt = (TextView) mView.findViewById(R.id.revealTXT);
        reveal1txt = (TextView) mView.findViewById(R.id.revealTXT1);

        mFabButton = (ImageButton) mView.findViewById(R.id.fabButton);
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, BrandSocialCreateRank.class);
                if (mContext instanceof BaseActivity)
                    ((BaseActivity) mContext).startActivity(i, true);
                else
                    mContext.startActivity(i);
            }
        });
        frameLayout = (LinearLayout) mView.findViewById(R.id.framelayout);

        list = (ListView) mView.findViewById(R.id.list);
        list.setAdapter(null);


        name.add("Srinivasan M.K");
        name.add("Ajay Patel");
        name.add("Rahul Kumar");
        name.add("Srinivasan M.K");

        place.add("Hero Splendor");
        place.add("Hero Splendor");
        place.add("Hero Splendor");
        place.add("Hero Splendor");

        comments.add("Lorem ipsum dolor sit amet, consectur adipising elit. auris convallis ipsum sit amet porttitor blandit. Send at cursus nunc, various elementum dui. Cras vestibulum lorem ut lectus aliquet, sed maximuns eros mattis. In et diam lobortis , fringilla dolor");
        comments.add("Lorem ipsum dolor sit amet, consectur adipising elit. auris convallis ipsum sit amet porttitor blandit. Send at cursus nunc, various elementum dui. Cras vestibulum lorem ut lectus aliquet, sed maximuns eros mattis. In et diam lobortis , fringilla dolor");
        comments.add("Lorem ipsum dolor sit amet, consectur adipising elit. auris convallis ipsum sit amet porttitor blandit. Send at cursus nunc, various elementum dui. Cras vestibulum lorem ut lectus aliquet, sed maximuns eros mattis. In et diam lobortis , fringilla dolor");
        comments.add("Lorem ipsum dolor sit amet, consectur adipising elit. auris convallis ipsum sit amet porttitor blandit. Send at cursus nunc, various elementum dui. Cras vestibulum lorem ut lectus aliquet, sed maximuns eros mattis. In et diam lobortis , fringilla dolor");

        days.add("5:38 PM");
        days.add("Yesterday");
        days.add("01-04-2016");
        days.add("01-04-2016");

        imageID.add("person1");
        imageID.add("person2");
        imageID.add("person3");
        imageID.add("person4");

        messageCount.add(81);
        messageCount.add(9);
        messageCount.add(0);
        messageCount.add(0);

        String[] name1 = name.toArray(new String[name.size()]);
        String[] place1 = place.toArray(new String[place.size()]);
        String[] days1 = days.toArray(new String[days.size()]);
        String[] imageID1 = imageID.toArray(new String[imageID.size()]);
        String[] comments1 = comments.toArray(new String[comments.size()]);
        Integer[] messageCount1 = messageCount.toArray(new Integer[messageCount.size()]);
        WalletBrandSocialChatList adapter = new WalletBrandSocialChatList(getActivity(), name1, place1, days1, imageID1, comments1, messageCount1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent mIntent = new Intent(mContext, WalletBrandSocialChatMessage.class);
                mIntent.putExtra("loadMessage", true);
                if (mContext instanceof BaseActivity)
                    ((BaseActivity) mContext).startActivity(mIntent, true);
                else
                    mContext.startActivity(mIntent);
            }
        });

        ImageButton fabButton = (ImageButton) mView.findViewById(R.id.fabButton);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, WalletBrandSocialSelectContact.class);
                if (mContext instanceof BaseActivity)
                    ((BaseActivity)mContext).startActivity(i, true);
                else
                    mContext.startActivity(i);
            }
        });
    }
}
