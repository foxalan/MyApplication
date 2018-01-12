package com.example.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.view.MenuItemCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import java.util.List;



public class MainActivity extends AppCompatActivity {

    private ShareActionProvider mShareActionProvider;

    /**
     * 使用ACTIONBAR的几个坑点
     * 1.getSupportActionBar   android.support.v7.app.ActionBar
     * 2.将原有的ACTIONBAR HIDE
     * 3.actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
     * 4.设置新的布局取代
     * @param savedInstanceState
     *
     * 1.ActionBar需要一个Styles来设定高度，背景色等
     * 2.MENU 在设置布局之后会自动加入到ACTIONBAR中
     * 3.可以在ACTIONBAR中添加自己的布局
     *
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null){

            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(R.layout.action_bar);
        }
        setContentView(R.layout.activity_main);

    }

    /**
     * 使用隐式INTENT 来与其它应用交互
     * <p>
     * 1.在使用前先要判断程序中是否有可以打开的程序
     * 2.INTENT.createChooser()来给可以打开的应用创建标题
     *
     * @param view
     */

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_net:

                Intent intent = new Intent(Intent.ACTION_MANAGE_NETWORK_USAGE);

                if (isContainIntent(intent)) {
                    Intent intent1 = Intent.createChooser(intent, "360");
                    startActivity(intent1);
                }

                break;
            case R.id.bt_start_activity:

                Intent intent1 = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
                intent1.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent1,1);

                break;
        }
    }

    private boolean isContainIntent(Intent intent) {

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isContain = activities.size() > 0;

        return isContain;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){

                Uri uri = data.getData();
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
                Cursor cursor = getContentResolver().query(uri,projection,null,null,null);
                cursor.moveToFirst();
                String string = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                Log.d("tang",string);

            }
        }
    }

    /**
     * 使用ShareActionProvider的几个坑
     *
     * 1.导入的包为import android.support.v7.widget.ShareActionProvider;
     * 2.在MENU XML 中的为 app:actionProviderClass="android.support.v7.widget.ShareActionProvider
     * 3.实倒化为 mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item)
     *
     */

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {

        getMenuInflater().inflate(R.menu.menu_test,menu);

        MenuItem item = menu.findItem(R.id.it_name);

        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent sendIntent =new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,"tesfls");
        sendIntent.setType("text/plain");
        setShareIntent(sendIntent);

        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.it_name:

                Intent sendIntent =new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"tesfls");
                sendIntent.setType("text/plain");
                Log.d("huiye","alan");
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void setShareIntent(Intent shareIntent) {

        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}
