package com.beauney.balloondemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.beauney.balloondemo.utils.DisplayUtil;


/**
 * @author zengjiantao
 * @since 2018/8/22
 */
public class BalloonView extends View {
    private Paint mPaint;

    private Bitmap mBitmap;

    private Path mPath;

    private float mLeft;

    private PointF mStart;

    private PointF mEnd;

    private PointF mControl;

    public BalloonView(Context context) {
        super(context);
        init();
    }

    public BalloonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BalloonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStart = new PointF(0, h);
        mEnd = new PointF();
        mControl = new PointF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, mLeft, 0, mPaint);

        mEnd.x = mLeft + DisplayUtil.dip2px(getContext(),13);
        mEnd.y = mBitmap.getHeight();

        mControl.x = mEnd.x;
        mControl.y = mStart.y - DisplayUtil.dip2px(getContext(),5);

        mPath.reset();
        mPath.moveTo(mStart.x, mStart.y);
        mPath.quadTo(mControl.x, mControl.y, mEnd.x, mEnd.y);

        canvas.drawPath(mPath, mPaint);
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(DisplayUtil.dip2px(getContext(),2));
        mPaint.setStyle(Paint.Style.STROKE);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.balloon);
        mPath = new Path();
        post(new Runnable() {
            @Override
            public void run() {
                start();
            }
        });
    }

    private void start() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, getWidth() - mBitmap.getWidth());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mLeft = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setDuration(2000L);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }
}
