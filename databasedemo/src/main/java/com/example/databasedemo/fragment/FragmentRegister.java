package com.example.databasedemo.fragment;

import android.app.Fragment;
import android.graphics.ImageFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;

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

public class FragmentRegister extends Fragment {

    private Button bt_back;
    private EditText et_register_username;
    private EditText et_register_password;
    private Button bt_register_sure;
    private SharedPreferencesUtil sharedUtil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,null);

        sharedUtil = new SharedPreferencesUtil(getActivity());
        initViews(view);


        return view;
    }

    private void initViews(View view) {

        bt_back = (Button) view.findViewById(R.id.bt_back);
        et_register_username = (EditText) view.findViewById(R.id.et_register_username);
        et_register_password = (EditText) view.findViewById(R.id.et_register_password);
        bt_register_sure = (Button) view.findViewById(R.id.bt_register_sure);


        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl_contain,new FragmentSign()).commit();
            }
        });


        bt_register_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(et_register_username.getText().toString())||"".equals(et_register_password.getText().toString())){
                    Toast.makeText(getActivity(),"信息不完全,请重新填写",Toast.LENGTH_SHORT).show();
                    return;
                }

                String user_name = et_register_username.getText().toString();
                String pass_word = et_register_password.getText().toString();

                sharedUtil.setUserName(user_name);
                sharedUtil.setUserPassword(pass_word);


            }
        });
    }
}
