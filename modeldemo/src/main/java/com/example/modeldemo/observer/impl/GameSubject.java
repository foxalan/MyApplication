package com.example.modeldemo.observer.impl;

import com.example.modeldemo.observer.Subject;
import com.example.modeldemo.observer.inter.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 2/8/17$
 * Input Parameter &
 */

public class GameSubject implements Subject {

    private List<Observer> observerList = new ArrayList<>();

    private String msg;

    @Override
    public void registerObservers(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObservers(Observer observer) {
        int position = observerList.indexOf(observer);
        observerList.remove(position);
    }

    @Override
    public void notifyObservers() {

        for (Observer observer : observerList) {
            observer.update(msg);
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyObservers();
    }
}
