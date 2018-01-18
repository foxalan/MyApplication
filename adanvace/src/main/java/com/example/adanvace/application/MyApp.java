package com.example.adanvace.application;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import com.example.adanvace.view.MyToast;

/**
 * Function :
 * Author : Alan
 * Modify Date : 24/8/17
 * Issue : TODO
 * Whether solve :
 */

public class MyApp extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        MyToast.init(this, LayoutInflater.from(this));
    }

    public static void setContext(Context context) {
        MyApp.context = context;
    }

    public static Context getContext() {
        return context;
    }
}
