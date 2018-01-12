package com.example.mybulter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mybulter.R;
import com.example.mybulter.adapter.SettingAdapter;
import com.example.mybulter.data.ListSource;
import com.example.mybulter.info.SettingInfo;

import java.util.ArrayList;
import java.util.List;


public class SettingActivity extends BaseActivity {

    private ListView lv_setting;
    private List<SettingInfo> infoList = new ArrayList<>();
    private ListSource listSource;
    private SettingAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().show();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        lv_setting = (ListView) findViewById(R.id.lv_setting);
    }

    @Override
    public void initData() {
        listSource = new ListSource(this);
        infoList = listSource.getAllData();

        adapter = new SettingAdapter(this,infoList);
    }

    @Override
    public void initEvent() {
        lv_setting.setAdapter(adapter);
        lv_setting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    //个人信息
                    case 0:
                        break;
                    //归属地查询
                    case 1:
                        startActivity(new Intent(SettingActivity.this,PhoneQueryActivity.class));
                        break;
                    /**
                     * 检查更新
                     */
                    case 2:

                        break;
                    case 3:

                        break;
                    default:
                        break;
                }

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }


}
