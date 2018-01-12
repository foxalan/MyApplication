package com.example.mybulter.info;

import android.graphics.Bitmap;

/**
 * Function Name : TODO
 * Author : Alan
 * Modify Date : 22/7/17$
 * Input Parameter &
 */

public class SettingInfo {

    private Bitmap icon;
    private String name;

    public SettingInfo(Bitmap icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
