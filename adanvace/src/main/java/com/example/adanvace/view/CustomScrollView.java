package com.example.adanvace.view;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Function : 自定义scrollView实现下拉
 * Author : Alan
 * Modify Date : 12/8/17
 * Issue : TODO
 * Whether solve :
 */

public class CustomScrollView extends ScrollView {

    private int startY;
    private int scrollLength;

    private int mHeight;

    private View mView;

    private int topLength;

    private Rect mRect = new Rect();

    public ICallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(ICallBack callBack) {
        this.callBack = callBack;
    }

    private boolean isFirst = true;

    private ICallBack callBack;
    /**
     * y坐标
     */
    private int y;


    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHeight = getHeight();
    }

    /***
     * 根据 XML 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之后. 即使子类覆盖了 onFinishInflate
     * 方法，也应该调用父类的方法，使该方法得以执行.
     */

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {

            mView = getChildAt(0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        int cy = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                Log.d("TANG", startY + ":::");

                break;
            case MotionEvent.ACTION_MOVE:

                startY = (int) ev.getY();

                int dy = cy - y;

                Log.d("TANG", cy + "=====" + dy + "=======" + y);

                if (isFirst) {
                    dy = 0;
                    isFirst = false;
                }
                y = cy;

                int currentY = (int) ev.getY();


                //   int currentY = (int) ev.getY();
                scrollLength = currentY - startY;


                if (mRect.isEmpty()){
                    mRect.set(mView.getLeft(), mView.getTop(), mView.getRight(), mView.getBottom());
                }



                /**
                 * 判断scrollLength的距离
                 */




                if (dy > mHeight / 6) {
                    callBack.call();

                }

                mView.layout(mView.getLeft(), mView.getTop() + dy, mView.getRight(), mView.getBottom() + dy);

                break;

            case MotionEvent.ACTION_UP:


                backToTop();

                break;
        }

        return super.onTouchEvent(ev);
    }

    /**
     * 返回顶端
     */
    private void backToTop() {


        Animation animation = new TranslateAnimation(0, 0, mView.getTop(),
                mRect.top);
        animation.setDuration(200);
        animation.setFillAfter(true);
        mView.startAnimation(animation);
        mView.layout(mRect.left, mRect.top, mRect.right, mRect.bottom);
        mRect.setEmpty();
        isFirst = true;

    }

    public interface ICallBack {
        void call();
    }
}
