package com.example.adanvace.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adanvace.R;
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

public abstract class CustomRecyclerViewAdapter<T> extends RecyclerView.Adapter<CustomRecyclerViewHolder> {

    public LayoutInflater inflater;
    public List<T> mData;
    private CustomRecyclerViewHolder viewHolder;
    private int resID;
    public int[] ids;

    public CustomRecyclerViewAdapter(Context context, List<T> mData, int resID, int[] ids) {
        inflater = LayoutInflater.from(context);
        this.mData = mData;
        this.resID = resID;
        this.ids = ids;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public CustomRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(resID, parent, false);
        viewHolder = new CustomRecyclerViewHolder(view);

        for (int i = 0; i < ids.length; i++) {
            viewHolder.getView(ids[i]);
        }

        return viewHolder;
    }


}
