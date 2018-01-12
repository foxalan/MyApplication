package com.example.modeldemo.tactic.impl;

import com.example.modeldemo.tactic.inter.IDefendBehavior;
import com.example.modeldemo.util.L;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 1/8/17$
 * Input Parameter &
 */

public class DefendHauberkImpl implements IDefendBehavior {
    @Override
    public void defend() {
        L.i("用护甲防御");
    }
}
