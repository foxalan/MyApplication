package com.example.mybulter.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mybulter.R;
import com.example.mybulter.presenter.impl.ProvePresenterImpl;
import com.example.mybulter.presenter.inter.ProvePresenter;
import com.example.mybulter.view.inter.ProveMessageView;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 25/7/17$
 * Input Parameter &
 */

public class ProveFragment extends Fragment implements ProveMessageView{

    private EditText et_new_password;
    private EditText et_new_password_again;
    private EditText et_phone_number;
    private EditText et_prove;

    private Button bt_prove_sure;
    private Button bt_prove_short_message;

    private String phoneNumber;
    private String new_password;
    private String new_password_again;
    private String identify_code;

    private ProvePresenter provePresenter;
    private MyCountTimer timer;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_password,null);
        provePresenter = new ProvePresenterImpl(this);

        initViews(view);
        initEvent();

        return view;
    }

    private void initViews(View view) {

        et_new_password = (EditText) view.findViewById(R.id.et_new_password);
        et_new_password_again = (EditText) view.findViewById(R.id.et_register_password_again);
        et_phone_number = (EditText) view.findViewById(R.id.et_phone_number);
        et_prove = (EditText) view.findViewById(R.id.et_prove_short_message);

        bt_prove_sure = (Button) view.findViewById(R.id.bt_prove_sure);
        bt_prove_short_message = (Button) view.findViewById(R.id.bt_prove_short_message);

    }
    
    private void initEvent(){
        
        bt_prove_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timer = new MyCountTimer(60000,1000);
                timer.start();
            }
        });
        
        bt_prove_short_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();

                provePresenter.prove(phoneNumber,new_password,new_password_again,identify_code);
                
            }
        });
        
    }

    private void getData() {

        phoneNumber = et_phone_number.getText().toString();
        new_password = et_new_password.getText().toString();
        new_password_again = et_new_password_again.getText().toString();
        identify_code = et_prove.getText().toString();
    }

    @Override
    public void passwordNull() {
        
    }

    @Override
    public void passwordDifferent() {

    }

    @Override
    public void phoneNumberNull() {

    }

    @Override
    public void phoneNumberNotStandard() {

    }

    @Override
    public void identifyCodeError() {

    }

    @Override
    public void proveFail() {

    }

    @Override
    public void proveSuccess() {

    }

    class MyCountTimer extends CountDownTimer {

        public MyCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {

            bt_prove_short_message.setText((millisUntilFinished/1000)+"s后重发");

        }
        @Override
        public void onFinish() {

            bt_prove_short_message.setText("重新发送验证码");
        }
    }



}
