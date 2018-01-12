package com.example.modeldemo.model;

import android.provider.ContactsContract;

import com.example.modeldemo.state.LuckStates;
import com.example.modeldemo.state.MoneyHasState;
import com.example.modeldemo.state.MoneyOutState;
import com.example.modeldemo.state.SoldOutState;
import com.example.modeldemo.state.SoldState;
import com.example.modeldemo.state.States;
import com.example.modeldemo.util.L;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 1/8/17$
 * Input Parameter &
 */

public class VendingMachine {

    private States currentStates;
    private int count;

    private MoneyOutState moneyOutState;
    private MoneyHasState moneyHasState;
    private SoldState soldState;
    private SoldOutState soldOutState;
    private LuckStates luckStates;

    public States getCurrentStates() {
        return currentStates;
    }

    public void setCurrentStates(States currentStates) {
        this.currentStates = currentStates;
    }

    public int getCount() {

        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MoneyOutState getMoneyOutState() {
        return moneyOutState;
    }

    public void setMoneyOutState(MoneyOutState moneyOutState) {
        this.moneyOutState = moneyOutState;
    }

    public MoneyHasState getMoneyHasState() {
        return moneyHasState;
    }

    public void setMoneyHasState(MoneyHasState moneyHasState) {
        this.moneyHasState = moneyHasState;
    }

    public SoldState getSoldState() {
        return soldState;
    }

    public void setSoldState(SoldState soldState) {
        this.soldState = soldState;
    }

    public SoldOutState getSoldOutState() {
        return soldOutState;
    }

    public void setSoldOutState(SoldOutState soldOutState) {
        this.soldOutState = soldOutState;
    }

    public LuckStates getLuckStates() {
        return luckStates;
    }

    public void setLuckStates(LuckStates luckStates) {
        this.luckStates = luckStates;
    }

    public VendingMachine(int count) {
        moneyOutState = new MoneyOutState(this);
        moneyHasState = new MoneyHasState(this);
        soldState = new SoldState(this);
        soldOutState = new SoldOutState(this);
        luckStates = new LuckStates(this);

        this.count = count;
        currentStates = moneyOutState;
    }

    public void insertCoins() {
        currentStates.setMoney();
    }

    public void shopping() {
        currentStates.buyWare();

        if (currentStates == soldState || currentStates == luckStates)
            currentStates.getLuck();
    }

    public void refund() {
        currentStates.getRefund();
    }

    public void vendOut() {
        if (count != 0){
            count--;
            L.d("出货成功 ");


        }

    }

}
