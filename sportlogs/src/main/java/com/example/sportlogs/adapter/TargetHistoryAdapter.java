package com.example.sportlogs.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sportlogs.R;
import com.example.sportlogs.info.TargetInfo;
import com.example.sportlogs.view.NumberView;

import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 4/9/17
 * Issue : TODO
 * Whether solve :
 */

public class TargetHistoryAdapter extends CustomAdapter<TargetInfo> {

    public TargetHistoryAdapter(Context mContext, List<TargetInfo> mData, int resID) {
        super(mContext, mData, resID);
    }

    @Override
    public void convert(CustomViewHolder viewHolder, int position) {

        viewHolder.setText(mData.get(position).getJournalName(), R.id.tv_list_target_name);
        viewHolder.setText(mData.get(position).getTargetName(), R.id.tv_list_target_name_item);
        viewHolder.setText(mData.get(position).getEndTime(), R.id.tv_list_target_end_time);
        viewHolder.setText(mData.get(position).getStartTime(), R.id.tv_list_target_start_time);
        ProgressBar progressBar = viewHolder.getView(R.id.pb_list_target);
        progressBar.setProgress(mData.get(position).getFinishValue() * 100 / mData.get(position).getTargetValue());
        NumberView numberView = viewHolder.getView(R.id.view_number_list_values_target);

        int progress = mData.get(position).getFinishValue() * 100 / mData.get(position).getTargetValue();

        TextView tv_target_progress = viewHolder.getView(R.id.tv_list_target_progress);
        if (mData.get(position).isFinish()) {
            Log.d("ALAN", mData.get(position).isFinish() + "=====");
            progressBar.setVisibility(View.GONE);
            tv_target_progress.setVisibility(View.GONE);
            if (mData.get(position).isFail()) {
                numberView.setJournalValue("失败");
            } else {
                numberView.setJournalValue("成功");
            }
        } else {
            progressBar.setVisibility(View.VISIBLE);
            tv_target_progress.setVisibility(View.VISIBLE);
            Log.d("ALAN", mData.get(position).isFinish() + "=====");
            numberView.setJournalValue(mData.get(position).getTargetValue() + "");
            tv_target_progress.setText(progress + "%");
        }

    }
}
