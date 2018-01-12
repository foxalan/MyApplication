package com.example.adanvace.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Function : 自定义
 * Author : Alan
 * Modify Date : 24/8/17
 * Issue : TODO
 * Whether solve :
 */

public class CustomHorizontalScrollView extends HorizontalScrollView {


    public CustomHorizontalScrollView(Context context) {
        this(context, null);
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
