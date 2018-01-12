package com.example.adanvace.activity.view;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adanvace.R;
import com.example.adanvace.adapter.ContactAdapter;
import com.example.adanvace.database.ContactSQLHelper;
import com.example.adanvace.info.ContactInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Function : 获取联系人列表并添加查询功能
 * Author : Alan
 * Modify Date : 9/8/17
 * Issue :
 * 1.ScrollView 只能有一个group
 * 2.在xml中View 分大小写
 * 3.java.lang.IllegalArgumentException: Invalid column data1
 * 在getContentResolver.query时传入的uri 有问题造成的
 * 4:在ActionBar上添加menu时,一定要重写onCreateOptionMenu()方法
 * Whether solve :1.从联系人中只拿一个,小米进不去,模拟器只能拿重复的数据
 */

public class ContactActivity extends BaseActivity {

    private ListView lv_search;
    private ContactAdapter adapter;

    private ContactSQLHelper helper;
    private SQLiteDatabase database;

    private List<ContactInfo> contactInfoList = new ArrayList<>();

    private static final int REQUEST_CODE = 1;



    private static final int MSG_TEXTVIEW = 0x123;
    private static final int MSG_LISTVIEW = 0X124;

    private Button bt_clear_data;
    private EditText et_search;

    private TextView tv_contacts;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_LISTVIEW:
                    updateListView();
                    break;
                case MSG_TEXTVIEW:
                    updateTextView();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("常用联系人");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int getContentView() {
        return R.layout.activtiy_contact;
    }

    @Override
    public void initView() {
        lv_search = (ListView) findViewById(R.id.lv_contacts);
        bt_clear_data = (Button) findViewById(R.id.bt_clear_data);
        et_search = (EditText) findViewById(R.id.et_search);


        tv_contacts = (TextView) findViewById(R.id.tv_contacts);

    }

    @Override
    public void initData() {

        helper = new ContactSQLHelper(this);
        database = helper.getWritableDatabase();

        Cursor cursor = database.rawQuery("select * from tb_contacts", null);
        if (cursor != null) {

            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));


                ContactInfo contactInfo = new ContactInfo(false, false, name, number);
                contactInfoList.add(contactInfo);
            }

            cursor.close();
        }

    }

    @Override
    public void initEvent() {

        bt_clear_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });

        adapter = new ContactAdapter(this, contactInfoList);
        lv_search.setAdapter(adapter);

        mHandler.sendEmptyMessage(MSG_TEXTVIEW);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = et_search.getText().toString();
                if (str.equals("")){
                    getListViewAgain();
                    tv_contacts.setVisibility(View.VISIBLE);
                }else {
                    queryFromDatabase(str);
                    tv_contacts.setVisibility(View.GONE);
                }
            }
        });


    }

    private void getListViewAgain() {

        contactInfoList.clear();
        Cursor cursor = database.rawQuery("select * from tb_contacts", null);
        if (cursor != null) {

            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));


                ContactInfo contactInfo = new ContactInfo(false, false, name, number);
                contactInfoList.add(contactInfo);
            }

            cursor.close();
        }
        mHandler.sendEmptyMessage(MSG_LISTVIEW);

    }

    /**
     * 模糊查询
     * @param str
     */
    private void queryFromDatabase(String str) {

        contactInfoList.clear();
        Cursor c = database.rawQuery("select * from tb_contacts where name like '%"+str+"%' or number like '%"+str+"%'",null);

        while (c.moveToNext()){

            String name = c.getString(c.getColumnIndex("name"));
            String number = c.getString(c.getColumnIndex("number"));
            contactInfoList.add(new ContactInfo(false,false,name,number));
        }

        c.close();

        mHandler.sendEmptyMessage(MSG_LISTVIEW);
    }

    /**
     * 删除选中条目
     */
    private void clearData() {


        for (int i = contactInfoList.size() - 1; i >= 0; i--) {
            if (contactInfoList.get(i).isDelete()) {
                contactInfoList.remove(i);
                removeDatabase(contactInfoList.get(i).getName());
            }
        }

        for (int j = 0; j < contactInfoList.size(); j++) {
            contactInfoList.get(j).setDelete(false);
            contactInfoList.get(j).setShow(false);
        }

        bt_clear_data.setVisibility(View.GONE);
        tv_contacts.setVisibility(View.VISIBLE);
        mHandler.sendEmptyMessage(MSG_LISTVIEW);
        mHandler.sendEmptyMessage(MSG_TEXTVIEW);

    }

    /**
     * 删除数据库中保存的元素
     *
     * @param name
     */
    private void removeDatabase(String name) {
        database.execSQL("delete from tb_contacts where name like ?", new String[]{name});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {

                String[] selections = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.Contacts.DISPLAY_NAME};
                Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, selections, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        insertDatabase(name, number);
                    }

                    cursor.close();
                }
            }
        }

    }

    /**
     * 添加数据到列表,要先判断是否重复
     *
     * @param name
     * @param number
     */

    private void insertDatabase(String name, String number) {

        Log.d("ALAN",name);

        boolean isContain = false;
        ContactInfo contactInfo = new ContactInfo(false, false, name, number);
        for (int i = 0 ; i < contactInfoList.size();i++){
            Log.d("ALAN",contactInfoList.get(i).getName());
            if (contactInfoList.get(i).getName().equals(name)){
                isContain = true;
            }
        }
        Log.d("ALAN",isContain+"---");
        if (!isContain) {
            contactInfoList.add(contactInfo);
            mHandler.sendEmptyMessage(MSG_LISTVIEW);
            database.execSQL("insert into tb_contacts values(null,?,?)", new String[]{name, number});
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.meau_setting, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.contact_add:

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);

                break;
            case R.id.contact_reduce:

                showCheckBox();
                bt_clear_data.setVisibility(View.VISIBLE);
                tv_contacts.setVisibility(View.GONE);
                break;
            case android.R.id.home:

                finish();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private void showCheckBox() {

        for (int i = 0; i < contactInfoList.size(); i++) {
            contactInfoList.get(i).setShow(true);
        }

        mHandler.sendEmptyMessage(MSG_LISTVIEW);

    }

    private void updateListView() {
        adapter.notifyDataSetChanged();
    }

    private void updateTextView() {
        tv_contacts.setText("一共有" + contactInfoList.size() + "个联系人");
    }


}
