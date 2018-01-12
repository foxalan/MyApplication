package com.example.mybulter.model.inter;

import com.example.mybulter.presenter.inter.OnLoginFinishedListener;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 21/7/17$
 * Input Parameter &
 */

public interface LoginModel {

    void login(String username, String password, OnLoginFinishedListener onLoginFinishedListener);

}
