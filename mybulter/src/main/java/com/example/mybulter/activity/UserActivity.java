package com.example.mybulter.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;

import com.example.mybulter.R;
import com.example.mybulter.adapter.UserAdapter;
import com.example.mybulter.info.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Modify Date : 2018/1/16
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class UserActivity extends BaseHomeActivity {

    private ListViewCompat mListView;
    private List<UserBean> beanList = new ArrayList<>();
    private String[] items;
    private AppCompatTextView mTextHeader;
    private AppCompatImageView mImageHeader;
    private UserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        init();
    }

    private void init() {
        initView();
        initData();
    }

    private void initView() {
        mListView = (ListViewCompat) findViewById(R.id.lv_user_info);
        View headView = LayoutInflater.from(this).inflate(R.layout.layout_header,null);
        mTextHeader = (AppCompatTextView) headView.findViewById(R.id.tv_user_head_item);
        mImageHeader = (AppCompatImageView) headView.findViewById(R.id.iv_user_head_content);
        mListView.addHeaderView(headView);
    }

    private void initData() {
        mTextHeader.setText("alan");
        mImageHeader.setImageResource(R.drawable.icon_user);
        beanList.clear();
        items = getResources().getStringArray(R.array.user);
        for (int i = 0; i < items.length; i++) {
            UserBean bean = new UserBean(items[i],"");
            beanList.add(bean);
        }
        mAdapter = new UserAdapter(this,beanList,R.layout.item_user_info);
        mListView.setAdapter(mAdapter);
    }


}
