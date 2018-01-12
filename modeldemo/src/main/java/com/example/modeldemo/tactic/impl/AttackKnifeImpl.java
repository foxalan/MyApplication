package com.example.modeldemo.tactic.impl;

import com.example.modeldemo.tactic.inter.IAttackBehavior;
import com.example.modeldemo.util.L;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 1/8/17$
 * Input Parameter &
 */

public class AttackKnifeImpl implements IAttackBehavior {

    @Override
    public void attack() {
        L.i("用刀攻击");
    }

}
