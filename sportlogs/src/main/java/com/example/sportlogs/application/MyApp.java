package com.example.sportlogs.application;

import android.app.Application;
import android.view.LayoutInflater;

import com.example.sportlogs.view.MyToast;

/**
 * Function :我的应用　
 * Author : Alan
 * Modify Date : 31/8/17
 * Issue : TODO
 * Whether solve :
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyToast.init(this, LayoutInflater.from(this));
    }
}
