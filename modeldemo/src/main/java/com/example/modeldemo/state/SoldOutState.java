package com.example.modeldemo.state;

import com.example.modeldemo.model.VendingMachine;
import com.example.modeldemo.util.L;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 1/8/17$
 * Input Parameter &
 */

public class SoldOutState implements States {

    private VendingMachine machine;

    public SoldOutState(VendingMachine machine){
        this.machine = machine;
    }


    @Override
    public void setMoney() {
        L.d("商品卖完了,投钱也没用");

    }

    @Override
    public void buyWare() {
        L.d("商品木有了");

    }

    @Override
    public void getRefund() {
        L.d("商品都没了,还想退钱");
    }

    @Override
    public void getLuck() {
        L.d("没有商品,无法出货");
    }
}
