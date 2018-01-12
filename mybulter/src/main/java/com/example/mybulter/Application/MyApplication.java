package com.example.mybulter.Application;

import android.app.Application;
import android.content.Context;

import com.example.mybulter.constant.Constant;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import cn.bmob.v3.Bmob;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 19/7/17$
 * Input Parameter
 */

public class MyApplication extends Application {

    public static final String VOICE_KEY = "583081c6";


    @Override
    public void onCreate() {
        super.onCreate();
     //   CrashReport.initCrashReport(getApplicationContext(), "f1177e1a26", true);

        Bmob.initialize(this, Constant.BMOB_APP_ID);

        SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID + "=" + VOICE_KEY);


    }


}
