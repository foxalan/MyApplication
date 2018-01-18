package com.example.adanvace.util;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Alan on 2017/11/17.
 */

public class WindowUtil {


    public static int[] getSystemLength(Context context){
        int[] length = new int[2];
        WindowManager wm =(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        length[0] = display.getWidth();
        length[1] = display.getHeight();

        return length;
    }


}
