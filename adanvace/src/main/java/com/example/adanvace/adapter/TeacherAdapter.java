package com.example.adanvace.adapter;

import android.content.Context;
import android.view.View;



import com.example.adanvace.adapter.viewholder.CustomRecyclerViewHolder;
import com.example.adanvace.info.StudentInfo;


import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 21/8/17
 * Issue : TODO
 * Whether solve :
 */

public class TeacherAdapter extends CustomRecyclerViewAdapter<StudentInfo> {

    private OnItemClickListener onItemClickListener;


    public TeacherAdapter(Context context, List<StudentInfo> mData, int resID, int[] ids) {
        super(context, mData, resID, ids);
    }

    @Override
    public void onBindViewHolder(final CustomRecyclerViewHolder holder, final int position) {

        holder.setText(ids[0], mData.get(position).getId() + "");
        holder.setText(ids[1], mData.get(position).getName());
        holder.setText(ids[2], mData.get(position).getPoint() + "");

        holder.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(holder.convertView, position);
            }
        });

    }

    public interface OnItemClickListener {

        void onClick(View view, int position);
    }

    public OnItemClickListener getOnItemClickListenr() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListenr) {
        this.onItemClickListener = onItemClickListenr;
    }
}
