package com.example.mybulter.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.mybulter.R;
import com.example.mybulter.fragment.LoginFragment;



public class LoginActivity extends BaseActivity {
    private Fragment fragment_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragment_login =  new LoginFragment();
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fl_contain,fragment_login).commit();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_login;
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
}
