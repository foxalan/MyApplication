package com.example.mybulter.activity;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mybulter.R;
import com.example.mybulter.util.ShareUtils;
import com.example.mybulter.view.CustomDialog;

import config.Config;

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

    private CustomDialog dialog;
    private boolean keepPassword;

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

        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_loding, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        dialog.setCancelable(false);
    }

    @Override
    public void initData() {
        keepPassword = ShareUtils.getBoolean(this, Config.CONFIG_LOGIN_KEEP_PASSWORD, false);
    }

    @Override
    public void initEvent() {
        mButtonLogin.setOnClickListener(buttonClick);
        mButtonRegister.setOnClickListener(buttonClick);
        mTextForgetPassword.setOnClickListener(buttonClick);
        if (keepPassword) {
            mKeepPassword.setChecked(true);
            //todo
        } else {
            mKeepPassword.setChecked(false);
        }

        mKeepPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ShareUtils.putBoolean(LoginActivity.this, Config.CONFIG_LOGIN_KEEP_PASSWORD, true);
                } else {
                    ShareUtils.putBoolean(LoginActivity.this, Config.CONFIG_LOGIN_KEEP_PASSWORD, false);
                }
            }
        });
    }

    class ButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_login:
                    //1.获取输入框的值
                    String name = mEditUserName.getText().toString().trim();
                    String password = mEditPassword.getText().toString().trim();

                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));

//                    if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)) {
//                        dialog.show();
//                        //登录
//                        final MyUser user = new MyUser();
//                        user.setUsername(name);
//                        user.setPassword(password);
//                        user.login(new SaveListener<MyUser>() {
//                            @Override
//                            public void done(MyUser myUser, BmobException e) {
//                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                                dialog.dismiss();
//                                //判断结果
//                                if (e == null) {
//                                    //判断邮箱是否验证
//                                    if (user.getEmailVerified()) {
//                                        //跳转
//                                        finish();
//                                    } else {
//                                        Toast.makeText(LoginActivity.this, "请前往邮箱验证", Toast.LENGTH_SHORT).show();
//                                    }
//                                } else {
//                                    Toast.makeText(LoginActivity.this, "登录失败：" + e.toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    } else {
//                        Toast.makeText(LoginActivity.this, "输入框不能为空", Toast.LENGTH_SHORT).show();
//                    }
                    break;
                case R.id.bt_register:
                    startActivity(new Intent(LoginActivity.this, RegisteredActivity.class));
                    break;
                case R.id.tv_forget_password:
                    startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                    break;
                default:
                    break;
            }
        }
    }
}
