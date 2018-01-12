package com.example.adanvace.activity.viewgroup;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;

/**
 * Function : 自定义ViewGroup1
 * Author : Alan
 * Modify Date : 19/8/17
 * Issue : TODO
 * Whether solve :
 */

public class ViewGroupActivity extends BaseActivity {

    private ViewGroup viewGroup;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_viewgroup;
    }

    @Override
    public void initView() {
        viewGroup = (ViewGroup) findViewById(R.id.vp_textView);

    }

    @Override
    public void initData() {

        textView = new TextView(this);
        textView.setText("5");
        textView.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setBackgroundColor(Color.BLUE);

    }

    @Override
    public void initEvent() {
        viewGroup.addView(textView);
    }
}
