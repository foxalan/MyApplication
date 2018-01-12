package com.example.adanvace.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.adanvace.R;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 5/8/17
 * Input Parameter & 待优化的地方
 * 1.自定义属性中少了间距
 * 2.对 canvas.drawArc()没有深入理解
 * 3.OnTouch事件没有写完整
 */

public class SoundView extends View {

    private int firstColor;
    private int secondColor;
    private int totalCount;
    private int round_size;

    private int mWidth;
    private int mHeight;

    private Paint mPaint;

    private int radius;
    private RectF rectF;

    private RectF sound_rect;

    private int sound_radius;

    private Bitmap bitmap;

    private int currentCount = 5;

    private int mSplitSize;

    private float itemSize;


    int currentY;
    int downY;


    public SoundView(Context context) {
        this(context, null);
    }

    public SoundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SoundView, defStyleAttr, 0);

        firstColor = typedArray.getColor(R.styleable.SoundView_firstColor, Color.GRAY);
        secondColor = typedArray.getColor(R.styleable.SoundView_secondColor, Color.RED);

        totalCount = typedArray.getColor(R.styleable.SoundView_round_count, 12);
        round_size = typedArray.getDimensionPixelSize(R.styleable.SoundView_round_size, 20);

        initPaint();

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sound);

        setFocusable(true);
    }


    /**
     * 初始化画笔
     */

    private void initPaint() {

        mPaint = new Paint();
        mPaint.setColor(firstColor);
        mPaint.setStrokeWidth(round_size);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setStrokeCap(Paint.Cap.ROUND); //

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpec = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpec = MeasureSpec.getMode(heightMeasureSpec);

        switch (widthSpec) {
            case MeasureSpec.EXACTLY:
                mWidth = MeasureSpec.getSize(widthMeasureSpec) + getPaddingLeft() + getPaddingRight();
                break;
            case MeasureSpec.AT_MOST:
                mWidth = 300;
                break;
            case MeasureSpec.UNSPECIFIED:
                mWidth = 300;
                break;
        }

        switch (heightSpec) {
            case MeasureSpec.EXACTLY:
                mHeight = MeasureSpec.getSize(heightMeasureSpec) + getPaddingLeft() + getPaddingRight();
                break;
            case MeasureSpec.AT_MOST:
                mHeight = 300;
                break;
            case MeasureSpec.UNSPECIFIED:
                mHeight = 300;
                break;
        }

        //设置半径
        setRoundData();

        setMeasuredDimension(mWidth, mHeight);
    }


    private void setRoundData() {

        mSplitSize = 20;

        radius = mWidth / 2 - round_size * 2;

        rectF = new RectF(mWidth / 2 - radius, mHeight / 2 - radius, mWidth / 2 + radius, mHeight / 2 + radius);

        sound_radius = mWidth / 2 - round_size / 2;
        sound_rect = new RectF(mWidth / 2 - sound_radius, mHeight / 2 - sound_radius, mHeight / 2 + sound_radius, mHeight / 2 + sound_radius);

        /**
         * 根据需要画的个数以及间隙计算每个块块所占的比例*360
         */
        itemSize = (360 * 1.0f - totalCount * mSplitSize) / totalCount;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBitmap(canvas);
        drawRound(canvas);
    }

    //画中心图片

    private void drawBitmap(Canvas canvas) {

        /**
         * oval :指定圆弧的外轮廓矩形区域。
           startAngle: 圆弧起始角度，单位为度。
           sweepAngle: 圆弧扫过的角度，顺时针方向，单位为度,从右中间开始为零度。
           useCenter: 如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形。关键是这个变量，下面将会详细介绍。
         */
        canvas.drawBitmap(bitmap, null, rectF, mPaint);
        //   canvas.drawRect(rectF, mPaint);
    }

    //画圆
    private void drawRound(Canvas canvas) {

        mPaint.setColor(firstColor);
        for (int i = 0; i < totalCount; i++) {
            canvas.drawArc(sound_rect, i * (itemSize + mSplitSize), itemSize, false, mPaint); // 根据进度画圆弧
        }

        mPaint.setColor(secondColor);

        for (int i = 0; i < currentCount; i++) {
            canvas.drawArc(sound_rect, i * (itemSize + mSplitSize), itemSize, false, mPaint); // 根据进度画圆弧
        }

    }


    private void addSound() {

        if (currentCount < totalCount) {
            currentCount++;
        }else {
            currentCount = 0;
        }

        postInvalidate();
    }

    private void reduceSound() {

        if (currentCount >= 0) {
            currentCount--;
        }

        postInvalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentY = (int) event.getY();
                addSound();
                Log.d("TANG","DOWN ======");
                break;
            case MotionEvent.ACTION_UP:
                downY = (int) event.getY();
                Log.d("TANG","UP ======");
                if (downY > currentY) {
                    reduceSound();
                } else {
                    addSound();
                }
                break;
        }

        return super.onTouchEvent(event);
    }
}
