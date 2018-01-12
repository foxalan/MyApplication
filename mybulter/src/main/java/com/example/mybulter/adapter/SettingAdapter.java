package com.example.mybulter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mybulter.R;
import com.example.mybulter.info.SettingInfo;

import java.util.List;


public class SettingAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<SettingInfo> settingInfoList;

    public SettingAdapter(Context context,List<SettingInfo> settingInfoList){
        inflater = LayoutInflater.from(context);
        this.settingInfoList = settingInfoList;
    }

    @Override
    public int getCount() {
        return settingInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return settingInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.list_my_setting_item,null);
            viewHolder.iv_icon = (ImageView) view.findViewById(R.id.iv_setting_list_item);
            viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_setting_list_item);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tv_name.setText(settingInfoList.get(i).getName());
        viewHolder.iv_icon.setImageBitmap(settingInfoList.get(i).getIcon());

        return view;
    }

    class ViewHolder{
        ImageView iv_icon;
        TextView  tv_name;
    }



}
