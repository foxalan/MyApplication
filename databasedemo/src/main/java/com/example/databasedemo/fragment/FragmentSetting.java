package com.example.databasedemo.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databasedemo.R;
import com.example.databasedemo.adapter.ListViewAdapter;
import com.example.databasedemo.database.SQLHelper;
import com.example.databasedemo.info.NoteInfo;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

/**
 * Function Name : SetAudioMute
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 */

public class FragmentSetting extends Fragment {
    private EditText et_remove_title;
    private Button bt_remove;


    private SQLHelper helper;
    private SQLiteDatabase database;
    private List<NoteInfo> noteInfoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,null);

        initView(view);
        initData();
        initEvent();

        return view;
    }

    private void initView(View view) {

        et_remove_title = (EditText) view.findViewById(R.id.et_setting_title);
        bt_remove = (Button) view.findViewById(R.id.bt_remove);
    }

    private void initData(){
        helper = new SQLHelper(getActivity());
        database = helper.getWritableDatabase();
        noteInfoList = new ArrayList<>();
        Cursor cursor = helper.getAllInfo(database);
        if (cursor.moveToFirst()){

            while (!cursor.isAfterLast()){

                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String time = cursor.getString(cursor.getColumnIndex("time"));

                NoteInfo info = new NoteInfo(title,content,time);
                noteInfoList.add(info);


                cursor.moveToNext();
            }
        }
        cursor.close();
    }

    private void initEvent(){
        bt_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("tang","onClick");
                if (et_remove_title.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"请输入内容",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isContain(et_remove_title.getText().toString())){
                    helper.deleteInfo(et_remove_title.getText().toString(),database);

                }else {
                    Toast.makeText(getActivity(),"请输入正确内容",Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });
    }

    private boolean isContain(String string) {
        if (noteInfoList.size() == 0 ){
            return  false;
        }

        for (int i = 0 ; i < noteInfoList.size();i++){

            Log.d("tang",noteInfoList.get(i).toString());
            if (noteInfoList.get(i).getTitle().equals(string)){
                return true;
            }
        }

        return false;
    }


}
