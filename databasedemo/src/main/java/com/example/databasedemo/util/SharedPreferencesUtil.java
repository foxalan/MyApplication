package com.example.databasedemo.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Function Name : SetAudioMute
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 */

public class SharedPreferencesUtil {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private final String NAME = "register";
    private final String USER_NAME = "user_name";
    private final String PASS_WORD = "pass_word";

    public SharedPreferencesUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUserName(String name) {
        editor.putString(USER_NAME, name);
        editor.commit();
    }

    public void setUserPassword(String password) {
        editor.putString(PASS_WORD, password);
        editor.commit();
    }

    public String getUSER_NAME() {
        String user_name = sharedPreferences.getString(USER_NAME, null);

        return user_name;
    }

    public String getPASS_WORD() {
        String pass_word = sharedPreferences.getString(PASS_WORD, null);

        return pass_word;
    }


}
