package com.example.adanvace.activity.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.adanvace.R;
import com.example.adanvace.view.CustomListView;
import com.example.adanvace.view.CustomScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Function : 自定义ScrollView的反弹效果
 * Author : Alan
 * Modify Date : 12/8/17
 * Issue : TODO
 * Whether solve :
 */

public class ScrollViewActivity extends BaseActivity {

    private CustomScrollView customScrollView;
    private CustomListView customListView;

    private ArrayAdapter<String> arrayAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_scroll;
    }

    @Override
    public void initView() {

        customScrollView = (CustomScrollView) findViewById(R.id.customScrollView);
        customListView = (CustomListView) findViewById(R.id.customListView);
    }

    @Override
    public void initData() {

        for (int i = 0; i < 15; i++) {
            list.add(i+"===================");
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        customListView.setAdapter(arrayAdapter);
    }

    @Override
    public void initEvent() {
        customScrollView.setCallBack(new CustomScrollView.ICallBack() {
            @Override
            public void call() {
                Toast.makeText(ScrollViewActivity.this,"---====",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
