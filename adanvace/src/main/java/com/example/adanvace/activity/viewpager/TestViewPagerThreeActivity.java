package com.example.adanvace.activity.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;
import com.example.adanvace.fragment.TestFragmentFour;
import com.example.adanvace.fragment.TestFragmentOne;
import com.example.adanvace.fragment.TestFragmentThree;
import com.example.adanvace.fragment.TestFragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 11/8/17
 * Issue : TODO
 * Whether solve :
 */

public class TestViewPagerThreeActivity extends BaseActivity {

    private ViewPager vp_test_03;
    private List<Fragment> fragmentList = new ArrayList<>();

    public static final String[] TITLES = new String[] { "业界", "移动", "研发", "程序员杂志", "云计算" };

    private TestFragmentOne fragment_01;
    private TestFragmentTwo fragment_02;
    private TestFragmentThree fragment_03;
    private TestFragmentFour fragment_04;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_viewpager_test_03;
    }

    @Override
    public void initView() {

        vp_test_03 = (ViewPager) findViewById(R.id.vp_test_03);
    }

    @Override
    public void initData() {

        fragment_01 = new TestFragmentOne();
        fragment_02 = new TestFragmentTwo();
        fragment_03 = new TestFragmentThree();
        fragment_04 = new TestFragmentFour();

        fragmentList.add(fragment_01);
        fragmentList.add(fragment_02);
        fragmentList.add(fragment_03);
        fragmentList.add(fragment_04);

    }

    @Override
    public void initEvent() {
        vp_test_03.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return TITLES[position];
            }
        });
    }
}
