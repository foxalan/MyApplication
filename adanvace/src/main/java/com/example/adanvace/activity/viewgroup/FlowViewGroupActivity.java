package com.example.adanvace.activity.viewgroup;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.adanvace.R;
import com.example.adanvace.activity.view.BaseActivity;
import com.example.adanvace.util.L;
import com.example.adanvace.view.MyToast;
import com.example.adanvace.viewgroup.FlowViewGroup;


/**
 * Function :自定义的标签ViewGroup
 * Author : Alan
 * Modify Date : 19/8/17
 * Issue : 1.无法对MarginLayout 的大小进行设置
 * Whether solve :
 */

public class FlowViewGroupActivity extends BaseActivity {

    private FlowViewGroup vp_flow;

    private EditText et_add_flow;
    private Button bt_add_flow;

    private final int MSG_UPDATE_FLOW = 0x123;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_UPDATE_FLOW) {

                String text = (String) msg.obj;
                addFlow(text);

            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyToast.init(this, LayoutInflater.from(this));

    }

    @Override
    public int getContentView() {
        return R.layout.activity_viewgroup_flow;
    }

    @Override
    public void initView() {

        vp_flow = (FlowViewGroup) findViewById(R.id.vp_flow);
        et_add_flow = (EditText) findViewById(R.id.et_add_flow);
        bt_add_flow = (Button) findViewById(R.id.bt_add_flow);
    }

    @Override
    public void initData() {
        WindowManager window = (WindowManager) getSystemService(WINDOW_SERVICE);

        Display metrics = window.getDefaultDisplay();
        int width = metrics.getWidth();
        int height = metrics.getHeight();

    }

    @Override
    public void initEvent() {
        vp_flow.setOnItemClickLister(new FlowViewGroup.OnItemClickLister() {
            @Override
            public void click(View view, int position) {
                TextView textView = (TextView) view;
                MyToast.showMessage(textView.getText().toString() + "==" + position);
            }
        });


        bt_add_flow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_add_flow.getText().toString().equals("")) {
                    MyToast.showMessage("NULL");
                    return;
                }

                Message message = new Message();
                message.what = MSG_UPDATE_FLOW;
                message.obj = et_add_flow.getText().toString();
                mHandler.sendMessage(message);


            }
        });
    }

    /**
     * 添加标签
     *
     * @param text
     */

    private void addFlow(String text) {
        TextView textView = new TextView(this);

        textView.setText(text);
        int width;
        textView.setTextSize(25);
        textView.setPadding(5, 5, 5, 5);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundResource(R.drawable.bg_tv_flow);

        textView.setTextColor(Color.BLACK);
        int length = text.length();
        width = 40 * length + 20;
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(width, 100);
        layoutParams.setMargins(26, 26, 26, 26);
        L.d("==============" + width);
        textView.setLayoutParams(layoutParams);
        vp_flow.addView(textView);
    }
}
