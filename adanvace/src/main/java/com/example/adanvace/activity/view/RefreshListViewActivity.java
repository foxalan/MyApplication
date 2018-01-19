package com.example.adanvace.activity.view;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.adanvace.R;
import com.example.adanvace.adapter.ContactAdapter;
import com.example.adanvace.info.ContactInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Function : listView的下拉刷新
 *
 * @Author : Alan
 * Modify Date : 14/8/17
 * Issue : 1.LayoutParams 的使用
 * accomplish: NO
 * Whether solve :
 */

public class RefreshListViewActivity extends BaseActivity implements AbsListView.OnScrollListener {

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
    private final static int MSG_CAN_REFRESH = 0x155;
    private final static int MSG_IS_REFRESH = 0x124;
    private final static int MSG_BACK = 0x125;
    private final static int MSG_HAS_REFRESH = 0X126;
    private LinearLayout.LayoutParams layoutParams;
    private int currentTop;

    private TextView tv_refresh;
    private ProgressBar pb_refresh;
    private TextView tv_refresh_time;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_BEFORE_REFRESH:
                    Log.e("refresh","before refresh");
                    pb_refresh.setVisibility(View.INVISIBLE);
                    tv_refresh.setText("下拉刷新");
                    tv_refresh_time.setVisibility(View.VISIBLE);
                    rl_header.setPadding(0, scrollLength-rl_head_length, 0, 0);
                    break;
                case MSG_CAN_REFRESH:
                    Log.e("refresh","can refresh");
                    pb_refresh.setVisibility(View.INVISIBLE);
                    tv_refresh.setText("放开可以刷新");
                    tv_refresh_time.setVisibility(View.VISIBLE);
                    break;
                case MSG_IS_REFRESH:
                    Log.e("refresh","is refresh");
                    pb_refresh.setVisibility(View.VISIBLE);
                    tv_refresh.setText("正在刷新");
                    tv_refresh_time.setVisibility(View.GONE);
                    lv_refresh.layout(lv_refresh.getLeft(), lv_refresh.getTop() + scrollLength, lv_refresh.getRight(), lv_refresh.getBottom() + scrollLength - rl_head_length);
                    break;
                case  MSG_HAS_REFRESH:
                    pb_refresh.setVisibility(View.GONE);
                    tv_refresh.setText("刷新完成");
                    tv_refresh_time.setVisibility(View.VISIBLE);
                    tv_refresh_time.setText(System.currentTimeMillis()+"");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            while (scrollLength != 0) {
                                mHandler.sendEmptyMessage(MSG_BACK);
                                try {
                                    Thread.sleep(5);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                scrollLength--;
                            }
                        }
                    },1000);
                    break;
                case MSG_BACK:
                    Log.e("refresh","back");
                    rl_header.setPadding(0, scrollLength - rl_head_length, 0, 0);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public int getContentView() {
        return R.layout.activity_refresh_listview;
    }

    @Override
    public void initView() {
        layoutInflater = LayoutInflater.from(this);
        rl_header = (RelativeLayout) layoutInflater.inflate(R.layout.layout_listview_header, null);
        tv_refresh = (TextView) rl_header.findViewById(R.id.tv_refresh);
        pb_refresh = (ProgressBar) rl_header.findViewById(R.id.pb_refresh);
        tv_refresh_time = (TextView) rl_header.findViewById(R.id.tv_refresh_time);
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
        rl_header.measure(0,0);
        rl_head_length = rl_header.getMeasuredHeight();
        Log.e("refresh",rl_head_length+"===rl_header_length");
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }


    private int state;
    private final static int RELEASE_To_REFRESH = 0;
    private final static int PULL_To_REFRESH = 1;
    private final static int REFRESHING = 2;
    private final static int DONE = 3;
    private final static int LOADING = 4;

    @Override
    public void initEvent() {

        lv_refresh.setAdapter(adapter);
        lv_refresh.addHeaderView(rl_header);
        lv_refresh.setSelection(1);

        lv_refresh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (isRefreshable){}
                        int scroll = lv_refresh.getScrollY();
                        int moveY = (int) event.getY();
                        scrollLength = moveY - startY + scroll;
                        Log.e("refresh", "tang" + "===" + scrollLength+"==="+rl_head_length);
                        if (scrollLength < (rl_head_length + 10)) {
                            state = RELEASE_To_REFRESH;
                            mHandler.sendEmptyMessage(MSG_BEFORE_REFRESH);

                        } else {
                            state = PULL_To_REFRESH;
                            mHandler.sendEmptyMessage(MSG_CAN_REFRESH);
                        }

                        break;

                    case MotionEvent.ACTION_UP:
                        currentTop = lv_refresh.getTop();
                        switch (state) {
                            case RELEASE_To_REFRESH:
                                state = DONE;
                                new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();
                                        while (scrollLength != 0) {
                                            mHandler.sendEmptyMessage(MSG_BACK);
                                            try {
                                                sleep(5);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            scrollLength--;
                                        }

                                    }
                                }.start();
                                break;
                            case PULL_To_REFRESH:
                                state = REFRESHING;
                                mHandler.sendEmptyMessage(MSG_IS_REFRESH);
                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        currentList.add(new ContactInfo(false,false,"huiye","die"));
                                        state = DONE;
                                        adapter.notifyDataSetChanged();
                                        mHandler.sendEmptyMessage(MSG_HAS_REFRESH);


                                    }
                                },2000);
                                break;
                        }
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }


    boolean isRefreshable;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem == 0) {
            isRefreshable = true;
        } else {
            isRefreshable = false;
        }
    }
}
