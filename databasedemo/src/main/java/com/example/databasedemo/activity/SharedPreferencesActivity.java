package com.example.databasedemo.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;


import com.example.databasedemo.R;
import com.example.databasedemo.fragment.FragmentRegister;
import com.example.databasedemo.fragment.FragmentSign;



public class SharedPreferencesActivity extends Activity {



    private FragmentTransaction transaction;
    private FragmentSign fragment_sign;

    private android.app.ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionBar = this.getActionBar();
        actionBar.setCustomView(R.layout.listview_item);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();

        setContentView(R.layout.activity_share);


        fragment_sign = new FragmentSign();
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_contain,fragment_sign);
        transaction.addToBackStack(null);
        transaction.commit();





    }
}
