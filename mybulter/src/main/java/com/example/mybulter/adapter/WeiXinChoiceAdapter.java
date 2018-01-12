package com.example.mybulter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mybulter.R;
import com.example.mybulter.info.ChoiceInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Function Name :
 * Author : Alan
 * Modify Date : 8/8/17
 * Input Parameter :
 */

public class WeiXinChoiceAdapter extends BaseAdapter {

    private List<ChoiceInfo> choiceInfoList;
    private LayoutInflater inflater;
    private Context context;

    public WeiXinChoiceAdapter(Context context, List<ChoiceInfo> choiceInfoList) {
        inflater = LayoutInflater.from(context);
        this.choiceInfoList = choiceInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return choiceInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return choiceInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){

            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_weixin_choice,null);

            viewHolder.iv_list_weixin_icon = (ImageView) convertView.findViewById(R.id.iv_list_weixin_icon);
            viewHolder.tv_list_weixin_title = (TextView) convertView.findViewById(R.id.tv_list_weixin_title);
            viewHolder.tv_list_weixin_pub = (TextView) convertView.findViewById(R.id.tv_list_weixin_where);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_list_weixin_title.setText(choiceInfoList.get(position).getTitle());
        viewHolder.tv_list_weixin_pub.setText(choiceInfoList.get(position).getPub());
        if (choiceInfoList.get(position).getImageUrl().equals("")){

        }else {

            Picasso.with(context).load(choiceInfoList.get(position).getImageUrl()).resize(110,100).into(viewHolder.iv_list_weixin_icon);
        }

        return convertView;
    }


    class ViewHolder{
        ImageView iv_list_weixin_icon;
        TextView tv_list_weixin_title;
        TextView tv_list_weixin_pub;

    }

}
