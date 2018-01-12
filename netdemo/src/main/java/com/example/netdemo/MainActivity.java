package com.example.netdemo;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.netdemo.activity.BreakDownloadActivity;
import com.example.netdemo.activity.CacheActivity;
import com.example.netdemo.activity.DownloadActivity;
import com.example.netdemo.activity.SimpleNetConnectActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_simple_net_connect:
                startActivity(new Intent(MainActivity.this, SimpleNetConnectActivity.class));
                break;
            case R.id.bt_cache_pic_connect:
                startActivity(new Intent(MainActivity.this, CacheActivity.class));
                break;
            case R.id.bt_download:
                startActivity(new Intent(MainActivity.this, DownloadActivity.class));
                break;
            case R.id.bt_breaking_point:
                startActivity(new Intent(MainActivity.this, BreakDownloadActivity.class));
                break;

        }
    }
}
