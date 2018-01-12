package com.example.sportlogs.model.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sportlogs.config.Config;
import com.example.sportlogs.database.SQLHelper;
import com.example.sportlogs.info.JournalInfo;
import com.example.sportlogs.model.inter.JournalHistoryModel;
import com.example.sportlogs.presenter.impl.JournalHistoryPresenterImpl;
import com.example.sportlogs.presenter.inter.JournalHistoryPresenterResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 31/8/17
 * Issue : TODO
 * Whether solve :
 */

public class JournalHistoryModelImpl implements JournalHistoryModel {


    private SQLHelper sqlHelper;
    private SQLiteDatabase database;
    private List<JournalInfo> journalInfoList = new ArrayList<>();

    @Override
    public void getCurrentJournal(Context context, JournalHistoryPresenterImpl result,int state) {
        sqlHelper = new SQLHelper(context);
        database = sqlHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from tb_journal",null);

        journalInfoList.clear();
        while (cursor.moveToNext()){
            String journal_name = cursor.getString(cursor.getColumnIndex("journal_name"));
            int journal_value = cursor.getInt(cursor.getColumnIndex("journal_value"));
            int year = cursor.getInt(cursor.getColumnIndex("journal_year"));
            int month = cursor.getInt(cursor.getColumnIndex("journal_month"));
            int day = cursor.getInt(cursor.getColumnIndex("journal_day"));
            int hour = cursor.getInt(cursor.getColumnIndex("journal_hour"));
            int min = cursor.getInt(cursor.getColumnIndex("journal_min"));
            int sec = cursor.getInt(cursor.getColumnIndex("journal_sec"));
            String journal_time = month+"-"+day+" "+hour+":"+min;
            JournalInfo info = new JournalInfo(journal_name,journal_time,journal_value);
            journalInfoList.add(info);
        }

        Collections.sort(journalInfoList, new Comparator<JournalInfo>() {
            @Override
            public int compare(JournalInfo o1, JournalInfo o2) {
                return o2.getJournal_time().compareTo(o1.getJournal_time());
            }
        });

        switch (state){
            case Config.STATE_SHOW_HISTORY_JOURNAL:
                result.currentJournalResult(journalInfoList);
                break;
            case Config.STATE_UPDATE_JOURNAL:
                result.updateJournalResult(journalInfoList);
                break;
        }


        cursor.close();
    }
}
