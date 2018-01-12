package com.example.modeldemo.state;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 1/8/17$
 * Input Parameter &
 */

public interface States {

    void setMoney();

    void buyWare();

    void getRefund();

    /**
     * 出货,不是幸运
     */
    void getLuck();
}
