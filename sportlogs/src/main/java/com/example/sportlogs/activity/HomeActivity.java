package com.example.sportlogs.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sportlogs.R;
import com.example.sportlogs.fragment.JournalFragment;
import com.example.sportlogs.fragment.SettingFragment;
import com.example.sportlogs.fragment.TargetFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Function : 主页面
 * Author : Alan
 * Modify Date : 25/8/17
 * Issue : TODO
 * Whether solve :
 */

public class HomeActivity extends BaseActivity {

    private ViewPager vp_home;

    private TextView tv_home_target;
    private TextView tv_home_journal;
    private TextView tv_home_setting;

    private ImageView iv_home_target;
    private ImageView iv_home_journal;
    private ImageView iv_home_setting;

    private LinearLayout ll_home_target;
    private LinearLayout ll_home_journal;
    private LinearLayout ll_home_setting;

    private List<Fragment> fragmentList = new ArrayList<>();
    private TargetFragment targetFragment;
    private JournalFragment journalFragment;
    private SettingFragment settingFragment;

    private ButtonClick buttonClick;

    private int color_gray;
    private int color_custom;
    private ActionBar actionBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.activity_home_target));
    }

    @Override
    public int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {

        vp_home = (ViewPager) findViewById(R.id.vp_home);

        tv_home_target = (TextView) findViewById(R.id.tv_home_target);
        tv_home_journal = (TextView) findViewById(R.id.tv_home_journal);
        tv_home_setting = (TextView) findViewById(R.id.tv_home_setting);

        iv_home_target = (ImageView) findViewById(R.id.iv_home_target);
        iv_home_journal = (ImageView) findViewById(R.id.iv_home_journal);
        iv_home_setting = (ImageView) findViewById(R.id.iv_home_setting);

        ll_home_target = (LinearLayout) findViewById(R.id.ll_home_target);
        ll_home_journal = (LinearLayout) findViewById(R.id.ll_home_journal);
        ll_home_setting = (LinearLayout) findViewById(R.id.ll_home_setting);

    }

    @Override
    public void initData() {

        targetFragment = TargetFragment.getInstance();
        journalFragment = JournalFragment.getInstance();
        settingFragment = SettingFragment.getInstance();

        fragmentList.add(targetFragment);
        fragmentList.add(journalFragment);
        fragmentList.add(settingFragment);

        buttonClick = new ButtonClick();

        ll_home_target.setOnClickListener(buttonClick);
        ll_home_journal.setOnClickListener(buttonClick);
        ll_home_setting.setOnClickListener(buttonClick);

        color_gray = getResources().getColor(R.color.home_bottom_text_color);
        color_custom = getResources().getColor(R.color.color_custom);
    }

    @Override
    public void initEvent() {
        setBottomBackground(0);

        vp_home.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        vp_home.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setBottomBackground(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private class ButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_home_target:
                    vp_home.setCurrentItem(0);
                    setBottomBackground(0);
                    actionBar.setTitle(getResources().getString(R.string.activity_home_target));

                    break;
                case R.id.ll_home_journal:
                    vp_home.setCurrentItem(1);
                    setBottomBackground(1);
                    actionBar.setTitle(getResources().getString(R.string.activity_home_label));

                    break;
                case R.id.ll_home_setting:
                    vp_home.setCurrentItem(2);
                    setBottomBackground(2);
                    actionBar.setTitle(getResources().getString(R.string.activity_home_setting));

                    break;
            }

        }
    }

    private void setBottomBackground(int position) {
        setAllGray();

        switch (position) {
            case 0:
                tv_home_target.setTextColor(color_custom);
                iv_home_target.setImageResource(R.drawable.traget_press);

                break;
            case 1:
                tv_home_journal.setTextColor(color_custom);
                iv_home_journal.setImageResource(R.drawable.journal_press);
                break;
            case 2:
                tv_home_setting.setTextColor(color_custom);
                iv_home_setting.setImageResource(R.drawable.setting_press);
                break;
        }
    }

    private void setAllGray() {

        tv_home_target.setTextColor(color_gray);
        tv_home_journal.setTextColor(color_gray);
        tv_home_setting.setTextColor(color_gray);

        iv_home_target.setImageResource(R.drawable.target_normal);
        iv_home_journal.setImageResource(R.drawable.journal_normal);
        iv_home_setting.setImageResource(R.drawable.setting_normal);
    }
}
