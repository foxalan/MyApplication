package com.example.sportlogs.model.targethistory;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sportlogs.database.SQLHelper;
import com.example.sportlogs.info.TargetInfo;
import com.example.sportlogs.presenter.targethistory.TargetHistoryPresenterResult;
import com.example.sportlogs.util.L;
import com.example.sportlogs.util.TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 4/9/17
 * Issue : TODO
 * Whether solve :
 */

public class TargetHistoryModelImpl implements TargetHistoryModel {

    private List<TargetInfo> targetInfoList = new ArrayList<>();
    private SQLHelper sqlHelper;
    private SQLiteDatabase database;

    @Override
    public void showTargetHistory(Context context, TargetHistoryPresenterResult result, int state) {
        targetInfoList.clear();

        sqlHelper = new SQLHelper(context);
        database = sqlHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("select * from tb_target", null);
        while (cursor.moveToNext()) {

            String journal_name = cursor.getString(cursor.getColumnIndex("journal_name"));
            String target_name = cursor.getString(cursor.getColumnIndex("target_name"));
            String target_start_time = cursor.getString(cursor.getColumnIndex("target_start_time"));
            String target_end_time = cursor.getString(cursor.getColumnIndex("target_end_time"));
            int values = cursor.getInt(cursor.getColumnIndex("target_value"));
            int finishValues = 0;

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = null;
            Date d2 = null;
            Date inputData = null;
            Date currentData = null;
            try {
                d1 = sf.parse(target_start_time);
                d2 = sf.parse(target_end_time);

            } catch (ParseException e) {
                e.printStackTrace();
            }


            Cursor c = database.rawQuery("select * from tb_journal where journal_name like ?", new String[]{journal_name});
            L.d(c.getCount() + "=================================================");
            while (c.moveToNext()) {

                int year = c.getInt(c.getColumnIndex("journal_year"));
                int month = c.getInt(c.getColumnIndex("journal_month"));
                int day = c.getInt(c.getColumnIndex("journal_day"));

                try {
                    inputData = sf.parse(year + "-" + month + "-" + day);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if ((inputData.before(d2) && inputData.after(d1)) || d1.toString().equals(inputData.toString())) {

                    int value = c.getInt(c.getColumnIndex("journal_value"));

                    finishValues = finishValues + value;
                }
            }

            boolean isFail = false;
            boolean isFinish = false;

            try {
                currentData = sf.parse(TimeUtil.getCurrentYear() + "-" + TimeUtil.getCurrentMonth() + "-" + TimeUtil.getCurrentDay());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (currentData.after(d2)){

                isFinish = true;
                if (finishValues < values) {
                    isFail = true;

                }
            }else {
                if (finishValues>values){
                    isFinish = true;
                    isFail = false;
                }
            }



            TargetInfo info = new TargetInfo(journal_name, target_name, values, target_start_time, target_end_time, isFinish, isFail, finishValues);

            targetInfoList.add(info);
            c.close();
        }

        if (state == 0) {

            result.currentTargetResult(targetInfoList);
        } else {
            result.updateTargetResult(targetInfoList);
        }

        cursor.close();
    }
}
