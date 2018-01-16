package com.example.mybulter.view;

/*
 *  项目名：  SmartButler 
 *  包名：    com.imooc.smartbutler.view
 *  文件名:   CustomDialog
 *  创建者:   LGL
 *  创建时间:  2016/11/5 22:26
 *  描述：    自定义Dialog
 */

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.mybulter.R;


public class CustomDialog extends Dialog {

    //定义模板
    public CustomDialog(Context context, int layout, int style) {
        this(context, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, layout, style, Gravity.CENTER);
    }

    //定义属性
    public CustomDialog(Context context, int width, int height, int layout, int style, int gravity, int anim) {
        super(context, style);
        //设置属性
        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = 300;
        layoutParams.height = 300;
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);
        window.setWindowAnimations(anim);
    }

    //实例
    public CustomDialog(Context context, int width, int height, int layout, int style, int gravity) {
        this(context, width, height, layout, style, gravity, R.style.pop_anim_style);
    }

    private static CustomDialog customDialog;
    private static CustomDialog girlDialog;

    public CustomDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }


    // 生成菜单对话框
    public static CustomDialog createCustomDialog(Context context) {

        if (customDialog == null) {
            customDialog = new CustomDialog(context, R.style.MyDialog);
            customDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
            customDialog.setCanceledOnTouchOutside(false);

            Window dialogWindow = customDialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            //所在页面透明度
            lp.dimAmount = 0.6f;
            lp.width = 200;
            lp.height = 700;
            dialogWindow.setAttributes(lp);
            dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                    WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        }

        return customDialog;
    }

    public static CustomDialog createGirlDialog(Context context) {
        if (girlDialog == null) {

            girlDialog = new CustomDialog(context, R.style.MyDialog);
            girlDialog.setContentView(R.layout.dialog_girl_wleface);

            Window window = girlDialog.getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            //所在页面透明度
            lp.dimAmount = 0.6f;
            lp.width = 1000;
            lp.height = 1000;
            lp.gravity = Gravity.CENTER;
            window.setAttributes(lp);
        }

        return girlDialog;
    }

    private static CustomDialog loginDialog;

    public static CustomDialog createLoginDialog(Context context) {
        if (loginDialog == null) {

            girlDialog = new CustomDialog(context, R.style.MyDialog);
            girlDialog.setContentView(R.layout.dialog_girl_wleface);

            Window window = girlDialog.getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            //所在页面透明度
            lp.width = 300;
            lp.height = 300;
            lp.gravity = Gravity.CENTER;
            window.setAttributes(lp);
        }

        return loginDialog;
    }
}
