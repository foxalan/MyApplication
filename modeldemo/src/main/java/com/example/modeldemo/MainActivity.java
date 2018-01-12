package com.example.modeldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.modeldemo.model.StatusModel;
import com.example.modeldemo.model.VendingMachine;
import com.example.modeldemo.observer.impl.FlyObserver;
import com.example.modeldemo.observer.impl.GameSubject;
import com.example.modeldemo.observer.impl.RunObserver;
import com.example.modeldemo.tactic.impl.AttackGunImpl;
import com.example.modeldemo.tactic.impl.DefendHauberkImpl;
import com.example.modeldemo.tactic.impl.DisplayBarsekerImpl;
import com.example.modeldemo.tactic.impl.RunCarImpl;
import com.example.modeldemo.tactic.model.RoleMo;
import com.example.modeldemo.util.L;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VendingMachine machine = new VendingMachine(100);

//        machine.insertCoins();
//        machine.shopping();
//      //  machine.refund();
//
//        machine.insertCoins();
//        machine.shopping();
//        machine.insertCoins();
//        machine.refund();
//        machine.shopping();
//
//        RoleMo mo = new RoleMo("小莫");
//
//        mo.setAttackBehavior(new AttackGunImpl());
//        mo.setDefendBehavior(new DefendHauberkImpl());
//        mo.setDisplayBehavior(new DisplayBarsekerImpl());
//        mo.setRunBehavior(new RunCarImpl());
//
//        mo.display();
//        mo.attack();
//        mo.defend();
//        mo.run();
//        L.i(mo.name);

        GameSubject subject = new GameSubject();
        FlyObserver flyObserver = new FlyObserver(subject);
        RunObserver runObserver = new RunObserver(subject);


        subject.setMsg("lol");
        subject.setMsg("CF");


    }
}
