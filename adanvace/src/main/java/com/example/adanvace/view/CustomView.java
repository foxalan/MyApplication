package com.example.adanvace.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.adanvace.R;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 3/8/17$
 * Input Parameter &
 */

public class CustomView extends View {

    private int textColor;
    private String text;
    private Bitmap bitmap;
    private int textSize;


    private Paint mPaint;

    private int mWidth;
    private int mHeight;

    private Rect mTextBound;

    private Rect rect;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, 0);


        int n = typedArray.getIndexCount();
        /**
         * 使用case时有一个问题,当没有定义属性的时候拿不到默认值
         */

        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);

            switch (attr) {
                case R.styleable.CustomView_image:
                    bitmap = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomView_text:
                    text = typedArray.getString(attr);
                    break;
                case R.styleable.CustomView_textColor:
                    textColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomView_textSize:
                    textSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                            16, getResources().getDisplayMetrics()));
                    break;

            }
        }

        //    bitmap = BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(R.styleable.CustomView_image,0));

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(textColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(textSize);

        mTextBound = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), mTextBound);

        rect = new Rect();

    }

    /**
     * 这里设置宽和高,要考虑,文本的大小,图片的大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);

        int widthSpec = MeasureSpec.getMode(widthMeasureSpec);

        int textWidth = mTextBound.width() + getPaddingLeft() + getPaddingRight();
        int imageWidth = bitmap.getWidth() + getPaddingRight() + getPaddingLeft();
        int maxWidth = Math.max(textWidth, imageWidth);

        switch (widthSpec) {

            case MeasureSpec.EXACTLY:
                mWidth = Math.max(maxWidth, mWidth);
                break;
            default:
                mWidth = Math.max(textWidth, imageWidth);
                break;
        }

        int heightSpec = MeasureSpec.getMode(heightMeasureSpec);
        int minHeight = mTextBound.height() + bitmap.getHeight() + getPaddingBottom() + getPaddingTop();

        switch (heightSpec) {
            case MeasureSpec.EXACTLY:
                mHeight = Math.max(mHeight, minHeight);
                break;
            default:
                mHeight = minHeight;
                break;
        }

        setMeasuredDimension(mWidth, mHeight);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //   canvas.drawText(text, 20, 20, mPaint);
        //  canvas.drawBitmap(bitmap, 30, 30, mPaint);

        rect.set(0, 0, mWidth, mHeight);
        canvas.drawRect(rect, mPaint);


        //    canvas.drawText(text, mWidth / 2 - mTextBound.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);

        int x = (mWidth - bitmap.getWidth()) / 2;
        int y = (mHeight - mTextBound.height() - bitmap.getHeight()) / 2;

        canvas.drawBitmap(bitmap, x, y, mPaint);

        /**
         *  * 计算文字在控件中间的两种方式：方法2是绝对准确，推荐使用。方法1有时候会测量不准确，高度不能居中显示
         * 1.宽 = （控件宽 - 文字宽）/2
         *    高 = (控件高 + 文字稿) /2
         * 2.宽 = （控件宽 - 文字宽）/2
         *    高 = （控件高 - （文字上边界 + 文字下边界）/2
         */
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        float x1 = getWidth() / 2 - mTextBound.width() / 2;
        float y1 = getHeight() / 2 - (fm.ascent + fm.descent) / 2;
        //       float y = getHeight()/2 + mBound.height()/2;
        canvas.drawText(text, x1, y1, mPaint);


    }
}
