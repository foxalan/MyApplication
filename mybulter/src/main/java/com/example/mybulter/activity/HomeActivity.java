package com.example.mybulter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.mybulter.R;
import com.example.mybulter.fragment.AlbumFragment;
import com.example.mybulter.fragment.ChatFragment;
import com.example.mybulter.fragment.SettingFragment;
import com.example.mybulter.fragment.WeiXinFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alan
 */


public class HomeActivity extends BaseActivity {

    private TabLayout tl_home;
    private ViewPager vp_home;

    private AlbumFragment fragment_album;
    private ChatFragment fragment_chat;
    private WeiXinFragment fragment_weixin;
    private SettingFragment fragment_setting;

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

        fragment_album = AlbumFragment.getInstance();
        fragment_chat = ChatFragment.getInstance();
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
                    floatingActionButton.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //预加载
        vp_home.setOffscreenPageLimit(fragmentList.size());

        floatingActionButton.setVisibility(View.GONE);
        tl_home.setupWithViewPager(vp_home);
    }
}
