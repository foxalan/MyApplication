package com.example.adanvace.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.adanvace.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 4/8/17$
 * Input Parameter 转动效果不太理想,总感觉少了一点
 */

public class RadiusView extends View {

    private int mRadius;
    private int background_color;
    private int run_color;

    private int mWidth;
    private int mHeight;

    private Paint mPaint;


    private RectF radiuRect;

    private int progress = 0;
    private int radius;
    // 半径

    private boolean isFirst = true;


    public RadiusView(Context context) {
        this(context, null);
    }

    public RadiusView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadiusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RadiusView, defStyleAttr, 0);

        mRadius = (int) typedArray.getDimension(R.styleable.RadiusView_radius_size, 15);
        background_color = typedArray.getColor(R.styleable.RadiusView_background_color, Color.BLUE);
        run_color = typedArray.getColor(R.styleable.RadiusView_radius_color, Color.RED);

        initPaint();
        initEvent();
    }


    private void initPaint() {

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mRadius);


        // 用于定义的圆弧的形状和大小的界限

    }

    private void initEvent() {
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                progress = progress + 10;
//
//                if (progress == 370) {
//                    progress = 10;
//                    isFirst = false;
//                }
//
//                Log.d("TANG", progress + ":");
//
//                postInvalidate();
//
//            }
//        }, 1000, 50);

        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {

                    if (progress == 360) {
                        progress = 1;
                    }

                    postInvalidate();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progress++;


                }
            }
        }.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpec = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpec = MeasureSpec.getMode(heightMeasureSpec);

        switch (widthSpec) {
            case MeasureSpec.EXACTLY:
                mWidth = MeasureSpec.getSize(widthMeasureSpec);
                break;
            case MeasureSpec.AT_MOST:
                mWidth = 300;
                break;
            default:
                mWidth = 300;
                break;
        }

        switch (heightSpec) {
            case MeasureSpec.EXACTLY:
                mHeight = MeasureSpec.getSize(heightMeasureSpec);
                break;
            case MeasureSpec.AT_MOST:
                mHeight = 300;
                break;
            default:
                mHeight = 300;
                break;
        }

        setMeasuredDimension(mWidth, mHeight);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        radius = mWidth / 2 - mRadius / 2;
        radiuRect = new RectF(mWidth / 2 - radius, mWidth / 2 - radius, mWidth / 2 + radius, mWidth / 2 + radius);


        mPaint.setColor(background_color);
        canvas.drawCircle(mWidth / 2, mWidth / 2, radius, mPaint);

        mPaint.setColor(run_color);
        canvas.drawArc(radiuRect, progress - 180, 180, false, mPaint); // 根据进度画圆弧

    }
}
