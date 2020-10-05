package com.beauney.balloondemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zengjiantao
 * @since 2018/8/22
 */
public class MultipleBalloonView extends View {

    private static final int DURATION = 3000;

    private int mCurrTime;

    private List<Balloon> mBalloons;

    public MultipleBalloonView(Context context) {
        super(context);
        init();
    }

    public MultipleBalloonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MultipleBalloonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Balloon balloon : mBalloons) {
            balloon.onDraw(canvas, mCurrTime, getWidth(), getHeight());
        }
    }

    private void init() {
        mBalloons = new ArrayList<>();
        Balloon balloon1 = new Balloon();
        balloon1.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_1);
        balloon1.delay = 0;
        balloon1.endX = 0;
        balloon1.endY = 68;
        mBalloons.add(balloon1);

        Balloon balloon2 = new Balloon();
        balloon2.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_2);
        balloon2.delay = 50;
        balloon2.endX = 292;
        balloon2.endY = 100;
        mBalloons.add(balloon2);

        Balloon balloon3 = new Balloon();
        balloon3.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_3);
        balloon3.delay = 100;
        balloon3.endX = 57;
        balloon3.endY = 2;
        mBalloons.add(balloon3);

        Balloon balloon4 = new Balloon();
        balloon4.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_4);
        balloon4.delay = 150;
        balloon4.endX = 225;
        balloon4.endY = -5;
        mBalloons.add(balloon4);

        Balloon balloon5 = new Balloon();
        balloon5.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_5);
        balloon5.delay = 200;
        balloon5.endX = 6;
        balloon5.endY = 135;
        mBalloons.add(balloon5);

        Balloon balloon6 = new Balloon();
        balloon6.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_6);
        balloon6.delay = 250;
        balloon6.endX = 18;
        balloon6.endY = 83;
        mBalloons.add(balloon6);

        Balloon balloon7 = new Balloon();
        balloon7.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_7);
        balloon7.delay = 300;
        balloon7.endX = 207;
        balloon7.endY = 43;
        mBalloons.add(balloon7);

        Balloon balloon8 = new Balloon();
        balloon8.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_8);
        balloon8.delay = 350;
        balloon8.endX = 44;
        balloon8.endY = 106;
        mBalloons.add(balloon8);

        Balloon balloon9 = new Balloon();
        balloon9.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_9);
        balloon9.delay = 400;
        balloon9.endX = 225;
        balloon9.endY = 71;
        mBalloons.add(balloon9);

        Balloon balloon10 = new Balloon();
        balloon10.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_10);
        balloon10.delay = 450;
        balloon10.endX = 40;
        balloon10.endY = 46;
        mBalloons.add(balloon10);

        Balloon balloon11 = new Balloon();
        balloon11.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_11);
        balloon11.delay = 500;
        balloon11.endX = 179;
        balloon11.endY = 82;
        mBalloons.add(balloon11);

        Balloon balloon12 = new Balloon();
        balloon12.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_12);
        balloon12.delay = 550;
        balloon12.endX = 142;
        balloon12.endY = 102;
        mBalloons.add(balloon12);

        Balloon balloon13 = new Balloon();
        balloon13.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_13);
        balloon13.delay = 600;
        balloon13.endX = 146;
        balloon13.endY = 33;
        mBalloons.add(balloon13);

        Balloon balloon14 = new Balloon();
        balloon14.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_14);
        balloon14.delay = 650;
        balloon14.endX = 80;
        balloon14.endY = 59;
        mBalloons.add(balloon14);

        post(new Runnable() {
            @Override
            public void run() {
                start();
            }
        });
    }

    private void start() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, DURATION + 700);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrTime = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(DURATION);
        valueAnimator.start();
    }


    private class Balloon {
        Bitmap bitmap;

        float endX;

        float endY;

        int delay;

        void onDraw(Canvas canvas, int currTime, int width, int height) {
            if (currTime < delay) {
                return;
            }
            float startX = (width - bitmap.getWidth()) / 2;
            float startY = height - bitmap.getHeight();
            int duration = DURATION;
            currTime = currTime - delay;
            if (currTime > DURATION) {
                currTime = DURATION;
            }
            float speedX = (endX - startX) / duration;
            float speedY = (endY - startY) / duration;

            float currX = startX + speedX * currTime;
            float currY = startY + speedY * currTime;
            canvas.drawBitmap(bitmap, currX, currY, null);
        }
    }
}
