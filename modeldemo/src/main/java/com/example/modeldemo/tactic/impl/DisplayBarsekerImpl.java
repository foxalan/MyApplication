package com.example.modeldemo.tactic.impl;

import com.example.modeldemo.tactic.inter.IDisplayBehavior;
import com.example.modeldemo.util.L;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 1/8/17$
 * Input Parameter &
 */

public class DisplayBarsekerImpl implements IDisplayBehavior{

    @Override
    public void display() {
        L.i("狂战士");
    }
}
