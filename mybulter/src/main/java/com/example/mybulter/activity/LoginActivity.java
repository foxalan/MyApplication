package com.example.mybulter.activity;

import android.widget.Button;

import com.example.mybulter.R;

/**
 * @author Alan
 */

public class LoginActivity extends BaseActivity {

    private Button mButtonLogin;
    private Button mButtonRegister;

    @Override
    public int getContentView() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView() {
        mButtonLogin = (Button) findViewById(R.id.bt_login);
        mButtonRegister = (Button) findViewById(R.id.bt_register);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
