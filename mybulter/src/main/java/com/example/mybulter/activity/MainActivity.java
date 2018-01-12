package com.example.mybulter.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mybulter.R;
import com.example.mybulter.util.SharedPreferencesUtil;

import static com.example.mybulter.constant.Constant.MSG_GUIDE;
import static com.example.mybulter.constant.Constant.MSG_HOME;

public class MainActivity extends AppCompatActivity {


    private SharedPreferencesUtil sharedPreferencesUtil;

    private boolean isFirst = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GUIDE:
                    startActivity(new Intent(MainActivity.this, GuideActivity.class));
                    finish();
                    break;
                case MSG_HOME:
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferencesUtil = new SharedPreferencesUtil(this);
        isFirst = sharedPreferencesUtil.getIsFirst();


        if (isFirst) {
            Message message = new Message();
            message.what = MSG_GUIDE;
            handler.sendMessageDelayed(message, 2000);
        } else {
            Message message = new Message();
            message.what = MSG_HOME;
            handler.sendMessageDelayed(message, 2000);
        }

    }

    @Override
    public void onBackPressed() {

    }
}
