package com.example.sportlogs.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Function Name : SetAudioMute
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 */

public class SharedPreferencesUtil {
    private String IS_FIRST_COMING = "is_first_coming";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String IS_FIRST = "is_first";

    public SharedPreferencesUtil(Context context){
        sharedPreferences = context.getSharedPreferences(IS_FIRST_COMING,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setIsFirst(boolean isFirst){

        editor.putBoolean(IS_FIRST,isFirst);
        editor.commit();
    }

    public boolean getIsFirst(){
        boolean isFirst = sharedPreferences.getBoolean(IS_FIRST,true);
        return isFirst;
    }

    public void setStringContent(String string){

    }


}
