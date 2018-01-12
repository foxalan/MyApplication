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
import android.widget.ListView;

import com.example.databasedemo.R;
import com.example.databasedemo.adapter.ListViewAdapter;
import com.example.databasedemo.database.SQLHelper;
import com.example.databasedemo.info.NoteInfo;

import java.util.ArrayList;
import java.util.List;


public class FragmentList extends Fragment {
    private SQLHelper sqlHelper;
    private SQLiteDatabase database;

    private ListView listView;
    private List<NoteInfo> noteInfoList;
    private ListViewAdapter adapter;
    private static final String TAG = "tang";

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list,null);

        initViews(view);
        initData();
        initEvent();

        Log.d(TAG,"onCreateView");


        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    private void initViews(View view) {

        listView = (ListView) view.findViewById(R.id.lv_fg_list);
    }



    private void initData() {

        sqlHelper = new SQLHelper(getActivity());
        database = sqlHelper.getWritableDatabase();
        noteInfoList = new ArrayList<>();
        Cursor cursor = sqlHelper.getAllInfo(database);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){

                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String time = cursor.getString(cursor.getColumnIndex("time"));

         //       Log.d(TAG,title+":"+content+":"+time);

                NoteInfo info = new NoteInfo(title,content,time);
                noteInfoList.add(info);

                cursor.moveToNext();
            }
        }
        cursor.close();

    }

    private void initEvent(){
        if (noteInfoList != null){
            adapter = new ListViewAdapter(getContext(),noteInfoList);
            listView.setAdapter(adapter);


        }


    }
}
