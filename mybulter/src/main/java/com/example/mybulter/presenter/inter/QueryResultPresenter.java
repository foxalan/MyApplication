package com.example.mybulter.presenter.inter;

import com.example.mybulter.info.PhoneInfo;

/**
 * Function Name :
 * Author : Alan
 * Modify Date : 8/8/17
 * Input Parameter :
 */

public interface QueryResultPresenter {

    void success(PhoneInfo info);

    void fail();
}
