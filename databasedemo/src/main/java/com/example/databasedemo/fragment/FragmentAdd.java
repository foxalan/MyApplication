package com.example.databasedemo.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databasedemo.R;
import com.example.databasedemo.database.SQLHelper;

import java.util.Date;

import static com.example.databasedemo.R.id.et_title;

/**
 * Function Name : SetAudioMute
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 */

public class FragmentAdd extends Fragment {

    private EditText et_add_title;
    private EditText et_add_content;
    private Button bt_add;
    private SQLHelper sqlHelper;
    private SQLiteDatabase database;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, null);


        initViews(view);
        initData();
        initEvent();

        return view;
    }

    private void initViews(View view) {

        et_add_title = (EditText) view.findViewById(R.id.et_title);
        et_add_content = (EditText) view.findViewById(R.id.et_content);
        bt_add = (Button) view.findViewById(R.id.bt_save_database);
    }

    private void initData() {

        sqlHelper = new SQLHelper(getActivity());
        database = sqlHelper.getWritableDatabase();

    }

    private void initEvent() {
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNull()) {
                    Toast.makeText(getActivity(), "请输入内容", Toast.LENGTH_SHORT).show();
                    return;
                }

                addDataInDataBase();
            }
        });

    }

    private boolean isNull() {
        if (et_add_content.getText().toString().equals("") || et_add_title.getText().toString().equals("")) {
            return true;
        }

        return false;
    }


    private void addDataInDataBase(){
        String title = et_add_title.getText().toString();
        String content = et_add_content.getText().toString();
        Date date = new Date();
        String time = String.valueOf(date.getTime());

//        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss ");
//        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
//        String str = formatter.format(curDate);

        sqlHelper.insertInfo(title,content,time,database);
    }
}
