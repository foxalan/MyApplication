package com.example.databasedemo.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databasedemo.R;
import com.example.databasedemo.util.SharedPreferencesUtil;

/**
 * Function Name : SetAudioMute
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 */

public class FragmentSign extends Fragment {
    private EditText et_username;
    private EditText et_password;
    private Button bt_register;
    private Button bt_sign;

    private FragmentTransaction transaction;

    private Fragment fragment_register;

    private String username;
    private String password;
    private SharedPreferencesUtil util;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign, null);

        initData();
        initViews(view);

        return view;
    }

    private void initData() {

        util = new SharedPreferencesUtil(getActivity());
        username = util.getUSER_NAME();
        password = util.getPASS_WORD();


    }

    private void initViews(View view) {

        fragment_register = new FragmentRegister();

        et_username = (EditText) view.findViewById(R.id.et_username);
        et_password = (EditText) view.findViewById(R.id.et_password);
        bt_register = (Button) view.findViewById(R.id.bt_register);
        bt_sign = (Button) view.findViewById(R.id.bt_login);

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_contain, fragment_register);
                transaction.addToBackStack(null);
                transaction.hide(new FragmentSign());
                transaction.commit();
            }
        });


        bt_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ("".equals(et_password.getText().toString())||"".equals(et_username.getText().toString())){
                    Toast.makeText(getActivity(),"无信息输入",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password == null || username == null){
                    Toast.makeText(getActivity(),"未注册",Toast.LENGTH_SHORT).show();

                    return;
                }
                Log.d("tang",username+":"+password);
                if (et_password.getText().toString().equals(password)&&et_username.getText().toString().equals(username)){
                    Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getActivity(),"用户名或密码错误",Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}
