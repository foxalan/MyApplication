package com.example.adanvace.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;
import android.widget.Switch;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 2/8/17$
 * Input Parameter &自定义的ViewPager
 */

public class VerticalLinearLayout extends ViewGroup {

    private int touch_slop;

    private int mScreenHeight;

    /**
     * 是否正在滚动
     */
    private boolean isScrolling;

    /**
     *
     */
    private int endY;

    private int mScrollEndY;
    private int mScrollStartY;

    private Scroller scroller;


    private int currentPage = 0;

    /**
     * 加速度检测
     */
    private VelocityTracker mVelocityTracker;

    private OnPageChangeListener mOnPageChangeListener;

    public VerticalLinearLayout(Context context) {
        this(context, null);
    }

    public VerticalLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        scroller = new Scroller(context);
        touch_slop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenHeight = displayMetrics.heightPixels;
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        if (b) {

            int count = getChildCount();

            // 设置主布局的高度
            MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
            lp.height = mScreenHeight * count;
            setLayoutParams(lp);

            /**
             * 设置Child的布局
             */
            for (int j = 0; j < count; j++) {
                View child = getChildAt(j);
                if (child.getVisibility() != View.GONE) {
                    child.layout(i, mScreenHeight * j, i2, mScreenHeight * (j + 1));
                }
            }

        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; ++i) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, mScreenHeight);
        }
    }

    /**
     * scrollTo(x,y),x为负向右划,Y为负向下划
     *
     * @param event
     * @return
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (isScrolling) {
            return super.onTouchEvent(event);
        }

        obtainVelocity(event);
        int action = event.getAction();

        int y = (int) event.getY();


        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScrollStartY = getScrollY();
                endY = y;
             //   Log.d("TANG", "endY:" + endY);
                break;
            case MotionEvent.ACTION_MOVE:

                if (!scroller.isFinished()) {
                    /**
                     * 停止动画
                     */
                    scroller.abortAnimation();
                }

                int dy = endY - y;

                // 边界值检查
                int scrollY = getScrollY();
                // 已经到达顶端，下拉多少，就往上滚动多少
                // 只判断scrollY<0 会出现向上划时,dy 一直等于0的情况
                if (dy < 0 && scrollY < 0) {
                    dy = -scrollY;
                    //dy = 0;
                }
                // 已经到达底部，上拉多少，就往下滚动多少
                if (dy > 0 && scrollY + dy > getHeight() - mScreenHeight) {

                    dy = getHeight() - mScreenHeight - scrollY;
                }

                //   Log.d("TANG","y:"+y);
                //   Log.d("TANG","scrollY"+scrollY);
                //    Log.d("TANG", "getHeight():" + getHeight() + "===mScreenHeight" + mScreenHeight);

                scrollBy(0, dy);
                endY = y;
                break;
            case MotionEvent.ACTION_UP:

                mScrollEndY = getScrollY();

                int dScrollY = mScrollEndY - mScrollStartY;

                /**
                 * dScrollY大小0时,向上划动
                 * dScrollY小于0时,向下划动
                 */
                if (dScrollY > 0) {
                    //滚动，startX, startY为开始滚动的位置，dx,dy为滚动的偏移量, duration为完成滚动的时间//

                    /**
                     * 当划动距离不足屏幕距离的2分之一时,会划回去
                     */
                    if (dScrollY > mScreenHeight / 2 || Math.abs(getVelocity()) > 600) {
                        scroller.startScroll(0, getScrollY(), 0, mScreenHeight - dScrollY);



                    } else {

                        scroller.startScroll(0, getScrollY(), 0, -dScrollY);

                    }
                }

                if (dScrollY < 0) {
                    int m = -dScrollY;

                    if (m > mScreenHeight / 2 || Math.abs(getVelocity()) > 600) {
                        scroller.startScroll(0, getScrollY(), 0, -mScreenHeight - dScrollY);
                    } else {
                        scroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    }


                }

                isScrolling = true;
                postInvalidate();

                recycleVelocity();

                break;
        }

        return true;

    }

    /**
     * @return
     */


    /**
     * computeScrollOffset()
     * 这个方法没有任何参数。这个作用就是用于判断移动过程是否已经完成，完成返回false，还未完成返回true。
     * 而我们在使用过程中会根据该值去判断是否继续调用scrollTo()方法进行小幅度滑动。
     */
    @Override
    public void computeScroll() {
        super.computeScroll();

        if (scroller.computeScrollOffset()) {
            scrollTo(0, scroller.getCurrY());
            Log.d("TANG",scroller.getCurrY()+"================================");
            postInvalidate();
        } else {

            int position = getScrollY() / mScreenHeight;
            //     Log.e("TANG", position + ",");
            if (position != currentPage) {
                if (mOnPageChangeListener != null) {
                    currentPage = position;
                    mOnPageChangeListener.onPageChange(currentPage);
                }
            }

            isScrolling = false;
        }
    }


    /**
     * 设置回调接口
     *
     * @param onPageChangeListener
     */
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        mOnPageChangeListener = onPageChangeListener;
    }


    public interface OnPageChangeListener {
        void onPageChange(int currentPage);
    }


    /**
     * 获取y方向的加速度
     *
     * @return
     */
    private int getVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        return (int) mVelocityTracker.getYVelocity();
    }

    /**
     * 释放资源
     */
    private void recycleVelocity() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    /**
     * 初始化加速度检测器
     *
     * @param event
     */
    private void obtainVelocity(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

}
