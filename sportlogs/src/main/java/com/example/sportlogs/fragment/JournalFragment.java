package com.example.sportlogs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sportlogs.R;
import com.example.sportlogs.activity.JournalFillActivity;
import com.example.sportlogs.config.Config;
import com.example.sportlogs.viewgroup.FlowViewGroup;

/**
 * Function : 添加日志界面
 * Author : Alan
 * Modify Date : 30/8/17
 * Issue : TODO
 * Whether solve :
 */

public class JournalFragment extends BaseFragment {

    private static JournalFragment journalFragment;

    private FlowViewGroup gp_flow;
    private ListView lv_history_journal;

    public static JournalFragment getInstance(){
        if (journalFragment == null){
            journalFragment = new JournalFragment();
        }

        return journalFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal,container,false);

        initView(view);
        return view;
    }

    private void initView(View view) {

        gp_flow = (FlowViewGroup) view.findViewById(R.id.gp_flow);

        gp_flow.setOnItemClickLister(new FlowViewGroup.OnItemClickLister() {
            @Override
            public void click(View view, int position) {

                TextView tv = (TextView) view;
                String journal_name = tv.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString(Config.JOURNAL_NAME,journal_name);
                Intent intent = new Intent(getActivity(), JournalFillActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

      //  lv_history_journal = (ListView) view.findViewById(R.id.lv_journal_history);
    }

    public static void updateJournal(){

    }
}
