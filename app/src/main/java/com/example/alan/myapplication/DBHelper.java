package com.example.alan.myapplication;

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

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "alan", null, 1);
        SQLiteDatabase readableDatabase = this.getReadableDatabase();

    }

    private String TB_NAME = "tb_alan";

    String[] projection = { "value", "content"};

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists tb_alan " + "( _id Integer primary key," +
                " value  varchar(50) , content varchar(50) )");
    }

    /**
     * 数据库插入
     *
     * @param values
     * @param sqLiteDatabase
     */

    public void insert(ContentValues values, SQLiteDatabase sqLiteDatabase) {

        /**
         * insert()方法的第一个参数是table名，第二个参数会使得系统自动对那些ContentValues
         * 没有提供数据的列填充数据为null，如果第二个参数传递的是null，那么系统则不会对那些没有提供数据的列进行填充
         */

        sqLiteDatabase.insert("tb_alan", null, values);
    }

    /**
     * 数据库查询
     *
     * @param sqLiteDatabase
     */

    public Cursor query(SQLiteDatabase sqLiteDatabase, String[] date) {

     //   Cursor cursor =
     //           sqLiteDatabase.query(TB_NAME, projection,
     //           "SELECT * FROM " + TB_NAME + " where value = " + date, null,null,null,null);

        Cursor cursor1 = sqLiteDatabase.rawQuery("select * from tb_alan where value = ?",date);


        return cursor1;


}

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
