package com.example.modeldemo.state;

import com.example.modeldemo.model.VendingMachine;
import com.example.modeldemo.util.L;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 1/8/17$
 * Input Parameter &
 */

public class SoldState implements States {

    private VendingMachine vendingMachine;

    public SoldState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void setMoney() {
        L.d("出货中,请不要再加钱");

    }

    @Override
    public void buyWare() {
        L.d("正在出货,不要点多次");

    }

    @Override
    public void getRefund() {
        L.d("正在出货,无法退款");
    }

    @Override
    public void getLuck() {
        vendingMachine.vendOut();
        if (vendingMachine.getCount() == 0){
            vendingMachine.setCurrentStates(vendingMachine.getSoldOutState());
        }else {
            vendingMachine.setCurrentStates(vendingMachine.getMoneyOutState());
        }

    }
}
