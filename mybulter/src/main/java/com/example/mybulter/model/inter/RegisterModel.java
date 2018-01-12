package com.example.mybulter.model.inter;

import com.example.mybulter.presenter.inter.OnRegisterFinishedListener;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 21/7/17$
 * Input Parameter 注册控制
 */

public interface RegisterModel {

    void startRegister(String username , String password , OnRegisterFinishedListener onRegisterFinishedListener);
}
