package com.example.mybulter.activity;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mybulter.R;
import com.example.mybulter.info.PhoneInfo;
import com.example.mybulter.presenter.impl.QueryPhonePresenterImpl;
import com.example.mybulter.util.KeyboardUtil;
import com.example.mybulter.view.MyToast;
import com.example.mybulter.view.inter.IQueryPhoneView;

import java.lang.reflect.Method;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 5/8/17$
 * Input Parameter:用于查询手机号
 */

public class PhoneQueryActivity extends BaseActivity implements IQueryPhoneView {

    private EditText et_query_phone;
    private ImageView iv_phone_area;

    private KeyboardView keyboardView;

    private Context ctx;

    private KeyboardUtil keyboardUtil;

    private QueryPhonePresenterImpl queryPhonePresenter;
    private Button bt_query_phone;

    private TextView tv_phone_province;
    private TextView tv_phone_city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().show();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_query_phone;
    }

    @Override
    public void initView() {

        et_query_phone = (EditText) findViewById(R.id.et_query_phone);
        keyboardView = (KeyboardView) findViewById(R.id.kb_query_phone);
        bt_query_phone = (Button) findViewById(R.id.bt_phone_query);

        ctx = this;

        keyboardUtil = new KeyboardUtil(PhoneQueryActivity.this, ctx, et_query_phone);
        queryPhonePresenter = new QueryPhonePresenterImpl(this);

        tv_phone_province = (TextView) findViewById(R.id.tv_phone_province);
        tv_phone_city = (TextView) findViewById(R.id.tv_phone_city);
    }

    @Override
    public void initData() {

    }

    private int phoneCount = 11;

    @Override
    public void initEvent() {

        disableShowSoftInput(et_query_phone);
        et_query_phone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                keyboardUtil.showKeyboard();
                return false;
            }
        });

        bt_query_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardUtil.hideKeyboard();
                String number = et_query_phone.getText().toString();
                if ("".equals(number) || (number.length() != phoneCount)) {
                    MyToast.showMessage("手机号码格式不正确");
                } else {
                    queryPhonePresenter.query(number);
                }
            }
        });
    }


    /**********************************************************
     Function Name : disableShowSoftInput
     Author : echo
     Modify Date : 17-7-4  下午4:28
     Feature : 禁止弹出系统软键盘
     Input Parameter & Range :
     Output Parameter & Range :
     Return Value : N/A
     **********************************************************/
    public void disableShowSoftInput(EditText paramEditText) {
        if (Build.VERSION.SDK_INT <= 10) {
            paramEditText.setInputType(0);
            return;
        }
        try {
            Class[] arrayOfClass = new Class[1];
            arrayOfClass[0] = Boolean.TYPE;
            Method localMethod = EditText.class.getMethod(
                    "setShowSoftInputOnFocus", arrayOfClass);
            localMethod.setAccessible(true);
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = Boolean.valueOf(false);
            localMethod.invoke(paramEditText, arrayOfObject);

        } catch (Exception localException) {
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        keyboardUtil.hideKeyboard();

        return super.onTouchEvent(event);
    }

    @Override
    public void querySueecss(PhoneInfo info) {
        tv_phone_city.setText(info.getCity());
        tv_phone_province.setText(info.getProvince());

    }

    @Override
    public void queryFail() {
        MyToast.showMessage("查询失败");

    }

    @Override
    public void phoneUnillgel() {
        MyToast.showMessage("手机号不正确");


    }

    @Override
    public void phoneNull() {
        MyToast.showMessage("手机号为空");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
