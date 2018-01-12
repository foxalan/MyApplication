package com.example.sportlogs.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Function :
 * Author : Alan
 * Modify Date : 25/8/17
 * Issue : TODO
 * Whether solve :
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initView();
        initData();
        initEvent();
    }


    public abstract int getContentView();

    public abstract void initView();

    public abstract void initData();

    public abstract void initEvent();

}
