package com.beauney.balloondemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MultiBalloonActivity extends AppCompatActivity {

    MultipleBalloonView mMultipleBalloonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_balloon);

        mMultipleBalloonView = findViewById(R.id.multi_balloon_view);
        mMultipleBalloonView.setAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("Debug", "动画结束，执行其他动画");
            }
        });
        mMultipleBalloonView.start();
    }
}
