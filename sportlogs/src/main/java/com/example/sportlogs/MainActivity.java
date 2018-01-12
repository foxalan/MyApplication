package com.example.sportlogs;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.sportlogs.activity.HomeActivity;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123){
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                finish();

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Message message = new Message();
        message.what = 0x123;
        handler.sendMessageDelayed(message,2000);

    }

    @Override
    public void onBackPressed() {

    }


}
