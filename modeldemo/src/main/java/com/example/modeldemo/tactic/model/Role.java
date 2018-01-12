package com.example.modeldemo.tactic.model;

import com.example.modeldemo.tactic.inter.IAttackBehavior;
import com.example.modeldemo.tactic.inter.IDefendBehavior;
import com.example.modeldemo.tactic.inter.IDisplayBehavior;
import com.example.modeldemo.tactic.inter.IRunBehavior;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 1/8/17$
 * Input Parameter &
 */

public abstract class Role {
    private IDisplayBehavior displayBehavior;
    private IAttackBehavior attackBehavior;
    private IDefendBehavior defendBehavior;
    private IRunBehavior runBehavior;
    public String name;

    public IDisplayBehavior getDisplayBehavior() {
        return displayBehavior;
    }

    public void setDisplayBehavior(IDisplayBehavior displayBehavior) {
        this.displayBehavior = displayBehavior;
    }

    public IAttackBehavior getAttackBehavior() {
        return attackBehavior;
    }

    public void setAttackBehavior(IAttackBehavior attackBehavior) {
        this.attackBehavior = attackBehavior;
    }

    public IDefendBehavior getDefendBehavior() {
        return defendBehavior;
    }

    public void setDefendBehavior(IDefendBehavior defendBehavior) {
        this.defendBehavior = defendBehavior;
    }

    public IRunBehavior getRunBehavior() {
        return runBehavior;
    }

    public void setRunBehavior(IRunBehavior runBehavior) {
        this.runBehavior = runBehavior;
    }

    public void display(){
        displayBehavior.display();
    }

    public void attack(){
        attackBehavior.attack();
    }

    public void defend(){
        defendBehavior.defend();
    }

    public void run(){
        runBehavior.run();
    }



}
