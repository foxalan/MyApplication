package com.example.mybulter.presenter.impl;

import com.example.mybulter.info.UserInfo;
import com.example.mybulter.model.inter.RegisterModel;
import com.example.mybulter.model.impl.RegisterModelImpl;
import com.example.mybulter.presenter.inter.OnRegisterFinishedListener;
import com.example.mybulter.presenter.inter.RegisterPresenter;
import com.example.mybulter.util.L;
import com.example.mybulter.view.inter.RegisterView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 21/7/17$
 * Input Parameter &
 */

public class RegisterPresenterImpl implements RegisterPresenter, OnRegisterFinishedListener {

    private RegisterView registerView;
    private RegisterModel registerModel;

    public RegisterPresenterImpl(RegisterView registerView) {
        this.registerView = registerView;
        registerModel = new RegisterModelImpl();
    }


    @Override
    public void register(String username, String password, boolean isIdentify) {
        if (!isIdentify) {
            registerView.identity_error();
            return;
        }
        querySingleData(username,password);

    }

    public void querySingleData(final String username, final String password) {


        BmobQuery<UserInfo> query = new BmobQuery<UserInfo>();
        query.addWhereEqualTo("userName", username);
        query.setLimit(10);
        query.findObjects(new FindListener<UserInfo>() {
            @Override
            public void done(List<UserInfo> object, BmobException e) {
                if(e==null){
                    if (object.size() == 0 ){

                        if (username.equals("") || password.equals("")) {
                            registerView.incomplete_information();
                        } else {
                            registerModel.startRegister(username, password, RegisterPresenterImpl.this);
                        }

                    }else {
                        registerView.setUserExits();
                    }

                    //       onLoginFinishedListener.onUsernameError();
                }else{
                    L.d(e.getErrorCode()+""+e.toString());
                    registerView.setFailed();
                }
            }
        });
    }

    @Override
    public void onSuccess() {
        registerView.setSuccess();

    }

    @Override
    public void onFail() {
        registerView.setFailed();
    }
}
