package com.example.databasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.databasedemo.activity.DataBaseActivity;
import com.example.databasedemo.activity.SharedPreferencesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_share:
                startActivity(new Intent(MainActivity.this, SharedPreferencesActivity.class));
                break;
            case R.id.bt_database:
                startActivity(new Intent(MainActivity.this, DataBaseActivity.class));
                break;

        }
    }
}
