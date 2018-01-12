package com.example.sportlogs.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.sportlogs.R;
import com.example.sportlogs.info.JournalInfo;
import com.example.sportlogs.view.NumberView;

import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 31/8/17
 * Issue : TODO
 * Whether solve :
 */

public class JournalHistoryAdapter extends CustomAdapter<JournalInfo> {

    public JournalHistoryAdapter(Context mContext, List<JournalInfo> mData, int resID) {
        super(mContext, mData, resID);
    }

    @Override
    public void convert(CustomViewHolder viewHolder, int position) {
        TextView tv_list_journal_name = viewHolder.getView(R.id.tv_list_journal_name);
        TextView tv_list_journal_time = viewHolder.getView(R.id.tv_list_journal_time);
        NumberView numberView = viewHolder.getView(R.id.view_number_list_values);

        tv_list_journal_name.setText(mData.get(position).getJournal_name());
        tv_list_journal_time.setText(mData.get(position).getJournal_time());
        numberView.setJournalValue(mData.get(position).getJournal_values()+"");
    }
}
