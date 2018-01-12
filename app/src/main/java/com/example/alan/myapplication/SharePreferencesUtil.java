package com.example.alan.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Function Name : SetAudioMute
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 */

public class SharePreferencesUtil {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String SHARED_NAME = "alan";

    public SharePreferencesUtil(Context context) {
        preferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void writeSting(String key, String value) {

        editor.putString(key, value);
        editor.commit();

    }

    public String getString(String key){
        String str = preferences.getString(key,"null");

        return str;
    }

}
