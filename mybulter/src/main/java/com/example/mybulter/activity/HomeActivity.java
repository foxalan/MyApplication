package com.example.mybulter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.mybulter.R;
import com.example.mybulter.fragment.AlbumFragment;
import com.example.mybulter.fragment.ChatFragment;
import com.example.mybulter.fragment.SettingFragment;
import com.example.mybulter.fragment.WeiXinFragment;
import com.example.mybulter.view.MyToast;


import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends BaseActivity {

    private TabLayout tl_home;
    private ViewPager vp_home;

    private Fragment fragment_album;
    private Fragment fragment_chat;
    private Fragment fragment_weixin;
    private Fragment fragment_setting;

    private List<Fragment> fragmentList = new ArrayList<>();
    private FloatingActionButton floatingActionButton;

    private String[] titles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉阴影
        if (getSupportActionBar() != null) {

            getSupportActionBar().setElevation(0);
        }
    }

    @Override
    public int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {


        tl_home = (TabLayout) findViewById(R.id.tl_home);
        vp_home = (ViewPager) findViewById(R.id.vp_home);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.bt_setting);
    }

    @Override
    public void initData() {
        titles = getResources().getStringArray(R.array.titles);

        fragment_album = new AlbumFragment();
        fragment_chat = ChatFragment.getIntance();
        fragment_weixin = WeiXinFragment.getInstance();
        fragment_setting = new SettingFragment();

        fragmentList.add(fragment_chat);
        fragmentList.add(fragment_weixin);
        fragmentList.add(fragment_album);
        fragmentList.add(fragment_setting);

    }

    @Override
    public void initEvent() {


        vp_home.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
                return titles[position];
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SettingActivity.class));
            }
        });

        vp_home.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    floatingActionButton.setVisibility(View.GONE);
                } else {
                    floatingActionButton.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        floatingActionButton.setVisibility(View.GONE);
        tl_home.setTabGravity(TabLayout.GRAVITY_CENTER);
        tl_home.setupWithViewPager(vp_home);

    }


}
