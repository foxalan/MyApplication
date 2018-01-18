package com.example.adanvace.util;


import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.adanvace.application.MyApp;

public class DimenUtil {

    public static int getScreenWidth(){

        return getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(){

        return getDisplayMetrics().heightPixels;
    }

    private static DisplayMetrics getDisplayMetrics(){

        final Resources resources = MyApp.getContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();

        return dm;
    }
}
