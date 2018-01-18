package com.example.adanvace.activity.view;

import com.example.adanvace.R;
import com.example.adanvace.view.CalenderView;

/**
 * Function :
 * Modify Date : 2018/1/18
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class SignActivity extends BaseActivity {

    private CalenderView mCalenderView;
    @Override
    public int getContentView() {
        return R.layout.activity_sign;
    }

    @Override
    public void initView() {
        mCalenderView = (CalenderView) findViewById(R.id.view_calender);
    //    mCalenderView.updateView();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
