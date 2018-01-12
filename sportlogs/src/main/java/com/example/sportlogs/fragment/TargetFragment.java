package com.example.sportlogs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sportlogs.R;
import com.example.sportlogs.activity.ModifyTargetActivity;
import com.example.sportlogs.activity.TargetFillActivity;
import com.example.sportlogs.activity.inter.IViewTargetHistory;
import com.example.sportlogs.adapter.TargetHistoryAdapter;
import com.example.sportlogs.config.Config;
import com.example.sportlogs.info.TargetInfo;
import com.example.sportlogs.presenter.targethistory.TargetHistoryPresenter;
import com.example.sportlogs.presenter.targethistory.TargetHistoryPresenterImpl;
import com.example.sportlogs.util.L;
import com.example.sportlogs.view.NumberView;
import com.example.sportlogs.viewgroup.FlowViewGroup;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * Function :  set target
 * Author : Alan
 * Modify Date : 30/8/17
 * Issue : TODO
 * Whether solve :
 */

public class TargetFragment extends BaseFragment implements IViewTargetHistory {

    private static TargetFragment targetFragment;

    public static TargetFragment getInstance() {
        if (targetFragment == null) {
            targetFragment = new TargetFragment();
        }
        return targetFragment;
    }

    private TextView tv_target;
    private Button bt_set_target;
    private ListView lv_show_target;
    private FlowViewGroup vp_target;

    private FloatingActionButton fb_add_target;
    private ButtonClick buttonClick;

    private List<TargetInfo> list = new ArrayList<>();
    private List<TargetInfo> currentList = new ArrayList<>();
    private TargetHistoryAdapter adapter;

    private TargetHistoryPresenter presenter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x123:
                    adapter.notifyDataSetChanged();
                    vp_target.setVisibility(View.GONE);
                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_target, container, false);

        initViews(view);
        initData();
        initEvent();
        return view;
    }

    private void initViews(View view) {

        tv_target = (TextView) view.findViewById(R.id.tv_target);
        bt_set_target = (Button) view.findViewById(R.id.bt_set_target);
        lv_show_target = (ListView) view.findViewById(R.id.lv_target);
        vp_target = (FlowViewGroup) view.findViewById(R.id.vg_target_list);

        fb_add_target = (FloatingActionButton) view.findViewById(R.id.fb_add_target);
    }

    private void initData() {

        presenter = new TargetHistoryPresenterImpl(this, getActivity());
        presenter.showHistoryTarget(getContext());


        buttonClick = new ButtonClick();
        bt_set_target.setOnClickListener(buttonClick);
        fb_add_target.setOnClickListener(buttonClick);
    }

    private void initEvent() {


        vp_target.setOnItemClickLister(new FlowViewGroup.OnItemClickLister() {
            @Override
            public void click(View view, int position) {
                L.d(position + "");
                resetCurrentList(position);
            }
        });

        initTarget();

    }

    /**
     * 初始化目标
     */
    private void initTarget() {


    }

    private void resetCurrentList(int position) {

        currentList.clear();
        for (int i = 0; i < list.size(); i++) {
            Log.d("ALAN", list.get(i).toString());
        }

        switch (position) {
            case 0:

                for (int i = 0; i < list.size(); i++) {
                    TargetInfo info = list.get(i);
                    currentList.add(info);

                }
                tv_target.setText("所有目标");

                break;
            case 1:
                for (int i = 0; i < list.size(); i++) {
                    if (!list.get(i).isFinish()) {
                        TargetInfo info = list.get(i);
                        currentList.add(info);
                    }
                }
                tv_target.setText("正在进行中的目标");
                break;
            case 2:

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isFinish()) {
                        if (!list.get(i).isFail()) {

                            TargetInfo info = list.get(i);
                            currentList.add(info);
                        }
                    }
                }
                tv_target.setText("已完成目标");
                break;
            case 3:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isFail()) {
                        TargetInfo info = list.get(i);
                        currentList.add(info);
                    }
                }
                tv_target.setText("失败目标");
                break;

        }

        mHandler.sendEmptyMessage(0x123);
    }

    public void updateListView() {
        presenter.updateHistoryTarget(getContext());
    }

    @Override
    public void updateTarget(List<TargetInfo> targetInfoList) {
        list.clear();
        list = new ArrayList<>(targetInfoList);
        currentList.clear();
        currentList = new ArrayList<>(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showTarget(List<TargetInfo> targetInfoList) {
        list = new ArrayList<>(targetInfoList);
        currentList = new ArrayList<>(list);

        adapter = new TargetHistoryAdapter(getContext(), currentList, R.layout.listview_target);
        lv_show_target.setAdapter(adapter);
        lv_show_target.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tv_target_name = (TextView) view.findViewById(R.id.tv_list_target_name);
                TextView tv_target_name_item = (TextView) view.findViewById(R.id.tv_list_target_name_item);
                TextView tv_target_start_time = (TextView) view.findViewById(R.id.tv_list_target_start_time);
                TextView tv_target_end_time = (TextView) view.findViewById(R.id.tv_list_target_end_time);
                NumberView numberView = (NumberView) view.findViewById(R.id.view_number_list_values_target);

                String str_name = tv_target_name.getText().toString();
                String str_name_item = tv_target_name_item.getText().toString();
                String str_start_time = tv_target_start_time.getText().toString();
                String str_end_time = tv_target_end_time.getText().toString();
                String str_value = numberView.getStr_journal_values();

                Intent intent = new Intent(getActivity(), ModifyTargetActivity.class);
                intent.putExtra(Config.TARGET_NAME,str_name);
                intent.putExtra(Config.TARGET_NAME_ITEM,str_name_item);
                intent.putExtra(Config.TARGET_START_TIME,str_start_time);
                intent.putExtra(Config.TARGET_END_TIME,str_end_time);
                intent.putExtra(Config.TARGET_VALUES,str_value);
                startActivity(intent);


            }
        });

    }

    private class ButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_set_target:
                    vp_target.setVisibility(View.VISIBLE);
                    break;
                case R.id.fb_add_target:
                    startActivity(new Intent(getActivity(), TargetFillActivity.class));
                    break;
            }
        }
    }
}
