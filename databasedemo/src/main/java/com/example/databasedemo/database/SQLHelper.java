package com.example.databasedemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Function Name : SetAudioMute
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 */

public class SQLHelper extends SQLiteOpenHelper {

    private String sql = "create table alan_test (_id integer primary key autoincrement,title varchar(50) , content varchar(50),time varchar(50))";
    private static final String DATABASE_NAME = "diary_db";


    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getAllInfo(SQLiteDatabase sqLiteDatabase){

        Cursor cursor = sqLiteDatabase.rawQuery("select * from alan_test",null);
        return cursor;
    }

    public void insertInfo(String title,String content,String time, SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("insert into alan_test values(null,?,?,?)",new String[]{title,content,time});
    }

    public void deleteInfo(String title , SQLiteDatabase sqLiteDatabase){
       // sqLiteDatabase.delete();

     //   sqLiteDatabase.execSQL("delete from alan_test where title like  " + title);
        sqLiteDatabase.delete("alan_test","title like ?",new String[]{title});

    }

    public void updateInfo(ContentValues contentValues,SQLiteDatabase sqLiteDatabase,String title){
        sqLiteDatabase.update("alan_test",contentValues,"title = ?",new String[]{title});
    }


}
