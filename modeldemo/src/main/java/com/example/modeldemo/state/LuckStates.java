package com.example.modeldemo.state;

import com.example.modeldemo.model.VendingMachine;
import com.example.modeldemo.util.L;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 1/8/17$
 * Input Parameter &
 */

public class LuckStates implements States {

    private VendingMachine machine;

    public LuckStates(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void setMoney() {

        L.d("非法状态");
    }

    @Override
    public void buyWare() {
        L.d("非法状态");

    }

    @Override
    public void getRefund() {
        L.d("非法状态");
    }

    @Override
    public void getLuck() {
        L.d("你中奖了");
        machine.vendOut();

        if (machine.getCount() == 0) {
            L.d("不好意思,中奖了也没东西给你了");
            machine.setCurrentStates(machine.getSoldOutState());

        } else {

            machine.vendOut();
            machine.setCurrentStates(machine.getMoneyOutState());
        }


    }
}
