package com.example.sportlogs.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.example.sportlogs.R;
import com.example.sportlogs.activity.inter.IViewTarget;
import com.example.sportlogs.fragment.TargetFragment;
import com.example.sportlogs.info.TargetInfo;
import com.example.sportlogs.presenter.target.TargetPresenter;
import com.example.sportlogs.presenter.target.TargetPresenterImpl;
import com.example.sportlogs.util.L;
import com.example.sportlogs.util.ShareUtils;
import com.example.sportlogs.util.TimeUtil;
import com.example.sportlogs.view.MyToast;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 4/9/17
 * Issue : TODO
 * Whether solve :
 */

public class TargetFillActivity extends BaseActivity implements IViewTarget {

    private LinearLayout ll_target_end_time;

    private ButtonClick buttonClick;

    private DatePickerPopWin pickerPopWin;

    private EditText et_target_order;
    private EditText et_target_start_time;
    private EditText et_target_end_time;
    private EditText et_target_values;
    private EditText et_target_name;

    private Spinner sp_target_item;
    private Button bt_target_sure;

    private String str_target_name;
    private String str_target_order;
    private String str_target_start_time;
    private String str_target_end_time;
    private String str_target_values;
    private String str_target_journal_name;

    private TargetInfo targetInfo;
    private TargetPresenterImpl targetPresenter;

    private String[] targets;

    private ArrayAdapter<String> adapter;
    private List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_target_fill;
    }

    @Override
    public void initView() {
        ll_target_end_time = (LinearLayout) findViewById(R.id.ll_target_end_time);
        bt_target_sure = (Button) findViewById(R.id.bt_target_sure);

        sp_target_item = (Spinner) findViewById(R.id.sp_target_name_items);

        et_target_name = (EditText) findViewById(R.id.et_target_name);
        et_target_start_time = (EditText) findViewById(R.id.et_target_start_time);
        et_target_end_time = (EditText) findViewById(R.id.et_target_end_time);
        et_target_values = (EditText) findViewById(R.id.et_target_values);
        et_target_order = (EditText) findViewById(R.id.et_target_order);

        targetPresenter = new TargetPresenterImpl(this, this);
    }

    @Override
    public void initData() {
        buttonClick = new ButtonClick();
        ll_target_end_time.setOnClickListener(buttonClick);
        bt_target_sure.setOnClickListener(buttonClick);
        et_target_end_time.setOnClickListener(buttonClick);

        targets = getResources().getStringArray(R.array.journal_list);

        for (int i = 0; i < targets.length; i++) {
            list.add(targets[i]);
        }

        str_target_journal_name = targets[0];
    }

    @Override
    public void initEvent() {

        pickerPopWin = new DatePickerPopWin.Builder(this, new DatePickerPopWin.OnDatePickedListener() {
            @Override
            public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                et_target_end_time.setText(year + "-" + month + "-" + day);
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

        et_target_start_time.setText(TimeUtil.getCurrentYear() + "-" + TimeUtil.getCurrentMonth() + "-" + TimeUtil.getCurrentDay());


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_target_item.setAdapter(adapter);



        sp_target_item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_target_journal_name = targets[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
     * 添加目标失败
     */
    @Override
    public void addTargetFail() {

        MyToast.showMessage("ADD fail");

    }

    /**
     * 时间不对
     */
    @Override
    public void addTargetTimeFail() {
        MyToast.showMessage("ADD Time fail");
    }

    /**
     * 添加目标成功
     */
    @Override
    public void addTargetSuccess() {
        MyToast.showMessage("ADD SUCCESS");
        finish();
        TargetFragment.getInstance().updateListView();

    }

    private class ButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_target_end_time:

                    break;
                case R.id.bt_target_sure:

                    addTarget();

                    break;
                case R.id.et_target_end_time:
                    hideSystemKeyboard();

                    pickerPopWin.showPopWin(TargetFillActivity.this);

                    break;
            }
        }
    }

    private void addTarget() {

        str_target_end_time = et_target_end_time.getText().toString();
        str_target_order = et_target_order.getText().toString();
        str_target_start_time = et_target_start_time.getText().toString();
        str_target_name = et_target_name.getText().toString();
        str_target_values = et_target_values.getText().toString();

        int values = 0;
        if (!str_target_values.equals("")){

            values = Integer.valueOf(str_target_values);
        }
        targetInfo = new TargetInfo(str_target_journal_name, str_target_name, values, str_target_start_time, str_target_end_time, false, false,0);

        targetPresenter.addTarget(targetInfo);
    }

    public void disableShowSoftInput(EditText paramEditText) {
        if (Build.VERSION.SDK_INT <= 10) {
            paramEditText.setInputType(0);
            return;
        }
        try {
            Class[] arrayOfClass = new Class[1];
            arrayOfClass[0] = Boolean.TYPE;
            Method localMethod = EditText.class.getMethod(
                    "setShowSoftInputOnFocus", arrayOfClass);
            localMethod.setAccessible(true);
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = Boolean.valueOf(false);
            localMethod.invoke(paramEditText, arrayOfObject);
            return;
        } catch (Exception localException) {
        }
    }

    public void hideSystemKeyboard() {

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }

    }
}
