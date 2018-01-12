package com.example.signdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Function Name : SetAudioMute
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 */

public class GridViewAdapter extends BaseAdapter {

    private List<String> stringList;
    private LayoutInflater layoutInflater;

    public GridViewAdapter(Context context,List<String> stringList){
        layoutInflater = LayoutInflater.from(context);
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int i) {
        return stringList.get(i);
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
            view = layoutInflater.inflate(R.layout.gridview_item,null);
            viewHolder.button = (TextView) view.findViewById(R.id.bt_grid_item);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.button.setText(stringList.get(i));



        return view;
    }

    class ViewHolder{
        TextView button;
    }
}
