package com.example.mybulter.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mybulter.R;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 22/7/17$
 * Input Parameter &
 */

public class SettingListView extends ListView {


    public SettingListView(Context context) {
        this(context, null);
    }

    public SettingListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }


    /**
     * 主要是处理用ScrollView只ListView显示一行的问题
     * <p>
     * int 是32位,30位是显示控件的大小,将右移两个单位,将模式设置为AT_MOST,尽可能让他提高
     * 它的大小
     * <p>
     * <p>
     * <p>
     * 我们知道我们的控件的大小的最大值是用30位表示的（int占32位，其中前两位用来表示文章开头
     * 所说的三种模式）。那么Integer.MAX_VALUE来获取int值的最大值，然后右移2位，就得到这个
     * 最大值了 。因为是要最大值所以只能选择AT_MOST模式。最后 super.onMeasure（）方法将我
     * 们的高度值传进去就可以使ListView内容都展示出来了
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2
                , MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }


    /**********************************************************
     * Function Name : onInterceptTouchEvent
     * Author : alan
     * Modify Date : 28/4/17
     * Feature :onInterceptTouchEvent是TOUCH拦截事件,默认返回FALSE,表示不消耗此时TOUCH事件,将
     *  将此次事件传递给子View,子view的ONTOUCH返回默认值为True.
     *
     * True表示拦截 ,false表示传递下去
     *
     **********************************************************/

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                int x = (int) ev.getX();
                int y = (int) ev.getY();
                int item_location = pointToPosition(x, y);


                if (item_location == AdapterView.INVALID_POSITION) {
                } else {
                    if (item_location == 0) {
                        if (item_location == (getAdapter().getCount() - 1)) {

                            //只有一项
                            setSelector(R.drawable.list_single_bg);
                        } else {
                            //第一项
                            setSelector(R.drawable.list_top_bg);
                        }
                    } else if (item_location == (getAdapter().getCount() - 1))
                        //最后一项
                        setSelector(R.drawable.list_bom_bg);
                    else {
                        //中间项
                        setSelector(R.drawable.list_mid_bg);
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                //       Log.d(TAG,"onInterceptTouchEvent"+"ACTION_UP"+"*******************");
                break;
        }

        return false;
    }


}
