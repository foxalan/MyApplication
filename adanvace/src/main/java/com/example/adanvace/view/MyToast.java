package com.example.adanvace.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adanvace.R;


public class MyToast {

    private static Toast toast;
    private static LayoutInflater inflater;

    public static void init(Context context, LayoutInflater mInflater)
    {
        toast = new Toast(context);
        inflater = mInflater;
    }


    public static void showMessage(String str) {

        View view = inflater.inflate(R.layout.view_toast, null);
        TextView tv_toast = (TextView) view.findViewById(R.id.tv_toast);
        tv_toast.setText(str);
        toast.setView(view);
        /**
         你也可以调用setGravity(int,int,int)改变其显示的位置。
         这里的三个参数，分别表示：Gravity，x方向偏移，y方向偏移。
         */
        toast.setGravity(Gravity.CENTER,0,15);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
