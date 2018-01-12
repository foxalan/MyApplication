package com.example.sportlogs.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.health.PackageHealthStats;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.example.sportlogs.R;
import com.example.sportlogs.activity.inter.IViewTargetModify;
import com.example.sportlogs.config.Config;
import com.example.sportlogs.fragment.TargetFragment;
import com.example.sportlogs.presenter.targetmodify.TargetModifyPresenter;
import com.example.sportlogs.presenter.targetmodify.TargetModifyPresenterImpl;
import com.example.sportlogs.util.L;
import com.example.sportlogs.view.MyToast;

/**
 * Function :
 * Author : Alan
 * Modify Date : 5/9/17
 * Issue : TODO
 * Whether solve :
 */

public class ModifyTargetActivity extends BaseActivity implements IViewTargetModify {

    private EditText et_target_modify_name_item;
    private EditText et_target_modify_end_time;
    private EditText et_target_modify_value;

    private EditText et_target_modify_name;
    private EditText et_target_modify_start_time;

    private String str_name;
    private String str_name_item;
    private String str_start_time;
    private String str_end_time;
    private String str_value;

    private Button bt_modify;
    private Button bt_delete;

    private TargetModifyPresenterImpl modifyPresenter;

    private String str_new_name_item;
    private String str_new_end_time;
    private String str_new_values;

    private DatePickerPopWin pickerPopWin;
    private LinearLayout ll_target_modify_end_time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_target_modify;
    }

    @Override
    public void initView() {

        et_target_modify_name = (EditText) findViewById(R.id.et_target_modify_name);
        et_target_modify_name_item = (EditText) findViewById(R.id.et_target_modify_name_item);
        et_target_modify_end_time = (EditText) findViewById(R.id.et_target_modify_end_time);
        et_target_modify_start_time = (EditText) findViewById(R.id.et_target_modify_start_time);
        et_target_modify_value = (EditText) findViewById(R.id.et_target_modify_values);

        bt_delete = (Button) findViewById(R.id.bt_target_modify_delete);
        bt_modify = (Button) findViewById(R.id.bt_target_modify_sure);

        ll_target_modify_end_time = (LinearLayout) findViewById(R.id.ll_target_modify_end_time);

        modifyPresenter = new TargetModifyPresenterImpl(this, this);

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        str_name = intent.getStringExtra(Config.TARGET_NAME);
        str_name_item = intent.getStringExtra(Config.TARGET_NAME_ITEM);
        str_start_time = intent.getStringExtra(Config.TARGET_START_TIME);
        str_end_time = intent.getStringExtra(Config.TARGET_END_TIME);
        str_value = intent.getStringExtra(Config.TARGET_VALUES);

        et_target_modify_name.setText(str_name);
        et_target_modify_name_item.setText(str_name_item);
        et_target_modify_start_time.setText(str_start_time);
        et_target_modify_end_time.setText(str_end_time);
        et_target_modify_value.setText(str_value);
    }

    @Override
    public void initEvent() {


        pickerPopWin = new DatePickerPopWin.Builder(this, new DatePickerPopWin.OnDatePickedListener() {
            @Override
            public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                et_target_modify_end_time.setText(year + "-" + month + "-" + day);
                String[] date = dateDesc.split("-");
                L.d(date[0] + "===" + date[1] + "===" + date[2]);
            }
        }).textConfirm("CONFIRM") //text of confirm button
                .textCancel("CANCEL") //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .colorCancel(Color.parseColor("#999999")) //color of cancel button
                .colorConfirm(Color.parseColor("#009900"))//color of confirm button
                .minYear(1990) //min year in loop
                .maxYear(2550) // max year in loop
                .showDayMonthYear(true) // shows like dd mm yyyy (default is false)
                .dateChose("2017-9-4") // date chose when init popwindow
                .build();

        ll_target_modify_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerPopWin.showPopWin(ModifyTargetActivity.this);
            }
        });



        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyPresenter.deleteTarget(str_name_item);
            }
        });

        bt_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_new_name_item = et_target_modify_name_item.getText().toString();
                str_new_end_time = et_target_modify_end_time.getText().toString();
                str_new_values = et_target_modify_value.getText().toString();
                modifyPresenter.modifyTarget(str_new_name_item, str_new_end_time, str_new_values,str_name_item,str_end_time,str_value);
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

    @Override
    public void targetModifyFail() {

    }

    @Override
    public void targetModifySuccess() {
        MyToast.showMessage("modify success");
        TargetFragment.getInstance().updateListView();

    }

    @Override
    public void targetDeleteSuccess() {

    }

    @Override
    public void targetDeleteFail() {

    }
}
