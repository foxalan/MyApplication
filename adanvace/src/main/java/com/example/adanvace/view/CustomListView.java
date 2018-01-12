package com.example.adanvace.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Function :
 * Author : Alan
 * Modify Date : 9/8/17
 * Issue : TODO
 * Whether solve :
 */

public class CustomListView extends ListView{


    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
