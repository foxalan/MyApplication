package com.example.sportlogs.model.targetmodify;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.sportlogs.database.SQLHelper;
import com.example.sportlogs.presenter.targetmodify.TargetModifyResultPresenter;
import com.example.sportlogs.util.L;

/**
 * Function :
 * Author : Alan
 * Modify Date : 5/9/17
 * Issue : TODO
 * Whether solve :
 */

public class TargetModifyModeImpl implements TargetModifyModel {

    private SQLHelper sqlHelper;
    private SQLiteDatabase database;

    public TargetModifyModeImpl(Context context) {
        sqlHelper = new SQLHelper(context);
        database = sqlHelper.getWritableDatabase();
    }



    @Override
    public void modify(String new_name, String new_end_time, String new_value, String name, String end_time, String value, TargetModifyResultPresenter presenter) {
        ContentValues values_name = new ContentValues();
        values_name.put("target_name",new_name);
        int number = Integer.valueOf(new_value);
        values_name.put("target_value",number);
        values_name.put("target_end_time",new_end_time);

        database.update("tb_target",values_name,"target_name = ?",new String[]{name});
        presenter.modifySuccess();
    }

    @Override
    public void delete(String name_item, TargetModifyResultPresenter presenter) {
        L.d(name_item);
        database.execSQL("delete from tb_target where target_name = ?",new String[]{name_item});
        presenter.modifySuccess();

    }
}
