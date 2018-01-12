package com.example.databasedemo.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.databasedemo.R;
import com.example.databasedemo.fragment.FragmentAdd;
import com.example.databasedemo.fragment.FragmentList;
import com.example.databasedemo.fragment.FragmentSetting;

import java.util.ArrayList;
import java.util.List;



public class DataBaseActivity extends FragmentActivity {

    private Fragment fragment_list;
    private Fragment fragment_add;
    private Fragment fragment_setting;
    private List<Fragment> fragmentList;

    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;

    private TextView tv_list;
    private TextView tv_add;
    private TextView tv_setting;
    private List<TextView> textList;

    private ButtonClick buttonClick;

    private android.app.ActionBar actionBar;
    private TextView tv_title;
    private String[] title  = {"主页","增加","设置"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionBar = this.getActionBar();
        actionBar.setCustomView(LayoutInflater.from(this).inflate(R.layout.actionbar_list,null));

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.show();



        setContentView(R.layout.activity_database);

        initViews();
        initData();
        initEvent();
    }

    private void initViews() {

        tv_title = (TextView) findViewById(R.id.tv_action_bar_title);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tv_list = (TextView) findViewById(R.id.tv_list);
        tv_add = (TextView) findViewById(R.id.tv_add);
        tv_setting = (TextView) findViewById(R.id.tv_setting);

        tv_list.setTextColor(Color.GREEN);

        textList = new ArrayList<>();
        textList.add(tv_list);
        textList.add(tv_add);
        textList.add(tv_setting);
    }

    private void initData() {

        fragmentList = new ArrayList<>();

        fragment_add = new FragmentAdd();
        fragment_list = new FragmentList();
        fragment_setting = new FragmentSetting();


        fragmentList.add(fragment_list);
        fragmentList.add(fragment_add);
        fragmentList.add(fragment_setting);
    }

    private void initEvent() {


        buttonClick = new ButtonClick();
        tv_list.setOnClickListener(buttonClick);
        tv_add.setOnClickListener(buttonClick);
        tv_setting.setOnClickListener(buttonClick);

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTestColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void setTestColor(int position) {

        for (int i = 0; i < textList.size(); i++) {
            textList.get(i).setTextColor(Color.WHITE);
        }

        textList.get(position).setTextColor(Color.GREEN);
        tv_title.setText(title[position]);

    }

    class ButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.tv_list:
                    setTestColor(0);
                    tv_title.setText(title[0]);
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.tv_add:
                    setTestColor(1);
                    tv_title.setText(title[1]);
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.tv_setting:
                    setTestColor(2);
                    tv_title.setText(title[2]);
                    viewPager.setCurrentItem(2);

                    break;

                default:
                    break;
            }
        }
    }
}
