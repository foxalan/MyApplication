package com.example.mybulter.view;

/**
 * Function :
 * Author : Alan
 * Modify Date : 16/8/17
 * Issue : TODO
 * Whether solve :
 */

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.mybulter.util.L;

/**
 * Function :设置界面的listView
 * Author : Alan
 * Modify Date : 16/8/17
 * Issue : TODO
 * Whether solve :
 */

public class SetListView extends ListView {

    private int count;

    private int mHeight;
    private int width;

    public SetListView(Context context) {
        super(context);
    }

    public SetListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

//        measureChildren(widthMeasureSpec,heightMeasureSpec);
//
//        int mWidth = 0;
//        int mHeight = 0;
//
//        for (int i = 0 ; i<getChildCount();i++){
//            View view = getChildAt(i);
//            MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
//
//            mHeight = mHeight + view.getMeasuredHeight()+layoutParams.topMargin;
//            mWidth = view.getMeasuredWidth();
//
//            L.d("mHeight:"+mHeight+"--mWidth:"+mWidth);
//        }
//
//        L.d(getChildCount()+"===");
//        L.d("mHeight:"+mHeight+"--mWidth:"+mWidth);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed) {

            int cCount = getChildCount();
            int cWidth = 0;
            int cHeight = 0;
            AbsListView.LayoutParams cParams = null;
            /**
             * 遍历所有childView根据其宽和高，以及margin进行布局
             */
            for (int i = 0; i < cCount; i++) {
                View childView = getChildAt(i);
                cWidth = childView.getMeasuredWidth();
                cHeight = childView.getMeasuredHeight();
                cParams = (LayoutParams) childView.getLayoutParams();
                int height = 150;
                L.d(cWidth + ":" + cHeight);

                if (i < 2) {
                    childView.layout(0, i * cHeight, cWidth, (i + 1) * cHeight);
                } else {
                    childView.layout(0, i * cHeight + height, cWidth, (i + 1) * cHeight + height);

                }
            }

            L.d("changed:"+changed);
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        super.setOnItemClickListener(listener);
    }
}

