package com.example.databasedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.databasedemo.R;
import com.example.databasedemo.info.NoteInfo;

import java.util.List;

/**
 * Function Name : SetAudioMute
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 */

public class ListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<NoteInfo> noteInfoList;

    public ListViewAdapter(Context context,List<NoteInfo> noteInfoList){
        inflater = LayoutInflater.from(context);
        this.noteInfoList = noteInfoList;
    }


    @Override
    public int getCount() {
        return noteInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return noteInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if (view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item,null);

            viewHolder.tv_title = (TextView) view.findViewById(R.id.list_tv_title);
            viewHolder.tv_content = (TextView) view.findViewById(R.id.list_tv_content);
            viewHolder.tv_time = (TextView) view.findViewById(R.id.list_tv_time);

            view.setTag(viewHolder);


        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tv_title.setText(noteInfoList.get(i).getTitle());
        viewHolder.tv_content.setText(noteInfoList.get(i).getContent());
        viewHolder.tv_time.setText(noteInfoList.get(i).getTime());

        return view;
    }

    class ViewHolder{
        TextView tv_title;
        TextView tv_content;
        TextView tv_time;
    }
}
