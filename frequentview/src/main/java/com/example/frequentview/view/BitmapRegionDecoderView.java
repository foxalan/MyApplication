package com.example.frequentview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 24/7/17$
 * Input Parameter &
 */

public class BitmapRegionDecoderView extends View {

    private BitmapRegionDecoder bitmapRegionDecoder;
    /**
     * 用于得到图片的大小
     */
    private BitmapFactory.Options options;

    private int mImageWidth;
    private int mImageHeight;

    /**
     * Rect位于android.graphics下，表示一个矩形，由四条边的坐标组成
     * rect 的定义只需要两个点,即左上,右下
     * <p>方法
     * offset(int dx, int dy)该矩阵在x轴和y轴分别发生的偏移量（很有用，可以上下移动矩阵）
     * offsetTo(int newLeft, int newTop)保持矩阵的宽高，矩阵的左上角偏移到（newLeft,newTop）该点
     */
    private Rect rect;

    public BitmapRegionDecoderView(Context context) {
        this(context, null);
    }

    public BitmapRegionDecoderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);


    }

    public BitmapRegionDecoderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();


    }

    private void init() {



    }

    public void setBitmap(InputStream inputStream) throws IOException {

        //false是什么??????
        bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);

        options = new BitmapFactory.Options();
        //???
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);

        mImageWidth = options.outWidth;
        mImageHeight = options.outHeight;

        rect = new Rect();

        rect.set(mImageWidth / 2 - getWidth() / 2, mImageHeight / 2 - getHeight() / 2,
                mImageWidth / 2 + getWidth() / 2, mImageHeight / 2 + getHeight() / 2);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {




        return super.onTouchEvent(event);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bitmap = bitmapRegionDecoder.decodeRegion(rect,options);
        //(0.0) 表示从上顶点开始画
        canvas.drawBitmap(bitmap,0,0,null);
    }
}
