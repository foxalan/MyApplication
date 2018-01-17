package com.example.mybulter.adapter;

import android.content.Context;

import com.example.mybulter.R;
import com.example.mybulter.info.UserBean;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/1/16
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class UserAdapter extends CustomAdapter<UserBean> {


    public UserAdapter(Context mContext, List<UserBean> mData, int resID) {
        super(mContext, mData, resID);
    }

    @Override
    public void convert(CustomViewHolder viewHolder, int position) {
        viewHolder.setText(mData.get(position).getItem(), R.id.tv_user_item);
        viewHolder.setText(mData.get(position).getContent(),R.id.tv_user_content);
    }
}
