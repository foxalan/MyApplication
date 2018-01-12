package com.example.sportlogs.model.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.sportlogs.database.SQLHelper;
import com.example.sportlogs.model.inter.JournalModel;
import com.example.sportlogs.presenter.inter.JournalPresenterResult;
import com.example.sportlogs.util.L;
import com.example.sportlogs.util.TimeUtil;

/**
 * Function :
 * Author : Alan
 * Modify Date : 30/8/17
 * Issue : TODO
 * Whether solve :
 */

public class JournalModelImpl implements JournalModel {


    private SQLHelper sqlHelper;

    @Override
    public void addInJournal(Context context, String journal_name, int journal_values, JournalPresenterResult journalPresenterResult) {
        sqlHelper = new SQLHelper(context);
        int year = TimeUtil.getCurrentYear();
        int month = TimeUtil.getCurrentMonth();
        int day = TimeUtil.getCurrentDay();
        int hour = TimeUtil.getCurrentHour();
        int minute = TimeUtil.getCurrentMinute();
        int sec = TimeUtil.getCurrentMilisecond();
        SQLiteDatabase database = sqlHelper.getReadableDatabase();
        database.execSQL("insert into tb_journal values(null,?,?,?,?,?,?,?,?)",
                new Object[]{journal_name, journal_values, year, month, day,hour,minute,sec});
        journalPresenterResult.success();
        L.d("insert ok");
    }
}
