package com.sptek.facilitysupervision;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.sptek.facilitysupervision.permission.SampleActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static boolean mFirstRun = true;
    private ProgressBar mActionBarProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionBarProgress = findViewById(R.id.actionbar_progress);
        mActionBarProgress.setVisibility(ProgressBar.VISIBLE);

        // Starts fade in animation
        ImageView myImageView = findViewById(R.id.loadingImage);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        myImageView.startAnimation(myFadeInAnimation);
        mFirstRun = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, SampleActivity.class));
                finish();
            }
        }, 800);
    }



    public static boolean isFirstRun()
    {
        return mFirstRun;
    }
}
