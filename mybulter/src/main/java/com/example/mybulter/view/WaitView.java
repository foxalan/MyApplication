package com.example.mybulter.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.mybulter.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Function : 自定义进入页面时的加载View
 * Author : Alan
 * Modify Date : 12/8/17
 * Issue : 1.半径没有设置好
 * Whether solve :
 */

public class WaitView extends View {

    private int firstColor;
    private int secondColor;

    private int radius;
    private int totalCount;

    private int mWidth;
    private int mHeight;

    private Paint mPaint;
    private RectF rect;

    private float itemSize;
    private int mSplitSize = 15;

    private int startPosition;

    public WaitView(Context context) {
        this(context, null);
    }

    public WaitView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WaitView, defStyleAttr, 0);

        firstColor = typedArray.getColor(R.styleable.WaitView_firstColor, Color.WHITE);
        secondColor = typedArray.getColor(R.styleable.WaitView_secondColor, Color.GRAY);

        radius = typedArray.getDimensionPixelSize(R.styleable.WaitView_radius, 20);
        totalCount = typedArray.getInteger(R.styleable.WaitView_number, 20);

        initPaint();

        initRunnable();
    }

    private void initRunnable() {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (startPosition == 19) {
                    startPosition = 0;
                }

                postInvalidate();
                startPosition++;

            }
        }, 0, 50);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(40);
        mPaint.setAntiAlias(true);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = 200;
        mHeight = 200;

        initRect();

        setMeasuredDimension(mWidth, mHeight);
    }

    private void initRect() {

        rect = new RectF();
        rect.set(mWidth / 2 - 60, mHeight / 2 - 60, mWidth / 2 + 60, mHeight / 2 + 60);

        itemSize = (360 * 1.0f - totalCount * mSplitSize) / totalCount;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(firstColor);

        for (int i = 0; i < totalCount; i++) {
            canvas.drawArc(rect, i * (itemSize + mSplitSize), itemSize, false, mPaint);
        }

        mPaint.setColor(secondColor);


        for (int j = startPosition; j < startPosition + 3; j++) {

            canvas.drawArc(rect, j * (itemSize + mSplitSize), itemSize, false, mPaint);
        }


    }
}
