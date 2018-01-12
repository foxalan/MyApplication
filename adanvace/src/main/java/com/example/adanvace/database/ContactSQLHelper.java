package com.example.adanvace.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Function :
 * Author : Alan
 * Modify Date : 9/8/17
 * Issue : 1.如何使用BIT
 * Whether solve :
 */

public class ContactSQLHelper extends SQLiteOpenHelper {
    /**
     * 四、 逻辑数据类型
     * BIT： BIT数据类型占用1 个字节的存储空间，其值为0
     * 或1 。如果输入0 或1 以外的值，将被视为1。 BIT 类型不能定义为NULL 值（所谓NULL 值是指空值或无意义的值）。
     */

    private static String db_name = "db_contacts";

    private String sql = "create table  tb_contacts(_id integer primary key,name varchar(50),number varchar(50)) ";

    public ContactSQLHelper(Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
