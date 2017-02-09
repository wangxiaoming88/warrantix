package com.warrantix.main.activities;


import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;


/**
 * Created by MyUserName on 2/26/2016.
 */

public class WalletBrandAlertNotification extends BaseActivity {

    private boolean isReceiveEmailNotification = false;
    private boolean isReceiveMobileSMS = false;
    private boolean isNewProductVideos = false;
    private boolean isSurveyPolls = false;
    private boolean isProductRecalls = false;
    private boolean isReceiveCalls = false;
    private boolean isPostServiceFeedback = false;
    private boolean isReferralProgram = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_notification);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    public void Initialize(){
        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

//        final ImageView emailNotificationView = (ImageView) findViewById(R.id.acceptEmailNotification);
//        emailNotificationView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isReceiveEmailNotification == false)
//                    isReceiveEmailNotification = true;
//                else
//                    isReceiveEmailNotification = false;
//
//                if (isReceiveEmailNotification == false)
//                    emailNotificationView.setBackgroundResource(R.drawable.square);
//                else
//                    emailNotificationView.setBackgroundResource(R.drawable.square_tick);
//            }
//        });
//
//
//        final ImageView mobileSMSView = (ImageView) findViewById(R.id.acceptMobileSMS);
//        mobileSMSView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isReceiveMobileSMS == false)
//                    isReceiveMobileSMS = true;
//                else
//                    isReceiveMobileSMS = false;
//
//                if (isReceiveMobileSMS == false)
//                    mobileSMSView.setBackgroundResource(R.drawable.square);
//                else
//                    mobileSMSView.setBackgroundResource(R.drawable.square_tick);
//            }
//        });
//
//        final ImageView productVideosView = (ImageView) findViewById(R.id.acceptProductVideo);
//        productVideosView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isNewProductVideos == false)
//                    isNewProductVideos = true;
//                else
//                    isNewProductVideos = false;
//
//                if (isNewProductVideos == false)
//                    productVideosView.setBackgroundResource(R.drawable.square);
//                else
//                    productVideosView.setBackgroundResource(R.drawable.square_tick);
//
//            }
//        });
//
//        final ImageView surveyPollsView = (ImageView) findViewById(R.id.acceptSurvey);
//        surveyPollsView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isSurveyPolls == false)
//                    isSurveyPolls = true;
//                else
//                    isSurveyPolls = false;
//
//                if (isSurveyPolls == false)
//                    surveyPollsView.setBackgroundResource(R.drawable.square);
//                else
//                    surveyPollsView.setBackgroundResource(R.drawable.square_tick);
//            }
//        });
//
//        final ImageView productRecallsView = (ImageView) findViewById(R.id.acceptProductRecalls);
//        productRecallsView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isProductRecalls == false)
//                    isProductRecalls = true;
//                else
//                    isProductRecalls = false;
//
//                if (isProductRecalls == false)
//                    productRecallsView.setBackgroundResource(R.drawable.square);
//                else
//                    productRecallsView.setBackgroundResource(R.drawable.square_tick);
//            }
//        });
//
//        final ImageView receiveCallsView = (ImageView) findViewById(R.id.acceptReceiveCalls);
//        receiveCallsView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isReceiveCalls == false)
//                    isReceiveCalls = true;
//                else
//                    isReceiveCalls = false;
//
//                if (isReceiveCalls == false)
//                    receiveCallsView.setBackgroundResource(R.drawable.square);
//                else
//                    receiveCallsView.setBackgroundResource(R.drawable.square_tick);
//            }
//        });
//
//        final ImageView postServiceFeedbackView = (ImageView) findViewById(R.id.acceptPostServiceFeedback);
//        postServiceFeedbackView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isPostServiceFeedback == false)
//                    isPostServiceFeedback = true;
//                else
//                    isPostServiceFeedback = false;
//
//                if (isPostServiceFeedback == false)
//                    postServiceFeedbackView.setBackgroundResource(R.drawable.square);
//                else
//                    postServiceFeedbackView.setBackgroundResource(R.drawable.square_tick);
//            }
//        });
//
//        final ImageView referralProgramView = (ImageView) findViewById(R.id.acceptReferralProgram);
//        referralProgramView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isReferralProgram == false)
//                    isReferralProgram = true;
//                else
//                    isReferralProgram = false;
//
//                if (isReferralProgram == false)
//                    referralProgramView.setBackgroundResource(R.drawable.square);
//                else
//                    referralProgramView.setBackgroundResource(R.drawable.square_tick);
//            }
//        });

    }

}
