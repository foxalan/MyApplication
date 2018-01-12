package com.example.sportlogs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sportlogs.R;
import com.example.sportlogs.activity.inter.IViewJournal;
import com.example.sportlogs.activity.inter.IViewJournalHistory;
import com.example.sportlogs.adapter.JournalHistoryAdapter;
import com.example.sportlogs.config.Config;
import com.example.sportlogs.fragment.TargetFragment;
import com.example.sportlogs.info.JournalInfo;
import com.example.sportlogs.presenter.impl.JournalHistoryPresenterImpl;
import com.example.sportlogs.presenter.impl.JournalPresenterImpl;
import com.example.sportlogs.util.L;
import com.example.sportlogs.util.TimeUtil;
import com.example.sportlogs.view.MyToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Function : 添加日志到数据库
 * Author : Alan
 * Modify Date : 30/8/17
 * Issue : TODO
 * Whether solve :
 */

public class JournalFillActivity extends BaseActivity implements IViewJournal, IViewJournalHistory {

    private EditText et_journal_name;
    private EditText et_journal_values;
    private EditText et_journal_time;

    private Button bt_add_journal;

    private String str_journal_name;

    private JournalPresenterImpl presenter;

    private ListView lv_jouranl_history;
    private JournalHistoryAdapter adapter;

    private JournalHistoryPresenterImpl journalHistoryPresenter;
    private List<JournalInfo> journalInfoList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_journal_fill;
    }

    @Override
    public void initView() {

        et_journal_name = (EditText) findViewById(R.id.et_journal_name);
        et_journal_values = (EditText) findViewById(R.id.et_journal_values);
        et_journal_time = (EditText) findViewById(R.id.et_journal_time);

        bt_add_journal = (Button) findViewById(R.id.bt_add_journal);
        lv_jouranl_history = (ListView) findViewById(R.id.lv_history_journal);
    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        str_journal_name = bundle.getString(Config.JOURNAL_NAME);

        journalHistoryPresenter = new JournalHistoryPresenterImpl(this);
        journalHistoryPresenter.showHistoryJournal(this);

    }

    @Override
    public void initEvent() {
        et_journal_name.setText(str_journal_name);
        et_journal_values.setFocusable(true);

        et_journal_time.setText(TimeUtil.getCurrentTimeA());

        presenter = new JournalPresenterImpl(this, this);

        bt_add_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str_journal_name = et_journal_name.getText().toString();
                String str_journal_values = et_journal_values.getText().toString();
                presenter.addJournalInDatabase(str_journal_name, str_journal_values);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 添加日志成功
     * 并更新目标进度
     */

    @Override
    public void addJournalSuccess() {
        MyToast.showMessage("Success");
        journalHistoryPresenter.updateHistoryJournal(this);
        TargetFragment.getInstance().updateListView();
    }


    /**
     * 添加日志失败
     */
    @Override
    public void addJournalFail() {
        MyToast.showMessage("NULL");
    }

    /**
     * 更新日志
     *
     * @param
     */
    @Override
    public void updateJournal(List<JournalInfo> list) {

        getCurrentList(list);
        adapter.notifyDataSetChanged();
    }

    /**
     * 　得到现在的List
     *
     * @param list
     */
    private void getCurrentList(List<JournalInfo> list) {

        journalInfoList.clear();

        for (int i = 0; i < list.size(); i++) {
            JournalInfo info = list.get(i);
            L.d(info.toString());
            journalInfoList.add(info);
        }
    }

    /**
     * 显示现在已有的日志
     *
     * @param journalInfoList
     */
    @Override
    public void showJournal(List<JournalInfo> journalInfoList) {
        this.journalInfoList = new ArrayList<>(journalInfoList);
        adapter = new JournalHistoryAdapter(this, journalInfoList, R.layout.listview_journal);
        if (adapter != null) {

            lv_jouranl_history.setAdapter(adapter);
        }

    }
}
