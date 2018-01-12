package com.example.modeldemo.state;

import com.example.modeldemo.model.VendingMachine;
import com.example.modeldemo.util.L;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 1/8/17$
 * Input Parameter &
 */

public class MoneyOutState implements States {

    private VendingMachine vendingMachine;

    public MoneyOutState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }


    @Override
    public void setMoney() {
        vendingMachine.setCurrentStates(vendingMachine.getMoneyHasState());
        L.d("投币成功");

    }

    @Override
    public void buyWare() {
        L.d("没钱还想买东西");

    }

    @Override
    public void getRefund() {
        L.d("没投币还想退款");
    }

    @Override
    public void getLuck() {
        L.d("非法操作");

    }


}
