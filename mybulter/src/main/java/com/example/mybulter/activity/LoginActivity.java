package com.example.mybulter.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mybulter.R;

/**
 * @author Alan
 */

public class LoginActivity extends BaseActivity {

    private Button mButtonLogin;
    private Button mButtonRegister;
    private EditText mEditUserName;
    private EditText mEditPassword;
    private CheckBox mKeepPassword;
    private TextView mTextForgetPassword;

    private ButtonClick buttonClick;

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        mButtonLogin = (Button) findViewById(R.id.bt_login);
        mButtonRegister = (Button) findViewById(R.id.bt_register);
        mEditUserName = (EditText) findViewById(R.id.et_username);
        mEditPassword = (EditText) findViewById(R.id.et_password);
        mKeepPassword = (CheckBox) findViewById(R.id.cb_rem_password);
        mTextForgetPassword = (TextView) findViewById(R.id.tv_forget_password);

        buttonClick = new ButtonClick();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mButtonLogin.setOnClickListener(buttonClick);
        mButtonRegister.setOnClickListener(buttonClick);
    }

    class ButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_login:

                    break;
                case R.id.bt_register:

                    break;
                default:
                    break;
            }
        }
    }
}
