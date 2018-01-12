package com.example.sportlogs.model.target;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.sportlogs.database.SQLHelper;
import com.example.sportlogs.info.TargetInfo;
import com.example.sportlogs.presenter.target.TargetResultPresenter;

/**
 * Function : 查询数据库
 * Author : Alan
 * Modify Date : 4/9/17
 * Issue : TODO
 * Whether solve :
 */

public class TargetModelImpl implements TargetModel {

    private SQLHelper helper;
    private SQLiteDatabase database;

    @Override
    public void addTargetModel(Context context,TargetInfo info, TargetResultPresenter presenter) {

        helper = new SQLHelper(context);
        database = helper.getWritableDatabase();

        database.execSQL("insert into tb_target values(null,?,?,?,?,?)",
                new Object[]{info.getJournalName(),info.getTargetName(),info.getTargetValue(),info.getStartTime(),info.getEndTime()});
        presenter.targetResultSuccess();
    }
}
