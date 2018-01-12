package com.example.adanvace.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.adanvace.R;

/**
 * Function Name : MyApplication
 * Author : Alan
 * Modify Date : 7/8/17
 * Input Parameter :
 */

public class StyleView extends View {

    private int image_radius;
    private Bitmap bitmap;

    private int mWidth;
    private int mHeight;

    public StyleView(Context context) {
        this(context, null);
    }

    public StyleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StyleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StyleView, defStyleAttr, 0);

        image_radius = (int) typedArray.getDimension(R.styleable.StyleView_image_radius, 50);
        bitmap = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(R.styleable.StyleView_background_image, 0));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpec = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpec = MeasureSpec.getMode(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        switch (widthSpec) {
            case MeasureSpec.EXACTLY:
                mWidth = width + getPaddingLeft() + getPaddingRight();
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
                mHeight = height + getPaddingBottom() + getPaddingTop();
                break;
            case MeasureSpec.AT_MOST:
                mHeight = 300;
                break;
            case MeasureSpec.UNSPECIFIED:
                mHeight = 300;
                break;
        }

        initSize(mWidth,mHeight);

        setMeasuredDimension(mWidth,mHeight);
    }

    /**
     * 设置尺寸大小
     * @param mWidth
     * @param mHeight
     */
    private void initSize(int mWidth, int mHeight) {



    }


}
