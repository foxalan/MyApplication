package com.example.frequentview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.frequentview.R;
import com.example.frequentview.fragment.TestFragmentOne;
import com.example.frequentview.fragment.TestFragmentThree;
import com.example.frequentview.fragment.TestFragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager无限循环
 */

public class ViewPageActivity extends FragmentActivity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;

    private Fragment testFragmentOne;
    private Fragment testFragmentTwo;
    private Fragment testFragmentThree;

    private FragmentPagerAdapter pagerAdapter;

    private List<Fragment> fragmentList;
    private static final String TAG = "tang";

    private int position;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
      //  viewPager.setOnPageChangeListener(this);

        initData();

        initEvent();
    }

    /**
     * 当position 为0 时 当前在当前页面
     * 当某一页面填充屏幕，它的值为0。当页面刚向屏幕右侧方向被拖走，它的值为1。
     * 如果用户在页面1和页面2间滑动到一半，那么页面1的position为-0.5并且页面2的position为 0.5。
     *
     */

    private void initEvent() {

        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }


        };

        viewPager.setAdapter(pagerAdapter);

        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {

            //    Log.d(TAG, position + "===");

                int mWidth = page.getWidth();

                if (position <= -1) {
                    page.setAlpha(0);
                } else if (position <=0 ) {

                    page.setScaleX(1);
                    page.setScaleY(1);
                    page.setAlpha(1F + position);
                    page.setTranslationX(0);

                } else if (position > 0) {

                    //核心代码
                    page.setAlpha(1 - position);

                    // Counteract the default slide transition
                    page.setTranslationX(mWidth * -position);

                    // Scale the page down (between MIN_SCALE and 1)
                    float scaleFactor = MIN_SCALE
                            + (1 - MIN_SCALE) * (1 - Math.abs(position));
                    page.setScaleX(scaleFactor);
                    page.setScaleY(scaleFactor);


                } else if (position == 0) {
                    page.setAlpha(1);
                    page.setScaleX(1);
                    page.setScaleY(1);
                    page.setTranslationX(0);
                }

            }
        });


    }

    private static final float MIN_SCALE = 0.75f;

    private void initData() {

        fragmentList = new ArrayList<>();

        testFragmentOne = new TestFragmentOne();
        testFragmentTwo = new TestFragmentTwo();
        testFragmentThree = new TestFragmentThree();

        fragmentList.add(testFragmentOne);
        fragmentList.add(testFragmentTwo);
        fragmentList.add(testFragmentThree);


    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;

        Log.d(TAG,position+"==");

    }

    @Override
    public void onPageScrollStateChanged(int state) {

        if(state == 0) {
            if ( fragmentList.size() > 1) { //多于1，才会循环跳转
                if ( position < 1) { //首位之前，跳转到末尾（N）
                    position = 2;
                    viewPager.setCurrentItem(position,false);

                } else if ( position >= 2) { //末位之后，跳转到首位（1）
                    viewPager.setCurrentItem(0,false); //false:不显示跳转过程的动画
                    position = 1;
                }
            }
        }

    }
}
