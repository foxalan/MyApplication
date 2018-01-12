package com.example.adanvace.activity.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Function : 使用最常用的ViewPager加上PagerAdapter
 * Author : Alan
 * Modify Date : 11/8/17
 * Issue : 1.在重写destroyItem时候调用super方法会报错
 * Whether solve :
 */

public class TestViewPageOneActivity extends BaseActivity {

    private List<View> viewList = new ArrayList<>();
    private LayoutInflater inflater;

    private ViewPager vp_test_01;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_viewpager_test_01;
    }

    @Override
    public void initView() {

        vp_test_01 = (ViewPager) findViewById(R.id.vp_test_01);
    }

    @Override
    public void initData() {

        inflater = LayoutInflater.from(this);

        View view1 = inflater.inflate(R.layout.fragment_one,null);
        View view2 = inflater.inflate(R.layout.fragment_two,null);
        View view3 = inflater.inflate(R.layout.fragment_three,null);
        View view4 = inflater.inflate(R.layout.fragment_four,null);

        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
              //  super.destroyItem(container, position, object);
                container.removeView(viewList.get(position));
            }
        };

    }

    @Override
    public void initEvent() {
        vp_test_01.setAdapter(pagerAdapter);
    }
}
