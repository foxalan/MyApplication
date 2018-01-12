package com.example.adanvace.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.adanvace.util.L;

/**
 * Function : 自定义ViewGroup实现标签
 * Author : Alan
 * Modify Date : 19/8/17
 * Issue : TODO
 * Whether solve :
 */

public class FlowViewGroup extends ViewGroup {

    private int mWidth;
    private int mHeight;

    private int currentWidth = 0;
    private int currentHeight = 0;

    private OnItemClickLister onItemClickLister;

    public OnItemClickLister getOnItemClickLister() {
        return onItemClickLister;
    }

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    public FlowViewGroup(Context context) {

        super(context);
    }

    public FlowViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                mWidth = MeasureSpec.getSize(widthMeasureSpec);
                break;
            case MeasureSpec.AT_MOST:
                mWidth = 600;
                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                mHeight = MeasureSpec.getSize(heightMeasureSpec);
                break;
            case MeasureSpec.AT_MOST:
                mHeight = 600;
                break;
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        currentWidth = 0;
        currentHeight = 0;
        MarginLayoutParams layoutParams;
        for (int i = 0; i < getChildCount(); i++) {
            final int position = i;

            final View view = getChildAt(i);
            layoutParams = (MarginLayoutParams) view.getLayoutParams();

            int width = view.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

            int left = layoutParams.leftMargin;
            int top = layoutParams.topMargin;
            int right = layoutParams.rightMargin;
            int bottom = layoutParams.bottomMargin;

            if (currentWidth + width > mWidth) {
                currentWidth = width;
                currentHeight = currentHeight + height;
            } else {
                currentWidth = currentWidth + width;
            }

            int currentLeft = currentWidth - right - view.getMeasuredWidth();
            int currentRight = currentWidth - right;
            int currentTop = currentHeight + top;
            int currentBottom = currentTop + view.getMeasuredHeight();

        //    L.d("LEFT:" + currentLeft + "===RIGHT:" + currentRight + "====TOP:" + currentTop + "===BOTTOM+" + currentBottom);
            L.d("view width:"+view.getMeasuredWidth());
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickLister.click(view, position);
                }
            });

            view.layout(currentLeft, currentTop, currentRight, currentBottom);
        }


    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public interface OnItemClickLister {
        void click(View view, int position);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
     //   invalidate();
    }
}
