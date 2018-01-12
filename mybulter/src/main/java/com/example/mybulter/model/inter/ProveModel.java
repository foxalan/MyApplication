package com.example.mybulter.model.inter;

import com.example.mybulter.presenter.inter.OnPorveFinishedListener;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 25/7/17$
 * Input Parameter &
 */

public interface ProveModel {

    void getProveResult(String phone, String new_password, String identify_code, OnPorveFinishedListener onPorveFinishedListener);
}
