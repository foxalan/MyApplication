package com.example.mybulter.presenter.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.mybulter.model.inter.LoginModel;
import com.example.mybulter.model.impl.LoginModelImpl;
import com.example.mybulter.presenter.inter.LoginPresenter;
import com.example.mybulter.presenter.inter.OnLoginFinishedListener;
import com.example.mybulter.view.inter.LoginView;



public class LoginPresenterImpl implements LoginPresenter,OnLoginFinishedListener {

    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        loginModel = new LoginModelImpl();
    }


    @Override
    public void startLogin(String username, String password) {
        if (username.equals("")||password.equals("")){
            loginView.setContentNull();
        }




        loginModel.login(username,password,this);
    }

    @Override
    public void onDestory() {

    }

    @Override
    public void onUsernameError() {
        loginView.setUsernameError();

    }

    @Override
    public void onPasswordError() {
        loginView.setPasswordError();

    }

    @Override
    public void onSuccess() {
        loginView.setSuccess();

    }
}
