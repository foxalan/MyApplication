package com.example.mybulter.view.inter;

import com.example.mybulter.info.PhoneInfo;

/**
 * Function Name :
 * Author : Alan
 * Modify Date : 8/8/17
 * Input Parameter :
 */

public interface IQueryPhoneView {

    //查询成功
    void querySueecss(PhoneInfo info);

    //查询失败
    void queryFail();

    //输入的手机号不规范

    void phoneUnillgel();

    //输入的手机号为空
    void phoneNull();
}
