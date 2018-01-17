package com.example.mybulter.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mybulter.R;
import com.example.mybulter.constant.Constant;
import com.example.mybulter.util.UtilTools;
import com.example.mybulter.view.MyToast;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alan
 */
public class AboutActivity extends BaseHomeActivity {

    private ListView mListView;
    private List<String> mList = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    private String versionName;
    private String url;
    private int versionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //去除阴影
        getSupportActionBar().setElevation(0);
        initView();

    }

    //初始化View
    private void initView() {

        try {
            getVersionNameCode();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        mListView = (ListView) findViewById(R.id.mListView);

        mList.add(getString(R.string.text_app_name) + getString(R.string.app_name));
        mList.add(getString(R.string.text_version) + UtilTools.getVersion(this));
        mList.add(getString(R.string.text_website_address));

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList);
        //设置适配器
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    /**
                     * 步骤:
                     * 1.请求服务器的配置文件，拿到code
                     * 2.比较
                     * 3.dialog提示
                     * 4.跳转到更新界面，并且把url传递过去
                     */
                    case 2:
                        RxVolley.get(Constant.CHECK_UPDATE_URL, new HttpCallback() {
                            @Override
                            public void onSuccess(String t) {
                                parsingJson(t);
                            }
                        });
                        MyToast.showMessage("click update");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            int code = jsonObject.getInt("versionCode");
            if (code > versionCode) {
                showUpdateDialog(jsonObject.getString("content"));
            } else {
                Toast.makeText(this, "当前是最新版本", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取版本号/Code
     */
    private void getVersionNameCode() throws PackageManager.NameNotFoundException {
        PackageManager pm = getPackageManager();
        PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
        versionName = info.versionName;
        versionCode = info.versionCode;
    }

    /**
     * 弹出升级提示
     */
    private void showUpdateDialog(String content) {
        new AlertDialog.Builder(this)
                .setTitle("有新版本啦！")
                //content
                .setMessage("修复多项Bug!")
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AboutActivity.this, UpdatActivity.class);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }
}
