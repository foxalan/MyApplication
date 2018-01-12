package com.example.modeldemo.observer.impl;

import com.example.modeldemo.observer.Subject;
import com.example.modeldemo.observer.inter.Observer;
import com.example.modeldemo.util.L;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 2/8/17$
 * Input Parameter &
 */

public class FlyObserver implements Observer {

    private Subject subject ;

    public FlyObserver(Subject subject)
    {
        this.subject = subject  ;
        subject.registerObservers(this);
    }



    @Override
    public void update(String msg) {
        L.d(msg+"FlyObserver");
    }
}
