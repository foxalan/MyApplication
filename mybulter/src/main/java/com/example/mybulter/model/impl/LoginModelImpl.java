package com.example.mybulter.model.impl;

import com.example.mybulter.info.UserInfo;
import com.example.mybulter.model.inter.LoginModel;
import com.example.mybulter.presenter.inter.OnLoginFinishedListener;
import com.example.mybulter.util.L;

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

public class LoginModelImpl implements LoginModel {

    public LoginModelImpl(){

    }


    @Override
    public void login(String username, String password, OnLoginFinishedListener onLoginFinishedListener) {
        querySingleData(username,password,onLoginFinishedListener);

    }


    public void querySingleData(String username , final String password, final OnLoginFinishedListener onLoginFinishedListener){

        BmobQuery<UserInfo> query = new BmobQuery<UserInfo>();
        query.addWhereEqualTo("userName", username);
        query.setLimit(10);
        query.findObjects(new FindListener<UserInfo>() {
            @Override
            public void done(List<UserInfo> object, BmobException e) {
                if(e==null){
                    for (UserInfo userInfo : object) {
                        //获得playerName的信息
                        if (userInfo.getPassword().equals(password)){
                            onLoginFinishedListener.onSuccess();
                            break;
                        }
                    }

             //       onLoginFinishedListener.onUsernameError();
                }else{
                    L.d(e.getErrorCode()+""+e.toString());
                    onLoginFinishedListener.onUsernameError();
                }
            }
        });


    }
}
