package com.example.adanvace.activity.viewgroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;
import com.example.adanvace.adapter.StudentAdapter;
import com.example.adanvace.info.StudentInfo;
import com.example.adanvace.view.MyToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :打造万能的适配器
 * Author : Alan
 * Modify Date : 21/8/17
 * Issue : TODO
 * Whether solve :
 */

public class AdapterActivity extends BaseActivity {

    private ListView lv_base;

    private List<StudentInfo> studentInfoList = new ArrayList<>();
    private StudentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyToast.init(this, LayoutInflater.from(this));
    }

    @Override
    public int getContentView() {
        return R.layout.activity_base_adapter;
    }

    @Override
    public void initView() {
        lv_base = (ListView) findViewById(R.id.lv_base);

    }

    @Override
    public void initData() {

        for (int i = 0; i < 10; i++) {

            StudentInfo info = new StudentInfo(i + 1, "ALAN" + i, 500 + i);
            studentInfoList.add(info);
        }
    }

    @Override
    public void initEvent() {
    //    adapter = new StudentAdapter(this, studentInfoList);
        adapter = new StudentAdapter(this, studentInfoList,R.layout.list_student);
        lv_base.setAdapter(adapter);
        lv_base.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyToast.showMessage("position"+position);
                StudentInfo info = new StudentInfo(studentInfoList.size(),"ALAN"+studentInfoList.size()+1,500+studentInfoList.size());
                studentInfoList.add(info);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
