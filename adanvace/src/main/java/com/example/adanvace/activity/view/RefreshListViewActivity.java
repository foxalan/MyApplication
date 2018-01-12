package com.example.adanvace.activity.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.adanvace.R;
import com.example.adanvace.adapter.ContactAdapter;
import com.example.adanvace.info.ContactInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Function : listView的下拉刷新
 * Author : Alan
 * Modify Date : 14/8/17
 * Issue : 1.LayoutParams 的使用
 * accomplish: NO
 * Whether solve :
 */

public class RefreshListViewActivity extends BaseActivity {

    private ListView lv_refresh;
    private LayoutInflater layoutInflater;
    private RelativeLayout rl_header;

    private List<ContactInfo> contactInfoList = new ArrayList<>();
    private ContactAdapter adapter;

    private List<ContactInfo> currentList = new ArrayList<>();

    private int startY;

    private int scrollLength;
    private int rl_head_length;

    private final static int MSG_BEFORE_REFRESH = 0x123;
    private final static int MSG_IS_REFRESH = 0x124;
    private final static int MSG_BACK = 0x125;

    private LinearLayout.LayoutParams layoutParams;

    private int currentTop;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_BEFORE_REFRESH:
                    lv_refresh.setPadding(rl_header.getPaddingLeft(), scrollLength, rl_header.getPaddingRight(), rl_header.getPaddingBottom());
                    break;
                case MSG_IS_REFRESH:
                    lv_refresh.layout(lv_refresh.getLeft(), lv_refresh.getTop() + scrollLength - rl_head_length, lv_refresh.getRight(), lv_refresh.getBottom() + scrollLength - rl_head_length);

                    Log.d("TANG", "left:" + lv_refresh.getLeft() + "==top:" + lv_refresh.getTop() + "==right:" + lv_refresh.getRight() + "==bottom:" + lv_refresh.getBottom());

                    break;
                case MSG_BACK:
                    lv_refresh.layout(lv_refresh.getLeft(), currentTop, lv_refresh.getRight(), lv_refresh.getBottom());
                    Log.d("TANG", "left:" + lv_refresh.getLeft() + "==top:" + lv_refresh.getTop() + "==right:" + lv_refresh.getRight() + "==bottom:" + lv_refresh.getBottom());

                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public int getContentView() {
        return R.layout.activity_refresh_listview;
    }

    @Override
    public void initView() {

        layoutInflater = LayoutInflater.from(this);
        rl_header = (RelativeLayout) layoutInflater.inflate(R.layout.layout_listview_header, null);

        lv_refresh = (ListView) findViewById(R.id.lv_refresh);

    }

    @Override
    public void initData() {

        for (int i = 0; i < 100; i++) {
            ContactInfo info = new ContactInfo(false, false, "ALAN" + i, "TANG" + i);
            contactInfoList.add(info);
        }

        for (int i = 0; i < 18; i++) {
            ContactInfo info = contactInfoList.get(i);
            currentList.add(info);
        }

        adapter = new ContactAdapter(this, currentList);

        rl_head_length = rl_header.getMeasuredHeight();
        Log.d("TANG", rl_head_length + "========");

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void initEvent() {
        lv_refresh.setAdapter(adapter);
        lv_refresh.addHeaderView(rl_header);

        Log.d("TANG", "left:" + lv_refresh.getLeft() + "==top:" + lv_refresh.getTop() + "==right:" + lv_refresh.getRight() + "==bottom:" + lv_refresh.getBottom());

        lv_refresh.setSelection(1);

        Log.d("TANG", "left:" + lv_refresh.getLeft() + "==top:" + lv_refresh.getTop() + "==right:" + lv_refresh.getRight() + "==bottom:" + lv_refresh.getBottom());


        lv_refresh.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        lv_refresh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        startY = (int) event.getY();

                        break;
                    case MotionEvent.ACTION_MOVE:

                        int moveY = (int) event.getY();
                        scrollLength = moveY - startY;
                        Log.d("TANG", scrollLength + "================" + rl_head_length);

                        mHandler.sendEmptyMessage(MSG_IS_REFRESH);


                        break;

                    case MotionEvent.ACTION_UP:
                        currentTop = lv_refresh.getTop();
                        Log.d("TANG", "ACTION_UP");
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                while (currentTop != 0) {
                                    Log.d("TANG", lv_refresh.getTop() + "======================");
                                    mHandler.sendEmptyMessage(MSG_BACK);
                                    try {
                                        sleep(5);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                    currentTop--;
                                }

                            }
                        }.start();

                        break;
                }


                return false;
            }
        });
    }


}
