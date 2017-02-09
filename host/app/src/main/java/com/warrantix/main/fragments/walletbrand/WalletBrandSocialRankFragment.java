package com.warrantix.main.fragments.walletbrand;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.product.BrandSocialCreateRank;
import com.warrantix.main.adapter.WalletBrandSocialRankList;
import com.warrantix.main.fragments.BaseFragment;

import java.util.ArrayList;


/**
 * Created by MyUserName on 3/4/2016.
 */
public class WalletBrandSocialRankFragment extends BaseFragment {
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
    private ArrayList<String> days = new ArrayList<String>();
    private ArrayList<String> imageID = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_brand_social_rank, container, false);
        InitializeView();
        return mView;
    }

    public void InitializeView(){
        revealtxt = (TextView) mView.findViewById(R.id.revealTXT);
        reveal1txt = (TextView) mView.findViewById(R.id.revealTXT1);

        mFabButton = (ImageButton) mView.findViewById(R.id.fabButton);
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, BrandSocialCreateRank.class);
                if (mContext instanceof BaseActivity)
                    ((BaseActivity)mContext).startActivity(i, true);
                else
                    mContext.startActivity(i);
            }
        });
        frameLayout = (LinearLayout)mView.findViewById(R.id.framelayout);


        list=(ListView)mView.findViewById(R.id.list);
        list.setAdapter(null);


        name.add("Srinivasan M.K");
        name.add("Ajay Patel");
        name.add("Rahul Kumar");
        name.add("Srinivasan M.K");

        place.add("owns a Hero Karizma ZMR");
        place.add("owns a Hero Splendor");
        place.add("owns a Hero Splendor");
        place.add("owns a Hero Karizma ZMR");

        days.add("1 day");
        days.add("12/15/2015");
        days.add("12/15/2015");
        days.add("1 day");

        imageID.add("person1");
        imageID.add("person2");
        imageID.add("person3");
        imageID.add("person4");


        String [] name1 = name.toArray(new String[name.size()]);
        String [] place1 = place.toArray(new String[place.size()]);
        String [] days1 = days.toArray(new String[days.size()]);
        String [] imageID1 = imageID.toArray(new String[imageID.size()]);
        WalletBrandSocialRankList adapter = new WalletBrandSocialRankList(getActivity(), name1,place1,days1,imageID1);
        list.setAdapter(adapter);

//
//        mFabButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                touched = true;
//                return false;
//            }
//        });
//
//        frameLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(touched == true) // any event from down and move
//                {
//                    AbsoluteLayout.LayoutParams lp = new AbsoluteLayout.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT,(int)event.getX()-mFabButton.getWidth()/2,(int)event.getY()-mFabButton.getHeight()/2);
//                    mFabButton.setLayoutParams(lp);
//                }
//                if(event.getAction()==MotionEvent.ACTION_UP){
//                    touched = false;
//                }
//                return true;
//            }
//        });

    }

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.fabButton){
//            list.setAdapter(null);
//            name.add("Srinivasan M.K");
//            place.add("owns a Hero Karizma ZMR");
//            days.add("1 day");
//            imageID.add("person1");
//            String [] name1 = name.toArray(new String[name.size()]);
//            String [] place1 = place.toArray(new String[place.size()]);
//            String [] days1 = days.toArray(new String[days.size()]);
//            String [] imageID1 = imageID.toArray(new String[imageID.size()]);
//            WalletBrandSocialRankList adapter = new WalletBrandSocialRankList(getActivity(), name1,place1,days1,imageID1);
//            list.setAdapter(adapter);
//        }
//
//    }

//    @Override
//    public boolean onTouch(View v, MotionEvent me) {
//        // TODO Auto-generated method stub
//
//        if (me.getAction() == MotionEvent.ACTION_DOWN) {
//            System.out.println("up");
//            status = 0;
//
//        }
//        if (me.getAction() == MotionEvent.ACTION_UP) {
//            status = 1;
//            //Log.i("Drag", "Stopped Dragging");
//        } else if (me.getAction() == MotionEvent.ACTION_MOVE) {
//            if (status == 0) {
//                System.out.println("Dragging");
//
//                mFabButton.setPadding((int) me.getRawX(), (int) me.getRawY(), 0, 0);
//                //  b.setPadding(0,50,0,0);
//                mFabButton.invalidate();
//
//            }
//        }
//        return false;
//    }



}
