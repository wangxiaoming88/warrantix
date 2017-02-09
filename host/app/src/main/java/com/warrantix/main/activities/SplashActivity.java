package com.warrantix.main.activities;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.eftimoff.androipathview.PathView;
import com.warrantix.main.R;

import java.util.concurrent.CountDownLatch;

/**
 * Splash Activity is the start-up activity that appears until a delay is expired
 * or the user taps the screen.  When the splash activity starts, various app
 * initialization operations are performed.
 */
public class SplashActivity extends BaseActivity {
    private final static String LOG_TAG = SplashActivity.class.getSimpleName();
    private final CountDownLatch timeoutLatch = new CountDownLatch(1);
    private PathView pathView;
    private Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            thread = new Thread(new Runnable() {
                public void run() {
                    // Wait for the splash timeout.
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) { }

                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent, true);

                    finish();

                }
            });
            thread.start();
            initializeAnimationView();

            isInitialized = true;
        }
    }


    private void initializeAnimationView() {
        pathView = (PathView) findViewById(R.id.pathView);
        pathView.useNaturalColors();
        pathView.setFillAfter(true);
        pathView.getPathAnimator()
                .delay(100)
                .duration(3000)
                .interpolator(new AccelerateDecelerateInterpolator())
                .start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d("SplashActivity", "OnTouchEvent is called");

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent, true);

        finish();
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
