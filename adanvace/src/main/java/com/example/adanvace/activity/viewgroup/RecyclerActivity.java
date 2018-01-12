package com.example.adanvace.activity.viewgroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;
import com.example.adanvace.adapter.CustomRecyclerViewAdapter;
import com.example.adanvace.adapter.TeacherAdapter;
import com.example.adanvace.info.StudentInfo;
import com.example.adanvace.view.MyToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 21/8/17
 * Issue : TODO
 * Whether solve :
 */

public class RecyclerActivity extends BaseActivity {

    private RecyclerView rv_base;

    private CustomRecyclerViewAdapter adapter;

    private TeacherAdapter teacherAdapter;

    private List<StudentInfo> studentInfoList = new ArrayList<>();

    private int[] ids = {R.id.tv_list_id, R.id.tv_list_name, R.id.tv_list_point};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyToast.init(this, LayoutInflater.from(this));
    }

    @Override
    public int getContentView() {
        return R.layout.activity_recycler_view;
    }

    @Override
    public void initView() {
        rv_base = (RecyclerView) findViewById(R.id.rv_base);

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

        //    adapter = new CustomRecyclerViewAdapter(this,studentInfoList);

        teacherAdapter = new TeacherAdapter(this, studentInfoList, R.layout.list_student, ids);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_base.setLayoutManager(linearLayoutManager);

        rv_base.setAdapter(teacherAdapter);

        teacherAdapter.setOnItemClickListener(new TeacherAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                MyToast.showMessage("position" + position);
                int size = studentInfoList.size() + 1;
                StudentInfo info = new StudentInfo(size, "ALAN" + size, (500 + size));
                studentInfoList.add(info);
                teacherAdapter.notifyDataSetChanged();

            }
        });

    }
}
