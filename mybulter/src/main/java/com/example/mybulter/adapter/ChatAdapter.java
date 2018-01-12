package com.example.mybulter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mybulter.R;
import com.example.mybulter.info.ChatInfo;

import java.util.List;


public class ChatAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<ChatInfo> chatInfoList;

    public ChatAdapter(Context context, List<ChatInfo> chatInfoList) {
        inflater = LayoutInflater.from(context);
        this.chatInfoList = chatInfoList;
    }


    @Override
    public int getCount() {
        return chatInfoList.size();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        return chatInfoList.get(position).getTypeEnum().ordinal();
    }

    @Override
    public Object getItem(int i) {
        return chatInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        int type = getItemViewType(chatInfoList.get(i).getTypeEnum().ordinal());
        ViewHolder viewHolder;

        switch (type) {
            case 0:

                if (view == null) {
                    view = inflater.inflate(R.layout.robot_chat_list_item, null);
                    viewHolder = new ViewHolder();
                    viewHolder.et_content = (EditText) view.findViewById(R.id.et_robot_content);
                    viewHolder.iv_pic = (ImageView) view.findViewById(R.id.iv_robot_icon);
                    viewHolder.tv_data = (TextView) view.findViewById(R.id.tv_robot_date);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }

                viewHolder.iv_pic.setBackgroundResource(R.drawable.assistant);
                viewHolder.et_content.setText(chatInfoList.get(i).getContext());
                viewHolder.tv_data.setText(chatInfoList.get(i).getData());
                break;
            case 1:

                if (view == null) {
                    view = inflater.inflate(R.layout.human_chat_list_item, null);
                    viewHolder = new ViewHolder();
                    viewHolder.et_content = (EditText) view.findViewById(R.id.et_human_content);
                    viewHolder.iv_pic = (ImageView) view.findViewById(R.id.iv_human_icon);
                    viewHolder.tv_data = (TextView) view.findViewById(R.id.tv_human_date);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }
                viewHolder.iv_pic.setBackgroundResource(R.drawable.user);
                viewHolder.et_content.setText(chatInfoList.get(i).getContext());
                viewHolder.tv_data.setText(chatInfoList.get(i).getData());

        }


        return view;
    }


    private class ViewHolder {
        EditText et_content;
        ImageView iv_pic;
        TextView tv_data;
    }

}
