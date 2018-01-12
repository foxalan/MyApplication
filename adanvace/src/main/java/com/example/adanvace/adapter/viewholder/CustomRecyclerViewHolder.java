package com.example.adanvace.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Function :
 * Author : Alan
 * Modify Date : 21/8/17
 * Issue : TODO
 * Whether solve :
 */

public class CustomRecyclerViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> mViews;


    public View convertView;
    public CustomRecyclerViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
        this.convertView = itemView;
    }

    public <T extends View> T getView(int resId) {

        View view = mViews.get(resId);
        if (view == null){
            view = convertView.findViewById(resId);
            mViews.put(resId,view);
        }

        return (T) view;
    }

    public void setText(int resID , String str){
        TextView textView = (TextView) convertView.findViewById(resID);
        textView.setText(str);
    }



}
