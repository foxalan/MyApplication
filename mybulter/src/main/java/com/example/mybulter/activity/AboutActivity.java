package com.example.mybulter.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mybulter.R;
import com.example.mybulter.util.UtilTools;
import com.example.mybulter.view.MyToast;

import java.util.ArrayList;
import java.util.List;

/*
 *项目名： SmartButler
 *包名：   com.imooc.smartbutler.ui
 *文件名:  AboutActivity
 *创建者:  LGL
 *创建时间:2016/12/1223:00
 *描述:    关于软件
 */
public class AboutActivity extends BaseHomeActivity {

    private ListView mListView;
    private List<String> mList = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //去除阴影
        getSupportActionBar().setElevation(0);
        initView();
    }

    //初始化View
    private void initView() {
        mListView = (ListView) findViewById(R.id.mListView);

        mList.add(getString(R.string.text_app_name) + getString(R.string.app_name));
        mList.add(getString(R.string.text_version) + UtilTools.getVersion(this));
        mList.add(getString(R.string.text_website_address));

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList);
        //设置适配器
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 2:
                        MyToast.showMessage("click update");
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
