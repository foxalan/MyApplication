package com.example.mybulter.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mybulter.R;
import com.example.mybulter.presenter.inter.RegisterPresenter;
import com.example.mybulter.presenter.impl.RegisterPresenterImpl;
import com.example.mybulter.util.L;
import com.example.mybulter.view.IdentifyView;
import com.example.mybulter.view.inter.RegisterView;

/**
 * 1.添加手机号注册
 * 2.手机短信验证
 */

public class RegisterFragment extends Fragment implements RegisterView {

    private EditText et_register_username;
    private EditText et_register_password;
    private EditText et_register_password_agein;
    private EditText et_identify;

    private Button bt_register_sure;
    private IdentifyView view_identify;

    private boolean isIdentify;


    private RegisterPresenter registerPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, null);

        initViews(view);
        initEvent();

        registerPresenter = new RegisterPresenterImpl(this);
        return view;
    }

    private void initViews(View view) {


        et_register_username = (EditText) view.findViewById(R.id.et_register_username);
        et_register_password = (EditText) view.findViewById(R.id.et_register_password);
        et_register_password_agein = (EditText) view.findViewById(R.id.et_register_password_again);
        et_identify = (EditText) view.findViewById(R.id.et_identify);
        bt_register_sure = (Button) view.findViewById(R.id.bt_register_sure);

        view_identify = (IdentifyView) view.findViewById(R.id.view_identify);


        bt_register_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String user_name = et_register_username.getText().toString();
                String pass_word = et_register_password.getText().toString();
                isIdentify = et_identify.getText().toString().equals(view_identify.getStr_identify()) ? true : false;

                registerPresenter.register(user_name, pass_word, isIdentify);


            }
        });
    }

    private void initEvent(){

        et_register_password_agein.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b){
                    if (et_register_password_agein.getText().toString().equals(et_register_password.getText().toString())){

                    }else {
                        L.d("两次的password不一致");
                    }
                }
            }
        });

    }

    @Override
    public void setSuccess() {
        L.d("register success");

    }

    @Override
    public void setFailed() {
        L.d("register failed");

    }

    @Override
    public void incomplete_information() {

        L.d("register incomplete information");
    }

    @Override
    public void identity_error() {
        L.d("identify_error");

    }

    @Override
    public void setUserExits() {
        L.d("user_exits");
    }
}
