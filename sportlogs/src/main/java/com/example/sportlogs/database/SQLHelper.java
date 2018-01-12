package com.example.sportlogs.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Function : 创造数据库
 * Author : Alan
 * Modify Date : 30/8/17
 * Issue : TODO
 * Whether solve :
 */

public class SQLHelper extends SQLiteOpenHelper {

    private static String sq_name = "sq_journal";

    private String sql_journal = "create table tb_journal(_id Integer primary key," +
            "journal_name varchar(50)," +
            "journal_value integer," +
            "journal_year integer," +
            "journal_month integer," +
            "journal_day integer," +
            "journal_hour integer," +
            "journal_min integer," +
            "journal_sec integer)";

    private String sql_target = "create table tb_target(_id Integer primary key," +
            "journal_name varchar(50)," +
            "target_name varchar(50)," +
            "target_value integer," +
            "target_start_time varchar(50)," +
            "target_end_time varchar(50))";

    public SQLHelper(Context context) {
        super(context, sq_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql_journal);
        db.execSQL(sql_target);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
