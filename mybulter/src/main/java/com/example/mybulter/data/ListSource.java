package com.example.mybulter.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.mybulter.R;
import com.example.mybulter.info.SettingInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Function Name : 用于存放固定的listview的数据
 * Author : Alan
 * Modify Date : 22/7/17
 * Input Parameter: new ArrayList<>() 需要理解
 */

public class ListSource {

    private List<SettingInfo> settingInfoList;
    private Context context;


    public ListSource(Context context){
        this.context = context;
        initData();
    }

    private void initData(){
        settingInfoList = new ArrayList<>();
        Bitmap bitmap_1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.icon_user);
        Bitmap bitmap_2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.icon_location);
        Bitmap bitmap_3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.icon_setting);
        Bitmap bitmap_4 = BitmapFactory.decodeResource(context.getResources(),R.drawable.icon_net);

        SettingInfo settingInfo_1 = new SettingInfo(bitmap_1, "个人信息");
        SettingInfo settingInfo_2 = new SettingInfo(bitmap_2, "归属地查询");
        SettingInfo settingInfo_3 = new SettingInfo(bitmap_3, "检查更新");
        SettingInfo settingInfo_4 = new SettingInfo(bitmap_4, "网络状态");

        settingInfoList.add(settingInfo_1);
        settingInfoList.add(settingInfo_2);
        settingInfoList.add(settingInfo_3);
        settingInfoList.add(settingInfo_4);
     }

     public List<SettingInfo> getAllData(){
         return  new ArrayList<>(settingInfoList);
     }
}
