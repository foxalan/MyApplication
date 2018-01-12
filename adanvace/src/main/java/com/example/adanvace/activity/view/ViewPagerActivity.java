package com.example.adanvace.activity.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.adanvace.R;
import com.example.adanvace.activity.viewpager.TestViewPageOneActivity;
import com.example.adanvace.activity.viewpager.TestViewPageTwoActivity;
import com.example.adanvace.activity.viewpager.TestViewPagerThreeActivity;

/**
 * Function : 各种样式的tab
 * Author : Alan
 * Modify Date : 11/8/17
 * Issue : TODO
 * Whether solve :
 */

public class ViewPagerActivity extends BaseActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_viewpager;
    }




    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }


    public void onClick(View view){

        switch (view.getId()){
            case R.id.bt_viewpager_01:
                startActivity(new Intent(ViewPagerActivity.this, TestViewPageOneActivity.class));
                break;
            case R.id.bt_viewpager_02:
                startActivity(new Intent(ViewPagerActivity.this, TestViewPageTwoActivity.class));
                break;
            case R.id.bt_viewpager_03:
                startActivity(new Intent(ViewPagerActivity.this, TestViewPagerThreeActivity.class));
                break;
        }


    }
}
