package com.example.adanvace.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.example.adanvace.util.L;

/**
 * Function :自定义的ViewGroup
 * Author : Alan
 * Modify Date : 19/8/17
 * Issue : TODO
 * Whether solve :
 */

public class CustomViewGroup extends ViewGroup {

    private int mWidth;
    private int mHeight;

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpec = MeasureSpec.getMode(widthMeasureSpec);

        int width = 0;

        MarginLayoutParams layoutParams = null;

        /**
         * 1.计算子view的宽和高
         */
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        switch (widthSpec) {
            case MeasureSpec.EXACTLY:
                mWidth = MeasureSpec.getSize(widthMeasureSpec);
                L.d("exactly" + mWidth);
                break;
            case MeasureSpec.AT_MOST:
                for (int i = 0; i < 3; i = i + 2) {

                    View view = getChildAt(i);
                    layoutParams = (MarginLayoutParams) view.getLayoutParams();
                    width = width + view.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                }

                mWidth = width;
                break;
        }

        int heightSpec = MeasureSpec.getMode(heightMeasureSpec);
        int height = 0;

        switch (heightSpec) {
            case MeasureSpec.EXACTLY:
                mHeight = MeasureSpec.getSize(heightMeasureSpec);

                L.d("exactly" + mHeight);
                break;
            case MeasureSpec.AT_MOST:
                for (int i = 1; i < 4; i++) {
                    View view = getChildAt(i);
                    layoutParams = (MarginLayoutParams) view.getLayoutParams();
                    height = height + view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
                }
                mHeight = height;
                break;
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    /**
     * changed用来判断View的大小与位置是否发生了改变
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            MarginLayoutParams layoutParams = null;
            for (int i = 0; i < getChildCount(); i++) {
                View view = getChildAt(i);



                layoutParams = (MarginLayoutParams) view.getLayoutParams();

                int width = view.getMeasuredWidth();
                int height = view.getMeasuredHeight();

                switch (i) {
                    case 0:
                        view.layout(l + layoutParams.leftMargin, t + layoutParams.topMargin, width, height);
                        break;
                    case 1:
                        view.layout(r - width, t, r, height);
                        break;
                    case 2:
                        view.layout(l, b - height, width, b);
                        break;
                    case 3:
                        view.layout(r - width, b - height, r, b);
                        break;
                    case 4:

                        view.layout(mWidth / 2 - width / 2, mHeight / 2 - height / 2, mWidth / 2 + width / 2, mHeight / 2 + height / 2);

                        break;
                }
            }
        }
    }

    /**
     * 决定该ViewGroup的LayoutParams
     */

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }


}
