package com.example.mybulter.model.impl;

import com.example.mybulter.info.UserInfo;
import com.example.mybulter.model.inter.RegisterModel;
import com.example.mybulter.presenter.inter.OnRegisterFinishedListener;
import com.example.mybulter.util.L;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 21/7/17$
 * Input Parameter &
 */

public class RegisterModelImpl implements RegisterModel {
    @Override
    public void startRegister(String username, String password, final OnRegisterFinishedListener onRegisterFinishedListener) {

        UserInfo userInfo = new UserInfo();
    //    userInfo.setUserId(TimeUtil.getCurrentTimeByMsec());
        userInfo.setUserId("4396");
        userInfo.setUserName(username);
        userInfo.setPassword(password);

        userInfo.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null){
                    onRegisterFinishedListener.onSuccess();
                }else {
                    L.d(e.getErrorCode()+"  "+e.toString());
                    onRegisterFinishedListener.onFail();
                }
            }
        });


        }
    }
