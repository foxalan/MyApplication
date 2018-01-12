package com.example.sportlogs.view;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.sportlogs.R;

/**
 * Function : 用于写数字的Ｖiew
 * Author : Alan
 * Modify Date : 30/8/17
 * Issue : TODO
 * Whether solve :
 */

public class NumberView extends View {

    private int round_radius;
    private int text_size;

    private String str_journal_values;

    private int mWidth;
    private int mHeight;

    private int centerX;
    private int centerY;

    private Paint mPaint;
    private Rect mTextBound;
    private RectF rect_round;


    public NumberView(Context context) {
        this(context, null);
    }

    public NumberView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NumberView, defStyleAttr, 0);

        round_radius = (int) typedArray.getDimension(R.styleable.NumberView_round_radius, 20);
        text_size = (int) typedArray.getDimension(R.styleable.NumberView_text_size, 10);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.color_custom));
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(text_size);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void setJournalValue(String journalValue) {

        this.str_journal_values = journalValue;
        invalidate();
    }


    public String getStr_journal_values(){
        return str_journal_values;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                mWidth = MeasureSpec.getSize(widthMeasureSpec);
                break;
            default:
                mWidth = (int) (round_radius*1.5);
                break;

        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                mHeight = MeasureSpec.getSize(heightMeasureSpec);
                break;
            default:
                mHeight = (int) (round_radius*1.5);
                break;
        }

        centerX = mWidth / 2;
        centerY = mHeight / 2;

        initRect();

        setMeasuredDimension(mWidth, mHeight);
    }

    private void initRect() {

        int length = mWidth-10;
        mTextBound = new Rect();
        mTextBound.set(centerX - length / 4, centerY - text_size, centerX + length / 4, centerY + text_size);

        rect_round = new RectF();
        rect_round.set(centerX - length / 2, centerY - length / 2, centerX + length / 2, centerY + length / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         *  * 计算文字在控件中间的两种方式：方法2是绝对准确，推荐使用。方法1有时候会测量不准确，高度不能居中显示
         * 1.宽 = （控件宽 - 文字宽）/2
         *    高 = (控件高 + 文字稿) /2
         * 2.宽 = （控件宽 - 文字宽）/2
         *    高 = （控件高 - （文字上边界 + 文字下边界）/2
         */

        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        // 转载请注明出处：http://blog.csdn.net/hursing
        int baseline = (mTextBound.bottom + mTextBound.top - fontMetrics.bottom - fontMetrics.top) / 2;
        // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()


        canvas.drawText(str_journal_values, mTextBound.centerX(), baseline, mPaint);
    //    canvas.drawRect(mTextBound, mPaint);
    //    canvas.drawCircle(centerX, centerY, round_radius, mPaint);

        int count = 30;
        int length = 360/count;
        for (int i = 0; i < count; i++) {
            canvas.drawArc(rect_round, i * length, 5, false, mPaint);
        }


    }
}
