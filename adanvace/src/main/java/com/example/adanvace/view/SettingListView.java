package com.example.adanvace.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Function :设置界面的listView
 * Author : Alan
 * Modify Date : 16/8/17
 * Issue : TODO
 * Whether solve :
 */

public class SettingListView extends ListView {

    private int count;

    public SettingListView(Context context) {
        super(context);
    }

    public SettingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = getChildMeasureSpec(heightMeasureSpec, getPaddingBottom(), 50);

        int mHeight = MeasureSpec.getSize(height);

        int width = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft(), 200);


        for (int i = 0; i < getChildCount(); i++) {

            if (i < 1) {
                getChildAt(i).layout(0, mHeight * i, width, mHeight * (i + 1));
            } else {
                getChildAt(i).layout(0, mHeight * i + 200, width, mHeight * (i + 1) + 200);
            }


        }

        count = getChildCount();
    }
}
