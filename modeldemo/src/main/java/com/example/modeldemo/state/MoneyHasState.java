package com.example.modeldemo.state;

import com.example.modeldemo.model.VendingMachine;
import com.example.modeldemo.util.L;

import java.util.Random;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 1/8/17$
 * Input Parameter &
 */

public class MoneyHasState implements States {

    private VendingMachine machine;

    public MoneyHasState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void setMoney() {
        L.d("已投币,请不要重复投币");

    }

    @Override
    public void buyWare() {
        L.d("正在购买");

        if (machine.getCount() == 0) {
            machine.setCurrentStates(machine.getSoldOutState());
        } else {

            machine.setCurrentStates(machine.getSoldState());

            Random random = new Random();
            int r = random.nextInt(5);

            if (r == 3 && machine.getCount() > 0) {

                machine.setCurrentStates(machine.getLuckStates());
            }
        }


    }

    @Override
    public void getRefund() {
        L.d("正在退款");
        machine.setCurrentStates(machine.getMoneyOutState());

    }

    @Override
    public void getLuck() {
        L.d("非法操作");

    }


}
